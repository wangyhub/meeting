<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议资料管理</title>
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

        function fileFormSubmit() {
            var allowSize = 10*1024*1024;// 10M
            var size = $("#fileUpload")[0].files[0].size;
            if (size > allowSize) {
                alert("文件大小限制在10M以内");
                return false;
            }
            $("#inputFileForm").submit();
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/meeting/file/tbAnnexFile/">会议资料列表</a></li>
	</ul>
    <div class="control-group">
        <form:form id="inputFileForm" modelAttribute="tbAnnexFile" action="${ctx}/meeting/file/tbAnnexFile/fileUpload" method="post" enctype = "multipart/form-data" class="form-horizontal">
            <input type="hidden" id="moduleid" name="moduleid" value="${sessionScope.SESSION_MEETING_ID}"/>
            <input type="hidden" id="modulecode" name="modulecode" value="01"/>
            <input type="hidden" id="modulename" name="modulename" value="会议资料"/>
            <input type="hidden" id="redirectPath" name="redirectPath" value="/meeting/file/tbAnnexFile/"/>

            <label class="control-label">选择文件：</label>
            <div class="controls">
                <input type="file" id="fileUpload"   class="required fileType"  name="fileUpload" />
                <input id="btnSubmit" class="btn btn-info" type="button" value="上 传 文 件" onclick="fileFormSubmit()"/>
            </div>
        </form:form>
    </div>

	<form:form id="searchForm" modelAttribute="tbAnnexFile" action="${ctx}/meeting/file/tbAnnexFile/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <th style="width:10%;text-align:center;">序号</th>
                <th style="width:30%;text-align:center;">上传时间</th>
                <th style="width:40%;text-align:center;">文件名</th>
                <th style="width:10%;text-align:center;">文件类型</th>
                <th style="width:10%;text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tbAnnexFile" varStatus="status">
			<tr>
                <td style="text-align:center;">${status.count}</td>
                <td style="text-align:center;"><fmt:formatDate value="${tbAnnexFile.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td style="text-align:center;"><a href="${ctx}/meeting/file/tbAnnexFile/fileDownload?id=${tbAnnexFile.id}">${tbAnnexFile.filename}</a></td>
                <td style="text-align:center;">${tbAnnexFile.filetype}</td>
				<td style="text-align:center;">
					<a href="${ctx}/meeting/file/tbAnnexFile/delete?id=${tbAnnexFile.id}&redirectPath=/meeting/file/tbAnnexFile" onclick="return confirmx('确认要删除该会议资料吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>