<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆信息管理</title>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/trip/tbCar/">车辆信息列表</a></li>
		<li class="active"><a href="${ctx}/meeting/trip/tbCar/form?id=${tbCar.id}">车辆信息${not empty tbCar.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbCar" action="${ctx}/meeting/trip/tbCar/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">车牌号：</label>
			<div class="controls">
				<form:input path="carNum" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font size="4" color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">车型：</label>
			<div class="controls">
				<form:input path="carModel" htmlEscape="false" maxlength="50" class="input-xlarge  required"/>
				<span class="help-inline"><font size="4" color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">座位数：</label>
			<div class="controls">
				<form:input path="seatNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits required"/>
				<span class="help-inline"><font size="4" color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="carMan" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人电话：</label>
			<div class="controls">
				<form:input path="manPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>