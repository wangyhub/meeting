<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>附近配置管理</title>
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
		    <a href="${ctx}/base/tBaAtt/">模板配置列表</a>
		</li>
		<shiro:hasPermission name="base:tBaAtt:edit">
		    <li>
		        <a href="${ctx}/base/tBaAtt/form">模板配置添加</a>
		    </li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="TBaAtt" action="${ctx}/base/tBaAtt/" 
	    method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>部门类型：</label>
				<sys:treeselect id="office" name="office.id" value="${TBaAtt.office.id}" 
				    labelName="office.name" labelValue="${TBaAtt.office.name}" title="部门"
				    url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" 
				    notAllowSelectParent="true"/>
			</li>
			<li>
			    <label>附件标题：</label>
				<form:input path="name" htmlEscape="false" maxlength="600" class="input-medium"/>
			</li>
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>模板名称</th>
                <th>机构类型</th>
				<th>模板类型</th>
				<th>使用状态</th>
				<th>修改时间</th>
				<th>顺序</th>
				<th>备注</th>
				<shiro:hasPermission name="base:tBaAtt:edit">
				    <th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaAtt">
			<tr>
				<td>
				    <a href="${ctx}/base/tBaAtt/form?id=${tBaAtt.id}">
					     ${tBaAtt.name}
				    </a>
				</td>
				<td>
                    ${tBaAtt.office.name}
                </td>
				<td>
					${tBaAtt.type.label}
				</td>
				<td>
                    <c:if test="${tBaAtt.useState eq '0' }">启用</c:if>
                    <c:if test="${tBaAtt.useState eq '1' }">停用</c:if>        
                </td>
				<td>
					<fmt:formatDate value="${tBaAtt.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
                    ${tBaAtt.sort}
                </td>
				<td>
                    ${tBaAtt.remarks}                   
                </td>
				<shiro:hasPermission name="base:tBaAtt:edit">
				    <td>
    				    <a href="${ctx}/base/tBaAtt/form?id=${tBaAtt.id}">修改</a>
					    <a href="${ctx}/base/tBaAtt/delete?id=${tBaAtt.id}" 
					        onclick="return confirmx('确认要删除该附件配置吗？', this.href)">删除</a>
				    </td>
			    </shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>