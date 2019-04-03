<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表单配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#matchDataBase").css("cursor","pointer");
			$("#cancelDataBase").css("cursor","pointer");
			$("#tongbu").click(function(){
				$("#tableConfigId").val($("#tableConId").val());
			});
			
			$(".modalBtn").click(function(){
				var id = $("#tableConfigId").val();
				var synMethod =  $("input[name='synMethod']:checked").val();
				$.ajax({
					async:false,
		            type: "POST",
		            url:"${ctx}/base/tBaTableConfig/ajaxMatchDataBase",
		            data:{"id":id,"synMethod":synMethod},	
		           	success:function(data){
		           		top.$.jBox.tip(data, "warning");
		           		window.setTimeout('location.reload()', 2000);
		           }
				});
			});
			$("#btnReset").click(function(){
				$("#searchForm").find("li.formVal").find("input").val("");
			});
			
		});
		/*
		function doIt(id){
			confirmx("是否同步数据库?",function(){	
				$.ajax({
						async:false,
			            type: "post",
			            url:"${ctx}/base/tBaTableConfig/ajaxMatchDataBase",
			            data:{"id":id},	
			            dataType:"json",
			           	success:function(data){
			           		location.reload();
			           }
					});
			 });
		}
		*/
		//function cancelIt(id){
		//	confirmx("是否取消同步数据库?",function(){
		//		$.ajax({
		//            type: "POST",
		//            url:"${ctx}/base/tBaTableConfig/ajaxCancelDataBase",
		//            data:{"id":id},	
		//           	success:function(data){
		//           		location.reload();
		//           }
		//		});
		// });
		//}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/tBaTableConfig/">表单配置列表</a></li>
		<shiro:hasPermission name="base:tBaTableConfig:edit"><li><a href="${ctx}/base/tBaTableConfig/form">表单配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="TBaTableConfig" action="${ctx}/base/tBaTableConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="formVal">
                <label>表名：</label>
                <input id="tableName" name="tableName" type="text" value="${TBaTableConfig.tableName }" />
            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnReset" class="btn btn-primary btnReset" type="button" value="重置"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>表类型</th>
				<th>表名</th>
				<th>表描述</th>
				<th>表关系</th>
				<th>修改日期</th>
				<th>备注</th>
				<th>是否同步数据库</th>
				<shiro:hasPermission name="base:tBaTableConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBaTableConfig">
			<tr>
				<td><c:if test="${tBaTableConfig.type eq '01' }">单表</c:if><c:if test="${tBaTableConfig.type eq '02' }">主表</c:if><c:if test="${tBaTableConfig.type eq '03' }">附表</c:if></td>
				<td>${tBaTableConfig.tableName }</td>
				<td>${tBaTableConfig.content }</td>
				<td><c:if test="${tBaTableConfig.relationType eq '01' }">一对多</c:if><c:if test="${tBaTableConfig.relationType eq '02' }">一对一</c:if></td>
				<td><fmt:formatDate value="${tBaTableConfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					${tBaTableConfig.remarks}
				</td>
				<td>
					<c:if test="${tBaTableConfig.matchDataBase eq '00'}">
						<input type="hidden" id="tableConId" value="${tBaTableConfig.id}">
						<button id="tongbu" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">同步数据库</button>
					</c:if>
					<c:if test="${tBaTableConfig.matchDataBase eq '01'}">
						<a id="cancelDataBase" onclick="cancelIt('${tBaTableConfig.id}')">已同步</a>
					</c:if>
				</td>
				<shiro:hasPermission name="base:tBaTableConfig:edit"><td>
    				<a href="${ctx}/base/tBaTableConfig/form?id=${tBaTableConfig.id}">修改</a>
					<a href="${ctx}/base/tBaTableConfig/delete?id=${tBaTableConfig.id}" onclick="return confirmx('确认要删除该表单配置吗？', this.href)">删除</a>
					<c:if test="${tBaTableConfig.type eq '01' or tBaTableConfig.type eq '02' }">
						<a href="${ctx}/base/tBaPublicBean/list?tableConfigId=${tBaTableConfig.id}">功能测试</a>
					</c:if>
					<c:if test="${tBaTableConfig.type eq '01' or tBaTableConfig.type eq '02' }">
						<a href="${ctx}/sys/menu/toMenuForm?tableConfigId=${tBaTableConfig.id}&gncs=/base/tBaPublicBean/list?tableConfigId=${tBaTableConfig.id}">添加菜单</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel" style="display:inline">
		           		 是否确认同步数据库？
		            </h4>
		         </div>
		         <div class="modal-body">
		        	<table>
		        		<tr>
		        		<td style="height:69px">
		        			<input type="hidden" id="tableConfigId"/>
		        			<input type="radio" value="normal"   name="synMethod" checked="checked">普通同步</br>
		        			<input type="radio" value="force"  name="synMethod">强制同步（删除数据库已有表）
		        		</td>
		        		</tr>
		        	</table>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" 
		               data-dismiss="modal">关闭
		            </button>
		            <button type="button" class="btn btn-primary modalBtn">
		               	提交
		            </button>
		         </div>
      		</div>
		</div>
	</div>
	
	<div class="pagination">${page}</div>
</body>
</html>