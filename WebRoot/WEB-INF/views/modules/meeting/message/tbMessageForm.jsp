<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息信息管理</title>
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
		});

		function setJoins(data){
            $("#users").select2("data", data);
            setSendNum();
		}

		function setSendNum(){
		    var list = $("#users").select2("data");
            var ids = $(list).map(function(i,item){return item.id;}).get().join(",");
            $("#sendNum").val(ids);
        }

        function chooseMessage(){
            var path = "${ctx}/meeting/meet/tbJoin/chooseCommonList?flag=mess";
            window.open(path,'_blank','width=700,height=600,top=120px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/message/tbMessage/">消息列表</a></li>
		<li class="active"><a href="${ctx}/meeting/message/tbMessage/form?id=${tbMessage.id}">发送消息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbMessage" action="${ctx}/meeting/message/tbMessage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<table>
	<tr>
		<td width="50%">
			<div class="control-group">
				<label class="control-label">类型：</label>
				<div class="controls">
					<form:select path="type" class="input-small required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('message_type')}" itemLabel="label"
									  itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width="50%" align="left">
			<div class="control-group">
				<label class="control-label">种类：</label>
				<div class="controls">
					<form:select path="kind" class="input-small required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('message_kind')}" itemLabel="label"
									  itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="2">
			<div class="control-group">
				<label class="control-label">内容：</label>
				<div class="controls">
					<form:textarea path="content" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="2">
			<div class="control-group">
				<label class="control-label">发送人员：
				</label>
				<div class="controls">
					<select id="users" name="users" style="width: 800px" multiple onchange="setSendNum()">
					</select>
                    <input type="text" style="width:1px;visibility:hidden" id="sendNum" name="sendNum" class="required">
					<span class="help-inline"><font size="4" color="red">*</font> </span>
					<input class="btn btn-info" type="button" value="选择人员" onclick="chooseMessage()"/>
				</div>
			</div>
		</td>
	</tr>

	<tr>
		<td width="100%" colspan="2" align="center">
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-danger" type="submit" value="发 送"/>&nbsp;
				<font color="red">注：此操作会直接给相关人员发送信息，请慎重操作！</font>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</td>
	</tr>
</table>

	</form:form>
</body>
</html>