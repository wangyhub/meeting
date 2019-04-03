<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邀请码生成管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#saveForm").validate({
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/meet/tbInviteCode/">邀请码列表</a></li>
	</ul>
	<form:form id="saveForm" modelAttribute="tbInviteCode" action="${ctx}/meeting/meet/tbInviteCode/save" method="post" class="breadcrumb form-search">
		<div class="controls">
			<sys:message content="${message}"/>
			<form:input path="inviteCode" htmlEscape="false" maxlength="20" class="input-xlarge required digits" placeholder="请输入要生成的邀请码数量" onfocus="this.placeholder=''" onblur="this.placeholder='请输入要生成的邀请码数量'"/>
			<input id="btnSubmit" class="btn btn-info" type="submit" value="一键生成"/>
		</div>
	</form:form>

	<form:form id="searchForm" modelAttribute="tbInviteCode" action="${ctx}/meeting/meet/tbInviteCode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10%;text-align:center;">序号</th>
				<th style="width:25%;text-align:center;">邀请码</th>
				<th style="width:25%;text-align:center;">使用状态</th>
				<th style="width:15%;text-align:center;">使用人账号</th>
				<th style="width:15%;text-align:center;">使用时间</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbInviteCode" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbInviteCode.inviteCode}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbInviteCode.status, 'code_status', '')}</td>
				<td style="text-align:center;">${tbInviteCode.operator}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbInviteCode.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align:center;">
					<c:if test="${tbInviteCode.status eq '0'}">
						<a href="${ctx}/meeting/meet/tbInviteCode/delete?id=${tbInviteCode.id}" onclick="return confirmx('确认要删除该邀请码吗？', this.href)">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>