<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新闻动态管理</title>
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
		<li class="active"><a href="${ctx}/meeting/news/tbNews/">新闻动态列表</a></li>
		<li><a href="${ctx}/meeting/news/tbNews/form">新闻动态添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbNews" action="${ctx}/meeting/news/tbNews/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:5%;text-align:center;">序号</th>
				<th style="width:35%;text-align:center;">标题</th>
				<th style="width:15%;text-align:center;">新闻时间</th>
				<th style="width:10%;text-align:center;">发布人员</th>
				<th style="width:10%;text-align:center;">是否置顶</th>
				<th style="width:10%;text-align:center;">状态</th>
				<th style="width:15%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbNews" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbNews.title}</td>
				<td style="text-align:center;">
					<fmt:formatDate value="${tbNews.newsTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align:center;">${tbNews.issuer}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbNews.isTop, 'yes_no', '')}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbNews.status, 'news_status', '')}</td>
				<td style="text-align:center;">
					<c:if test="${tbNews.status eq '0'}">
						<a href="${ctx}/meeting/news/tbNews/submit?id=${tbNews.id}&status=1" onclick="return confirmx('确认要发布新闻吗，发布以后不可修改！', this.href)">发布</a>
    					<a href="${ctx}/meeting/news/tbNews/form?id=${tbNews.id}">修改</a>
						<a href="${ctx}/meeting/news/tbNews/delete?id=${tbNews.id}" onclick="return confirmx('确认要删除该新闻动态吗？', this.href)">删除</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>