<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程管理管理</title>
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
		<li class="active">
		    <a href="${ctx}/workflow/tBaTempActivity/">流程管理列表
		    </a>
		</li>
		<shiro:hasPermission name="workflow:tBaTempActivity:edit">
		    <li>
		        <a href="${ctx}/workflow/tBaTempActivity/form">流程管理添加
		        </a>
		    </li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="TBaTempActivity" action="${ctx}/workflow/tBaTempActivity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程名称：</label>
				<form:input path="activityName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>序号</th>
				<th>流程名称</th>
				<th>流程标识</th>
				<th>流程状态</th>
				<th>备注</th>
				<shiro:hasPermission name="workflow:tBaTempActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaTempActivity" varStatus="indSta">
			<tr>
				<td>
                    <a href="${ctx}/workflow/tBaTempActivity/form?id=${tBaTempActivity.id}">
                        ${indSta.index + 1}
                    </a>
                </td>
				<td>
				    ${tBaTempActivity.activityName}
				</td>
				<td>
					${tBaTempActivity.logo}
				</td>
				<td>
                    <c:choose>
                        <c:when test="${tBaTempActivity.status eq '1'}">运行中
                        </c:when>
                        <c:when test="${tBaTempActivity.status eq '2'}">终止
                        </c:when>
                        <c:when test="${tBaTempActivity.status eq '3'}">暂停
                        </c:when>
                   </c:choose>
                </td>
				<td>
					${tBaTempActivity.remarks}
				</td>
				<shiro:hasPermission name="workflow:tBaTempActivity:edit"><td>
    				<a href="${ctx}/workflow/tBaTempActivity/form?id=${tBaTempActivity.id}">修改</a>
					<a href="${ctx}/workflow/tBaTempActivity/delete?id=${tBaTempActivity.id}" onclick="return confirmx('确认要删除该流程管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>