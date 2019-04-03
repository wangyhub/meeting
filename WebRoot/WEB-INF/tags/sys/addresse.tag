<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>

<div id="address${id}" style="float: left;">
	<select id="p-1_id${id}" onchange="dataChange(this)" class="input-xlarge" 
	    name="p-1_id${id}" style="width: 140px;">
	    <option value="">
	        --请选择省份--
	    </option>
	    <c:forEach items="${areaList}" var="pro">
	        <option value="${pro.id}">
	            ${pro.name}
	        </option>
	    </c:forEach>
	</select>
	<select id="c-1_id${id}" onchange="dataChange(this)" name="c-1_id${id}"
	    style="width: 150px;" class="input-xlarge pcd1">
	    <option value="">
	        --请选择城市--
	    </option>
	</select>
	<select id="d-1_id${id}" name="d-1_id${id}" style="width: 180px;" onchange="addressShow(this)"
	    maxlength="10" class="input-xlarge pcd2">
	    <option value="">
	        --请选择区县--
	    </option>
	</select>
	<input type="hidden" id="${id}" name="${name}" value="${value}">
</div>
<script type="text/javascript">	
    initAddress();
    
    function initAddress(){
        var dName = "${id}";// 对应的编号
        var addressCode = "${value}";
        if("" != addressCode){
           // 查询加载地址
           var pId = addressCode.substring(0, 2) + "0000";// 省级ID
           var cId = addressCode.substring(0, 4) + "00";  // 市级ID
           var dId = addressCode;                         // 县级ID
           $("#p-1_id" + dName).val(pId);
           ajaxList("p-1_id" + dName);
           $("#c-1_id" + dName).val(cId);
           ajaxList("c-1_id" + dName);
           $("#d-1_id" + dName).val(addressCode);
        }else{
           // 全部清空
           $("#d-1_id" + dName).val("");
           $("#c-1_id" + dName).val("");
           $("#p-1_id" + dName).val("");
        }
    }
    
    // 地址 省 市区 县 的选择
	function dataChange(obj){
	    ajaxList(obj.id);
	}
	function ajaxList(pId){//p1_id,c1_id,d1_id
	    var pIdVal = $("#"+pId).val();
	    var name = "";
	    var url = "";
	    var successStr = "";
	    var innerText = "";
	    if(pId.indexOf("p")!=-1){
	        name = pId.replace("p","c");
	        url = "${ctx}/base/tBaRegistered/ajaxfindCityList";
	        innerText = $("#"+name+" option")[0];
	        successStr = function (data){
	            $("#"+name).append(innerText);
	            $("#"+name).val("").trigger('change');
	            if(data){
	                for(var i=0;i<data.length;i++){
	                    var obj = data[i];
	                    $("#"+name).append('<option value="'+obj.id+'">'+obj.name+'</option>');
	                }
	            }
	        }
	    }else if(pId.indexOf("c")!=-1){
	        name = pId.replace("c","d");
	        url = "${ctx}/base/tBaRegistered/ajaxfindDistrictList";
	        innerText = $("#"+name+" option")[0];
	        successStr = function (data){
	            $("#"+name).append(innerText);
	            $("#"+name).val("").trigger('change');
	            if(data){
	                for(var i=0;i<data.length;i++){
	                    var obj = data[i];
	                    $("#"+name).append('<option value="'+obj.id+'">'+obj.name+'</option>');
	                }
	            }
	        }
	    }
	    if(pIdVal==""){
	        $("#"+name).empty();
	        $("#"+name).append(innerText);
	        $("#"+name).val("").trigger('change');
	        return;
	    }
	    $("#"+name).empty();
	    $.ajax({
	        url:url,
	        type:"post",
	        async:false, 
	        data:{"pid":pIdVal},
	        dataType:"json",
	        success:successStr
	    });
	}
	
	// 将县级编号存到
	function addressShow(obj){
	    var dId = obj.id;    // Id
	    var dName = obj.name.substring(6);// 对应的编号
	    var pValue = $("#p-1_id" + dName).val();
	    var cValue = $("#c-1_id" + dName).val();
        var dValue = $("#d-1_id" + dName).val();
	    if("" != pValue){// 到省结束
            $("#" + dName).val(pValue);
        }
	    if("" != cValue){// 到市结束
            $("#" + dName).val(cValue);
        }
	    if("" != dValue){// 到县结束
	        $("#" + dName).val(dValue);
	    }
	}
</script>