<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>住宿信息管理</title>
	<meta name="decorator" content="default"/>
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

            setJoins(${joinList});
		});

        function setJoins(data){
            $("#users").select2("data", data);
            setJoinNum();
        }

        function setJoinNum(){
            var list = $("#users").select2("data");
            var ids = $(list).map(function(i,item){return item.id;}).get().join(",");
            $("#joinNum").val(ids);
        }

        function chooseCommon(){
            var path = "${ctx}/meeting/meet/tbJoin/chooseCommonList";
            window.open(path,'_blank','width=700,height=600,top=120px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/stay/tbStay/">住宿信息列表</a></li>
		<li class="active"><a href="${ctx}/meeting/stay/tbStay/form?id=${tbStay.id}">住宿信息${not empty tbStay.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbStay" action="${ctx}/meeting/stay/tbStay/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">酒店选择：</label>
			<div class="controls">
				<form:select path="hotelId" class="input-xlarge required">
					<form:option value="" label=" "/>
					<form:options items="${hotelList}" itemLabel="hotelName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font size="4" color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">入住时间：</label>
			<div class="controls">
				<input name="stayTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					   value="<fmt:formatDate value="${tbStay.stayTime}" pattern="yyyy-MM-dd HH:mm"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
				<span class="help-inline"><font size="4" color="red">*</font> </span>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				离店时间：
				<input name="leaveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
					   value="<fmt:formatDate value="${tbStay.leaveTime}" pattern="yyyy-MM-dd HH:mm"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
				<span class="help-inline"><font size="4" color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">安排人员：
			</label>
			<div class="controls">
				<select id="users" name="users" style="width: 800px" multiple onchange="setJoinNum()">
				</select>
				<input type="text" style="width:1px;visibility:hidden" id="joinNum" name="joinNum" class="required">
				<span class="help-inline"><font size="4" color="red">*</font> </span>
				<input class="btn btn-info" type="button" value="选择人员" onclick="chooseCommon()"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>