<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
		<title>智能会议后台管理系统</title>
		<meta name="decorator" content="blank" />
	    <link href="${ctxStatic}/login/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
        <link href="${ctxStatic}/login/css/styles_data.css" type="text/css" rel="stylesheet"/>
        <link href="${ctxStatic}/login/css/styles.css" type="text/css" rel="stylesheet"/>
		<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script> 
        <script type="text/javascript">
            $(document).ready(function() {
            	
            	//获取cookie的值
                var username = $.cookie('username');
                var password = $.cookie('password');
                //将获取的值填充入输入框中
                $('#username').val(username);
                $('#password').val(password); 
                if(username != null && username != '' && password != null && password != ''){//选中保存秘密的复选框
                    $("#ck").attr('checked',true);
                }
	            $("#loginForm").validate({
	                submitHandler: function(form){
	                    var uName =$('#username').val();
	                    var psw = $('#password').val();
	                    if(document.getElementById("ck").checked)
	                    {//保存密码
	                        $.cookie('username',uName, {expires:7,path:'/'});
	                        $.cookie('password',psw, {expires:7,path:'/'});
	                    }else{//删除cookie
	                        $.cookie('username', '', { expires: -1, path: '/' });
	                        $.cookie('password', '', { expires: -1, path: '/' });
	                    }
	                    form.submit();
	                },
	                rules: {
	                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
	                },
	                messages: {
	                    username: {required: "请填写用户名."},password: {required: "请填写密码."},
	                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
	                },
	                errorLabelContainer: "#messageBox",
	                errorPlacement: function(error, element) {
	                    error.appendTo($("#loginError").parent());
	                } 
	            });
	            $("#zhuce").click(function(){
	            	$(location).attr('href', "${ctx}/register");
	            }); 
	            	   
	        });
	        // 如果在框架或在对话框中，则弹出提示并跳转到首页
     	        if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
     	            alert('未登录或登录超时。请重新登录，谢谢！');
    	            top.location = "${ctx}";
     	        }
	        
	        $("body").keydown(function() {
	        	var e = event.keyCode?event.keyCode:event.which?event.which:event.charCode;
	        	if (e == 13) {//keyCode=13是回车键
	        		$('#u11_input').click();
	       		}
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
        <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}" style="text-align: center;">
            <button data-dismiss="alert" class="close">×</button>
            <label id="loginError" class="error">${message}</label>
        </div>
        <div style="background-color: #CCFFCC;text-align: center; " class="alert alert-right ${empty suc ? 'hide' : ''}">
            <button data-dismiss="alert" class="close">×</button>
            <label style="color: #33CC33">${suc}</label>
        </div>
      <form id="loginForm" class="form-signin" action="${ctx}/login"  style="width: 100%;height: 100%;" method="post">
       <div id="base" class="">
      <div id="u0" class="ax_image">
        <img id="u0_img" class="img " src="${ctxStatic}/login/images/u0.jpg"/>
        <div id="u1" class="text">
          <p><span></span></p>
        </div>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u2" class="ax_文本段落">
        <img id="u2_img" class="img " src="${ctxStatic}/login/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u3" class="text">
          <p><span>智能会议</span></p><p><span>后台管理系统</span></p>
        </div>
      </div>

      <!-- Unnamed (文本框) -->
      <div id="u4" class="ax_文本框">
        <input id="username" name="username"  type="text" value="" style="width:280px;height:42px" />
      </div>

      <!-- Unnamed (形状) -->
      <div id="u5" class="ax_文本段落">
        <img id="u5_img" class="img " src="${ctxStatic}/login/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u6" class="text">
          <p><span>用户名</span></p>
        </div>
      </div>

      <!-- Unnamed (文本框) -->
      <div id="u7" class="ax_文本框">
        <input type="password" id="password" name="password" value="${password }" style="width:280px;height:42px"/>
      </div>

      <!-- Unnamed (文本框) -->
      <div id="u8"   style="width: 280px; height: 42px;">
        <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0px;width:120px;height:42px"  imageCssStyle="height:40px;" buttonCssStyle="line-height:42px;"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u9" class="ax_文本段落">
        <img id="u9_img" class="img " src="${ctxStatic}/login/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u10" class="text">
          <p><span>验证码</span></p>
        </div>
      </div>

      <!-- Unnamed (提交按钮) -->
      <div id="u11" class="ax_提交按钮">
        <input id="u11_input" type="submit" value="登  录" style="background-color: #056CAF;border: 0px;"/>
         <input id="zhuce" type="button" value="注  册" style="background-color: #056CAF;border: 0px;"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u12" class="ax_文本段落">
        <img id="u12_img" class="img " src="${ctxStatic}/login/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u13" class="text">
          <p><span>密 码</span></p>
        </div>
      </div>
    </div>
    </form>
        <script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
    </body>
</html>