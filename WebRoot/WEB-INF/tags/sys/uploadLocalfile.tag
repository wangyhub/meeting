<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="隐藏域值（ID）"%>
<%@ attribute name="kind" type="java.lang.String" required="true" description="single单附件more多附件"%>
<%@ attribute name="filetype" type="java.lang.String" required="false" description="文件类型"%>
<%@ attribute name="filesize" type="java.lang.String" required="false" description="文件大小"%>
<%@ attribute name="buttonName" type="java.lang.String" required="false" description="按钮名称"%>
<%@ attribute name="isFilterEncryption" type="java.lang.String" required="false" description="yes过滤加密no忽略加密，默认校验加密"%>

<!-- DOC DOCX XLS XLSX BMP PNG JPG JPEG TXT RAR 7Z ZIP PDF NO-->
<style>
    .showFile{
        margin-top:8px;
        margin-left:8px;
        background-color:#FFF; 
        background-repeat:no-repeat;
        background-image: url('${ctxStatic}/images/fileico_v5.png');
    }

	.showFile-WY{
	    width:32px;
		height:32px;
		background-position:-0px -64px;
	}
	
	.showFile-DOC{
        width:32px;
        height:32px;
        background-position:-64px -64px;
    }
    
    .showFile-DOCX{
        width:32px;
        height:32px;
        background-position:-64px -64px;
    }
    
    .showFile-XLS{
        width:32px;
        height:32px;
        background-position:-32px -64px;
    }
    
    .showFile-XLSX{
        width:32px;
        height:32px;
        background-position:-32px -64px;
    }
    
    .showFile-BMP{
        width:32px;
        height:32px;
        background-position:-96px -96px;
    }
    
    .showFile-PNG{
        width:32px;
        height:32px;
        background-position:-64px -96px;
    }
    
    .showFile-JPG{
        width:32px;
        height:32px;
        background-position:-0px -96px;
    }
    
    .showFile-JPEG{
        width:32px;
        height:32px;
        background-position:-0px -96px;
    }
    
    .showFile-TXT{
        width:32px;
        height:32px;
        background-position:-672px -64px;
    }
    
    .showFile-RAR{
        width:32px;
        height:32px;
        background-position:-160px -64px;
    }
    
    .showFile-7Z{
        width:32px;
        height:32px;
        background-position:-128px -64px;
    }
    
    .showFile-ZIP{
        width:32px;
        height:32px;
        background-position:-160px -64px;
    }
    
    .showFile-PDF{
        width:32px;
        height:32px;
        background-position:-512px -64px;
    }
    
    .showFile-NO{
        width:32px;
        height:32px;
        background-position:-384px -64px;
    }

</style>

<div id="fileupload-buttonbar${id}" class="fileupload-buttonbar" style="width: 360px;">  
    <!-- 提供复制的tbody start-->
    <div id="tbody${id}" style="width: 360px;display: none;margin-bottom:10px;">
	    <div style="width: 350px; background-color: #e3eaf4;float: left;" >
	        <!-- 图片展现  未实现-->
	        <div style="width: 15%;float: left;">
	            <div id="imgShow" class="showFile showFile-DOC" >
	                &nbsp;
	            </div>
	        </div>
	        <div style="width: 63%;float: left;margin-top: 5px;">
	            <div>
	                <span id="showName" style="color:#444"></span>
	            </div>
	            <div style="margin-top: -0.5%;">
	                <div id="fileupload-progress" class="fileupload-progress" 
	                    style="width: 100px;height: 10px; float:left;">  
	                    <div id="progress" class="progress" style="width: 100px;">  
	                        <div class="bar" style="width:0%;float:left;" ></div>  
	                    </div>  
	                    <div class="progress-extended" style="float:left;"></div>  
	                </div>
	            </div>
	            <div>
	                &nbsp;
	            </div>
	        </div>
	        <div id="dela" style="width: 22%;float: left;margin-top: 5px;" >
	            <span>
		            <a style="cursor:pointer;color: #157ab3;text-decoration: none;" id="delAd"
	                    onMouseOver="javascript:this.style.backgroundColor = 'blue';this.style.color = 'white';"
	                    onMouseOut="javascript:this.style.backgroundColor = '#e3eaf4';this.style.color = '#157ab3';">下载
	                </a>
                </span>
                &nbsp;&nbsp;
                <span id="delAa">
		            <a style="cursor:pointer; color: #157ab3;text-decoration: none;" 
		                onMouseOut="javascript:this.style.backgroundColor = '#e3eaf4';this.style.color = '#157ab3';"
		                onclick="del(this, '${id}', '${kind}')" 
		                onMouseOver="javascript:this.style.backgroundColor = 'blue';this.style.color = 'white';">删除
		            </a>
	            </span>
	        </div>
	    </div>
    </div>
    <!-- 提供复制的tbody end-->
    <div id="fileSpan${id}" class="btn btn-success fileinput-button" style="background: #2576db">
        <i style="margin-left: -10px;" class="glyphicon glyphicon-plus"></i>	    
        <input type="file" id="file${id}" name="file${id}" 
            style="opacity:0;position: absolute;width: 100px;height: 32px;"/>
        <input type="hidden" id="${id}" name ="${name}" value="${value}" class="haveFileId"/>
        <span style="margin-left: 20px;" id="label${id}">${(buttonName == null or buttonName == '')?'资料上传':buttonName}</span>
    </div>
    <br/>
</div>

<script type="text/javascript">	

	var addId = 0;               // 页面文件ID保持递增
	var fileArray = new Array(); // 存储页面文件ID的数组
	initFile();
	
	// 初始化 查询的展现
	function initFile(){
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
                    newBody.appendTo("#fileupload-buttonbar${id}"); // 添加到展示效果的框中
                    newBody.show();  
                    newBody.css("float", "left");
                    // 修改新建的属性
                    newBody.attr("id", "newBody" + addId);         // 改变新文件展示域的id 
                    newBody.find("#showName").html(filenameShow);  // 放入文件名
                    newBody.find("#showName").attr("title", filename); 
                    newBody.find("#fileupload-progress").attr("id", "fileupload-progress" + addId);
                    newBody.find("#progress").attr("id", "progress" + addId);
                    newBody.find("#dela").attr("id", "dela" + addId);
                    newBody.find("#delAa").attr("id", "delAa" + addId);
                    newBody.find("#delAd").attr("id", "delAd" + addId);
                    newBody.find("#imgShow").attr("class", "showFile showFile-" + fileNewType); 
                    // 进度条去掉; 显示已完成的字样; 删除添加方法; 添加下载链接
                    newBody.find("#progress" + addId).attr("display", "none");
                    // newBody.find("#fileupload-progress" + addId).html("已完成"); 
                    newBody.find("#fileupload-progress" + addId).css("width", "100%");
                    newBody.find("#fileupload-progress" + addId).html("<span style='color: grey;font-size:12px;'>" 
                        + fileSize + "</span><span style='color: green;font-size:12px;'> 上传完成" + "</span>"); 
                    newBody.find("#delAa" + addId).attr("onclick", "delFile('" + fileId + "','" + fid + "','" + kind + "' )");
                    newBody.find("#delAd" + addId).attr("href", "${ctx}/base/tBaFile/downLocationFile?id=" + fileId); 
                }
            }
         });
	}
	
	// 页面删除文件的处理
	function del(obj, id, kind){
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
        obj.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode.parentNode.parentNode);
	}	
	
	// 后台进行删除数据
	function delFile(fileId, id, kind){
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
                url: "${ctx}/base/tBaFile/saveLocation", // 上传地址  
                formData: {name: "${id}", filesize: "${filesize}"},       // 传递name
                sequentialUploads: true,         // 是否按顺序上传文件
                dataType: 'json',                // json格式，不加上ie9不能传值
                //maxFileSize: 50 * 1024 * 1024,   // 文件大小，ie9不起作用
                done: function (e, data) {
                    var isFilterEncryption = "${isFilterEncryption}"; // 是否过滤加密文件
                    var id = "${id}";     // 传入的Id号
                    var result = data.result["result"];     // 上传的结果
                    if(isFilterEncryption == null || isFilterEncryption == ""){
                        isFilterEncryption = "yes";
                    }
                    
                    if(isFilterEncryption == "yes" && result == "error"){
                        var messageError = data.result["messageError"];     // 上传的结果
                        if("havePassword" == messageError){
                            top.$.jBox.tip("您上传的文件存在加密问题，请重新上传!", "warning");
                        }else {
                            top.$.jBox.tip("您上传的文件存在问题，请重新上传!", "warning");
                        }
                        $("#fileupload-buttonbar" + id + " #newBody" + addId).css("display","none");
                        $("#fileSpan${id}").css('display',''); 
                        $("#file${id}").fileupload("enable");// 文件上传失败后，上传控件恢复可用
                    }else {
	                    top.$.jBox.tip("附件上传成功","warning");
	                    var kind = "${kind}"; // 传入的文件类型
	                    var fileId = data.result["fileId"];     // 上传的文件id
	                    var fileSize = data.result["filesize"]; // 上传的文件大小
	                    var activeUploads = $("#file" + id).fileupload("active");// 正在执行的文件数
	                    var tempLength = $("#newBody" + addId).length;
	                    if(1 != activeUploads || 0 == tempLength){  
	                        delFile(fileId, id, kind);// 删除获得fileId
	                    }else{
	                        // 进度条去掉; 显示已完成的字样; 删除添加方法; 添加下载链接
	                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#progress" + addId).attr("display", "none");
	                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#fileupload-progress" + addId).css("width", "100%");
	                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#fileupload-progress" + addId).html("<span style='color: grey;font-size:12px;'>" 
	                            + fileSize + "</span><span style='color: green;font-size:12px;'> 上传完成" + "</span>"); 
	                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#delAa" + addId).attr("onclick", "delFile('" + fileId + "', '" + id + "')");
	                        $("#fileupload-buttonbar" + id + " #newBody" + addId).find("#delAd" + addId).attr("href", "${ctx}/base/tBaFile/downLocationFile?id=" + fileId);
	                        // 文件上传成功,存值处理
	                        var kind = "${kind}";// 文件上传的类型 single：单附件 more：多附件
	                        if('more' != kind){//单附件
	                            $("#" + id).val(fileId);
	                        }else {// 多附件
	                            $("#" + id).val($("#" + id).val()+"," + fileId);
	                        }
	                        $("#file" + id).fileupload("enable"); 
	                    }
                    }
                },  
                progressall: function (e, data) {
                    loading('正在提交，请稍等...');
                    //设置上传进度条事件的回调函数      火狐，谷歌，ie11 正常。 ie9 无效
                    var id = "${id}"; 
                    $("#fileupload-buttonbar" + id + " #fileupload-progress" + addId).css('display','block');
                    
                    var progress = parseInt(data.loaded / data.total * 100, 10);  
                    $("#fileupload-buttonbar" + id + " #progress" + addId + " .bar").css(    
                        'width',  
                        progress + '%'  
                    );  
                    $("#fileupload-buttonbar" + id + " #progress" + addId +" .bar").html(progress + '%');
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
                    newBody.appendTo("#fileupload-buttonbar${id}"); // 添加到展示效果的框中
                    newBody.show(); 
                    newBody.css("float", "left"); 
                    // 谷歌必须加上，否则谷歌浏览器无法显示!
                    $("<span id='tempspan'>&nbsp;</span>").appendTo("#fileupload-buttonbar${id}");
                    $("#fileupload-buttonbar${id}").find("#tempspan").remove();
                    // 修改新建的属性
                    newBody.attr("id", "newBody" + addId);     // 改变新文件展示域的id 
                    newBody.find("#showName").html(filenameShow);  // 放入文件名
                    newBody.find("#showName").attr("title", filename);  
                    newBody.find("#fileupload-progress").attr("id", "fileupload-progress" + addId);
                    newBody.find("#progress").attr("id", "progress" + addId);
                    newBody.find("#dela").attr("id", "dela" + addId);
                    newBody.find("#delAa").attr("id", "delAa" + addId);
                    newBody.find("#delAd").attr("id", "delAd" + addId);
                    newBody.find("#imgShow").attr("class", "showFile showFile-" + fileNewType);
                }
            });
</script>