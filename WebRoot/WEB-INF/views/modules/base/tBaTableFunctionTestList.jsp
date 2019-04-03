<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>功能测试</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btnReset").click(function(){
				$("select").val("").trigger("change");
				$("#searchForm").find("li.formVal").find("input").val("");
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<sys:message content="${message}"/>
	<ul class="nav nav-tabs">
		<li class="active">
		    <a href="${ctx}/base/tBaPublicBean/?tableConfigId=${tableConfigId}">功能测试列表</a>
		</li>
		<li>
		    <a href="${ctx}/base/tBaPublicBean/form?id=${tBaParameter.id}&tableConfigId=${tableConfigId}">功能测试<shiro:hasPermission 
		        name="base:tBaParameter:edit">${not empty tBaParameter.id?'修改':'添加'}
		        </shiro:hasPermission>
		        <shiro:lacksPermission name="base:tBaParameter:edit">
		                               查看
		        </shiro:lacksPermission>
		    </a>
	    </li>
	</ul><br/>
	<form:form id="searchForm" modelAttribute="TBaPublicBean" action="${ctx}/base/tBaPublicBean/?tableConfigId=${tableConfigId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<c:forEach items="${findDataList }" var="findData">
				<li class="formVal">
	                <label>${findData.fieldName }：</label>
	                <c:set var="fieldName" value="${findData.fieldCode }"/>
	                <c:set var="start" value="${fieldName }_start"/>
	                <c:set var="end" value="${fieldName }_end"/>
	                <c:if test="${findData.controlType eq '01' or findData.controlType eq '02' or findData.controlType eq '08' }">
	                	<c:if test="${findData.queryType eq '01' or findData.queryType eq '02' }">
	                		<input name="${findData.fieldCode }" type="text" value="${map[fieldName]}" />
	                	</c:if>
	                	<c:if test="${findData.queryType eq '03' }">
	                		<input name="${findData.fieldCode }_start" type="text" value="${map[start]}" />--
	                		<input name="${findData.fieldCode }_end" type="text" value="${map[end]}" />
	                	</c:if>
	                </c:if>
	                <c:if test="${findData.controlType eq '03' or findData.controlType eq '04' or findData.controlType eq '09' }">
						<select name="${findData.fieldCode }" class="input-mini">
							<option value="">请选择</option>
							<c:forEach items="${fns:getDictList(fieldName)}" var="dict">
	                        	<option value="${dict.value}" <c:if test="${map[fieldName] eq dict.value }">selected="selected"</c:if>>${dict.label}</option>
	                        </c:forEach>
						</select>
					</c:if>
	                <c:if test="${findData.controlType eq '05' or findData.controlType eq '06' }">
	                	<input name="${findData.fieldCode }_start" id="startTime" type="text" readonly="readonly"  class="input-medium Wdate endDate required"
							value="<fmt:formatDate value="${map[start]}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--
						<input name="${findData.fieldCode }_end" id="endTime" type="text" readonly="readonly"  class="input-medium Wdate endDate required"
							value="<fmt:formatDate value="${map[end]}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</c:if>
	            </li>
            </c:forEach>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnReset" class="btn btn-primary btnReset" type="button" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:forEach items="${tBaTableFieldList}" var="tBaTableField" >
					<th <c:if test="${tBaTableField.isShowList eq '02' }">style="display: none;"</c:if>>${tBaTableField.fieldName}</th>
				</c:forEach>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="tBaPublicBean">
				<tr>
									
					<c:forEach items="${tBaTableFieldList}" var="tBaTableField" varStatus="status">
					    <c:set var="strings" value="${condList[status.index]}" />
					    <c:set var="code" value="${tBaTableField.fieldCode }"/>
					    <c:if test="${tBaTableField.controlType eq '01' or tBaTableField.controlType eq '02' or tBaTableField.controlType eq '08' }">
							<td <c:if test="${tBaTableField.isShowList eq '02' }">style="display: none;"</c:if>>${tBaPublicBean[strings]}</td>
						</c:if>
						<c:if test="${tBaTableField.controlType eq '03' or tBaTableField.controlType eq '04' or tBaTableField.controlType eq '09' }">
							<td <c:if test="${tBaTableField.isShowList eq '02' }">style="display: none;"</c:if>>
								<c:forEach items="${fns:getDictList(code)}" var="dict">
									<c:if test="${tBaPublicBean[strings] eq dict.value}">${dict.label}</c:if>
	                    		</c:forEach>
							</td>
						</c:if>
						<c:if test="${tBaTableField.controlType eq '05' or tBaTableField.controlType eq '06' }">
							<td <c:if test="${tBaTableField.isShowList eq '02' }">style="display: none;"</c:if>><fmt:formatDate value="${tBaPublicBean[strings]}" pattern="yyyy-MM-dd"/></td>
						</c:if>
						<c:if test="${tBaTableField.controlType eq '07' }">
							<td <c:if test="${tBaTableField.isShowList eq '02' }">style="display: none;"</c:if>>
							<a href="${ctx }/base/tBaFile/downFile?id=${tBaPublicBean[strings]}"/>
							<c:if test="${!empty tBaPublicBean[strings]}">下载</c:if></td>
						</c:if>
						
					</c:forEach>
					<td>
					<a href="${ctx}/base/tBaPublicBean/form?tableId=${tBaPublicBean.str1 }&tableConfigId=${tableConfigId}">修改</a>
					<a href="${ctx}/base/tBaPublicBean/delete?str1=${tBaPublicBean.str1 }&tableConfigId=${tableConfigId}" onclick="return confirmx('确认要删除该条记录吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>