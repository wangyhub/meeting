<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店信息管理</title>
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
		<li><a href="${ctx}/meeting/stay/tbHotel/">酒店信息列表</a></li>
		<li class="active"><a href="${ctx}/meeting/stay/tbHotel/form?id=${tbHotel.id}">酒店信息${not empty tbHotel.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbHotel" action="${ctx}/meeting/stay/tbHotel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

<table width = "100%">
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">酒店名称：</label>
				<div class="controls">
					<form:input path="hotelName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">酒店地址：</label>
				<div class="controls">
					<form:input path="hotelAddress" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
					<button type="button" style="height:30px;width:50px;display:inline-block;cursor: pointer;" onclick="companyReveal()">搜  索</button>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<font color="#0080ff">注：点击"搜索"按钮可根据地址查找大致方位<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鼠标左键标记具体位置，右键删除标记！</font>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">地图坐标：</label>
				<div class="controls">
					〖X：<input type="text" id="pointX" name="pointX" value="${tbHotel.pointX}" style="width:100px;" readonly="true"/>
					Y：<input type="text" id="pointY" name="pointY" value="${tbHotel.pointY}" style="width:100px;" readonly="true"/>〗
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">酒店电话：</label>
				<div class="controls">
					<form:input path="hotelPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">入住须知：</label>
				<div class="controls">
					<form:input path="stayNotice" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				</div>
			</div>
		</td>
		<td width = "50%" valign="top">
				<iframe name="mapFrame" src="${ctx}/meeting/meet/tbMeeting/map?flag=edit" width="600" height="300" frameborder="0"></iframe>

				<script type="text/javascript">
					function companyReveal() {
						var msearch = $("#hotelAddress").val();
						mapFrame.local.search(msearch);  //百度地图关键字检索 触发加载
					}
				</script>
		</td>
	</tr>

	<tr>
		<td  colspan="2" align="center" >
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</td>
	</tr>
</table>

	</form:form>
</body>
</html>