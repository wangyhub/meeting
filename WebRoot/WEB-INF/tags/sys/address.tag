<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="code" type="java.lang.String" required="false" description="用于初始化地区信息"%>
<%@ attribute name="funName" type="java.lang.String" required="false" description="注入的方法需是全局对象,例如：(funName=function(){$province,$city,$district}).注：funName前不可加 var,参数是省，市，区的jquery对象，可用于业务操作" %>
<%@ attribute name="areaGrade"  type="java.lang.Integer" required="false" description="地区级数，可选值(1,2,3)"%>

<div id="address${id}" style="float: left;">
	<div style="display:inline">
    <select id="province${id}"  name="province${id}"  class="input-xlarge areaInit" style="width:140px;">
        <option value="">
            --请选择省份--
        </option>        
    </select>
    </div>
    <div style="display:inline">
    <select id="city${id}" name="city${id}"  class="input-xlarge areaInit" style="width: 150px;">
        <option value="">
            --请选择城市--
        </option>        
    </select>
    </div>
    <!-- name="addCounty" -->
    <div style="display:inline">
    <select id="district${id}" name="district${id}" class="input-xlarge areaInit"  style="width:135px;"> 
        <option value="">
            --请选择区县--
        </option>        
    </select>
    </div>
    <input type="hidden" id="${id}" name="${id}" >
</div>
<script type="text/javascript" async="async"> 

    $(document).ready(function(){
       $("#${id}").val('${code}');
    	//把最终选定的地区放入input中
        var changeInputVal=function($obj){
        	$obj.change(function(){
        		$("#${id}").val($(this).val());
        	});
        
        }
      
    	//默认显示3级地区
    	var grade=3;
    	//定义地区显示的等级
    	var re= /^[0-9]*[1-9][0-9]*$/ ;
    	if(re.test('${areaGrade}')){
    		if(parseInt('${areaGrade}')>0&&parseInt('${areaGrade}')<4){
    			grade=parseInt('${areaGrade}');
    		}
    	}
    	
    	if(grade==1){
    		 $("#city${id}").parent().hide();
    		 $("#district${id}").parent().hide();
    		 changeInputVal( $("#province${id}"));
    	}
    	
    	 if(grade==2){
    		 $("#district${id}").parent().hide();
    		  changeInputVal( $("#city${id}"));
    	}
    	
    	if(grade==3){
    		  changeInputVal( $("#district${id}"));
    	}
    	
    	//绑定业务操作	
    	
	    	var funName='${funName}';
	    	var funTo='${funTo}';
	    	if(funName!=null&&funName!=""){
	    		var $province=$("#province${id}");
	    		var $city=$("#city${id}");
	    		var $district=$("#district${id}");
	    		//运行注入的方法
	    		eval(funName+"($province,$city,$district)");
	    	}
	   
    	//在span中显示选中的文本
    	var showChoosenText=function($obj,text){
    		$obj.prev().find("span:first").text(text);
    	}
    	//根据父级查子级c
    	var getChildsByParent=function($obj,condition){
    	
    		$.ajax({
    			type:"post",
    			url:"${ctx}/sys/area/getChildArea",
    			data:condition,
    			success:function(data){
    				$obj.find("option:first").val("");
    				$obj.find("option:not(:first)").remove();
    				if(data.length==0){
    				    if(condition.pid!=""){
	    					$obj.parent().hide();
	    					
	    					$obj.find("option:first").val(condition.pid);
	    					$obj.val(condition.pid);
	    				
	    					return;
    					}
    				}else{
    					$obj.parent().show();
    				}
	    			for(var i=0;i<data.length;i++){
		    			var $option=$("<option>");
		    			$option.attr("value",data[i].code);
		    			$option.html(data[i].name);
		    			//判断数据是否被默认选中	
		    			if(condition.initCondition!=""&&data[i].code==condition.initCondition){
		    				$option.attr("selected",true);
		    				showChoosenText($obj,data[i].name);
						}
		    			$obj.append($option);
    				}
    			}
    		});
    	}; 
	    //初始化地区方法
	    var initArea=function($obj,pid,initCondition){
	    	var condition={};
	    	condition['pid']=pid;
	    	condition['initCondition']=initCondition;
	    	getChildsByParent($obj,condition);
	    
	    };
	    //给省级地区绑定事件
	    $("#province${id}").change(function(){
	    	var condition={};
	    	condition['pid']=$(this).val();
	    	$("#district${id}").find("option:first").val("");
	    	$("#district${id}").find("option:not(:first)").remove();
	    	getChildsByParent($("#city${id}"),condition);
	    	showChoosenText($("#city${id}"),"--请选择城市--");
	    	showChoosenText($("#district${id}"),"--请选择区县--");
	    	$("#${id}").val($(this).val());
	    });
	    
	  
	    //给市级地区绑定事件
	    $("#city${id}").change(function(){
	    	var condition={};
	    	condition['pid']=$(this).val();
	    	getChildsByParent($("#district${id}"),condition);
	    	showChoosenText($("#district${id}"),"--请选择区县--");
	    	$("#${id}").val($(this).val());
	    });
    	
    	var initExistArea=function(cid,fun){
            var condition={};
            condition['cid']=cid;
        	$.ajax({
    			type:"post",
    			url:"${ctx}/sys/area/getParentArea",
    			data:condition,
    			success:function(data){
    			//省级
    				if(data.type=="2"){
    					fun($("#province${id}"),1,data.provinceCode);
    					fun($("#city${id}"),data.provinceCode,"");
    				}
    			//市级	
    				if(data.type=="3"){
        					fun($("#province${id}"),1,data.provinceCode);
    					fun($("#city${id}"),data.provinceCode,data.cityCode);
    					fun($("#district${id}"),data.cityCode,"");
	    			}	
    			//县级
    				if(data.type=="4"){
        					fun($("#province${id}"),1,data.provinceCode);
    					fun($("#city${id}"),data.provinceCode,data.cityCode);
    					fun($("#district${id}"),data.cityCode,data.districtCode);
    					
    				}
    			}
    		});
        	
        
        };
        //------------------------------
    	//开始初始化控件
    	//------------------------------
        var currentData='${code}';
        var fun=initArea;
       //判断库中已有业务数据存在
        if(currentData!=""&&currentData!=null){
        	initExistArea(currentData,fun);
        }else{
      		initArea($("#province${id}"),1,"");
        }
    	
    
    });
   
</script>