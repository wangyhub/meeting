<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
		<title>智能会议后台管理系统</title>
		<meta name="decorator" content="blank" />
        <script type="text/javascript">
            $(document).ready(function() {
            	$("#btn").click(function() {
            		alert("注册码短信已发出，请注意查收！");
            	});
            	$("#btnSubmit").click(function() {
            		 var name = $("#name").val();
            		 if(name == null || name ==''){
            			 alert("请填写用户名！");
                         return;
            		 }
            		 var pass = $("#pass").val();
            		 if(pass == null || pass ==''){
                         alert("请填写密码！");
                         return;
                     }
            		 var phone = $("#phone").val();
            		 if(phone == null || phone ==''){
                         alert("请填写手机号！");
                         return;
                     }
            		 var verificationCode = $("#verificationCode").val();
            		 if(verificationCode == null || verificationCode ==''){
                         alert("请填写验证码！");
                         return;
                     }
            		  if($("#check").is(':checked') != true){
            			  alert("请选择阅读并接受《平台用户协议》");
            			  return;
            		  }
            		 $.post("${ctx}/register/doRegister",{name:name,pass:pass,phone:phone,verificationCode:verificationCode},
            				 function(result){
	            			 if("exists" == result){
	            				 alert("用户名已存在！");
	            			 }else if("success" == result){
	            				 alert("注册成功，返回登录页面！");
	            				 $(location).attr('href', "${ctx}/login");
	            			 }
            		 });
                });
            });
        </script>
        
        <style type="text/css">
            html, body {
				height: 100%;
				width: 100%;
			}
        </style>
    </head>
    
    <body style="width: 100%;heigth:100%;margin:0;padding:0; min-width: 1360px; min-height: 660px;">
       <form id="inputForm" action="${ctx}/register/doRegister" method="post">
       <div class="control-group">
	       <table style="width: 100%;heigth:100%;" >
	           <tr>
                   <td width = "40%;" align="right" >
                        &nbsp;
                   </td>
                   <td  width = "60%;" align="left" >
                        &nbsp;
                   </td>
                </tr>
		        <tr>
		           <td width = "40%;" align="right" >
		                <label class="control-label">用户名：</label>
		           </td>
		           <td  width = "60%;" align="left" >
                        <input  id="name" name="name" type="text" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                   </td>
		        </tr>
		         <tr>
                   <td width = "40%;" align="right" >
                        <label class="control-label">密   码：</label>
                   </td>
                   <td  width = "60%;" align="left" >
                        <input  id="pass" name="pass" type="password" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                   </td>
                </tr>
                <tr>
                    <td width = "40%;" align="right" >
                        <label class="control-label">手机号：</label>
                    </td>
                    <td  width = "60%;" align="left" >
                        <input  id="phone" name="phone" type="text" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>
                <tr>
                    <td width = "40%;" align="right" >
                         <label class="control-label">验证码：</label>
                    </td>
                    <td  width = "60%;" align="left" >
                         <input  id="verificationCode" name="verificationCode" type="text" maxlength="50" class="required"/>
                         <span class="help-inline"><font color="red">*</font> </span>
                         <input id="btn" class="btn btn-primary" type="button" value="获取短信验证码"/>
                    </td>
                </tr>
                <tr>
                    <td width = "40%;" align="right" ></td>
                    <td  width = "60%;" align="left" >
                           <input id="check" type="checkbox" >阅读并接受《平台用户协议》
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center" >
                        <div class="form-actions">
                            <input id="btnSubmit" class="btn btn-primary"  style="width:280px;height:42px"   type="button" value="注册"/>
                        </div>
                    </td>
                </tr>
	       </table>
       </div>
	    </form>
        <script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
    </body>
</html>