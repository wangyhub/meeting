<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>功能测试录入</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".liTabSon .select2-container").remove();//框架自带的select样式有问题
			$(".liTabSon").find("select").removeClass();
			$(".liTabSon").find("select").addClass("dd");
			$("#btnCancel").click(function(){
				window.location.href="${ctx}/base/tBaPublicBean/?tableConfigId=${tableConfigId}";
			});
			function copyIt(){
				var copyTable = $(".copyTab").find("table").clone(true);
				$(".liTab").append(copyTable);
			}
			var sonListSize =  parseInt('${sonListSize}');
			if(sonListSize == 0){
				copyIt();
				$(".liTab").find("table").addClass("table table-striped table-bordered table-condensed");
			}
			$("#addTable").click(function(){
				copyIt();
				$(".liTab").find("table").addClass("table table-striped table-bordered table-condensed");
			});
			
			
			$(".deleteIt").each(function(){
				$(this).click(function(){
					var sontableId = $("input[name='sonTableId']").val();
					var sonId = $(this).next().val();
					var thisTable = $(this).parents("table");
					if(sonId == null){
						thisTable.remove();
					}else{
						confirmx("是否删除该条数据?",function(){
							thisTable.remove();
							$.ajax({
					            type: "POST",
					            url:"${ctx}/base/tBaPublicBean/ajaxDeleteSonTable",
					            data:{"id":sonId,"configTableId":sontableId},
					           	success:function(data){
					           }
							});
					 	});
					}
				});
			});
			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var i = 0;
					var sonCondList = '${sonCondList}';
					var json = eval('('+sonCondList+')');
					$(".liTab").find("table").each(function(){
						var j = 0;
						$(this).find("tr").find("td.tdd").each(function(){
							$(this).children().attr("name","tbaBaSonPublicBeanList["+i+"]."+json[j]);
							j++;
						});
						$(this).find("tr").find("td.tdd").each(function(){
							$(this).children("div").attr("name","");
						});
						
							i++;
					});
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
		    <a href="${ctx}/base/tBaPublicBean/?tableConfigId=${tableConfigId}">功能测试列表</a>
		</li>
		<li class="active">
		    <a href="${ctx}/base/tBaPublicBean/form?id=${tBaParameter.id}">功能测试<shiro:hasPermission 
		        name="base:tBaParameter:edit">${not empty tBaPublicBean.str1?'修改':'添加'}
		        </shiro:hasPermission>
		        <shiro:lacksPermission name="base:tBaParameter:edit">
		                               查看
		        </shiro:lacksPermission>
		    </a>
	    </li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="TBaPublicBean" action="${ctx}/base/tBaPublicBean/save?tableConfigId=${tableConfigId}" 
	    method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<c:forEach items="${tBaTableFieldList }" var="list" varStatus="status">
			<div class="control-group" <c:if test="${list.isShowForm eq '02' }">style="display: none;"</c:if>>
				<label class="control-label">${list.fieldName}：</label>
				<div class="controls">
					<c:set var="code" value="${list.fieldCode }"/>
					<c:set var="strings" value="${condList[status.index]}" />
					<c:if test="${list.controlType eq '01' }">
						<c:if test="${!empty list.fartherTable and !empty list.fartherField }">
							<select name="${strings }" class="input-mini">
								<c:forEach items="${fartherList}" var="fartherTable">
	                        		<option value="${fartherTable.str1}" <c:if test="${tBaPublicBean[strings] eq fartherTable.str1 }">selected="selected"</c:if>>${fartherTable.str2}</option>
	                       		</c:forEach>
							</select>
						</c:if>
						<c:if test="${empty list.fartherTable and empty list.fartherField }">
							<input type="text" name="${strings }" value="${tBaPublicBean[strings] }"/>
						</c:if>
					</c:if>
					<c:if test="${list.controlType eq '02' }">
						<input type="password" name="${strings }" value="${tBaPublicBean[strings] }"/>
					</c:if>
					<c:if test="${list.controlType eq '04' }">
						<c:forEach items="${fns:getDictList(code)}" var="dict">
							<input type="checkbox" name="${strings }" value="${dict.value}" <c:if test="${tBaPublicBean[strings] eq dict.value }">checked="checked"</c:if>/>${dict.label}
	                    </c:forEach>
					</c:if>
					<c:if test="${list.controlType eq '03' }">
						<c:forEach items="${fns:getDictList(code)}" var="dict">
							<input type="radio" name="${strings }" value="${dict.value}" <c:if test="${tBaPublicBean[strings] eq dict.value }">checked="checked"</c:if>/>${dict.label}
	                    </c:forEach>
					</c:if>
					<c:if test="${list.controlType eq '05' or list.controlType eq '06' }">
						<input name="${strings }" type="text" readonly="readonly"  class="input-medium Wdate endDate required"
							value="<fmt:formatDate value="${tBaPublicBean[strings]}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</c:if>
					<c:if test="${list.controlType eq '07' }">
						<sys:uploadfile id="${strings }file" value="${tBaPublicBean[strings]}" name="${strings }" kind="single" filetype="doc,pdf,jpg,jpeg" buttonName="附件上传"></sys:uploadfile>
					</c:if>
					<c:if test="${list.controlType eq '08' }">
						<textarea name="${strings }">${tBaPublicBean[strings] }</textarea>
					</c:if>
					<c:if test="${list.controlType eq '09' }">
						<select name="${strings }" class="input-mini">
							<c:forEach items="${fns:getDictList(code)}" var="dict">
	                        	<option value="${dict.value}" <c:if test="${tBaPublicBean[strings] eq dict.value }">selected="selected"</c:if>>${dict.label}</option>
	                        </c:forEach>
						</select>
					</c:if>
				</div>
			</div>
		</c:forEach>
		<c:if test="${!empty sonTableInfoList }">
			<c:forEach items="${sonTableInfoList }" var="sonTable">
				${sonTable.content} 
				<input name="sonTableId" value="${sonTable.id}" type="hidden"/>
				<c:if test="${sonTable.relationType eq '01' }">
					<input id="addTable" type="button" class="btn btn-primary" value="增加"/>
				</c:if>
				<div class="controls copyTab" style="display: none;">
					<table class="liTabSon" class="table table-striped table-bordered table-condensed">
						<tr>
							<c:forEach items="${sonTable.TBaTableFieldList}" var="fieldColmun" varStatus="status">
								<td <c:if test="${fieldColmun.isShowForm eq '02' }">style="display: none;"</c:if>>
									${fieldColmun.fieldName}：
									<c:set var="sonCode" value="${fieldColmun.fieldCode }"/>
								</td>
								<td <c:if test="${fieldColmun.isShowForm eq '02' }">style="display: none;"</c:if> class="tdd">
									<c:if test="${fieldColmun.controlType eq '01' }">
										<input type="text" class="dd"/>
									</c:if>
									<c:if test="${fieldColmun.controlType eq '02' }">
										<input type="password"/>
									</c:if>
									<c:if test="${fieldColmun.controlType eq '04' }">
										<c:forEach items="${fns:getDictList(sonCode)}" var="dict">
											<input type="checkbox" value="${dict.value}"/>${dict.label}
					                    </c:forEach>
									</c:if>
									<c:if test="${fieldColmun.controlType eq '03' }">
										<c:forEach items="${fns:getDictList(sonCode)}" var="dict">
											<input type="radio" value="${dict.value}"/>${dict.label}
					                    </c:forEach>
									</c:if>
									<c:if test="${fieldColmun.controlType eq '05' or fieldColmun.controlType eq '06' }">
										<input type="text" readonly="readonly"  class="input-medium Wdate endDate required"
										value="<fmt:formatDate value="${time_time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
									</c:if>
									<c:if test="${fieldColmun.controlType eq '08' }">
										<textarea></textarea>
									</c:if>
									<c:if test="${fieldColmun.controlType eq '09' }">
										<select style="width: 100px;">
											<c:forEach items="${fns:getDictList(sonCode)}" var="dict">
					                        	<option value="${dict.value}">${dict.label}</option>
					                        </c:forEach>
										</select>
									</c:if>
								</td>
							</c:forEach>
							<c:if test="${sonTable.relationType eq '01' }">
								<td><a class="deleteIt">删除</a></td>
							</c:if>
						</tr>
					</table>
				</div>
			</c:forEach>
			
			<div class="controls liTab">
				<c:if test="${!empty sonList }">
					<c:set var="sonTable" value="${sonTableInfoList}"/>
					<c:forEach items="${sonList }" var="sonList" varStatus="sts">
						<div>
							<table class="table table-striped table-bordered table-condensed">
								<tr>
									<c:forEach items="${sonTable[0].TBaTableFieldList}" var="fieldColmun" varStatus="status">
										<c:set var="soncon" value="${sonconList[status.index]}"/>
										<td <c:if test="${fieldColmun.isShowForm eq '02' }">style="display: none;"</c:if>>
											${fieldColmun.fieldName}：
											<c:set var="sonCode" value="${fieldColmun.fieldCode }"/>
										</td>
										<td <c:if test="${fieldColmun.isShowForm eq '02' }">style="display: none;"</c:if> class="tdd">
											<c:if test="${fieldColmun.controlType eq '01' }">
												<input type="text" value="${sonList[soncon] }"/>
											</c:if>
											<c:if test="${fieldColmun.controlType eq '02' }">
												<input type="password" value="${sonList[soncon] } "/>
											</c:if>
											<c:if test="${fieldColmun.controlType eq '04' }">
												<c:forEach items="${fns:getDictList(sonCode)}" var="dict">
													<input type="checkbox" value="${dict.value}" <c:if test="${sonList[soncon] eq dict.value }">checked="checked"</c:if>/>${dict.label}
							                    </c:forEach>
											</c:if>
											<c:if test="${fieldColmun.controlType eq '03' }">
												<c:forEach items="${fns:getDictList(sonCode)}" var="dict">
													<input type="radio" value="${dict.value}" <c:if test="${sonList[soncon] eq dict.value }">checked="checked"</c:if>/>${dict.label}
							                    </c:forEach>
											</c:if>
											<c:if test="${fieldColmun.controlType eq '05' or fieldColmun.controlType eq '06' }">
												<input type="text" readonly="readonly"  class="input-medium Wdate endDate required "
												value="<fmt:formatDate value="${sonList[soncon]}" pattern="yyyy-MM-dd HH:mm:ss"/>"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
											</c:if>
											<c:if test="${fieldColmun.controlType eq '08' }">
												<textarea>${sonList[soncon]}</textarea>
											</c:if>
											<c:if test="${fieldColmun.controlType eq '09' }">
												<select style="width: 100px;">
													<c:forEach items="${fns:getDictList(sonCode)}" var="dict">
							                        	<option value="${dict.value}" <c:if test="${sonList[soncon] eq dict.value }">selected="selected"</c:if>>${dict.label}</option>
							                        </c:forEach>
												</select>
											</c:if>
										</td>
									</c:forEach>
									<c:if test="${sonTable[0].relationType eq '01' }">
										<td>
											<a class="deleteIt">删除</a>
											<input type="hidden" value="${sonList.str1 }"/>
										</td>
									</c:if>
								</tr>
							</table>
						</div>
					</c:forEach>
			</c:if>
			</div>
			</div>
			
		</c:if>
		
		<div class="form-actions">
			<shiro:hasPermission name="base:tBaParameter:edit">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			    &nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"/>
		</div>
	</form:form>
</body>
</html>