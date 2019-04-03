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
            var seatNum = "${param.num}";
            var seatIds = "${param.seats}";
            var len = $("input:checkbox[name='joins']:checked").length;
            if(len > 0){
                if(seatNum==len){
                    var joins = $("input:checkbox[name='joins']:checked").map(function(){return this.value;}).get().join(",");
                    $("#seatNo").val(seatIds);
                    $("#joinId").val(joins);

                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx}/meeting/meet/tbAgendaSeat/save",
                        data: $('#saveForm').serialize(),
                        success: function (result) {
                            window.opener.location.reload();
                            window.close();
                        },
                        error : function() {
                            alert("出现异常！");
                        }
                    });
                }
                else {
                    alert("选择座位数"+seatNum+"个，"+"选择人员数"+len+"个，两者不符，请保持相同数量！");
                }
            }
            else{
                alert("请选择人员！");
            }
        }
	</script>
</head>
<body>
    <form:form id="saveForm" modelAttribute="tbAgendaSeat" action="" method="post" class="form-horizontal">
        <input type="hidden" id="agendaId" name="agendaId" value="${param.agendaId}"/>
        <input type="hidden" id="joinId" name="joinId" value=""/>
        <input type="hidden" id="seatNo" name="seatNo" value=""/>
    </form:form>

	<form:form id="searchForm" modelAttribute="tbJoin" action="${ctx}/meeting/meet/tbJoin/chooseJoinList?num=${param.num}&seats=${param.seats}&agendaId=${param.agendaId}" method="post" class="breadcrumb form-search">
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
				<td style="text-align:center;"><input type="checkbox" name="joins" value="${tbJoin.id}" /></td>
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