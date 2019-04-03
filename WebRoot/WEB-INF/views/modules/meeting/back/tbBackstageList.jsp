<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据维护管理</title>
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

        function lookSql(id){
            var path = "${ctx}//meeting/back/tbBackstage/view?id="+id;
            window.open(path,'_blank','width=750,height=300,top=120px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/back/tbBackstage/">数据维护列表</a></li>
		<li><a href="${ctx}/meeting/back/tbBackstage/form">数据修改</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tbBackstage" action="${ctx}/meeting/back/tbBackstage/" method="post" class="breadcrumb form-search">
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
				<th style="width:15%;text-align:center;">执行人</th>
				<th style="width:20%;text-align:center;">执行时间</th>
				<th style="width:10%;text-align:center;">类型</th>
				<th style="width:20%;text-align:center;">对象表名</th>
				<th style="width:15%;text-align:center;">影响数</th>
				<th style="width:15%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbBackstage" varStatus="status">
			<tr>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbBackstage.userName}</td>
				<td style="text-align:center;"><fmt:formatDate value="${tbBackstage.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align:center;">${tbBackstage.execType}</td>
				<td style="text-align:center;">${tbBackstage.execTable}</td>
				<td style="text-align:center;">${tbBackstage.affectNum}</td>
				<td>
    				<a href="javascript:void(0)" onclick ="lookSql('${tbBackstage.id}')" >查看SQL</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>