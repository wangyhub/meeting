<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>动态查询管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic }/breadmenu/css/style.css" rel="stylesheet" type="text/css" />
    <script src="${ctxStatic}/jquery-plugin/tablemerge.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctxStatic }/breadmenu/scroller.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#contentTable .select2-container").remove();//框架自带的select样式有问题
			$("#contentTable").find("select").attr("class", "");
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
		
		//sql进行解析
		function checkIsSql(){        
		    var flag = true;
		    var value = $("#headSql").val();
            $.ajax({
                url:'${ctx}/base/tBaMainSql/checkIsSql',
                async:false, 
                type:"post",
                data:{"headSql":value},
                dataType:"json",
                success:function(result){
                	if(result.success == "0"){
                		top.$.jBox.tip("该SQL语句无法解析，请重新录入！", "warning");
                    }else {
                        var fieldArray = result.msgArray;
                        $(".addData").remove();//删除表格下所有的新增的数据
                        //进行行添加操作
                        for(var i =  0; i < fieldArray.length; i++){
                        	//alert(fieldArray[i].codeName + " || " + fieldArray[i].physicsType + " || " + fieldArray[i].physicsLength + "||" + fieldArray[i].fieldSort);
                        	var newTr = $("#copyTr").clone(true);
                            newTr.show();
                            newTr.attr("class", "addData");
                            newTr.attr("id", "");
                            
                            //编码名称
                            newTr.find("#codeName").val(fieldArray[i].codeName);
                            newTr.find("#codeName").attr("name", "fieldList[" + i + "].codeName");
                            newTr.find("#codeName").attr("id", "fieldList[" + i + "]CodeName");
                            
                            //排序
                            newTr.find("#fieldSort").val(fieldArray[i].fieldSort);
                            newTr.find("#fieldSort").attr("name", "fieldList[" + i + "].fieldSort");
                            newTr.find("#fieldSort").attr("id", "fieldList[" + i + "]FieldSort");
                            
                            //物理类型
                            newTr.find("#psLabel").html(fieldArray[i].physicsType + "(" + fieldArray[i].physicsLength + ")");
                            newTr.find("#psLabel").attr("id", "fieldList[" + i + "]PsLabel");
                            newTr.append("<input type='hidden' name='fieldList[" + i + "].physicsType' value='" + fieldArray[i].physicsType + "'/>");
                            newTr.append("<input type='hidden' name='fieldList[" + i + "].physicsLength' value='" + fieldArray[i].physicsLength + "'/>");
                            
                            //文本框
                            newTr.find("#fieldInput").val(fieldArray[i].codeName);
                            newTr.find("#fieldInput").attr("name", "fieldList[" + i + "].fieldInput");
                            newTr.find("#fieldInput").attr("id", "fieldList[" + i + "]FieldInput");
                            
                            //类型
                            //newTr.find("#fieldType").val(fieldArray[i].codeName);
                            newTr.find("#fieldType").attr("name", "fieldList[" + i + "].fieldType");
                            newTr.find("#fieldType").attr("id", "fieldList[" + i + "]FieldType");
                            
                            //是否显示
                            //newTr.find("#isShow").val(fieldArray[i].codeName);
                            newTr.find("#isShow").attr("name", "fieldList[" + i + "].isShow");
                            newTr.find("#isShow").attr("id", "fieldList[" + i + "]IsShow");
                            
                            //字段href
                            //newTr.find("#fieldHref").val(fieldArray[i].codeName);
                            newTr.find("#fieldHref").attr("name", "fieldList[" + i + "].fieldHref");
                            newTr.find("#fieldHref").attr("id", "fieldList[" + i + "]FieldHref");
                            
                            //查询模式
                            //newTr.find("#fieldInput").val(fieldArray[i].codeName);
                            newTr.find("#selectModel").attr("name", "fieldList[" + i + "].selectModel");
                            newTr.find("#selectModel").attr("id", "fieldList[" + i + "]SelectModel");
                            
                            //类型
                            //newTr.find("#fieldInput").val(fieldArray[i].codeName);
                            newTr.find("#selectType").attr("name", "fieldList[" + i + "].selectType");
                            newTr.find("#selectType").attr("id", "fieldList[" + i + "]SelectType");
                            
                            //字典code
                            //newTr.find("#fieldInput").val(fieldArray[i].codeName);
                            newTr.find("#dictCode").attr("name", "fieldList[" + i + "].dictCode");
                            newTr.find("#dictCode").attr("id", "fieldList[" + i + "]DictCode");
                            
                            //是否查询
                            newTr.find("#isSelect").attr("name", "fieldList[" + i + "].isSelect");
                            newTr.find("#isSelect").attr("id", "fieldList[" + i + "]IsSelect");
                            
                            newTr.appendTo("#contentTable");
                        }
                    }
                }   
            });
            
		}
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/base/tBaMainSql/">动态查询列表</a></li>
		<li class="active"><a href="${ctx}/base/tBaMainSql/mainSqlform?id=${tBaMainSql.id}">${not empty tBaMainSql.id?'动态查询修改':'动态查询添加'}</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="TBaMainSql" action="${ctx}/base/tBaMainSql/saveMainSql" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="padding-top: 8px;background-color:#f5f5f5;">
	        <div class="span5" style="width: 100%">
	            <font style="font-weight: 600;">基本信息配置</font>
	        </div>
	    </div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SQL语句：</label>
			<div class="controls">
				<form:textarea path="headSql" htmlEscape="false" rows="4" maxlength="400" class="input-xxlarge required checkIsSql"/>
			    &nbsp;&nbsp;
			    <input id="btnSql" style="margin-top: 70px;" class="btn btn-primary" type="button" value="sql解析" onclick="checkIsSql()"/>&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="400" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group" style="padding-top: 8px;background-color:#f5f5f5;">
            <div class="span5">
                <font style="font-weight: 600;">动态报表配置明细</font>
            </div>
        </div>
        <table id="contentTable" class="table table-striped table-bordered table-condensed" >
	        <thead>
                <tr>
                    <th width="8%">编码名称</th>
                    <th width="5%">排序</th>
                    <th width="5%">物理类型</th>                               
                    <th width="8%">文本框</th>
                    <th width="8%">类型</th>
                    <th width="6%">是否显示</th>
                    <th width="8%">字段href</th>
                    <th width="8%">查询模式</th>
                    <th width="8%">类型</th>
                    <th width="8%">字典code</th>
                    <th width="6%">是否查询</th>
                </tr>
            </thead>
	          
            <tr id="copyTr" style="display: none;">
				<td><input id="codeName" type="text" style="width: 90px;" />
				</td>
				<td><input id="fieldSort" type="text" style="width: 30px;" />
				</td>
				<td><label id="psLabel"></label>
				</td>
				<td><input id="fieldInput" type="text" style="width: 90px;" />
				</td>
				<td>
                    <select id="fieldType" style="width: 90px;">
                        <option value="1">字符串类型</option>
                        <option value="2">日期类型</option>
                    </select>
                </td>
                <td>
                    <select id="isShow" style="width: 50px;" >
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </td>
                <td><input id="fieldHref" type="text" style="width: 90px;"/></td>
                <td>
                    <select id="selectModel" style="width: 90px;">
                        <option value="1">精确查询</option>
                        <option value="2">模糊查询</option>
                        <option value="2">范围查询</option>
                    </select>
                </td>
                <td><input id="selectType" type="text" style="width: 90px;"/></td>
                <td><input id="dictCode" type="text" style="width: 90px;"/></td>
                <td>
                    <select id="isSelect" style="width: 50px;">
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </td>
            </tr>
            
            <c:forEach items="${tBaMainSql.fieldList}" var="fieldObj" varStatus="fieldIndex">
                <tr  class="addData">
	                <td>
	                    <input id="fieldList[${fieldIndex.index}]CodeName" type="text" value="${fieldObj.codeName}"
	                        style="width: 90px;" name="fieldList[${fieldIndex.index}].codeName"/>
	                </td>
					<td>
					    <input id="fieldList[${fieldIndex.index}]FieldSort" type="text" value="${fieldObj.fieldSort}"
					        style="width: 30px;" name="fieldList[${fieldIndex.index}].fieldSort"/>
					</td>
					<td>
					    <label id="fieldList[${fieldIndex.index}]PsLabel">${fieldObj.physicsType}(${fieldObj.physicsLength})</label>
					    <input type="hidden" name="fieldList[${fieldIndex.index}].physicsType" value="${fieldObj.physicsType}" />
					    <input type="hidden" name="fieldList[${fieldIndex.index}].physicsLength" value="${fieldObj.physicsLength}" />
					</td>
					<td>
					    <input id="fieldList[${fieldIndex.index}]FieldInput" type="text" value="${fieldObj.fieldInput}"
					        style="width: 90px;" name="fieldList[${fieldIndex.index}].fieldInput"/>
					</td>
					<td>
	                    <select id="fieldList[${fieldIndex.index}]FieldType" style="width: 90px;" name="fieldList[${fieldIndex.index}].fieldType">
	                        <option value="1" <c:if test="${fieldObj.fieldType == '1'}">selected="selected"</c:if>>字符串</option>
	                        <option value="2" <c:if test="${fieldObj.fieldType == '2'}">selected="selected"</c:if>>日期类型</option>
	                    </select>
	                </td>
	                <td>
	                    <select id="fieldList[${fieldIndex.index}]IsShow" style="width: 50px;" name="fieldList[${fieldIndex.index}].isShow">
	                        <option value="1" <c:if test="${fieldObj.isShow == '1'}">selected="selected"</c:if>>是</option>
	                        <option value="2" <c:if test="${fieldObj.isShow == '2'}">selected="selected"</c:if>>否</option>
	                    </select>
	                </td>
	                <td>
	                    <input id="fieldList[${fieldIndex.index}]FieldHref" type="text"  value="${fieldObj.fieldHref}"
	                        style="width: 90px;" name="fieldList[${fieldIndex.index}].fieldHref"/>
	                </td>
	                <td>
	                    <select id="fieldList[${fieldIndex.index}]SelectModel" style="width: 90px;" name="fieldList[${fieldIndex.index}].selectModel">
	                        <option value="1" <c:if test="${fieldObj.selectModel == '1'}">selected="selected"</c:if>>精确查询</option>
	                        <option value="2" <c:if test="${fieldObj.selectModel == '2'}">selected="selected"</c:if>>模糊查询</option>
	                        <option value="3" <c:if test="${fieldObj.selectModel == '3'}">selected="selected"</c:if>>范围查询</option>
	                    </select>
	                </td>
					<td>
					    <input id="fieldList[${fieldIndex.index}]SelectType" type="text" style="width: 90px;" value="${fieldObj.selectType}"
					        name="fieldList[${fieldIndex.index}].selectType"/>
					</td>
					<td><input id="fieldList[${fieldIndex.index}]DictCode" type="text" style="width: 90px;" value="${fieldObj.dictCode }"
					        name="fieldList[${fieldIndex.index}].dictCode"/>
					</td>
					<td>
	                    <select id="fieldList[${fieldIndex.index}]IsSelect" style="width: 50px;" name="fieldList[${fieldIndex.index}].isSelect">
	                        <option value="1" <c:if test="${fieldObj.isSelect == '1'}">selected="selected"</c:if>>是</option>
	                        <option value="2" <c:if test="${fieldObj.isSelect == '2'}">selected="selected"</c:if>>否</option>
	                    </select>
	                </td>
                </tr>
            </c:forEach>
	    </table>
        
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>