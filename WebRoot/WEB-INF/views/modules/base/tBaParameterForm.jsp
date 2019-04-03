<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参数管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>
		    <a href="${ctx}/base/tBaParameter/">参数列表</a>
		</li>
		<li class="active">
		    <a href="${ctx}/base/tBaParameter/form?id=${tBaParameter.id}">参数<shiro:hasPermission 
		        name="base:tBaParameter:edit">${not empty tBaParameter.id?'修改':'添加'}
		        </shiro:hasPermission>
		        <shiro:lacksPermission name="base:tBaParameter:edit">
		                               查看
		        </shiro:lacksPermission>
		    </a>
	    </li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="TBaParameter" action="${ctx}/base/tBaParameter/save" 
	    method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
            <label class="control-label">模板名称：</label>
            <div class="controls">
                <form:select path="tempId" class="input-xlarge required">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('temp_name')}" itemLabel="label" 
                        itemValue="id" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">参数名称：</label>
            <div class="controls">
                <form:input path="paraName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">参数键：</label>
            <div class="controls">
                <form:input path="paraKey" htmlEscape="false" maxlength="20" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">参数值：</label>
            <div class="controls">
                <form:input path="paraValue" htmlEscape="false" maxlength="20" class="input-xlarge "/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label">参数类型：</label>
            <div class="controls">
                <form:select path="paraType" class="input-xlarge ">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('para_type')}" itemLabel="label" 
                        itemValue="id" htmlEscape="false"/>
                </form:select>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">使用状态：</label>
			<div class="controls">
				<select name="useState" style="width: 120px;">
                    <option value="0" <c:if test="${tBaParameter.useState eq '0' }">selected</c:if>>
                                                            启用
                    </option>
                    <option value="1" <c:if test="${tBaParameter.useState eq '1' }">selected</c:if>>
                                                            停用
                    </option>
                </select>
                <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">顺序：</label>
            <div class="controls">
                <input name="sort" type="text" style="width: 100px;" value="${tBaParameter.sort }"
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
			<shiro:hasPermission name="base:tBaParameter:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			    &nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>