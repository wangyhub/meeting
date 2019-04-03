<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参会人管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#all").click(function () {
                if (this.checked) {
                    $("input:checkbox[name='joins']").prop("checked", true);
                } else {
                    $("input:checkbox[name='joins']").prop("checked", false);
                }
            });
		});

        function choose(){
            var len = $("input:checkbox[name='joins']:checked").length;
            if(len > 0){
                var data = [] ;
                $("input:checkbox[name='joins']:checked").each(function() {
                    var idname = $(this).val().split("_");
                    var option = {"id":idname[0], "text": idname[1]};
                    data.push(option);
                });

                window.opener.setJoins(data);
                window.close();
            }
            else{
                alert("请选择人员！");
            }
        }

	</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="tbJoin" action="${ctx}/meeting/meet/tbJoin/chooseCommonList?flag=${param.flag}" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<table style="width:100%;">
				<tr>
					<td>
						<li>
							<label style="width:100px;" >单位：</label>
							<form:select path="companyId" class="input-xlarge" onchange="searchForm.submit()">
								<form:option value="" label=" "/>
								<form:options items="${companyList}" itemLabel="companyName" itemValue="id" htmlEscape="false"/>
							</form:select>
						</li>
					</td>

					<td>
						<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="选择" onclick="choose()" /></li>
					</td>
				</tr>
			</table>

		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:5%;text-align:center;"><input type="checkbox" id="all" /></th>
				<th style="width:5%;text-align:center;">序号</th>
				<th style="width:50%;text-align:center;">单位</th>
				<th style="width:15%;text-align:center;">姓名</th>
				<th style="width:25%;text-align:center;">职务</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${joinList}" var="tbJoin" varStatus="status">
			<tr>
				<td style="text-align:center;">
					<c:if test="${param.flag eq 'mess'}">
						<input type="checkbox" name="joins" value="${tbJoin.userId}|${tbJoin.phone}_${tbJoin.userName}" />
					</c:if>
					<c:if test="${empty param.flag}">
						<input type="checkbox" name="joins" value="${tbJoin.id}_${tbJoin.userName}" />
					</c:if>
				</td>
				<td style="text-align:center;">${status.count}</td>
				<td style="text-align:center;">${tbJoin.companyName}</td>
				<td style="text-align:center;">${tbJoin.userName}</td>
				<td style="text-align:center;">${tbJoin.position}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>