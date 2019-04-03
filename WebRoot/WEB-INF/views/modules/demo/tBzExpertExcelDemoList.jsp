<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Excel演示管理</title>
	<meta name="decorator" content="default"/>
		<script src="${ctxStatic}/jquery-plugin/ajaxfileupload.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#searchForm").attr("action", "${ctx}/demo/tBzExpertExcelDemo/list");
			$("#searchForm").validate({
				submitHandler: function(form){
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
			
			// 点击导入文件
			 $("#impExcel").click(function(){
				$("#modal-recive").modal("show");
			 });
			
			 //导入文件
			 $("#btnSubmitImp").click(function(){
				 if($("#changDateForm").valid()){//对弹窗的校验
					 $("#messageImp").hide();
				     $("#contentTableTemp").hide();
				     $("#contentTableTempDiv").hide();
				     $("#messageImp").html("");
				     $("#tempBody").find("tr").remove();
					 loading('正在导入，请稍等...');    
					 $.ajaxFileUpload({
					     type : "POST",
						 url:"${ctx}/demo/tBzExpertExcelDemo/excelSave", //需要链接到服务器地址
						 secureuri:false,
						 fileElementId:"fileUpload", //文件选择框的id属性
						 dataType: "json", //服务器返回的格式，可以是json					
						 success: function (data) {
							 if(data.isSuccess=="01"){
								 top.$.jBox.tip("导入失败！", "warning");
								 if(data.errorMsg != null && data.errorMsg != ""){
									 $("#messageImp").show();
									 $("#messageImp").html(data.errorMsg);
								 }
								 var userArray = data.msgArray;
								 
								 for(var i = 0; i < userArray.length; i++){  
									 var errorInfo = userArray[i];
									 var tr = '<tr><td>'+(i+1)
					             			+'</td><td>'+errorInfo.sheetName
					             			+'</td><td>'+errorInfo.rowIndex
					             			+'</td><td>'+errorInfo.errorMsg+'</td></tr>';
					             	$("#tempBody").append(tr);
		                         }  
								 
								 if(userArray.length > 0){
									 $("#contentTableTemp").show();
									 $("#contentTableTempDiv").show();
								 }
							 }else{
								 top.$.jBox.tip("导入成功！", "warning");
								 setTimeout(function (){
									 window.location.href="${ctx}/demo/tBzExpertExcelDemo/list";
								 },1000);
								 
							 }	    
						 },
						 error: function (data, status){			
						     $('#messageImp').html(data.message);					  
						 }
				     });
				 }
	        });
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action", "${ctx}/demo/tBzExpertExcelDemo/list");
			$("#searchForm").submit();
        	return false;
        }
		
		function changeAction(changeFlag){
			if(changeFlag == 1){ // 导出
				$("#searchForm").attr("action", "${ctx}/demo/tBzExpertExcelDemo/exportExcel");
			}else if(changeFlag == 2){ // 查询
				$("#searchForm").attr("action", "${ctx}/demo/tBzExpertExcelDemo/list");
			}
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/demo/tBzExpertExcelDemo/list">Excel演示列表</a></li>
		<shiro:hasPermission name="demo:tBzExpertExcelDemo:edit"><li><a href="${ctx}/demo/tBzExpertExcelDemo/form">Excel演示添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tBzExpertExcelDemo" action="${ctx}/demo/tBzExpertExcelDemo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input  class="btn btn-primary" type="submit" onclick="changeAction(1)" value="导出"/></li>
			<li class="btns"><input id="impExcel" class="btn" type="button" value="导入计划"/></li>
		    <li class="btns"><a id="downStaticFile" href="${ctx}/base/tBaFile/downStaticFile?name=drys.xlsx" target="_blank"  class="btn btn-primary">下载计划导入模板</a></li>
			<li class="clearfix"></li>
		</ul>

	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>列一</th>
				<th>列二</th>
				<th>列三</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBzExpertExcelDemo">
			<tr>
				<td>${tBzExpertExcelDemo.columnOne}</td>
				<td>${tBzExpertExcelDemo.columnTwo}</td>
				<td>${tBzExpertExcelDemo.columnThree}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
    <!-- 模态弹窗:增加项目 -->	
	<form id="changDateForm" class="form-horizontal" action="${ctx}/demo/tBzExpertExcelDemo/excelSave" method="post">
        <div class="modal hide fade" id="modal-recive">
		    <div class="modal-header" >
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		    </div>	   
		    <div class="modal-body">
				   	<label class="control-label" for="name">上传EXCEL:</label>
					<div class="controls">
					    <input type="file" id="fileUpload" accept=".xls,.xlsx" class="required fileType"  name="fileUpload" />						
					</div>
					<span id="messageImp" style="color: red;"></span>
	        </div>	

			<div id="contentTableTempDiv" style="height: 200px; overflow-y: auto;display: none;">
		        <table id="contentTableTemp" class="table table-striped table-bordered table-condensed" style="display:none;width: 90%; margin-left: 5%; " >
			        <thead>
					   	<tr>
						   	<td>序号</td>
							<td>SHEET名称</td>
							<td>行号</td>
							<td>错误信息</td>
						</tr>
					</thead>
					<tbody id="tempBody">
					</tbody>
				</table>     
			</div>
		    <div class="modal-footer">
			    <div class="span4">
			    	<input id="btnSubmitImp" type="button" class="btn btn-primary" value="保存" />
				    <a href="#" class="btn"  data-dismiss="modal" aria-hidden="true">关闭</a>
			    </div>
		    </div> 
        </div>
	</form>	
	
</body>
</html>