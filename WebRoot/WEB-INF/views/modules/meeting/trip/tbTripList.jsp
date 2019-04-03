<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>行程安排管理</title>
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

        function setTrip(tripId){
            var path = "${ctx}/meeting/trip/tbJoinTrip/list?tripId="+tripId;
            window.open(path,'_blank','width=1000,height=650,top=100px,left=200px');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/trip/tbTrip/">行程安排列表</a></li>
		<li><a href="${ctx}/meeting/trip/tbTrip/form">行程安排添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbTrip" action="${ctx}/meeting/trip/tbTrip/" method="post" class="breadcrumb form-search">
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
				<th style="width:15%;text-align:center;">时间</th>
				<th style="width:20%;text-align:center;">乘车地点</th>
				<th style="width:10%;text-align:center;">使用车辆</th>
				<th style="width:10%;text-align:center;">联系人</th>
				<th style="width:20%;text-align:center;">备注</th>
				<th style="width:10%;text-align:center;">参加人数</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbTrip" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbTrip.tripTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td style="text-align:center;">${tbTrip.tripAddress}</td>
				<td style="text-align:center;">${tbTrip.carNum}</td>
				<td style="text-align:center;">${tbTrip.callMan}</td>
				<td style="text-align:center;">${tbTrip.remark}</td>
				<td style="text-align:center;">${tbTrip.joinNum}</td>
				<td style="text-align:center;">
    				<a href="${ctx}/meeting/trip/tbTrip/form?id=${tbTrip.id}">修改</a>
					<a href="${ctx}/meeting/trip/tbTrip/delete?id=${tbTrip.id}" onclick="return confirmx('确认要删除该行程安排吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>