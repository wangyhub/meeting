<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>注册管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
                rules: {
                    orgCode: {remote: "${ctx}/base/tBaRegistered/checkOrgCode"},
                    name: {remote: "${ctx}/base/tBaRegistered/checkName"},
                    username:{remote: "${ctx}/base/tBaRegistered/checkUserName?flag=0"}
                },
                messages: {
                    orgCode: {remote: "组织机构代码已存在！"},
                    name: {remote: "机构名称已存在！"},
                    username:{remote:"机构账号已存在！"}
                },
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
			
			$("#btnMenu").hide();// 注册页面隐藏菜单按钮
			
			// 发送验证码的手机号码
			jQuery.validator.addMethod("mobilePhone", 
                function(value, element) {          
                    var flag = true;
                    var reg= /^[1][3578]\d{9}$/; //验证手机号码  
                    if(!reg.test(value)){
                       flag = false;
                    }
                    return flag;
                }, 
                "手机号码格式错误！"
            );
            
            // 验证码
            jQuery.validator.addMethod("checkcode", 
                function(value, element) {          
                    var flag = true;
                    var checkCode = $("#checkcode").val();
                    var phone = $("#phone").val();
                    $.ajax({
                        url:'${ctx}/base/tBaRegistered/checkCode',
                        async:false, 
                        type:"post",
                        data:{"code":checkCode,"phone":phone},
                        dataType:"json",
                        success:function(data){
	                        if(data == 0){
	                            flag = true;
	                        }else if(data == 2){
	                            flag = false;
	                        }else if(data==1){
	                            //未被注册
	                            flag = false;
	                        }
                        }   
                    });
                    return flag;
                }, 
                "验证码错误！"
            );
            
            // 机构账号
            jQuery.validator.addMethod("username", 
                function(value, element) {          
                    var flag = true;
                    var reg = /^[A-Za-z0-9]+$/;
                    if(!reg.test(value)){
                        flag = false;
                    }
                    return flag;
                }, 
                "用户名只能是数字或者英文！"
            );
            
            // 密码
            jQuery.validator.addMethod("password", 
                function(value, element) {          
                    var flag = true;
                    var reg = /^[A-Za-z0-9]+$/;
                    var password = $("#password").val();
                    var crimpassword = $("#pwdAgain").val();
                    if(password != crimpassword){
                        $("#pwdAgain").val("");
                    }
                    if(!reg.test(value)){
                        flag = false;
                    }
                    return flag;
                }, 
                "密码只能是数字或者英文！"
            );
            
            // 确认密码
            jQuery.validator.addMethod("pwdAgain", 
                function(value, element) {          
                    var flag = true;
                    var pwd = $("#password").val();
                    if("" != pwd && value != pwd){
                        flag = false;
                    }
                    return flag;
                }, 
                "两次密码不一致！"
            );
		});
		
		// 发送短信验证码
        var InterValObj;    // timer变量，控制时间  
        var count = 120;    // 间隔函数，1秒执行  
        var curCount;       // 当前剩余秒数  
        var code = "";      // 验证码  
        var codeLength = 6; // 验证码长度
        
        //timer处理函数  
        function SetRemainTime(){  
            if(curCount == 0){                  
                window.clearInterval(InterValObj);//停止计时器  
                $("#sendSmsBtn").removeAttr("disabled");//启用按钮  
                $("#sendSmsBtn").val("重新发送验证码");  
                code="";//清除验证码。如果不清除，过时间后，输入收到的验证码依然有效  
            }else{  
                curCount--;  
                $("#sendSmsBtn").val("请在" + curCount + "秒内输入验证码");  
            }  
        }  
        
		// 获取验证码处理
		function getCode() {
		    curCount = count;  
		    var mobilePhone = $("#phone").val();
		    var reg= /^[1][3578]\d{9}$/; //验证手机号码  
		    if("" == mobilePhone) {
                  // 触发提醒输入提示信息
                  $("#phone").val("checkPhoneNotNull");
                  $("#phone").focus();
                  $("#phone").blur();
                  $("#phone").val("");
            }
            if(!reg.test(mobilePhone)) {
               $("#phone").focus();
               $("#phone").blur();
               return false;
            }
            for( var i = 0; i < codeLength; i++){  
                code += parseInt(Math.random() * 9).toString();  
            }
            //设置button效果，开始计时  
            $("#sendSmsBtn").attr("disabled", "true");  
            $("#sendSmsBtn").val("请在" + curCount + "秒内输入验证码");  
            InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次  
            //向后台发送处理数据  
            $.ajax({  
                type:"POST",                              //用POST方式传输  
                dataType:"text",                          //数据格式:JSON  
                url:"${ctx}/base/tBaRegistered/sendCode", //目标地址  
                data:"phone="+ mobilePhone + "&code=" + code,  
                error:function(XMLHttpRequest, textStatus, errorThrown) {   
                      
                },  
                success: function (data){   
                    data = parseInt(data, 10);  
                    if(data == 1){  
                        //alert("成功");
                    }else if(data == 0){ 
                        //alert("失败"); 
                    }  
                }  
            });   
		}
		
	</script>
</head>
<body>
	<div style="height: 80px; background-image: url(${ctxStatic}/images/bg_blue.png); 
        background-repeat: repeat-x; -moz-box-shadow: 3px 3px 3px rgba(100, 100, 100, .4);
        -webkit-box-shadow: 3px 3px 3px rgba(100, 100, 100, .4);">
        <div style="display: block; margin-left: 30px; position: relative; width: 970px;">
            <img src="${ctxStatic}/images/bg_logo.png">
            <div style="font-size: 24px; position: absolute; top: 30px; left: 62px; color: #faf7f7">
                ${fns:getConfig('productName')}
            </div>
        </div>
    </div>
	<form:form id="inputForm" modelAttribute="TBaRegistered" action="${ctx}/base/tBaRegistered/save" 
	    method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
        <legend style="font-size: 14px;">
                              申请机构账户注册
        </legend>	
        <table width="100%" class="table table-bordered">
            <tr>
                <td width="25%">
                    <label class="control-label">机构名称：</label>
                </td>
                <td colspan="4">
                    <form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
                    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">组织机构代码：</label>
                </td>
                <td colspan="4">
                    <form:input path="orgCode" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
                    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>
                </td>
            </tr>
            <tr>
                <td width="25%">
                    <label class="control-label">机构账号：</label>
                </td>
                <td colspan="4">
                    <input id="username" name="username" type="text" htmlEscape="false" maxlength="200" 
                        class="input-xlarge required username"/>
                    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>
                </td>
            </tr>
            <tr>
                <td width="25%">
                    <label class="control-label">密码：</label>
                </td>
                <td colspan="4">
                    <input id="password" name="password" minlength="6" type="password" htmlEscape="false" 
                        placeholder="请输入英文字母、数字" maxlength="20" class="input-xlarge required"/>
                    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>
                </td>
            </tr>
            <tr>
                <td width="25%">
                    <label class="control-label">确认密码：</label>
                </td>
                <td colspan="4">
                    <input  id="pwdAgain" name="pwdAgain" type="password" minlength="6" htmlEscape="false" 
                        class="input-xlarge required pwdAgain" maxlength="20"/>
                    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">短信校验号码：</label>
                </td>
                <td colspan="4">
                    <form:input path="phone" htmlEscape="false" maxlength="11" class="input-xlarge required mobilePhone"/>
                    <span name="tip" id="tip" style="color: red;font-size: 12px;">*</span>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="control-label">短信验证：</label>
                </td>
                <td colspan="4">
                    <input id="checkcode" name="checkcode" class="input-xlarge required checkcode" type="text" htmlEscape="false"
                        maxlength="6" style="width: 150px;" >
                    <span name="tip" id="codeTip" style="color: red">*</span>
                    <input class="btn btn-primary" type="button" id="sendSmsBtn" 
                        value="点击获取验证码" onclick="getCode()">
                </td>
            </tr>
        </table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="注 册"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		    
		    <a href="${ctx}/base/userlogin/qqlogin"><img alt="" src="${ctxStatic}/images/qq.png"></a>
			<span id="qq_login_btn" 
			      _origtext="get_user_info 加载昵称中..." 
			      title="nickname">
			    <a href="javascript:;" 
			       onclick="return window.open('https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101205389&amp;response_type=token&amp;scope=all&amp;redirect_uri=http%3A%2F%2Ftj.jaiq.org%2Fh%2Fintro%2Flogin%2FredirectURI',  'oauth2Login_10591' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes');">
			        <img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png" alt="QQ登录" border="0"></a>
			</span>
https://graph.qq.com/oauth/show?which=Login&display=pc&amp;client_id=101205389&amp;response_type=token&amp;scope=all&amp;redirect_uri=http%3A%2F%2Ftj.jaiq.org%2Fh%2Fintro%2Flogin%2FredirectURI
https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=[YOUR_APPID]&redirect_uri=[YOUR_REDIRECT_URI]&scope=[THE_SCOPE]
https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101205389APP&redirect_uri=http://tj.njdt.org/h/userlogin/qqlogin_return&scope=[THE_SCOPE]
		</div>
	</form:form>
</body>
</html>