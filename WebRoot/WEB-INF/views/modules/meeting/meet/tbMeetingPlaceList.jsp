<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会场管理管理</title>
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
		<li class="active"><a href="${ctx}/meeting/meet/tbMeetingPlace/">会场管理列表</a></li>
		<li><a href="${ctx}/meeting/meet/tbMeetingPlace/form">会场管理添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbMeetingPlace" action="${ctx}/meeting/meet/tbMeetingPlace/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10%;text-align:center;">序号</th>
				<th style="width:25%;text-align:center;">会场名称</th>
				<th style="width:15%;text-align:center;">联系电话</th>
				<th style="width:10%;text-align:center;">容纳人数</th>
				<th style="width:30%;text-align:center;">会场地址</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbMeetingPlace" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;"><a href="${ctx}/meeting/meet/tbMeetingPlace/view?id=${tbMeetingPlace.id}">${tbMeetingPlace.placeName}</a></td>
				<td style="text-align:center;">${tbMeetingPlace.placeTel}</td>
				<td style="text-align:center;">${tbMeetingPlace.contain}</td>
				<td style="text-align:center;">${tbMeetingPlace.placeAddress}</td>
				<td>
    				<a href="${ctx}/meeting/meet/tbMeetingPlace/form?id=${tbMeetingPlace.id}">修改</a>
					<a href="${ctx}/meeting/meet/tbMeetingPlace/delete?id=${tbMeetingPlace.id}" onclick="return confirmx('确认要删除该会场管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>