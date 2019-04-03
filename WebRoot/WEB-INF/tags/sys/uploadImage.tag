<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="隐藏域值（ID）"%>
<%@ attribute name="kind" type="java.lang.String" required="true" description="single单附件more多附件"%>
<%@ attribute name="filetype" type="java.lang.String" required="false" description="文件类型"%>
<%@ attribute name="filesize" type="java.lang.String" required="false" description="文件大小"%>
<%@ attribute name="buttonName" type="java.lang.String" required="false" description="按钮名称"%>

<div id="fileupload-buttonbar${id}" class="fileupload-buttonbar" >  
    <div id="fileSpan${id}" class="btn btn-success fileinput-button" style="background: #2576db">
        <i style="margin-left: -10px;" class="glyphicon glyphicon-plus"></i>	    
        <input type="file" id="file${id}" name="file${id}" 
            style="opacity:0;position: absolute;width: 100px;height: 32px;"/>
        <input type="hidden" id="${id}" name ="${name}" value="${value}" class="haveFileId"/>
        <span style="margin-left: 20px;" id="label${id}">${(buttonName == null or buttonName == '')?'资料上传':buttonName}</span>
    </div>
    
    <br/>
    
    <!-- 提供复制的tbody start-->
    <ul id="fileupload-ul${id}" class="thumbnails">
   		<li class="span3" style="width: 160px;height: 100px;display: none;"  id="tbody${id}" >
        	<a id="liA" href="#" target="_blank" class="thumbnail" style="width: 130px; height:95px; float: left;" >
            	<img src="#" id="imge"  style="width: 130px; float: left; height: 95px;"  />
	            <a id="imgA" href="#" style="color: red;float: none;"  >
	                <span class="icon-remove" onclick="delImage(this, '${id}', '${kind}')"></span>
	            </a>
	            <div id="fileupload-progress" class="fileupload-progress" style="margin-top: 42px; width: 100px; height: 58px;display: none;">  
                    <div id="progress" class="progress" style="width: 124px;">  
                        <div class="bar" style="width:0%;float:left;" ></div>  
                    </div>  
                   	<div class="progress-extended" style="float:left;"></div>  
                </div>
            </a>
    	</li>
    </ul>
    <!-- 提供复制的tbody end-->
</div>

<script type="text/javascript">	
	var addId = 0;               // 页面文件ID保持递增
	var fileArray = new Array(); // 存储页面文件ID的数组
	initImageFile();
	
	// 初始化 查询的展现
	function initImageFile(){
	    var value = "${value}";
	    var fid = "${id}";
	    $("#${id}").val(value);
	    var kind = "${kind}";
        if('more' != kind){//单附件
            $("#fileupload-buttonbar${id}").find("br").remove();
            $("#fileupload-buttonbar${id}").find("#tbody${id}").css("margin-bottom", "0px");
        }
	    if("" == value){
	        return false;
	    }
	    $.ajax({
            url:"${ctx}/base/tBaFile/queryFile",
            async:false, 
            type:"post",
            data:{"value":value},
            dataType:"json",
            success:function (data){  
                if(data.length == 0){
                    $("#${id}").val("");
                } 
                for(var i = 0;i < data.length;i++) {
                    addId = addId + 1;             // 上传页面文件ID递增
                    fileArray.push(addId);         // 将页面的ID放在数组中
                    var fileId = data[i].id;
                    var filename = data[i].fileName;
                    var fileSize = data[i].fileSize;
                    var lastLength = filename.lastIndexOf(".");
                    var fileNewType = filename.substring(lastLength + 1,filename.length).toUpperCase();
                    if('more' != kind){//单附件
                        $("#fileSpan${id}").css('display','none');    
                    }
                    var filenameShow = filename;
                    if(filename.length > 11){
                        filenameShow = filename.substring(0, 11) + "...";
                    }
                    if(fileNewType != "DOC" && fileNewType != "DOCX" && fileNewType != "XLS" && fileNewType != "XLSX"
                        && fileNewType != "BMP" && fileNewType != "JPG" && fileNewType != "JPEG" && fileNewType != "PNG"
                        && fileNewType != "GIF" && fileNewType != "PDF" && fileNewType != "TXT" && fileNewType != "RAR"
                        && fileNewType != "ZIP" && fileNewType != "7Z" ){
                        fileNewType = "NO";
                    }
                    var newBody = $("#tbody${id}").clone(true);     // 复制    
                    newBody.appendTo("#fileupload-ul${id}"); // 添加到展示效果的框中
                    newBody.show();  
                    newBody.css("float", "left");
                    // 修改新建的属性
                    newBody.attr("id", "newBody" + addId);         // 改变新文件展示域的id 
                    newBody.find("#liA").attr("title", filename); 
                    newBody.find("#fileupload-progress").attr("id", "fileupload-progress" + addId);
                    newBody.find("#liA").attr("id", "liA" + addId);
                    newBody.find("#progress").attr("id", "progress" + addId);
                    newBody.find("#imgA").attr("id", "imgA" + addId);
                    newBody.find("#imge").attr("id", "imge" + addId);
                    newBody.find("#progress" + addId).attr("display", "none");
                    newBody.find("#liA" + addId).attr("href", "${ctx}/base/tBaFile/showImaged?attFileId=" + fileId); 
                    newBody.find("#imgA" + addId).attr("onclick", "delImageFile('" + fileId + "','" + fid + "','" + kind + "' )");
                    newBody.find("#imge" + addId).attr("src", "${ctx}/base/tBaFile/showImage?id=" + fileId);
                    newBody.find("#imge" + addId).attr("alt", "${ctx}/base/tBaFile/showImage?id=" + fileId);
                    newBody.find("#liA").attr("href", "${ctx}/base/tBaFile/showImaged?attFileId=" + fileId);
                    newBody.find("#liA").find("img").show(); 
                }
            }
         });
	}
	
	// 页面删除文件的处理
	function delImage(obj, id, kind){
	    // 删除对应的文件
        if('more' != kind){//单附件
            $("#fileSpan" + id).css("display", ""); // 添加文件按钮重新显示
            $("#file" + id).fileupload("enable");   // 文件上传可用
        }else {// 多附件
            for(var i = 0;i < fileArray.length;i++) {
                var tempId = fileArray[i];
                var tempLength = $("#fileupload-buttonbar" + id).children("#newBody" + tempId).length;
                if(0 == tempLength) {    // 被删除的文件长度为0
                    fileArray.splice(i, 1); 
                    if(addId == tempId){ // 删除最新的页面文件,文件可以继续上传。
                        $("#file" + id).fileupload("enable");
                    } 
                }
            }
        }
        obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);
	}	
	
	// 后台进行删除数据
	function delImageFile(fileId, id, kind){
	    //alert(fileId);// 文件id
	    $.ajax({
            url:"${ctx}/base/tBaFile/deleteFile",
            type:"post",
            data:{"fileId":fileId},
            dataType:"json",
            success:function (data){   
            }
         });
         // 修改value的值
         if('more' != kind){//单附件
             $("#" + id).val("");
         }else {// 多附件
             var tempValues = $("#" + id).val().split(",");
             var tempValue = "";
             for(var i = 0; i < tempValues.length; i++){
                 if(fileId != tempValues[i]){
                     if("" == tempValue){
                         tempValue = tempValue + tempValues[i];
                     }else{
                         tempValue = tempValue + "," + tempValues[i];
                     }
                 }
             }
             $("#" + id).val(tempValue);
         }
	}	

	var jqXHR = $("#file${id}").fileupload({
                autoUpload: true,                // 是否自动上传  
                url: "${ctx}/base/tBaFile/save", // 上传地址  
                formData: {name: "${id}", filesize: "${filesize}"},       // 传递name
                sequentialUploads: true,         // 是否按顺序上传文件
                dataType: 'json',                // json格式，不加上ie9不能传值
                //maxFileSize: 50 * 1024 * 1024,   // 文件大小，ie9不起作用
                done: function (e, data) {
                    var id = "${id}";     // 传入的Id号
                    var kind = "${kind}"; // 传入的文件类型
                    var fileId = data.result["fileId"];     // 上传的文件id
                    var fileSize = data.result["filesize"]; // 上传的文件大小
                    var activeUploads = $("#file" + id).fileupload("active");// 正在执行的文件数
                    var tempLength = $("#newBody" + addId).length;
                    if(1 != activeUploads || 0 == tempLength){  
                        delImageFile(fileId, id, kind);// 删除获得fileId
                    }else{
                        // 进度条去掉; 显示已完成的字样; 删除添加方法; 添加下载链接
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#progress" + addId).css("display", "none");
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#fileupload-progress" + addId).css("width", "100%");
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#imgA" + addId).attr("onclick", "delImageFile('" + fileId + "', '" + id + "')");
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#imge").attr("src", "${ctx}/base/tBaFile/showImage?id=" + fileId);
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#imge").attr("href", "${ctx}/base/tBaFile/showImaged?attFileId=" + fileId);
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#imge").show(); 
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#liA" + addId).attr("href", "${ctx}/base/tBaFile/showImaged?attFileId=" + fileId); 
                        
                        // 文件上传成功,存值处理
                        var kind = "${kind}";// 文件上传的类型 single：单附件 more：多附件
                        if('more' != kind){//单附件
                            $("#" + id).val(fileId);
                        }else {// 多附件
                            $("#" + id).val($("#" + id).val()+"," + fileId);
                        }
                        $("#file" + id).fileupload("enable"); 
                    }
                },  
                progressall: function (e, data) {
                    //设置上传进度条事件的回调函数      火狐，谷歌，ie11 正常。 ie9 无效
                    $("#fileupload-progress" + addId).css('display','block');
                    var progress = parseInt(data.loaded / data.total * 100, 10);  
                    $('#progress' + addId + ' .bar').css(  
                        'width',  
                        progress + '%'  
                    );  
                    $('#progress' + addId +' .bar').html(progress + '%');
                },
                fail: function (e, data) {  
                    top.$.jBox.tip("上传文件过大，上传失败!", "warning");
                    var id = "${id}";     // 传入的Id号
                    $("#fileupload-buttonbar" + id + " #newBody" + addId).css("display","none");
                    $("#fileSpan${id}").css('display',''); 
                   // $("#newBody" + addId).find("#fileupload-progress" + addId).html("上传失败"); 
                    $("#file${id}").fileupload("enable");// 文件上传失败后，上传控件恢复可用
                },
                change: function (e, data){ // 文件上传入口
                    var flag = "0"; // 1:不启动；0：启动
                    var filename = data.files[0].name; // 获取文件名
                    var lastLength = filename.lastIndexOf(".");
                    var fileNewType = filename.substring(lastLength + 1,filename.length).toUpperCase();
                    var flagType = 0;
                    var fileType = "${filetype}";
                    var tipType = "";
                    var strs = new Array(); //定义储存文件类型的数组
                    strs = fileType.split(",");
                    for (i = 0;i < strs.length;i++ ){
                        var temp = strs[i].toUpperCase();
                        if(temp != "" && fileNewType == temp){
                            flagType = 1;
                        }
                        if(temp != ""){
                            if(tipType == ""){
                                tipType = temp;
                            }else{
                                tipType = tipType + "或者" +temp;
                            }
                        }
                    }
                    if(flagType == 0 && fileType != ""){
                        top.$.jBox.tip("附件类型只能为" + tipType + "格式!请重新选择!", "warning");
                        $("#file${id}").fileupload("destory");
                    }
                    var activeUploads = $("#file${id}").fileupload("active");// 正在执行的文件数
                    if(0 != activeUploads && 0 != fileArray.length){
                        for(var i = 0;i < fileArray.length;i++) {
                            var tempId = fileArray[i];
                            var tempLength = $("#fileupload-buttonbar${id}").children("#newBody" + tempId).length;
                            if(0 == tempLength) { //被删除的文件长度为0
                                 flag = "1";
                            }
                        }
                    }
                    if("1" == flag){// 文件正在上传
                        top.$.jBox.tip("后台正在处理文件，请稍等!", "warning");
                        $("#file${id}").fileupload("destory");
                    }else{ 
                        addId = addId + 1;             // 上传页面文件ID递增
                        fileArray.push(addId);         // 将页面的ID放在数组中
                    }
                    var kind = "${kind}";              // 文件上传的类型 single：单附件 more：多附件
                    if(kind != "more"){//单附件
                       $("#fileSpan${id}").css("display", "none"); // 单附件上传要隐藏按钮 
                       $("#fileupload-buttonbar${id}").find("br").remove();
                    }else{//多附件      
                    }
                    if(fileNewType != "DOC" && fileNewType != "DOCX" && fileNewType != "XLS" && fileNewType != "XLSX"
                        && fileNewType != "BMP" && fileNewType != "JPG" && fileNewType != "JPEG" && fileNewType != "PNG"
                        && fileNewType != "GIF" && fileNewType != "PDF" && fileNewType != "TXT" && fileNewType != "RAR"
                        && fileNewType != "ZIP" && fileNewType != "7Z" ){
                        fileNewType = "NO";
                    }
                    var newBody = $("#tbody${id}").clone(true);     // 复制          
                    newBody.appendTo("#fileupload-ul${id}"); // 添加到展示效果的框中
                    newBody.show(); 
                    newBody.css("float", "left"); 
                    // 谷歌必须加上，否则谷歌浏览器无法显示!
                    $("<span id='tempspan'>&nbsp;</span>").appendTo("#fileupload-buttonbar${id}");
                    $("#fileupload-buttonbar${id}").find("#tempspan").remove();
                    // 修改新建的属性
                    newBody.attr("id", "newBody" + addId);     // 改变新文件展示域的id 
                    newBody.find("#fileupload-progress").show();
                    newBody.find("#fileupload-progress").attr("id", "fileupload-progress" + addId);
                    newBody.find("#progress").attr("id", "progress" + addId);
                    newBody.find("#liA").attr("title", "1"); 
                    newBody.find("#imge").hide(); 
                    newBody.find("#liA").attr("id", "liA" + addId);
                    newBody.find("#imgA").attr("id", "imgA" + addId);
                }
            });
</script>