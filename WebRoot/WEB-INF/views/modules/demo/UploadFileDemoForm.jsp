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
		</script>
	</head>
	
	<body>
	    <br/>
		<form:form id="inputForm" modelAttribute="demo" action="" method="post" class="form-horizontal">
	        <sys:message content="${message}"/>
	        <table class="table table-bordered table-condensed">
	            <tbody>
		            <tr>
		                <td>FTP服务器/项目服务器文件上传：</td>
		                <td>
		                    <sys:uploadfile id="fileIdOne" name="fileIdOne" 
						    	value="${fileIdOne}"
								kind="single" buttonName="资料上传" 
								filesize="50" filetype="jpg,pdf"
								isFilterEncryption="yes">
							</sys:uploadfile>
					    </td>
		            </tr>
		            
		            <tr>
		                <td>FTP图片文件上传:</td>
		                <td>
		                    <sys:uploadImage id="fileIdThree" name="fileIdThree" 
					    	value="${fileIdThree}"
							kind="single" buttonName="资料上传" 
							filesize="50" filetype="jpg">
							</sys:uploadImage>
					    </td>
		            </tr>
		            
		            
		            <tr>
		                <td>FTP图片文件上传:</td>
		                <td>
		                    <sys:uploadImageAndCompress smallName="fileIdFourSmall" name="fileIdFour" id="fileIdFour" kind="more">
		                    
		                    </sys:uploadImageAndCompress>
					    </td>
		            </tr>
	            </tbody>
	            
	            <tfoot>
		            <tr>
		                <td colspan="2">
		                    <span style="color: red;" >（注：此处根据config.properties文件中的DEFINE_UPLOAD属性进行配置 true FTP服务器上传   其他值 项目服务器）</span>
		                </td>
		            </tr>
	            </tfoot>
	        </table>
       		
		</form:form>
	</body>
</html>