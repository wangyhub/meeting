<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息管理</title>
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
		<li class="active"><a href="${ctx}/meeting/message/tbMessage/">消息列表</a></li>
		<li><a href="${ctx}/meeting/message/tbMessage/form">发送消息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbMessage" action="${ctx}/meeting/message/tbMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label style="width:100px;" >消息类型：</label>
				<form:select path="type" class="input-small">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('message_type')}" itemLabel="label"
								  itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label style="width:100px;" >消息种类：</label>
				<form:select path="kind" class="input-small">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('message_kind')}" itemLabel="label"
								  itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label style="width:100px;" >消息内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="225" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:10%;text-align:center;">序号</th>
				<th style="width:20%;text-align:center;">消息类型</th>
				<th style="width:20%;text-align:center;">消息种类</th>
				<th style="width:20%;text-align:center;">发送时间</th>
				<th style="width:20%;text-align:center;">发送人数</th>
				<th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbMessage" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbMessage.type, 'message_type', '')}</td>
				<td style="text-align:center;">${fns:getDictLabels(tbMessage.kind, 'message_kind', '')}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbMessage.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align:center;">${tbMessage.sendNum}</td>
				<td style="text-align:center;"><a href="${ctx}/meeting/message/tbMessage/view?id=${tbMessage.id}">查看</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>