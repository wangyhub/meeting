<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参会人管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/meeting/meet/tbJoin/">参会人列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbJoin" action="${ctx}/meeting/meet/tbJoin/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<table style="width:100%;">
				<tr>
					<td>
						<li>
							<label style="width:100px;" >姓名：</label>
							<form:input path="userName" htmlEscape="false" maxlength="225" class="input-medium"/>
						</li>
					</td>
					<td>
						<li>
							<label style="width:100px;" >电话：</label>
							<form:input path="phone" htmlEscape="false" maxlength="225" class="input-medium"/>
						</li>
					</td>
					<td>
						<li>
							<label style="width:100px;" >单位：</label>
							<form:input path="companyName" htmlEscape="false" maxlength="225" class="input-medium"/>
						</li>
					</td>

					<td>
						<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
					</td>
				</tr>
			</table>

		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:5%;text-align:center;">序号</th>
				<th style="width:15%;text-align:center;">报名人姓名</th>
				<th style="width:40%;text-align:center;">单位</th>
				<th style="width:15%;text-align:center;">职务</th>
				<th style="width:15%;text-align:center;">电话</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbJoin" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;"><a href="${ctx}/meeting/meet/tbJoin/view?id=${tbJoin.id}">${tbJoin.userName}</a></td>
				<td style="text-align:center;">${tbJoin.companyName}</td>
				<td style="text-align:center;">${tbJoin.position}</td>
				<td style="text-align:center;">${tbJoin.phone}</td>
				<td style="text-align:center;">
					<a href="${ctx}/meeting/meet/tbJoin/delete?id=${tbJoin.id}" onclick="return confirmx('确认要删除该参会人吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>