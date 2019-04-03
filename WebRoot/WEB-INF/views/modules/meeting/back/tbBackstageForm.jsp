<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据维护管理</title>
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

		function runSql(commit) {
		    var sql = $("#execSql").val();
            $.getJSON("${ctx}/meeting/back/tbBackstage/save?commit="+commit+"&execSql="+sql,function(data){
				if(data.remarks==null){
				    $("#affectNum").val("执行成功！\n影响行数："+data.affectNum);
				    if(commit=="1"){
				        alert("提交成功!");
					}
				}
				else{
                    $("#affectNum").val(data.remarks);
				}
            });
        }

        function clean(){
		    $("#execSql").val("");
		}


	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/back/tbBackstage/">数据维护列表</a></li>
		<li class="active"><a href="${ctx}/meeting/back/tbBackstage/form?id=${tbBackstage.id}">数据修改</a></li>
	</ul><br/>

	<form:form id="inputForm" modelAttribute="tbBackstage" action="${ctx}/meeting/back/tbBackstage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>

		<div class="control-group">
			<form:textarea path="execSql" htmlEscape="false" rows="4" maxlength="800" style="width: 700px; height: 200px;" placeholder="请输入要执行的SQL语句" onfocus="this.placeholder=''" onblur="this.placeholder='请输入要执行的SQL语句'" />
		</div>
		<div class="control-group">
			&nbsp;&nbsp;<input class="btn btn-info" type="button" value="执 行" onclick="runSql(0)"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="btn" type="button" value="清 除" onclick="clean()" />
		</div>
		<div class="control-group">
			<form:textarea path="affectNum" htmlEscape="false" rows="2" maxlength="800" style="width: 700px; height: 100px;" placeholder="执行结果" readonly="true" />
		</div>
		<div class="control-group">
			<input id="btnSubmit" class="btn btn-danger" type="button" value="提 交" onclick="runSql(1)"/>&nbsp;
			<font color="red">注：提交后会直接更新到数据库中，请慎重操作！</font>
		</div>
		<p align="center">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</p>
	</form:form>
</body>
</html>