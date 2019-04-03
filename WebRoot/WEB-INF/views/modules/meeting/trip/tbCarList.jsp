<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆信息管理</title>
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
		<li class="active"><a href="${ctx}/meeting/trip/tbCar/">车辆信息列表</a></li>
		<li><a href="${ctx}/meeting/trip/tbCar/form">车辆信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbCar" action="${ctx}/meeting/trip/tbCar/" method="post" class="breadcrumb form-search">
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
				<th style="width:20%;text-align:center;">车牌号</th>
				<th style="width:15%;text-align:center;">车型</th>
				<th style="width:10%;text-align:center;">座位数</th>
				<th style="width:15%;text-align:center;">联系人</th>
				<th style="width:20%;text-align:center;">电话</th>
				<th style="width:15%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbCar" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbCar.carNum}</td>
				<td style="text-align:center;">${tbCar.carModel}</td>
				<td style="text-align:center;">${tbCar.seatNum}</td>
				<td style="text-align:center;">${tbCar.carMan}</td>
				<td style="text-align:center;">${tbCar.manPhone}</td>
				<td style="text-align:center;">
    				<a href="${ctx}/meeting/trip/tbCar/form?id=${tbCar.id}">修改</a>
					<a href="${ctx}/meeting/trip/tbCar/delete?id=${tbCar.id}" onclick="return confirmx('确认要删除该车辆信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>