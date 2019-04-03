<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>酒店信息管理</title>
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
		<li class="active"><a href="${ctx}/meeting/stay/tbHotel/">酒店信息列表</a></li>
		<li><a href="${ctx}/meeting/stay/tbHotel/form">酒店信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbHotel" action="${ctx}/meeting/stay/tbHotel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:5%;text-align:center;">序号</th>
				<th style="width:30%;text-align:center;">酒店名称</th>
				<th style="width:40%;text-align:center;">地址</th>
				<th style="width:10%;text-align:center;">电话</th>
				<th style="width:15%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbHotel" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbHotel.hotelName}</td>
				<td style="text-align:center;">${tbHotel.hotelAddress}</td>
				<td style="text-align:center;">${tbHotel.hotelPhone}</td>
				<td style="text-align:center;">
    				<a href="${ctx}/meeting/stay/tbHotel/form?id=${tbHotel.id}">修改</a>
					<a href="${ctx}/meeting/stay/tbHotel/delete?id=${tbHotel.id}" onclick="return confirmx('确认要删除该酒店信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>