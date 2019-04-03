<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>住宿信息管理</title>
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

        function setStay(stayId){
            var path = "${ctx}/meeting/stay/tbJoinStay/list?stayId="+stayId;
            window.open(path,'_blank','width=1000,height=650,top=100px,left=200px');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/stay/tbStay/">住宿信息列表</a></li>
		<li><a href="${ctx}/meeting/stay/tbStay/form">住宿信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbStay" action="${ctx}/meeting/stay/tbStay/" method="post" class="breadcrumb form-search">
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
				<th style="width:15%;text-align:center;">入住时间</th>
				<th style="width:15%;text-align:center;">离店时间</th>
				<th style="width:30%;text-align:center;">入住酒店</th>
				<th style="width:15%;text-align:center;">参加人数</th>
				<th style="width:20%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbStay" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbStay.stayTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td style="text-align:center;"><fmt:formatDate value="${tbStay.leaveTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td style="text-align:center;">${tbStay.hotelName}</td>
				<td style="text-align:center;">${tbStay.joinNum}</td>
				<td style="text-align:center;">
    				<a href="${ctx}/meeting/stay/tbStay/form?id=${tbStay.id}">修改</a>
					<a href="${ctx}/meeting/stay/tbStay/delete?id=${tbStay.id}" onclick="return confirmx('确认要删除该住宿信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>