<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			init();
			
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
		
		// 删除模板附件
		function deleteFile(attId, id){
		    $.ajax({
                url:'${ctx}/base/tBaAtt/deleteFile?attId=' + attId + '&id=' + id,
                type:"post",
                success:function(obj){
                    $("#filea").hide();
                    $("#fileUpload").show();
                    top.$.jBox.tip('模板文件删除成功!','warning');
                }   
            });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>
		    <a href="${ctx}/base/tBaAtt/">模板配置列表</a>
		</li>
		<li class="active">
		    <a href="${ctx}/base/tBaAtt/form?id=${tBaAtt.id}">模板配置<shiro:hasPermission 
		        name="base:tBaAtt:edit">${not empty tBaAtt.id?'修改':'添加'}
		        </shiro:hasPermission>
		        <shiro:lacksPermission name="base:tBaAtt:edit">
		                              查看
		        </shiro:lacksPermission>
		    </a>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="TBaAtt"  enctype="multipart/form-data"
	    action="${ctx}/base/tBaAtt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模板名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构类型：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${tBaAtt.office.id}" 
				    labelName="office.name" labelValue="${tBaAtt.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" 
					notAllowSelectParent="true"/>
				<span class="help-inline">
				    <font color="red">*</font>
			    </span>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">模板类型：</label>
            <div class="controls">
                <form:select path="type" class="input-xlarge required">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('busi_type')}" itemLabel="label" 
                        itemValue="id" htmlEscape="false"/>
                </form:select>
                <span style="color: red">*（字典表中类型是：busi_type）</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">使用状态:</label>
            <div class="controls">
                <select name="useState" style="width: 120px;">
                    <option value="0" <c:if test="${tBaAtt.useState eq '0' }">selected</c:if>>
                                                          启用
                    </option>
                    <option value="1" <c:if test="${tBaAtt.useState eq '1' }">selected</c:if>>
                                                          停用
                    </option>
                </select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">模板上传:</label>
            <div class="controls" >
                <sys:uploadfile id="fileId" name="fileId" value="${tBaAtt.fileId}" kind="single">
                </sys:uploadfile>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">是否必填:</label>
            <div class="controls">
                <select name="isrequired" style="width: 120px;">
                    <option value="0" <c:if test="${tBaAtt.isrequired eq '0' }">selected</c:if>>
                                                          否
                    </option>
                    <option value="1" <c:if test="${tBaAtt.isrequired eq '1' }">selected</c:if>>
                                                          是
                    </option>
                </select>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">顺序:</label>
            <div class="controls">
                <input name="sort" type="text" style="width: 100px;" value="${tBaAtt.sort }"
                    onkeyup="value=value.replace(/[^\d]/g,'') "
                    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,'')" 
                    maxlength="6" class="input-xlarge required"/>
                <span class="help-inline">
                    <font color="red">*（只能输入正整数数字）</font>
                </span>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">备注：</label>
            <div class="controls">
                <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="400" class="input-xxlarge "/>
            </div>
        </div>
        
		<div class="form-actions">
			<shiro:hasPermission name="base:tBaAtt:edit"><input id="btnSubmit" class="btn btn-primary" 
			    type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>