<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程管理管理</title>
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
			
			// 流程定义名称
            jQuery.validator.addMethod("activityName", 
                function(value, element) {          
                    var flag = true;
                    var companyId = $("#companyId").val();
                    $.ajax({
                        url:'${ctx}/workflow/tBaTempActivity/checkActivityName',
                        async:false, 
                        type:"post",
                        data:{activityName: value, flag: "0", aid: "${tBaTempActivity.id}", cid: companyId},
                        dataType:"json",
                        success:function(data){
                            if(data == true){
                                flag = true;
                            }else if(data == false){
                                flag = false;
                            }
                        }   
                    });  
                    return flag;
                }, 
                "该流程定义名已存在！"
            );
            
            // 流程定义标识
            jQuery.validator.addMethod("checkLogo", 
                function(value, element) {          
                    var flag = true;
                    var companyId = $("#companyId").val();
                    if("" == companyId){
                       flag = false;
                    }
                    return flag;
                }, 
                "请先选择归属公司！"
            );
            
            // 流程定义标识
            jQuery.validator.addMethod("activityLogo", 
                function(value, element) {          
                    var flag = true;
                    var companyId = $("#companyId").val();
                    $.ajax({
                        url:'${ctx}/workflow/tBaTempActivity/checkActivityLogo',
                        async:false, 
                        type:"post",
                        data:{logo: value, flag: "0", aid: "${tBaTempActivity.id}", cid: companyId},
                        dataType:"json",
                        success:function(data){
                            if(data == true){
                                flag = true;
                            }else if(data == false){
                                flag = false;
                            }
                        }   
                    });  
                    return flag;
                }, 
                "该流程标识已存在！"
            );
            
		});
		
		// 增加行
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
		
		// 删除行
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
		<li><a href="${ctx}/workflow/tBaTempActivity/">流程管理列表</a></li>
		<li class="active">
		    <a href="${ctx}/workflow/tBaTempActivity/form?id=${tBaTempActivity.id}">
			    <shiro:hasPermission name="workflow:tBaTempActivity:edit">
			        ${not empty tBaTempActivity.id?'流程管理修改':'流程管理添加'}
			    </shiro:hasPermission>
			    <shiro:lacksPermission name="workflow:tBaTempActivity:edit">流程管理查看
			    </shiro:lacksPermission>
		    </a>
		 </li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="TBaTempActivity" action="${ctx}/workflow/tBaTempActivity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
            <label class="control-label">
                <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>归属公司：
            </label>
            <div class="controls">
                <sys:treeselect id="company" name="company.id" value="${tBaTempActivity.company.id}" 
                    labelName="company.name" labelValue="${tBaTempActivity.company.name}" title="公司" 
                    url="/sys/office/treeData?type=1" cssClass="required" notAllowSelectParent="true"
                    dataMsgRequired="必填信息" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">
                <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>流程定义名称：
            </label>
            <div class="controls">
                <form:input path="activityName" htmlEscape="false" maxlength="40" 
                    class="input-xlarge required checkLogo activityName"/>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">
			    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>流程标识：
			</label>
			<div class="controls">
				<form:input path="logo" htmlEscape="false" maxlength="40" 
				    class="input-xlarge required checkLogo activityLogo"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
			    <form:select path="status" class="input-small">
                        <form:options items="${fns:getDictList('flow_status')}" itemLabel="label" 
                            itemValue="value" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group" style="width: 100%;">
	                <label class="control-label" style="text-align: left;">流程节点定义表：</label>
	       </div>
			<div class="control-group" style="width: 100%;">
				<div >
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th style="width: 14%;">流程节点名称</th>
								<th style="width: 10%;">状态</th>
								<th style="width: 10%;">是否发送短信</th>
								<th style="width: 14%;">发送短信内容</th>
								<th style="width: 10%;">优先级</th>
								<th style="width: 15%;">备注</th>
								<th style="width: 22%;">角色分配</th>
								<shiro:hasPermission name="workflow:tBaTempActivity:edit">
								    <th width="10">操作</th>
								</shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="tBaTempNoteList">
						</tbody>
						<shiro:hasPermission name="workflow:tBaTempActivity:edit">
							<tfoot>
								<tr>
								    <td colspan="8">
								        <a href="javascript:" 
								            onclick="addRow('#tBaTempNoteList', tBaTempNoteRowIdx, tBaTempNoteTpl);tBaTempNoteRowIdx = tBaTempNoteRowIdx + 1;" class="btn">新增
								        </a>
								    </td>
							    </tr>
							</tfoot>
						</shiro:hasPermission>
					</table>
					<script type="text/template" id="tBaTempNoteTpl">
						<tr id="tBaTempNoteList{{idx}}">
							<td class="hide">
								<input id="tBaTempNoteList{{idx}}_id" name="tBaTempNoteList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="tBaTempNoteList{{idx}}_delFlag" name="tBaTempNoteList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="tBaTempNoteList{{idx}}_name" name="tBaTempNoteList[{{idx}}].name" 
                                    type="text" value="{{row.name}}" maxlength="40" class="input-small required"/>
							</td>
							<td>
                                <select id="tBaTempNoteList{{idx}}_status" name="tBaTempNoteList[{{idx}}].status" 
                                    data-value="{{row.status}}" class="input-small required">
                                    <option value=""></option>
                                    <c:forEach items="${fns:getDictList('flow_status')}" var="dict">
                                        <option value="${dict.value}">${dict.label}</option>
                                    </c:forEach>
                                </select>
							</td>
							<td>
								<select id="tBaTempNoteList{{idx}}_sendSms" name="tBaTempNoteList[{{idx}}].sendSms" data-value="{{row.sendSms}}" class="input-small required">
                                    <option value=""></option>
                                    <c:forEach items="${fns:getDictList('yes_no')}" var="dict">
                                        <option value="${dict.value}">${dict.label}</option>
                                    </c:forEach>
                                </select>
							</td>
							<td>
								<textarea id="tBaTempNoteList{{idx}}_smsContent" name="tBaTempNoteList[{{idx}}].smsContent"
                                    rows="4" maxlength="4000" class="input-small ">{{row.smsContent}}</textarea>
							</td>
							<td>
								<input id="tBaTempNoteList{{idx}}_priority" name="tBaTempNoteList[{{idx}}].priority" type="text" value="{{row.priority}}" maxlength="18" class="input-small required number"/>
							</td>
							<td>
								<textarea id="tBaTempNoteList{{idx}}_remarks" name="tBaTempNoteList[{{idx}}].remarks" 
                                    rows="4" maxlength="250" class="input-small ">{{row.remarks}}</textarea>
							</td>
                            <td>
                                <c:forEach items="${allRoles}" var="role" varStatus="roleStatus">
                                    <input id="tBaTempNoteList{{idx}}_roleIdList${roleStatus.index}" name="tBaTempNoteList[{{idx}}].roleIdList" type="checkbox" value="${role.id}" data-value="{{row.roleIdList}}" />
                                    <label for="tBaTempNoteList{{idx}}_roleIdList${roleStatus.index}">
                                        ${role.name}
                                    </label>
                                    <br/>
                                </c:forEach>
                            </td>
							<shiro:hasPermission name="workflow:tBaTempActivity:edit">
                                <td class="text-center" width="10">
								    {{#delBtn}}
                                         <span class="close" onclick="delRow(this, '#tBaTempNoteList{{idx}}')" title="删除">&times;</span>
                                    {{/delBtn}}
							    </td>
                            </shiro:hasPermission>
						</tr>
					</script>
					<script type="text/javascript">
						var tBaTempNoteRowIdx = 0;
						var tBaTempNoteTpl = $("#tBaTempNoteTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(tBaTempActivity.TBaTempNoteList)};
							for (var i=0; i<data.length; i++){
								addRow('#tBaTempNoteList', tBaTempNoteRowIdx, tBaTempNoteTpl, data[i]);
								tBaTempNoteRowIdx = tBaTempNoteRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="workflow:tBaTempActivity:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			    </shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>