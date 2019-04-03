<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Pdf合成Demo</title>
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
     <br/>
	<form:form id="inputForm"  action="${ctx}//demo/form" method="post" class="form-horizontal">
		<h3>B/S系统实现的QQ弹窗</h3> <br />
		
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
				    <th>QQ类型</th>
				    <th>交流方式</th>
				    <th>是否需要登录QQ</th>
				    <th>发起网页聊天</th>
				    <th>客户端弹出对话框</th>
				    <th>存在的问题</th>
				    <th>收费情况</th>
				    <th>示例</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>普通QQ（不弹出新页面）</td>
					<td>客户端弹出对话框</td>
					<td>需要</td>
					<td><font color="red">不可以</font></td>
					<td>可以</td>
					<td>A作为服务QQ，删除的好友，不能再接收删除的QQ临时信息</td>
					<td>免费</td>
					<td>
					    <a href="tencent://message/?uin=3491085076&Site=&menu=yes">
		                    <img border="0" src="http://wpa.qq.com/pa?p=2:854325871:53" 
		                        alt="QQ交谈" title="QQ交谈"/>
		                </a>
                    </td>
				</tr>
				<tr>
					<td>普通QQ（弹出新页面）</td>
					<td>客户端弹出对话框/发起网页聊天</td>
					<td>需要</td>
					<td><font color="red">不可以</font></td>
					<td>可以</td>
					<td>客户端弹出对话框存在问题，同上。发起网页聊天，信息无法发送</td>
					<td>免费</td>
					<td>
					    <a target="_blank" 
				            href="http://wpa.qq.com/msgrd?v=3&uin=3491085076&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:854325871:51" 
				            alt="QQ交谈" title="QQ交谈"/>
				        </a>
                    </td>
				</tr>
				<tr>
					<td>企业QQ（弹出新页面）</td>
					<td>发起网页聊天</td>
					<td>不需要</td>
					<td>可以</td>
					<td><font color="red">不可以</font></td>
					<td>无</td>
					<td>收费</td>
					<td>
					    <a target="_blank" 
					       href=" http://wpd.b.qq.com/page/webchat.html?nameAccount=4000035591">
					        <img border="0" src="http://wpa.qq.com/pa?p=2:854325871:52" 
					             alt="企业QQ" title="企业QQ"/>
					    </a>
                    </td>
				</tr>
				<tr>
					<td>企业QQ（不弹出新页面）</td>
					<td>客户端弹出对话框</td>
					<td>需要</td>
					<td><font color="red">不可以</font></td>
					<td><font color="red">不可以</font>（理论上可以）</td>
					<td>实现需要加密的编码tencent://message/?Menu=yes&uin=938062523&Service=58&SigT=?&SigU=? 否则提示“QQ在线状态”</td>
					<td>收费</td>
					<td>
					   &nbsp;失败的：<br />
					    <a href="tencent://message/?Menu=yes&uin=938062523&Service=58"><img border="0" src="http://wpa.qq.com/pa?p=2:854325871:51" 
				            alt="QQ交谈" title="QQ交谈"/>
				        </a><br />
				       &nbsp;官网的唯一性链接：<br />
				        <a href="tencent://message/?Menu=yes&uin=938062523&Service=58&SigT=46139A4FBB03CEFD6F731DA59E2C493B46B4ADADBB4E5C91F9671604591B46BF015E8FBF6896F36DDEF51456B90AE0A0DED2CA98FA052ED5A0062F0CA6124DCD964468141CD8A25E1FAA4ED7FA57D28F6B99081C81C47B9FF53026842A6929BEB6BB7C2061FA2CEABAB843BD15F1DB2F9F39901B35954F8A&SigU=C23704C1EB4957A1D7E2A85F3B29365CEAEB1611510A19679B02115B39D3B1FE50DB0584A87AB3AE083C5CC9BCD832BF09277E5C48A2737A40E056C8E83557E5F73531D8AF52868A"><img border="0" src="http://wpa.qq.com/pa?p=2:854325871:51" 
				            alt="QQ交谈" title="QQ交谈"/>
				        </a>
                    </td>
				</tr>
			</tbody>
	   </table>
	   <font color="red">说明：A为系统QQ号，B为游客QQ号。</font>
	</form:form>
</body>
</html>