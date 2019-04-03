<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>动态查询管理</title>
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
		<li class="active"><a href="${ctx}/base/tBaMainSql/">动态查询列表</a></li>
		<li><a href="${ctx}/base/tBaMainSql/mainSqlform">动态查询添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="TBaMainSql" action="${ctx}/base/tBaMainSql/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题查询：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>修改时间</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaMainSql">
			<tr>
				<td>
					<a href="${ctx}/base/tBaMainSql/form?id=${tBaMainSql.id}">
						${tBaMainSql.title}
					</a>
				</td>
				<td>
					<fmt:formatDate value="${tBaMainSql.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tBaMainSql.remarks}
				</td>
				<td>
    				<a href="${ctx}/base/tBaMainSql/mainSqlform?id=${tBaMainSql.id}">修改</a>
					<a href="${ctx}/base/tBaMainSql/delete?id=${tBaMainSql.id}" onclick="return confirmx('确认要删除该动态查询吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>