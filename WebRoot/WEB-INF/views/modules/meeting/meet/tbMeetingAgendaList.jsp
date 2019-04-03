<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日程安排管理</title>
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

        function setSeat(id,agendaId){
            var path = "${ctx}/meeting/meet/tbMeetingPlace/setSeat?id="+id+"&agendaId="+agendaId;
            window.open(path,'_blank','width=1000,height=650,top=100px,left=200px');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/meet/tbMeetingAgenda/">日程安排列表</a></li>
		<li><a href="${ctx}/meeting/meet/tbMeetingAgenda/form">日程安排添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbMeetingAgenda" action="${ctx}/meeting/meet/tbMeetingAgenda/" method="post" class="breadcrumb form-search">
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
				<th style="width:15%;text-align:center;">会议名称</th>
				<th style="width:15%;text-align:center;">会议主题</th>
				<th style="width:15%;text-align:center;">开始时间</th>
				<th style="width:15%;text-align:center;">结束时间</th>
				<th style="width:20%;text-align:center;">会场</th>
				<th style="width:15%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbMeetingAgenda" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;"><a href="${ctx}/meeting/meet/tbMeetingAgenda/form?id=${tbMeetingAgenda.id}">
						${tbMeetingAgenda.name}
				</a></td>
				<td style="text-align:center;">${tbMeetingAgenda.subject}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbMeetingAgenda.beginDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td style="text-align:center;"><fmt:formatDate value="${tbMeetingAgenda.endDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td style="text-align:center;">${tbMeetingAgenda.placeName}</td>
				<td style="text-align:center;">
					<a href="javascript:void(0)" onclick="setSeat('${tbMeetingAgenda.placeId}','${tbMeetingAgenda.id}')">座位安排</a>
    				<a href="${ctx}/meeting/meet/tbMeetingAgenda/form?id=${tbMeetingAgenda.id}">修改</a>
					<a href="${ctx}/meeting/meet/tbMeetingAgenda/delete?id=${tbMeetingAgenda.id}" onclick="return confirmx('确认要删除该日程安排吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>