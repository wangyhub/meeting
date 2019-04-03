<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日程安排管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/meet/tbMeetingAgenda/">日程安排列表</a></li>
		<li class="active"><a href="${ctx}/meeting/meet/tbMeetingAgenda/form?id=${tbMeetingAgenda.id}">日程安排${not empty tbMeetingAgenda.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbMeetingAgenda" action="${ctx}/meeting/meet/tbMeetingAgenda/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

<table width = "100%">
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">会场：</label>
				<div class="controls">
					<form:select path="placeId" class="input-xlarge required">
						<form:option value="" label=" "/>
						<form:options items="${placeList}" itemLabel="placeName" itemValue="id" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">会议时间：</label>
				<div class="controls">
					<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="<fmt:formatDate value="${tbMeetingAgenda.beginDate}" pattern="yyyy-MM-dd HH:mm"/>" style="width:130px"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
					—
					<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="<fmt:formatDate value="${tbMeetingAgenda.endDate}" pattern="yyyy-MM-dd HH:mm"/>" style="width:130px"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">名称：</label>
				<div class="controls">
					<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required "/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">主题：</label>
				<div class="controls">
					<form:input path="subject" htmlEscape="false" maxlength="200" class="input-xlarge required "/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>

	</tr>
	<tr>
		<td width = "100%" colspan="2">
			<div class="control-group">
				<label class="control-label">主持人：</label>
				<div class="controls">
					<form:input path="moderator" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</div>
			</div>
		</td>
	</tr>

	<tr>
		<td width = "100%" colspan="2">
			<div class="control-group">
				<label class="control-label">日程详细：
				<br/>
				<a href="javascript:" onclick="addRow('#tbAgendaDetailList', tbAgendaDetailRowIdx, tbAgendaDetailTpl);tbAgendaDetailRowIdx = tbAgendaDetailRowIdx + 1;" class="btn">添加</a>
				</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th >时间</th>
								<th>主题</th>
								<th>内容</th>
								<th width="10">&nbsp;</th>
							</tr>
						</thead>
						<tbody id="tbAgendaDetailList">
						</tbody>
					</table>
					<script type="text/template" id="tbAgendaDetailTpl">//<!--
						<tr id="tbAgendaDetailList{{idx}}">
							<td class="hide">
								<input id="tbAgendaDetailList{{idx}}_id" name="tbAgendaDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="tbAgendaDetailList{{idx}}_delFlag" name="tbAgendaDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="tbAgendaDetailList{{idx}}_agendaTime" name="tbAgendaDetailList[{{idx}}].agendaTime" type="text" value="{{row.agendaTime}}" maxlength="20" class="input-small "/>
							</td>
							<td>
								<input id="tbAgendaDetailList{{idx}}_subject" name="tbAgendaDetailList[{{idx}}].subject" type="text" value="{{row.subject}}" maxlength="200" class="input-xlarge "/>
							</td>
							<td>
								<textarea id="tbAgendaDetailList{{idx}}_content" name="tbAgendaDetailList[{{idx}}].content" cols="15" rows="2" maxlength="200" class="input-xxlarge ">{{row.content}}</textarea>
							</td>
							<td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#tbAgendaDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var tbAgendaDetailRowIdx = 0, tbAgendaDetailTpl = $("#tbAgendaDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(tbMeetingAgenda.tbAgendaDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#tbAgendaDetailList', tbAgendaDetailRowIdx, tbAgendaDetailTpl, data[i]);
								tbAgendaDetailRowIdx = tbAgendaDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<div class="control-group">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</td>
	</tr>
</table>


	</form:form>
</body>
</html>