<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参会人管理管理</title>
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
		<li><a href="${ctx}/meeting/meet/tbJoin/">参会人管理列表</a></li>
		<li class="active"><a href="${ctx}/meeting/meet/tbJoin/form?id=${tbJoin.id}">参会人管理<shiro:hasPermission name="meeting:meet:tbJoin:edit">${not empty tbJoin.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="meeting:meet:tbJoin:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbJoin" action="${ctx}/meeting/meet/tbJoin/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
<table width = "100%">
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">姓名：</label>
				<div class="controls">
					<label> ${tbJoin.userName}</label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">手机号：</label>
				<div class="controls">
					<label> ${tbJoin.phone}</label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">性别：</label>
				<div class="controls">
					<label> ${tbJoin.sex}</label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">民族：</label>
				<div class="controls">
					<label> ${tbJoin.nation}</label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">单位：</label>
				<div class="controls">
					<label> ${tbJoin.companyName}</label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">职务：</label>
				<div class="controls">
					<label> ${tbJoin.position}</label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="control-group">
				<label class="control-label">邮箱：</label>
				<div class="controls">
					<label> ${tbJoin.email}</label>
				</div>
			</div>
		</td>
	</tr>

	<tr><td colspan="2" height="20"></td></tr>

	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">是否接站：</label>
				<div class="controls">
					<label> ${fns:getDictLabels(tbJoin.isComeStation, 'yes_no', '')} </label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">是否送站：</label>
				<div class="controls">
					<label> ${fns:getDictLabels(tbJoin.isSendStation, 'yes_no', '')} </label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">到达航班/车次：</label>
				<div class="controls">
					<label> ${tbJoin.comeNumber}</label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">离开航班/车次：</label>
				<div class="controls">
					<label> ${tbJoin.goNumber}</label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">到达站：</label>
				<div class="controls">
					<label> ${tbJoin.comeStation}</label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">离开站：</label>
				<div class="controls">
					<label> ${tbJoin.goStation}</label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">到达时间：</label>
				<div class="controls">
					<label><fmt:formatDate value="${tbJoin.comeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">离开时间：</label>
				<div class="controls">
					<label><fmt:formatDate value="${tbJoin.goTime}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">接站地址：</label>
				<div class="controls">
					<label> ${tbJoin.comeAddress}</label>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">送站地址：</label>
				<div class="controls">
					<label> ${tbJoin.goAddress}</label>
				</div>
			</div>
		</td>
	</tr>

	<tr><td colspan="2" height="20"></td></tr>

	<tr>
		<td colspan="2">
			<div class="control-group">
				<label class="control-label">是否住宿：</label>
				<div class="controls">
					<label> ${fns:getDictLabels(tbJoin.isLive, 'yes_no', '')} </label>
				</div>
			</div>
		</td>
	</tr>

	<tr>
		<td  colspan="2" align="center" >
			<div class="control-group">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</td>
	</tr>
</table>

	</form:form>
</body>
</html>