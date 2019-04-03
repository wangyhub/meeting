<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用餐信息管理</title>
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

        function setMeal(mealId,isTable){
            var path = "${ctx}/meeting/meal/tbJoinMeal/list?mealId="+mealId+"&isTable="+isTable;
            window.open(path,'_blank','width=1000,height=650,top=100px,left=200px');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/meal/tbMeal/">用餐信息列表</a></li>
		<li><a href="${ctx}/meeting/meal/tbMeal/form">用餐信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbMeal" action="${ctx}/meeting/meal/tbMeal/" method="post" class="breadcrumb form-search">
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
				<th style="width:10%;text-align:center;">餐厅名称</th>
				<th style="width:10%;text-align:center;">联系电话</th>
				<th style="width:19%;text-align:center;">餐厅地址</th>
				<th style="width:12%;text-align:center;">用餐时间</th>
				<th style="width:8%;text-align:center;">用餐类型</th>
				<th style="width:8%;text-align:center;">用餐种类</th>
				<th style="width:8%;text-align:center;">是否分桌</th>
				<th style="width:8%;text-align:center;">参加人数</th>
				<th style="width:12%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbMeal" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbMeal.mealName}</td>
				<td style="text-align:center;">${tbMeal.mealPhone}</td>
				<td style="text-align:center;">${tbMeal.mealAddress}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbMeal.mealDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td style="text-align:center;">${fns:getDictLabels(tbMeal.mealType, 'meal_type', '')}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbMeal.mealKind, 'meal_kind', '')}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbMeal.isTable, 'yes_no', '')}</td>
				<td style="text-align:center;">${tbMeal.joinNum}</td>
				<td style="text-align:center;">
    				<a href="${ctx}/meeting/meal/tbMeal/form?id=${tbMeal.id}">修改</a>
					<a href="${ctx}/meeting/meal/tbMeal/delete?id=${tbMeal.id}" onclick="return confirmx('确认要删除该用餐信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>