<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议创建码管理</title>
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
		<li class="active"><a href="${ctx}/meeting/meet/tbCreateCode/">会议创建码列表</a></li>
	</ul>
	<form:form id="saveForm" modelAttribute="tbCreateCode" action="${ctx}/meeting/meet/tbCreateCode/save" method="post" class="breadcrumb form-search">
		<div class="controls">
			<sys:message content="${message}"/>
			<form:input path="createCode" htmlEscape="false" maxlength="20" class="input-xlarge required digits" placeholder="请输入要生成的创建码数量" onfocus="this.placeholder=''" onblur="this.placeholder='请输入要生成的创建码数量'"/>
			<input id="btnSubmit" class="btn btn-info" type="submit" value="一键生成"/>
		</div>
	</form:form>

	<form:form id="searchForm" modelAttribute="tbCreateCode" action="${ctx}/meeting/meet/tbCreateCode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10%;text-align:center;">序号</th>
				<th style="width:40%;text-align:center;">创建码</th>
				<th style="width:20%;text-align:center;">使用状态</th>
				<th style="width:20%;text-align:center;">创建时间</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbCreateCode" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbCreateCode.createCode}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbCreateCode.status, 'code_status', '')}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbCreateCode.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align:center;">
					<c:if test="${tbCreateCode.status eq '0'}">
						<a href="${ctx}/meeting/meet/tbCreateCode/delete?id=${tbCreateCode.id}" onclick="return confirmx('确认要删除该创建码吗？', this.href)">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>