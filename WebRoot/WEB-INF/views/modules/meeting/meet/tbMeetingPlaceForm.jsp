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
			var platform = $("#platform").val();
            var regionRow = $("#regionRow").val();
            var region = $("#region").val();

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
			会场名称：<form:input path="placeName" htmlEscape="false" maxlength="50"  class="required"/><font size="4" color="red">*</font>
			&nbsp;&nbsp;
			联系电话：<form:input path="placeTel" htmlEscape="false" maxlength="50" />
			&nbsp;&nbsp;
			容纳人数：<form:input path="contain" htmlEscape="false" maxlength="18" onKeyUp="onlyNumber(this)" onchange="onlyNumber(this)"  />
		</td>
	</tr>
	<tr>
		<td width = "100%">
			会场地址：<form:input path="placeAddress" htmlEscape="false" maxlength="100" style="width:250px;" class="required"/><font size="4" color="red">*</font>
			<button type="button" style="height:30px;width:50px;display:inline-block;cursor: pointer;" onclick="companyReveal()">搜  索</button>
			〖地图坐标：
			X：<input type="text" id="pointX" name="pointX" value="${tbMeetingPlace.pointX}" style="width:150px;" readonly="true"/>
			Y：<input type="text" id="pointY" name="pointY" value="${tbMeetingPlace.pointY}" style="width:150px;" readonly="true"/>〗
			<br/>
			<font color="#0080ff">注：点击"搜索"按钮可根据地址查找大致方位，鼠标左键标记具体位置，右键删除标记！</font>
		</td>
	</tr>
	<tr>
		<td>
			<iframe name="mapFrame" src="${ctx}/meeting/meet/tbMeeting/map?flag=edit" width="870" height="300" frameborder="0"></iframe>

			<script type="text/javascript">
                function companyReveal() {
                    var msearch = $("#placeAddress").val();
                    mapFrame.local.search(msearch);  //百度地图关键字检索 触发加载
                }
			</script>
		</td>
	</tr>
</table>

	<br/><br/>
	<fieldset style="width:850px">
		<legend>坐席分布图设置</legend>
		〖主席台：<input type="text" style="width:90px;" id="platform" name="platform"  value="${tbMeetingPlace.platform}" placeholder="请输入数字" onKeyUp="onlyNumber(this)" onchange="onlyNumber(this)" onfocus="this.placeholder=''" onblur="this.placeholder='请输入数字'">〗
		【参会席：<input type="text" style="width:90px;" id="regionRow" name="regionRow" value="${tbMeetingPlace.regionRow}"  placeholder="请输入数字" onKeyUp="onlyNumber(this)" onchange="onlyNumber(this)" onfocus="this.placeholder=''" onblur="this.placeholder='请输入数字'"  class="required"><font size="4" color="red">*</font>排 ×
		<input type="text" style="width:350px;" id="region" name="region" value="${tbMeetingPlace.region}"  placeholder="示例:ccccccc=ccccccccc=ccccccc" onkeyup="onlySeat(this)" onchange="onlySeat(this)" onfocus="this.placeholder=''" onblur="this.placeholder='示例:ccccccc=ccccccccc=ccccccc'"  class="required"><font size="4" color="red">*</font>列】

		<button type="button" style="height:30px;width:50px;display:inline-block;cursor: pointer;" onclick="showSeatCharts()">预  览</button>
		<br/><br/>
		<font color="#0080ff">注：主席台请输入具体人数，可为空，参会席请输入具体排数，以及每排具体的座位信息：c表示座位，=表示走道</font>
	</fieldset>

		<br/><br/>
			<div style="text-align: center">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
	</form:form>
</body>
</html>