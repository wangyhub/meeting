<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新闻动态管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/kindeditor-4.1.6/themes/default/default.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctxStatic}/kindeditor-4.1.6/kindeditor-all-min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/kindeditor-4.1.6/lang/zh_CN.js"></script>

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

        var editor;
        KindEditor.ready(function(K) {
            editor = K.create('textarea[name="content"]', {
                filePostName: "imgFile",
                uploadJson: '${ctx}/meeting/news/tbNews/uploadJson',
                afterUpload: function(){this.sync();}, //图片上传后，将上传内容同步到textarea中

                // afterUpload : function(url, data, name) { //上传文件后执行的回调函数，必须为3个参数
                //     if(name=="image" || name=="multiimage"){ //单个和批量上传图片时
                //         var img = new Image();
                //         img.src = url;
                //         img.onload = function(){ //图片必须加载完成才能获取尺寸
                //             if(img.width>800) editor.html(editor.html().replace('<img src="' + url + '"','<img src="' + url + '" width="800"'))
                //         }
                //     }
                // },

                afterBlur: function(){this.sync();},   ////失去焦点时，将上传内容同步到textarea中
                allowImageRemote: false,
                allowImageUpload:true,
                allowFileManager: true,

                items:['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                    'table', 'hr', 'emoticons', 'pagebreak',
                    'anchor', 'link', 'unlink'] //配置kindeditor编辑器的工具栏菜单项
            });

        });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/meeting/news/tbNews/">新闻动态列表</a></li>
		<li class="active"><a href="${ctx}/meeting/news/tbNews/form?id=${tbNews.id}">新闻动态${not empty tbNews.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tbNews" action="${ctx}/meeting/news/tbNews/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" id="status" name="status" value="${tbNews.status==null?0:tbNews.status}"/>
<table width = "100%">
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">标题：</label>
				<div class="controls">
					<form:input path="title" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">发布人员：</label>
				<div class="controls">
					<form:input path="issuer" htmlEscape="false" maxlength="100" class="input-small "/>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">新闻时间：</label>
				<div class="controls">
					<input name="newsTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						   value="<fmt:formatDate value="${tbNews.newsTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
		<td width = "50%">
			<div class="control-group">
				<label class="control-label">是否置顶：</label>
				<div class="controls">
					<form:radiobuttons path="isTop" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font size="4" color="red">*</font> </span>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "100%" colspan="2">
			<div class="control-group">
				<label class="control-label">内容：</label>
				<div class="controls">
					<form:textarea path="content" htmlEscape="false" rows="4" maxlength="4000" style="width: 1000px; height: 400px;"/>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td width = "100%" colspan="2" align="center">
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