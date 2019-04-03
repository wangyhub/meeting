<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构注册管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
			
			// 用户名
			jQuery.validator.addMethod("usernameCheck", 
                function(value, element) {          
                    var falg = true;
                    $.ajax({
                        url:'${ctx}/base/tBaRegistered/checkUserName',
                        async:false, 
                        type:"post",
                        data:{username: value, flag: "1"},
                        dataType:"json",
                        success:function(data){
                            if(data == true){
                                flag = true;
                                checkPhone = 0;
                            }else if(data == false){
                                flag = false;
                                checkPhone = 1;
                            }
                        }   
                    });  
                    return flag;
                }, 
                "该用户名不存在！"
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
            
            // 用户名和手机号是否匹配
            jQuery.validator.addMethod("userAndMob", 
                function(value, element) {          
                    var flag = true;
                    var username = $("#username").val();
                    var phone = $("#phone").val();
                    $.ajax({
                        url:'${ctx}/base/tBaRegistered/checkUserAndMob',
                        async:false, 
                        type:"post",
                        data:{"username":username,"phone":phone},
                        dataType:"json",
                        success:function(data){
                            if(data == 1){
                                sendFlag = 0;
                                flag = true;
                            }else if(data == 0){
                                sendFlag = 1;
                                flag = false;
                            }
                        }   
                    });  
                    return flag;
                }, 
                "用户名和手机号不匹配，无法发送验证码！"
            );
            
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
		});
	
		// 发送短信验证码
        var InterValObj;    // timer变量，控制时间  
        var count = 120;    // 间隔函数，1秒执行  
        var curCount;       // 当前剩余秒数  
        var code = "";      // 验证码  
        var codeLength = 6; // 验证码长度
        var sendFlag = 1;   // 是否发送验证码  0:发送；1：不发送
        var checkPhone = 1; // 是否检测手机号码 0：检测；1：不检测
        
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
            if("" == mobilePhone) {
                // 触发提醒输入提示信息
                $("#phone").val("checkPhoneNotNull");
                $("#phone").focus();
                $("#phone").blur();
                $("#phone").val("");
            }
            $("#phone").focus();
            $("#phone").blur();
            if(1 == sendFlag) {
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
		
		function checkPhonenum(){
		    if(0 == checkPhone){
		        $("#phone").focus();
		        $("#phone").blur();
		    }
		}
		
		function back(){
			document.location.href="${ctx }";
		}
	</script>
</head>
<body>
	<div style="height:80px; background-image:url(${ctxStatic}/images/bg_blue.png); background-repeat:repeat-x;
	     -moz-box-shadow:3px 3px 3px rgba(100,100,100,.4);
	     -webkit-box-shadow:3px 3px 3px rgba(100,100,100,.4);">
	  <div style=" display:block; margin-left:30px; position:relative; width:970px; ">
	    <img src="${ctxStatic}/images/bg_logo.png" >
	    <div style=" font-size:24px; position:absolute; top:30px; left:62px; color:#faf7f7">
	       ${fns:getConfig('productName')}
	    </div>
	  </div>
	</div>
	<form:form id="inputForm" modelAttribute="TBaRegistered" action="${ctx}/base/tBaRegistered/getPwd" 
	    method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<legend style="font-size: 14px;">找回密码</legend>	
		<table width="100%" class="table table-bordered">	
			<tr>
				<td width="300px">
					<label class="control-label" >用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label>
				</td>
				<td colspan="3">
					<input id="username" name="username" maxlength="20" type="text" htmlEscape="false"
	                     onblur="checkPhonenum()" class="input-xlarge required username usernameCheck"/>
					<span  style="color: red">*请输入注册时用户名</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</label>
				</td>
				<td colspan="3">
					<input id="password" name="password" htmlEscape="false" maxlength="20" 
					   minlength="6" class="input-xlarge required password" type="password" />
					<span style="color: red">*</span>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">确认密码：</label>
				</td>
				<td colspan="3">
					<input id="pwdAgain"  htmlEscape="false" maxlength="20" type="password"
					    minlength="6" class="input-xlarge required pwdAgain" />
					<span style="color: red">*</span>
				</td>
			</tr>
		    <tr>
				<td>
					<label class="control-label">移动电话：</label>
				</td>
				<td>
					<form:input path="phone" htmlEscape="false" maxlength="11" 
					    class="input-xlarge required mobilePhone userAndMob"/> 
					<span style="color: red">*</span>
				</td>
		    </tr>
			<tr>
				<td>
					<label class="control-label">输入验证码：</label>
				</td>
				<td colspan="3">
					<input id="checkcode" name="checkcode" class="input-xlarge required checkcode" type="text" htmlEscape="false"
                        maxlength="6" style="width: 150px;" >
					<span id="codeTip" style="color: red">*</span>
					<input class="btn btn-primary" type="button" id="sendSmsBtn" 
                        value="点击获取验证码" onclick="getCode()">
				</td>
		    </tr>
		</table>
		<div class="form-actions" >
			<input id="btnSubmit" class="btn btn-primary" type="submit"  value="提 交"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="javascript:back()"/>
		</div>
	</form:form>
</body>
</html>