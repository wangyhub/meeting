<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议管理</title>
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
		<li class="active"><a href="${ctx}/meeting/meet/tbMeeting/">会议列表</a></li>
		<li><a href="${ctx}/meeting/meet/tbMeeting/form">创建会议</a></li>
	</ul>
    <div style=" position:absolute; right:0px; top:10px; color:#FFFFFF">
    <ul id="userControl" class="nav pull-right">
        <li style="padding-right:100px"><a href="${ctx}/logout" title="退出登录" style="color:blueviolet;">退出登录</a></li>
    </ul>
    </div>

	<form:form id="searchForm" modelAttribute="tbMeeting" action="${ctx}/meeting/meet/tbMeeting/" method="post" class="breadcrumb form-search">
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
                <th style="width:25%;text-align:center;">会议名称</th>
                <th style="width:25%;text-align:center;">主办单位</th>
                <th style="width:15%;text-align:center;">开始时间</th>
                <th style="width:15%;text-align:center;">结束时间</th>
				<th style="width:5%;text-align:center;">状态</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbMeeting" varStatus="status">
			<tr>
                <td style="text-align:center;">${status.count}</td>

                <td style="text-align:center;"><a href="${ctx}/meeting/meet/tbMeeting/view?id=${tbMeeting.id}">${tbMeeting.meetingName}</a></td>
                <td style="text-align:center;">${tbMeeting.sponsor}</td>
                <td style="text-align:center;"><fmt:formatDate value="${tbMeeting.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td style="text-align:center;"><fmt:formatDate value="${tbMeeting.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td style="text-align:center;">${fns:getDictLabels(tbMeeting.status, 'meet_status', '')}</td>
				<td style="text-align:center;">
                    <c:if test="${tbMeeting.status eq '0'}">
    				    <a href="${ctx}/meeting/meet/tbMeeting/form?id=${tbMeeting.id}">修改</a>
                        <a href="${ctx}/meeting/meet/tbMeeting/submit?id=${tbMeeting.id}&status=1" onclick="return confirmx('确认要提交会议吗，提交以后不可修改！', this.href)">提交</a>
                        <a href="${ctx}/meeting/meet/tbMeeting/delete?id=${tbMeeting.id}" onclick="return confirmx('确认要删除该会议管理吗？', this.href)">删除</a>
                    </c:if>

                    <c:if test="${tbMeeting.status eq '1' or tbMeeting.status eq '2'}">
                        <a href="${ctx}/meeting/meet/tbMeeting/submit?id=${tbMeeting.id}&status=9">进入会议</a>
                    </c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>