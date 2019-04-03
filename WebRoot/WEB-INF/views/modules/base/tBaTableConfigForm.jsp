<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表单配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			
			$("#tableName").blur(function(){
				var tableName = $("#tableName").val();
				var reg = /^[a-z_]+$/;
				
				$.ajax({
		            type: "POST",
		            url:"${ctx}/base/tBaTableConfig/ajaxCheckTableName",
		            data:{"tableName":tableName},
		           	success:function(data){
		           		 if(data>0){
		           			$("#tableName").next().text("表名已存在");
		           			$("#tableName").focus();
		           		}else if(!reg.test(tableName)){
							$("#tableName").next().text("表名必须全部小写");
							$("#tableName").focus();
						}else{
		           			$("#tableName").next().text("");
		           		}
		           		
		           }
				});
			});
			//通用的点击增加按钮的方法
			function addRowFirst(){
				var $trOne = $("<tr>"
						+"<td><input type='hidden' class='input-small fieldId'><input type='text' class='input-small fieldCode'/><span style='color:red'></span></td>"
						+"<td><input type='text' class='input-small fieldName'/><span style='color:red'></span></td>"
						+"<td><input type='text' class='input-small fieldLength'/><span style='color:red'></span></td>"
						+"<td><select class='input-small fieldType'>"
						+"<option value='01'>String</option>"
						+"<option value='02'>Integer</option>"
						+"<option value='03'>Double</option>"
						+"<option value='04'>Date</option>"
						//+"<option value='05'>BigDecimal</option>"
						//+"<option value='06'>Text</option>"
						//+"<option value='07'>Blob</option>"
						+"</select></td>"
						+"<td><select class='input-mini isNull'>"
						+"<option value='01'>是</option>"
						+"<option value='02'>否</option>"
						+"</select></td>"
						+"<td class='center'><span class='close'>X</span></td>"
					+"</tr>");
			
			
			var $trTwo = $("<tr>"
						+"<td><input type='text' class='input-small fieldCodeC' readonly='readonly'/></td>"
						+"<td><input type='text' class='input-small fieldNameC' readonly='readonly'/></td>"
						+"<td><select class='input-mini isShowForm'>"
						+"<option value='01'>是</option>"
						+"<option value='02'>否</option>"
						+"</select></td>"
						+"<td><select class='input-mini isShowList'>"
						+"<option value='01'>是</option>"
						+"<option value='02'>否</option>"
						+"</select></td>"
						+"<td><select class='input-small controlType'>"
						+"<option value='01'>text</option>"
						+"<option value='02'>password</option>"
						+"<option value='03'>radio</option>"
						+"<option value='04'>checkbox</option>"
						+"<option value='05'>date</option>"
						+"<option value='06'>datetime</option>"
						+"<option value='07'>file</option>"
						+"<option value='08'>textarea</option>"
						+"<option value='09'>select</option>"
						+"<option value='10'>popup</option>"
						+"</select></td>"
						+"<td><input type='text' class='input-small controlLength'/></td>"
						+"<td><select class='input-mini isQueryCondition'>"
						+"<option value='01'>是</option>"
						+"<option value='02'>否</option>"
						+"</select></td>"
						+"<td><select class='input-small queryType'>"
						+"<option value=''></option>"
						+"<option value='01'>模糊查询</option>"
						+"<option value='02'>精确查询</option>"
						+"<option value='03'>范围查询</option>"
						+"</select></td>"
					+"</tr>");
			
			var $trThree = $("<tr>"
						+"<td><input type='text' class='input-small fieldCodeC' readonly='readonly'/></td>"
						+"<td><input type='text' class='input-small fieldNameC' readonly='readonly'/></td>"
						+"<td><input type='text' class='input-small fieldHref'/></td>"
						+"<td><input type='text' class='input-small fieldValidRule'/></td>"
						+"<td><input type='text' class='input-small dictTable'/></td>"
						+"<td><input type='text' class='input-small dictCode'/></td>"
						+"<td><input type='text' class='input-small dictText'/></td>"
						+"<td><input type='text' class='input-small fartherTable'/></td>"
						+"<td><input type='text' class='input-small fartherField'/></td>"
						+"<td><select class='input-mini isDatebaseField'>"
						+"<option value='01'>是</option>"
						+"<option value='02'>否</option>"
						+"</select></td>"
						+"<td><input type='text' class='input-small listSql'/></td>"
						+"<td><input type='text' class='input-small sort'/></td>"
						+"<td><input type='text' class='input-small remarks'/></td>"
					+"</tr>");
			
			$trOne.find("input").eq(1).blur(function(){
				var trIndex=$(this).parents("tr").prevAll().size();
				$("#tBaTableFieldListTwo").find("tr").eq(trIndex).find("input").eq(0).val($(this).val());
				$("#tBaTableFieldListThree").find("tr").eq(trIndex).find("input").eq(0).val($(this).val());	
			});
			$trOne.find("input").eq(2).blur(function(){
				var trIndex=$(this).parents("tr").prevAll().size();
				$("#tBaTableFieldListTwo").find("tr").eq(trIndex).find("input").eq(1).val($(this).val());
				$("#tBaTableFieldListThree").find("tr").eq(trIndex).find("input").eq(1).val($(this).val());	
			});
			$trOne.find("span").click(function(){
				var trIndex=$(this).parents("tr").prevAll().size();
				confirmx("是否确认删除?",function(){
					var fieldId = $("#tBaTableFieldListOne").find("tr").eq(trIndex).find(".fieldId").val();
					if(id != ""){
						$.ajax({
				            type: "POST",
				            url:"${ctx}/base/tBaTableConfig/ajaxDeleteTBaTableField",
				            data:{"fieldId":fieldId},	
				           	success:function(data){
				           		
				           }
						});
					}
					$("#tBaTableFieldListOne").find("tr").eq(trIndex).remove();
					$("#tBaTableFieldListTwo").find("tr").eq(trIndex).remove();
					$("#tBaTableFieldListThree").find("tr").eq(trIndex).remove();
				});
			});
			
			$("#tBaTableFieldListOne").append($trOne);
			$("#tBaTableFieldListTwo").append($trTwo);
			$("#tBaTableFieldListThree").append($trThree);
			var trGroup = [$trOne, $trTwo, $trThree];
			return trGroup;
			
			}
			
			var tBaTableFieldList = '${tBaTableFieldList}';
			//console.log(tBaTableFieldList);
			if(tBaTableFieldList!=""){
				var json = eval('('+tBaTableFieldList+')');
				if(json.length > 0){
					for(var j in json){
						var trs=addRowFirst();
						var $oneTr=trs[0];
						var $twoTr=trs[1];
						var $threeTr=trs[2];
						$oneTr.find(".fieldId").val(json[j].id);
						$oneTr.find(".fieldCode").val(json[j].fieldCode);
						$oneTr.find(".fieldCode").each(function(){
							if(json[j].fieldCode == "id"){
								$(this).attr("readonly","readonly");
							}
						});
						$oneTr.find(".fieldName").val(json[j].fieldName);
						$oneTr.find(".fieldLength").val(json[j].fieldLength);
						$oneTr.find(".fieldType").children().each(function(){
							if($(this).val()==json[j].fieldType){
								$(this).attr("selected","selected");
							}
						});
						$oneTr.find(".isNull").children().each(function(){
							if($(this).val()==json[j].isNull){
								$(this).attr("selected","selected");
							}
						});
						
						$twoTr.find(".fieldCodeC").val(json[j].fieldCode);
						$twoTr.find(".fieldNameC").val(json[j].fieldName);
						$twoTr.find(".isShowForm").children().each(function(){
							if($(this).val()==json[j].isShowForm){
								$(this).attr("selected","selected");
							}
						});
						$twoTr.find(".isShowList").children().each(function(){
							if($(this).val()==json[j].isShowList){
								$(this).attr("selected","selected");
							}
						});
						$twoTr.find(".controlType").children().each(function(){
							if($(this).val()==json[j].controlType){
								$(this).attr("selected","selected");
							}
						});
						$twoTr.find(".controlLength").val(json[j].controlLength);
						$twoTr.find(".isQueryCondition").children().each(function(){
							if($(this).val()==json[j].isQueryCondition){
								$(this).attr("selected","selected");
							}
						});
						$twoTr.find(".queryType").children().each(function(){
							if($(this).val()==json[j].queryType){
								$(this).attr("selected","selected");
							}
						});
						
						$threeTr.find(".fieldCodeC").val(json[j].fieldCode);
						$threeTr.find(".fieldNameC").val(json[j].fieldName);
						$threeTr.find(".fieldHref").val(json[j].fieldHref);
						$threeTr.find(".fieldValidRule").val(json[j].fieldValidRule);
						$threeTr.find(".dictTable").val(json[j].dictTable);
						$threeTr.find(".dictCode").val(json[j].dictCode);
						$threeTr.find(".dictText").val(json[j].dictText);
						$threeTr.find(".fartherTable").val(json[j].fartherTable);
						$threeTr.find(".fartherField").val(json[j].fartherField);
						$threeTr.find(".isDatebaseField").children().each(function(){
							if($(this).val()==json[j].isDatebaseField){
								$(this).attr("selected","selected");
							}
						});
						$threeTr.find(".listSql").val(json[j].listSql);
						$threeTr.find(".sort").val(json[j].sort);
						$threeTr.find(".remarks").val(json[j].remarks);
					}
				}
			}
			//else{
				//alert(1111);
				//var trs=addRowFirst();
				//var $oneTr=trs[0];
				//var $twoTr=trs[1];
				//var $threeTr=trs[2];
				//$oneTr.find(".fieldCode").val("create_by");
				//$twoTr.find(".fieldCodeC").val("create_by");
				//$threeTr.find(".fieldCodeC").val("create_by");
				
			//}
			
			if(${!empty tBaTableConfig.id}){
				if(${tBaTableConfig.type eq '03'}){
					$(".relationType").css("display","");
				}else{
					$(".relationType").css("display","none");
				}
			}else{
				$(".relationType").css("display","none");
			}
			$("#type").change(function(){
				var type = $("#type").val();
				if(type=='03'){
					$(".relationType").css("display","");
				}else{
					$("#relationType").val("");
					$(".relationType").css("display","none");
				}
			});
			
			$("#addRow").click(function(){
				addRowFirst();
			});
			
			
			
			$("#inputForm").validate({
				submitHandler: function(form){
					var trOne = $("#tBaTableFieldListOne").find("tr");
					var trTwo = $("#tBaTableFieldListTwo").find("tr");
					var trThree = $("#tBaTableFieldListThree").find("tr");
					for(var i = 0;i < trOne.size();i++){
						trOne.eq(i).find("td").eq(0).find(".fieldId").attr("name","tBaTableFieldList["+i+"].id");
						trOne.eq(i).find("td").eq(0).find(".fieldCode").attr("name","tBaTableFieldList["+i+"].fieldCode");
						trOne.eq(i).find("td").eq(1).find(".fieldName").attr("name","tBaTableFieldList["+i+"].fieldName");
						trOne.eq(i).find("td").eq(2).find(".fieldLength").attr("name","tBaTableFieldList["+i+"].fieldLength");
						trOne.eq(i).find("td").eq(3).find(".fieldType").attr("name","tBaTableFieldList["+i+"].fieldType");
						trOne.eq(i).find("td").eq(4).find(".isNull").attr("name","tBaTableFieldList["+i+"].isNull");
					}
					for(var i = 0;i < trTwo.size();i++){
						trTwo.eq(i).find("td").eq(2).find(".isShowForm").attr("name","tBaTableFieldList["+i+"].isShowForm");
						trTwo.eq(i).find("td").eq(3).find(".isShowList").attr("name","tBaTableFieldList["+i+"].isShowList");
						trTwo.eq(i).find("td").eq(4).find(".controlType").attr("name","tBaTableFieldList["+i+"].controlType");
						trTwo.eq(i).find("td").eq(5).find(".controlLength").attr("name","tBaTableFieldList["+i+"].controlLength");
						trTwo.eq(i).find("td").eq(6).find(".isQueryCondition").attr("name","tBaTableFieldList["+i+"].isQueryCondition");
						trTwo.eq(i).find("td").eq(7).find(".queryType").attr("name","tBaTableFieldList["+i+"].queryType");
					}
					for(var i = 0;i < trThree.size();i++){
						trThree.eq(i).find("td").eq(2).find(".fieldHref").attr("name","tBaTableFieldList["+i+"].fieldHref");
						trThree.eq(i).find("td").eq(3).find(".fieldValidRule").attr("name","tBaTableFieldList["+i+"].fieldValidRule");
						trThree.eq(i).find("td").eq(4).find(".dictTable").attr("name","tBaTableFieldList["+i+"].dictTable");
						trThree.eq(i).find("td").eq(5).find(".dictCode").attr("name","tBaTableFieldList["+i+"].dictCode");
						trThree.eq(i).find("td").eq(6).find(".dictText").attr("name","tBaTableFieldList["+i+"].dictText");
						trThree.eq(i).find("td").eq(7).find(".fartherTable").attr("name","tBaTableFieldList["+i+"].fartherTable");
						trThree.eq(i).find("td").eq(8).find(".fartherField").attr("name","tBaTableFieldList["+i+"].fartherField");
						trThree.eq(i).find("td").eq(9).find(".isDatebaseField").attr("name","tBaTableFieldList["+i+"].isDatebaseField");
						trThree.eq(i).find("td").eq(10).find(".listSql").attr("name","tBaTableFieldList["+i+"].listSql");
						trThree.eq(i).find("td").eq(11).find(".sort").attr("name","tBaTableFieldList["+i+"].sort");
						trThree.eq(i).find("td").eq(12).find(".remarks").attr("name","tBaTableFieldList["+i+"].remarks");
					}
					
					var canCommit = true;
					var reg = /^[a-z_]+$/;
					var regs = /^\d{1,3}$/;
					$(".fieldCode").each(function(){
						if($(this).val() == ""){
							$(this).next().text("*必填项");
							canCommit = false;
						}else if(!reg.test($(this).val())){
							$(this).next().text("*必须小写字母");
							canCommit = false;
						}else{
							$(this).next().text("");
						}
					});
					$(".fieldName").each(function(){
						if($(this).val() == ""){
							$(this).next().text("*必填项");
							canCommit = false;
						}else{
							$(this).next().text("");
						}
					});
					$(".fieldLength").each(function(){
						if($(this).val() == ""){
							$(this).next().text("*必填项");
							canCommit = false;
						}else if(!regs.test($(this).val())){
							$(this).next().text("*1-3位数字");
							canCommit = false;
						}else{
							$(this).next().text("");
						}
					});
					if(canCommit == true){
						loading('正在提交，请稍等...');
						form.submit();
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正!");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

		function changeLi(liIndex){
			$("#liChangeOne").removeClass("active");
			$("#liChangeTwo").removeClass("active");
			$("#liChangeThree").removeClass("active");
			
			$("#divOne").removeClass("active");
			$("#divTwo").removeClass("active");
			$("#divThree").removeClass("active");
			if(liIndex == "01"){
				$("#liChangeOne").addClass("active");
				$("#divOne").addClass("active");
			}else if(liIndex == "02"){
				$("#liChangeTwo").addClass("active");
				$("#divTwo").addClass("active");
			}else if(liIndex == "03"){
				$("#liChangeThree").addClass("active");
				$("#divThree").addClass("active");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/base/tBaTableConfig/">表单配置列表</a></li>
		<li class="active"><a href="${ctx}/base/tBaTableConfig/form?id=${tBaTableConfig.id}">表单配置<shiro:hasPermission name="base:tBaTableConfig:edit">${not empty tBaTableConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="base:tBaTableConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBaTableConfig" action="${ctx}/base/tBaTableConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">表名：</label>
			<div class="controls">
				<input type="text" name="tableName" value="${tBaTableConfig.tableName }" <c:if test="${!empty tBaTableConfig.id }">readonly="readonly"</c:if> />
				<span style="color: red"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">表描述：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">表类型：</label>
			<div class="controls">
				<c:if test="${empty tBaTableConfig.id }">
					<select id="type" name="type" class="input-mini">
						<c:forEach items="${fns:getDictList('type')}" var="dict">
                        	<option value="${dict.value}">${dict.label}</option>
                        </c:forEach>
					</select>
				</c:if>
				<c:if test="${!empty tBaTableConfig.id }">
					<select id="type" name="type" class="input-mini">
						<c:forEach items="${fns:getDictList('type')}" var="dict">
                        	<option value="${dict.value}" <c:if test="${tBaTableConfig.type eq dict.value }">selected="selected"</c:if>>${dict.label}</option>
                        </c:forEach>
					</select>
				</c:if>
			</div>
		</div>
		<div class="control-group relationType">
			<label class="control-label">表对应关系:</label>
			<div class="controls">
				<c:if test="${empty tBaTableConfig.id }">
					<select id="relationType" name="relationType">
						<option value="">请选择</option>
						<option value="01">一对多</option>
						<option value="02">一对一</option>
					</select>
				</c:if>
				<c:if test="${!empty tBaTableConfig.id }">
					<select id="relationType" name="relationType">
						<option value="">请选择</option>
						<option value="01" <c:if test="${tBaTableConfig.relationType eq '01' }">selected="selected"</c:if>>一对多</option>
						<option value="02" <c:if test="${tBaTableConfig.relationType eq '02' }">selected="selected"</c:if>>一对一</option>
					</select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否分页：</label>
			<div class="controls">
				<c:if test="${empty tBaTableConfig.id }">
					<select id="isPagination" name="isPagination" class="input-mini">
						<c:forEach items="${fns:getDictList('yes_or_no')}" var="dict">
                             <option value="${dict.value}">${dict.label}</option>
                        </c:forEach>
					</select>
				</c:if>
				<c:if test="${!empty tBaTableConfig.id }">
					<select id="isPagination" name="isPagination" class="input-mini">
						<c:forEach items="${fns:getDictList('yes_or_no')}" var="dict">
                             <option value="${dict.value}" <c:if test="${tBaTableConfig.isPagination eq dict.value }">selected="selected"</c:if>>${dict.label}</option>
                        </c:forEach>
					</select>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">表单字段信息：</label>
				<div class="controls">
					<ul class="nav nav-tabs">
      					<li class="active" id="liChangeOne" onclick="changeLi('01')">
		             		<a href="#divOne" data-toggle="tab" style="font-weight: bold;">数据库属性</a>
		        		</li>
						<li id="liChangeTwo" onclick="changeLi('02')">
		             		<a href="#divTwo" data-toggle="tab" style="font-weight: bold;">页面属性</a>
		        		</li>
						<li id="liChangeThree" onclick="changeLi('03')">
		             		<a href="#divThree" data-toggle="tab" style="font-weight: bold;">扩展属性</a>
		        		</li>
      				</ul>
      			</div>
				<div class="controls">
					<div class="tab-content">
						<div class="tab-pane active" id="divOne">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th class="hide"></th>
									<th>字段编码</th>
									<th>字段名称</th>
									<th>字段长度</th>
									<th>字段类型</th>
									<th>允许空值</th>
									<shiro:hasPermission name="base:tBaTableConfig:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
								</tr>
							</thead>
							<tbody id="tBaTableFieldListOne">
							</tbody>
							<shiro:hasPermission name="base:tBaTableConfig:edit"><tfoot>
								<tr><td colspan="23"><a id="addRow" class="btn">新增</a></td></tr>
							</tfoot></shiro:hasPermission>
					</table>
				</div>
			</div>
			<div class="tab-content">
						<div class="tab-pane" id="divTwo">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th class="hide"></th>
									<th>字段编码</th>
									<th>字段名称</th>
									<th>表单显示</th>
									<th>列表显示</th>
									<th>控件类型</th>
									<th>控件长度</th>
									<th>是否查询条件</th>
									<th>查询类型</th>
								</tr>
							</thead>
							<tbody id="tBaTableFieldListTwo">
							</tbody>
					</table>
				</div>
			</div>
			<div class="tab-content">
						<div class="tab-pane" id="divThree">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th class="hide"></th>
									<th>字段编码</th>
									<th>字段名称</th>
									<th>字段href</th>
									<th>校验规则</th>
									<th>字典table</th>
									<th>字典code</th>
									<th>字典text</th>
									<th>主表名</th>
									<th>主表字段</th>
									<th>是否数据库字段</th>
									<th>列表自定义sql</th>
									<th>排序</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="tBaTableFieldListThree">
							</tbody>
					</table>
				</div>
			</div>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="base:tBaTableConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>