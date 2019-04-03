<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>PDF合成演示模块</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var fileIdOne = $("#fileIdOne").val();
					var fileIdTwo = $("#fileIdTwo").val();
					var fileIdThree = $("#fileIdThree").val();
					if(fileIdOne == null || fileIdOne == ""){
						top.$.jBox.tip("请将模版上传一补齐", "warning");
						return;
					}
					if(fileIdTwo == null || fileIdTwo == ""){
						top.$.jBox.tip("请将模版上传二补齐", "warning");
						return;
					}
					if(fileIdThree == null || fileIdThree == ""){
						top.$.jBox.tip("请将模版上传三补齐", "warning");
						return;
					}
					
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		//pdf预览
        function showPdf(fileId){
            var url = "${ctxStatic}/pdf/web/viewer.html?file=" + encodeURIComponent("${ctx}/base/tBaFile/downFile?id=" + fileId );
            window.open(url);
        }
	</script>
</head>
<body>
     <br/>
	<form:form id="inputForm" modelAttribute="demo" action="${ctx}/demo/PdfDemo/composePdf" method="post" class="form-horizontal">
        <sys:message content="${message}"/>
        <div class="control-group">
            <label class="control-label">模板上传一:</label>
            <div class="controls" >
				<sys:uploadfile id="fileIdOne" name="fileIdOne" 
				    value="${fileIdOne}"
					kind="single" buttonName="资料上传" 
					filesize="50" filetype="jpg,pdf">
				</sys:uploadfile>
			</div>
        </div>
        <div class="control-group">
            <label class="control-label">模板上传二:</label>
            <div class="controls" >
				<sys:uploadfile id="fileIdTwo" name="fileIdTwo" 
				    value="${fileIdTwo}"
					kind="single" buttonName="资料上传" 
					filesize="50" filetype="jpg,pdf">
				</sys:uploadfile>
			</div>
        </div>
        <div class="control-group">
            <label class="control-label">模板上传三:</label>
            <div class="controls" >
				<sys:uploadfile id="fileIdThree" name="fileIdThree" 
				    value="${fileIdThree}"
					kind="single" buttonName="资料上传" 
					filesize="50" filetype="jpg,pdf">
				</sys:uploadfile>
			</div>
        </div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="合成"/>
			<c:if test="${resultPdf != null && resultPdf.isMarge == '01'}">
			     <input id="btnSubmit" class="btn btn-primary" type="button" value="合成成功，请查看！" onclick="showPdf('${resultPdf.id}')"/>
			</c:if>
			<c:if test="${resultPdf != null && resultPdf.isMarge != '01'}">
			     ${resultPdf.isMarge}
			</c:if>
		</div>
	</form:form>
</body>
</html>