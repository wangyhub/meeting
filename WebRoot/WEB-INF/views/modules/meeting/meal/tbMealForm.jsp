<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用餐信息管理</title>
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

            setJoins(${joinList});
		});

		var dataAll = [];
		var dataMap = new Map();
        function setJoins(data) {
            var mess = checkData(data);
            if(mess != ""){
            	alert("【"+mess+"】已安排，请重新选择！");
            	return;
			}

            if (typeof(dataAll)=="undefined" || dataAll.length==0) {
                dataAll = data;
            } else {
                dataAll = dataAll.concat(data);
            }

            $("#users").select2("data", dataAll);
            setJoinNum();
        }

        function checkData(list){
            var returnValue = "";
            if (typeof(list)=="undefined" || list.length==0) {
                return returnValue;
            }

			$.each(list, function (index, obj) {
				var ids = obj.id.split("|");
				if (dataMap.has(ids[1])) {
                    returnValue = dataMap.get(ids[1]);
                    return false;
				} else {
					dataMap.set(ids[1], obj.text);
				}
			});

            return returnValue;
		}

        function setJoinNum(){
            var list = $("#users").select2("data");
            var ids = $(list).map(function(i,item){return item.id;}).get().join(",");
            $("#joinNum").val(ids);
        }

        function chooseCommon(){
            var isTable = $('input:radio[name=isTable]:checked').val();
            if (typeof(isTable) == "undefined") {
                alert("请先选择是否分桌！");
                return;
			}
            var path = "${ctx}/meeting/meet/tbJoin/chooseMealList?isTable="+isTable;
            window.open(path,'_blank','width=700,height=600,top=120px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
        }
        
        function cleanJoin() {
            var list = $("#users").select2("data");
			if(list!="" && confirm("更改分桌信息将会清除已安排的人员，是否继续？")){
                dataAll = [] ;
                dataMap = new Map();
                setJoins(dataAll);
			}
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/meal/tbMeal/">用餐信息列表</a></li>
		<li class="active"><a href="${ctx}/meeting/meal/tbMeal/form?id=${tbMeal.id}">用餐信息${not empty tbMeal.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbMeal" action="${ctx}/meeting/meal/tbMeal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<table width="100%">
	<tr>
		<td width="40%">
			<div class="control-group">
				<label class="control-label">餐厅名称：</label>
				<div class="controls">
					<form:input path="mealName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width="60%">
			<div class="control-group">
				<label class="control-label">餐厅地址：</label>
				<div class="controls">
					<form:input path="mealAddress" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="40%">
			<div class="control-group">
				<label class="control-label">用餐时间：</label>
				<div class="controls">
					<input name="mealDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${tbMeal.mealDate}" pattern="yyyy-MM-dd HH:mm"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width="60%">
			<div class="control-group">
				<label class="control-label">联系电话：</label>
				<div class="controls">
					<form:input path="mealPhone" htmlEscape="false" maxlength="20" class="input-xlarge "/>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="40%">
			<div class="control-group">
				<label class="control-label">用餐类型：</label>
				<div class="controls">
					<form:select path="mealType" class="input-medium required">
						<form:option value=""></form:option>
						<form:options items="${fns:getDictList('meal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width="60%">
			<div class="control-group">
				<label class="control-label">用餐种类：</label>
				<div class="controls">
					<form:select path="mealKind" class="input-medium required">
						<form:option value=""></form:option>
						<form:options items="${fns:getDictList('meal_kind')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="2">
			<div class="control-group">
				<label class="control-label">是否分桌：</label>
				<div class="controls">
					<form:radiobuttons path="isTable" items="${fns:getDictList('yes_no')}" onchange="cleanJoin()" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="2">
			<div class="control-group">
				<label class="control-label">安排人员：
				</label>
				<div class="controls">
					<select id="users" name="users" style="width: 800px" multiple onchange="setJoinNum()">
					</select>
					<input type="text" style="width:1px;visibility:hidden" id="joinNum" name="joinNum" class="required">
					<span class="help-inline"><font size="4" color="red">*</font> </span>
					<input class="btn btn-info" type="button" value="选择人员" onclick="chooseCommon()"/>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="2" align="center">
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</td>
	</tr>
</table>
	</form:form>
</body>
</html>