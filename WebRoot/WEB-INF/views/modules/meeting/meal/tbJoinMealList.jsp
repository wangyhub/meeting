<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用餐管理</title>
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

        function chooseMeal(){
            var path = "${ctx}/meeting/meet/tbJoin/chooseMealList?mealId=${param.mealId}&isTable=${param.isTable}";
            window.open(path,'_blank','width=700,height=600,top=120px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
        }

        $(window).unload(function(){
            window.opener.location.reload();
        });
	</script>
</head>
<body>
	<form:form id="saveForm" modelAttribute="tbJoinMeal" action="${ctx}/meeting/meal/tbJoinMeal/save" method="post" class="breadcrumb form-search">
		<div class="controls">
			<sys:message content="${message}"/>
			<input class="btn btn-primary" type="button" value="添加人员" onclick="chooseMeal()"/>
		</div>
	</form:form>

	<form:form id="searchForm" modelAttribute="tbJoinMeal" action="${ctx}/meeting/meal/tbJoinMeal/list?mealId=${param.mealId}&isTable=${param.isTable}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:5%;text-align:center;">序号</th>
				<th style="width:10%;text-align:center;">桌号</th>
				<th style="width:10%;text-align:center;">人员姓名</th>
				<th style="width:35%;text-align:center;">单位</th>
				<th style="width:30%;text-align:center;">职务</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbJoinMeal" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbJoinMeal.tableNum}</td>
				<td style="text-align:center;">${tbJoinMeal.userName}</td>
				<td style="text-align:center;">${tbJoinMeal.companyName}</td>
				<td style="text-align:center;">${tbJoinMeal.position}</td>
				<td>
					<a href="${ctx}/meeting/meal/tbJoinMeal/delete?id=${tbJoinMeal.id}&mealId=${param.mealId}&isTable=${param.isTable}" onclick="return confirmx('确认要删除该用餐安排吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>