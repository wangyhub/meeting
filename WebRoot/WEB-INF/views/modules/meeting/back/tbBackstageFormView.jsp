<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查看SQL</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
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
	<form:form id="inputForm" modelAttribute="tbBackstage" action="${ctx}/meeting/back/tbBackstage/save" method="post" class="form-horizontal">

		<div class="control-group">
			<form:textarea path="execSql" htmlEscape="false" rows="4" maxlength="800" style="width: 700px; height: 200px;" readonly="true"/>
		</div>

		<p align="center">
			<input id="btnCancel" class="btn" type="button" value="关 闭" onclick="window.close();"/>
		</p>
	</form:form>
</body>
</html>