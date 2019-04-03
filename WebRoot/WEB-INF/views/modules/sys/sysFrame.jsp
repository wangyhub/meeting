<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构登陆系统</title>
	<meta name="decorator" content="blank"/>
	<link href="${ctxStatic}/login/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="${ctxStatic}/login/css/styles_data.css" type="text/css" rel="stylesheet"/>
    <link href="${ctxStatic}/login/css/styles.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">
		function frameFormSubmit(v) {
			$("#frameType").val(v);
	        $("#frameForm").submit();
	    }

	</script>

	<style type="text/css">
		#u0 {
		  position:absolute;
		  left:0px;
		  top:0px;
		  width:1300px;
		  height:620px;
		  color:#FFFFFF;
		}
		#u0_img {
		  position:absolute;
		  left:0px;
		  top:0px;
		  width:1300px;
		  height:700px;
		}

    	#d1 {
		  position:absolute;
		  left:800px;
		  top:150px;
		  width:280px;
		  height:50px;
		}
		#d2 {
		  position:absolute;
		  left:800px;
		  top:260px;
		  width:280px;
		  height:50px;
		}
		#d3 {
		  position:absolute;
		  left:800px;
		  top:370px;
		  width:280px;
		  height:50px;
		}
		#d4 {
		  position:absolute;
		  left:800px;
		  top:480px;
		  width:280px;
		  height:50px;
		}
		
		.btn_frame {
		  left:0px;
		  top:0px;
		  width:129px;
		  height:40px;
		  font-weight:400;
		  font-style:normal;
		  font-size:25px;
		  text-decoration:none;
		  color:#FFFFFF;
		  text-align:center;
		}
    </style>
        

</head>
<body scroll="no" style="overflow-y:hidden">
	
		<form id="frameForm" class="form-signin" action="${ctx}/login/frame" method="post">
			<div id="u0">
		        <img id="u0_img" src="${ctxStatic}/images/frame.jpg"/>
		     </div>
      
			<input type="hidden" id="frameType" name="frameType" >
			
			<div id="d1">
				<input class="btn_frame" style="background-color: #005782;border: 0px;" type="button" onclick="frameFormSubmit('inst_role1')" value="  电子商务服务量化在线认证子系统  "/>
			</div>
			<div id="d2">
				<input class="btn_frame" style="background-color: #005782;border: 0px;" type="button" onclick="frameFormSubmit('inst_role2')" value="  电子商务交互式协同智能评价子系统  "/>
			</div>
			<div id="d3">
				<input class="btn_frame" style="background-color: #005782;border: 0px;" type="button" onclick="frameFormSubmit('inst_role3')" value="  电子商务认证全流程管理子系统  "/>
			</div>
			<div id="d4">
				<input class="btn_frame" style="background-color: #005782;border: 0px;" type="button" onclick="frameFormSubmit('inst_role4')" value="  电子商务公共服务子系统  "/>
			</div>
		</form>
		
	
</body>


</html>