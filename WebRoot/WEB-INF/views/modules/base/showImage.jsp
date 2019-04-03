<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
	    <title>图片展示</title>
	    <meta name="decorator" content="default"/>
	    <script src="${ctxStatic}/jquery-plugin/tablemerge.js" type="text/javascript"></script>
	    <script type="text/javascript">
	        $(document).ready(function() {
	            $("#btnMenu").hide();
	            // 解决部分浏览（ie11）图片无法展示问题
	            $("<span id='tempspan'>&nbsp;</span>").appendTo("#imageDiv");
	            $("#imageDiv").find("#tempspan").remove();
	        });
	        
	    </script>
	</head>
	<body>
	      <div style="height: 3%;">&nbsp;</div>
	      <div id="imageDiv"  style="width: 80%;height: 80%; background-color: white; text-align: center;margin-top: 2%;margin-left: 6%;" align="center">
	          <img align="center" src="${ctx}/base/tBaFile/showImage?id=${attFileId}" />
	      </div>
	</body>

</html>