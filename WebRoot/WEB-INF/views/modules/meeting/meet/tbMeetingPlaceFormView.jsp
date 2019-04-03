<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会场管理管理</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=RG3ybaaMUA9rksCZfmjXeLyO9WG5QRqI"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js"></script>
	<style type="text/css">
		body, html {
			width: 100%;
			height: 100%;
			margin: 0;
			font-family: "微软雅黑";
			font-size: 14px;
		}

		input[type="text"] {
			height: 30px;
			line-height: 40px;
			color: #000;
			font-size: 14px;
			border-radius: 5px;
			display: inline-block;
			-webkit-border-radius: 5px;
			background: #fff;
			border: 1px #ccc solid;
			padding: 0 10px
		}
		table.gridtable {
			font-family: verdana,arial,sans-serif;
			font-size:14px;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;
		}
		table.gridtable td {
			border-width: 0px;
			padding: 8px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}

		.btn {
			display: inline-block;
			padding: 6px 12px;
			margin-bottom: 0;
			font-size: 14px;
			font-weight: normal;
			line-height: 1.42857143;
			text-align: center;
			white-space: nowrap;
			vertical-align: middle;
			-ms-touch-action: manipulation;
			touch-action: manipulation;
			cursor: pointer;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
			background-image: none;
			border: 1px solid transparent;
			border-radius: 4px;
		}
		.btn-primary {
			color: #fff;
			background-color: #3daae9;
			border-color: #2fa4e7 #2fa4e7 #157ab5;
			border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
		}

	</style>

	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

        function showSeatCharts(){
			var platform = "${tbMeetingPlace.platform}";
            var regionRow = "${tbMeetingPlace.regionRow}";
            var region = "${tbMeetingPlace.region}";

            if(regionRow=='' || region==''){
                alert("参会席不能为空");
                $("#regionRow").focus();
                return;
			}
            var path = "${ctx}/meeting/meet/tbMeetingPlace/showSeat?platform="+platform+"&regionRow="+regionRow+"&region="+region;
            window.open(path,'_blank','width=1000,height=650,top=100px,left=200px');
        }

        function onlyNumber(o) {
            o.value=o.value.replace(/\D/g,'');
        }

        function onlySeat(o) {
            o.value=o.value.replace(/[^=cC]/g,'');
        }
	</script>

</head>
<body>

	<form:form id="inputForm" modelAttribute="tbMeetingPlace" action="${ctx}/meeting/meet/tbMeetingPlace/save" method="post" >
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" id="status" name="status" value="${tbMeetingPlace.status==null?0:tbMeetingPlace.status}"/>

<table width = "100%" class="gridtable">
	<tr>
		<td width = "100%">
			会场名称：<label> ${tbMeetingPlace.placeName}</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			联系电话：<label> ${tbMeetingPlace.placeTel}</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			容纳人数：<label> ${tbMeetingPlace.contain}</label>
		</td>
	</tr>
	<tr>
		<td width = "100%">
			会场地址：<label> ${tbMeetingPlace.placeAddress}</label>
			〖地图坐标：
			X：<input type="text" id="pointX" name="pointX" value="${tbMeetingPlace.pointX}" style="width:150px;" readonly="true"/>
			Y：<input type="text" id="pointY" name="pointY" value="${tbMeetingPlace.pointY}" style="width:150px;" readonly="true"/>〗
			<br/>
		</td>
	</tr>
	<tr>
		<td>
			<iframe name="mapFrame" src="${ctx}/meeting/meet/tbMeeting/map?flag=view" width="870" height="300" frameborder="0"></iframe>
		</td>
	</tr>
</table>

	<br/><br/>
	<fieldset style="width:850px">
		<legend>坐席分布图设置</legend>
		〖主席台：<label> ${tbMeetingPlace.platform}</label>〗
		【参会席：<label> ${tbMeetingPlace.regionRow}</label>排 ×
		<label> ${tbMeetingPlace.region}</label>列】

		<button type="button" style="height:30px;width:50px;display:inline-block;cursor: pointer;" onclick="showSeatCharts()">预  览</button>
		<br/><br/>

	</fieldset>

		<br/><br/>
			<div style="text-align: center">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
	</form:form>
</body>
</html>
