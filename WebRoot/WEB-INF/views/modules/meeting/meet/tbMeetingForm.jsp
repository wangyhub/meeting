<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#createCode").focus();
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

        function checkCode() {
            var createCode = $("#createCode").val();
            $.getJSON("${ctx}/meeting/meet/tbCreateCode/checkCode?createCode="+createCode,function(data){
                if(data=="0"){
                    $("#createCode").val("");
                    $("#createCode").focus();
					alert("会议创建码输入错误，请重新输入!");
                }
            });
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/meet/tbMeeting/">会议列表</a></li>
		<li class="active"><a href="${ctx}/meeting/meet/tbMeeting/form?id=${tbMeeting.id}">会议${not empty tbMeeting.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbMeeting" action="${ctx}/meeting/meet/tbMeeting/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" id="status" name="status" value="${tbMeeting.status==null?0:tbMeeting.status}"/>

		<div class="control-group">
			<label class="control-label"><font style="font-weight:bold;color:red">会议创建码：</font></label>
			<div class="controls">
				<form:input path="createCode" htmlEscape="false" maxlength="40" class="input-xlarge required" onchange="checkCode()" />
				<span class="help-inline"><font size="4" color="red">*</font> </span>
				<font style="font-weight:bold;color:#0080ff">&nbsp;&nbsp;注：请输入16位系统分配的创建码，无创建码不可创建会议！</font>
			</div>
		</div>

<table width = "100%">
	<tr>
		<td width = "40%">
			<div class="control-group">
				<label class="control-label">会议名称：</label>
				<div class="controls">
					<form:input path="meetingName" htmlEscape="false" maxlength="100" class="input-xlarge required "/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">主办单位：</label>
				<div class="controls">
					<form:input path="sponsor" htmlEscape="false" maxlength="100" class="input-xlarge required "/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">举办时间：</label>
				<div class="controls">
					<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
						value="<fmt:formatDate value="${tbMeeting.startTime}" pattern="yyyy-MM-dd HH:mm"/>" style="width:130px"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
					—
					<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required "
						   value="<fmt:formatDate value="${tbMeeting.endTime}" pattern="yyyy-MM-dd HH:mm"/>" style="width:130px"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">会议地址：</label>
				<div class="controls">
					<form:input path="meetingAddress" htmlEscape="false" maxlength="100" class="input-xlarge required "/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
					<button type="button" style="height:30px;width:50px;display:inline-block;cursor: pointer;" onclick="companyReveal()">搜  索</button>
					<br/>
					<font color="#0080ff">注：点击"搜索"按钮可根据地址查找大致方位<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;鼠标左键标记具体位置，右键删除标记！</font>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">会议人数：</label>
				<div class="controls">
					<form:input path="personNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">接送安排：</label>
				<div class="controls">
					<form:radiobuttons path="isStation" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">住宿安排：</label>
				<div class="controls">
					<form:radiobuttons path="isLive" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">用餐安排：</label>
				<div class="controls">
					<form:radiobuttons path="isMeal" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">签到管理：</label>
				<div class="controls">
					<form:radiobuttons path="isSignin" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">动态发布：</label>
				<div class="controls">
					<form:radiobuttons path="isNews" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</div>
			</div>

		</td>
		<td width = "30%" valign="top">
			〖地图坐标：
			X：<input type="text" id="pointX" name="pointX" value="${tbMeeting.pointX}" style="width:100px;" readonly="true"/>
			Y：<input type="text" id="pointY" name="pointY" value="${tbMeeting.pointY}" style="width:100px;" readonly="true"/>〗
			<br/>

			<iframe name="mapFrame" src="${ctx}/meeting/meet/tbMeeting/map?flag=edit" width="400" height="400" frameborder="0"></iframe>

			<script type="text/javascript">
                function companyReveal() {
                    var msearch = $("#meetingAddress").val();
                    mapFrame.local.search(msearch);  //百度地图关键字检索 触发加载
                }
			</script>
		</td>

		<td width = "30%" valign="top" >

					<table id="contentTable" class="table table-striped table-bordered table-condensed" >
						<thead>
						<tr>
							<th class="hide"></th>
							<th>参会单位名录&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:" onclick="addRow('#tbMeetingCompanyList', tbMeetingCompanyRowIdx, tbMeetingCompanyTpl);tbMeetingCompanyRowIdx = tbMeetingCompanyRowIdx + 1;" class="btn">添加</a></th>
							<th width="10">&nbsp;</th>
						</tr>
						</thead>
						<tbody id="tbMeetingCompanyList">
						</tbody>

					</table>
					<script type="text/template" id="tbMeetingCompanyTpl">//<!--
						<tr id="tbMeetingCompanyList{{idx}}">
							<td class="hide">
								<input id="tbMeetingCompanyList{{idx}}_id" name="tbMeetingCompanyList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="tbMeetingCompanyList{{idx}}_delFlag" name="tbMeetingCompanyList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="tbMeetingCompanyList{{idx}}_companyName" name="tbMeetingCompanyList[{{idx}}].companyName" type="text" value="{{row.companyName}}" maxlength="100" class="input-xlarge "/>
							</td>
							<td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#tbMeetingCompanyList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
                        var tbMeetingCompanyRowIdx = 0, tbMeetingCompanyTpl = $("#tbMeetingCompanyTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
                        $(document).ready(function() {
                            var data = ${fns:toJson(tbMeeting.tbMeetingCompanyList)};
                            for (var i=0; i<data.length; i++){
                                addRow('#tbMeetingCompanyList', tbMeetingCompanyRowIdx, tbMeetingCompanyTpl, data[i]);
                                tbMeetingCompanyRowIdx = tbMeetingCompanyRowIdx + 1;
                            }
                        });
					</script>
		</td>
	</tr>

	<tr>
		<td  colspan="3" align="center" >
			<div class="control-group">
				<input id="btnSubmit1" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel1" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</td>
	</tr>
</table>

	</form:form>
</body>
</html>