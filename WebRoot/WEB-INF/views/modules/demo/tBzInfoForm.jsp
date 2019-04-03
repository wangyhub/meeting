<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>弹出消息管理</title>
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
		<li><a href="${ctx}/demo/tBzInfo/">弹出消息列表</a></li>
		<li class="active"><a href="${ctx}/demo/tBzInfo/form?id=${tBzInfo.id}">弹出消息<shiro:hasPermission name="demo:tBzInfo:edit">${not empty tBzInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="demo:tBzInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBzInfo" action="${ctx}/demo/tBzInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户编号：</label>
			<div class="controls">
				<form:input path="userid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="600" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">00未读，01已读：</label>
			<div class="controls">
				<form:input path="flag" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">createby：</label>
			<div class="controls">
				<form:input path="createby" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">createdate：</label>
			<div class="controls">
				<input name="createdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tBzInfo.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">updateby：</label>
			<div class="controls">
				<form:input path="updateby" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">updatedate：</label>
			<div class="controls">
				<input name="updatedate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tBzInfo.updatedate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="demo:tBzInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>