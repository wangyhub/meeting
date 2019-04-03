<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日历设置管理</title>
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
			
			$("#year").change(function(){
			  var form1 = $("#inputForm");
			  form1.attr("action","${ctx}/base/tBaCalendar/form");
			  //alert('${ctx}/base/tBaCalendar/form');
              form1.submit();
			});
		});
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="TBaCalendar" action="${ctx}/base/tBaCalendar/save" method="post" class="form-horizontal">
		
<sys:message content="${message}"/>		
 <table>
 <tr height="30px">
  <td colspan="4" >
      年份：
     <select id="year" name="year">
				    <c:forEach items="${yearList}" var="yearValue" varStatus="indexNum">
				       <option value="${yearValue.year}" ${yearValue.year==tBaCalendar.year?'selected':''}>${yearValue.year}</option>
				    </c:forEach>				    			    
      </select>
      &nbsp;&nbsp;&nbsp;&nbsp;
   <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
  </td>
 </tr>
 <tr>   
	    <td>
	        <div id="month1"></div>
	    </td>
	    <td>
	       <div id="month2"></div>
	    </td>
	    <td>
	        <div id="month3"></div>
	    </td>
	    <td>
	       <div id="month4"></div>
	    </td>   
    </tr>
    <tr>
	     <td>
	        <div id="month5"></div>
	    </td>
	    <td>
	       <div id="month6"></div>
	    </td>
	         <td>
	        <div id="month7"></div>
	    </td>
	    <td>
	       <div id="month8"></div>
	    </td>
    </tr>
    <tr>
	     <td>
	        <div id="month9"></div>
	    </td>
	    <td>
	       <div id="month10"></div>
	    </td>
	         <td>
	        <div id="month11"></div>
	    </td>
	    <td>
	       <div id="month12"></div>
	    </td>
    </tr>
 </table>		
		<input type="hidden" name="hdnwwdata1" id="hdnwwdata1" value="${tBaCalendar.hdnwwdata1}" />  
        <input type="hidden" name="hdnwwdata2" id="hdnwwdata2" value="${tBaCalendar.hdnwwdata2}" />  
        <input type="hidden" name="hdnwwdata3" id="hdnwwdata3" value="${tBaCalendar.hdnwwdata3}"/>  
        <input type="hidden" name="hdnwwdata4" id="hdnwwdata4" value="${tBaCalendar.hdnwwdata4}" />  
        <input type="hidden" name="hdnwwdata5" id="hdnwwdata5" value="${tBaCalendar.hdnwwdata5}" />  
        <input type="hidden" name="hdnwwdata6" id="hdnwwdata6" value="${tBaCalendar.hdnwwdata6}" />  
        <input type="hidden" name="hdnwwdata7" id="hdnwwdata7" value="${tBaCalendar.hdnwwdata7}" />  
        <input type="hidden" name="hdnwwdata8" id="hdnwwdata8" value="${tBaCalendar.hdnwwdata8}" />  
        <input type="hidden" name="hdnwwdata9" id="hdnwwdata9" value="${tBaCalendar.hdnwwdata9}"  />  
        <input type="hidden" name="hdnwwdata10" id="hdnwwdata10" value="${tBaCalendar.hdnwwdata10}" />  
        <input type="hidden" name="hdnwwdata11" id="hdnwwdata11" value="${tBaCalendar.hdnwwdata11}" />  
        <input type="hidden" name="hdnwwdata12" id="hdnwwdata12" value="${tBaCalendar.hdnwwdata12}" /> 	
	</form:form>
<script type="text/javascript">
//Wwday是蓝色字体样式
      var currentIframeDocument = null;  
      var currentDataM = null;  
      var currentDataD = null; 
      //alert(${tBaCalendar.year});
      //初始化12个月
      for (i = 1; i <= 12; i++) { 
                WdatePicker({ eCont: 'month' + i, 
                    startDate: '${tBaCalendar.year}-' + i + '-1',  
                    minDate: '${tBaCalendar.year}-' + i + '-1',  
                    maxDate: '${tBaCalendar.year}-' + i + '-%ld',
                    onpicked: function(dp) {  
                        currentDataM = dp.cal.getDateStr('M');  
                        currentDataD = dp.cal.getDateStr('d');  
                        currentIframeDocument = $($("iframe")[parseInt(currentDataM) - 1].contentDocument);
                        var obj = currentIframeDocument.find("td[class='Wselday']");                    
                        var hdnwwdataValue = ","+$("#hdnwwdata" + currentDataM).val()+",";
                       // alert(currentDataD);
                        //alert(hdnwwdataValue);
                        var isWorkDay =hdnwwdataValue.indexOf(","+currentDataD+",")>-1;//ture 设置为工作日，false设置为节假日
                        var context =  isWorkDay?('确定把${tBaCalendar.year}-'+currentDataM+'-'+currentDataD+'设置为工作日吗？'):('确定把${tBaCalendar.year}-'+currentDataM+'-'+currentDataD+'设置为节假日日吗？');
                        if(!confirm(context)){
                           return false;
                        }
                        
                        if(isWorkDay){                        
			                obj.attr("class", "Wday");  
			                obj.mouseover(function(event) { this.className = 'WdayOn' })  
			                obj.mouseout(function(event) { this.className = 'Wday' });  
			                AddWorkDay(); 
		                }else{		                    
			                obj.attr("class", "Wwday");  
			                obj.mouseover(function(event) { this.className = 'WwdayOn' });  
			                obj.mouseout(function(event) { this.className = 'Wwday' });  
			                AddFreeDay(); 
		                }		                	                                      
                        UpdatePickerWwday(currentDataD);
                       
                    }  
                });  
     }     
     
      //更新选中项  
        function UpdatePickerWwday(obj) { 
            $.each(currentIframeDocument.find("td[class='Wwday']"),  
                            function(i, n) {  
                                $(n).attr("class", "Wday");  
                            }  
                           );  
            $.each(currentIframeDocument.find("td[class='Wday']"),  
	        function(i, n) {  
	            var freeDays = $("#hdnwwdata" + currentDataM).val().split(",");  
	            var k = 0;  
	            for (k = 0; k < freeDays.length; k++) {  
	                if ($(n).text() == freeDays[k]) {  
	                    $(n).attr("class", "Wwday");  
	                    $(n).mouseover(function(event) { this.className = 'WwdayOn' })  
	                    $(n).mouseout(function(event) { this.className = 'Wwday' });  
	                }  
	            }  
	        });  
        } 
      //初始化节假日  
        setTimeout("InitSelect()", 2000);
        function InitSelect() {  
            var action = '${tBaCalendar.hdnwwdata1}';  
            if (action != '') {  
                var k = 0;  
                try {  
                    for (k = 0; k < 12; k++) {  
                        var iObj = $($("iframe")[k].contentDocument);  
                        $.each(iObj.find("td[class='Wwday']"),  
                                function(i, n) {  
                                    $(n).attr("class", "Wday");  
                                    $(n).mouseover(function(event) { this.className = 'WdayOn' })  
                                    $(n).mouseout(function(event) { this.className = 'Wday' });  
                                }  
                               );  
  
                        var selday = iObj.find("td[class='Wselday']");  
                        selday.attr("class", "Wday");  
                        selday.mouseout(function(eent) { this.className = 'Wday' });  
                        $.each(iObj.find("td[class='Wday']"),  
                                function(i, n) {  
                                    var freeDays = $("#hdnwwdata" + (k + 1)).val().split(",");  
                                    var j = 0;  
                                    for (j = 0; j < freeDays.length; j++) {  
                                        if ($(n).text() == freeDays[j]) {  
                                            $(n).attr("class", "Wwday");  
                                            $(n).mouseover(function(event) { this.className = 'WwdayOn' })  
                                            $(n).mouseout(function(event) { this.className = 'Wwday' });  
                                        }  
                                    }  
                                }  
                               );  
  
                    }  
                } catch (err) {  
                    document.getElementById("lblMsg").innerText = "Initialize data fail, please try it again";  
                } finally {  
                   // $.unblockUI();  
                }  
            } else {  
                var k = 0;  
                try {  
                    for (k = 0; k < 12; k++) {  
                        var iObj = $($("iframe")[k].contentDocument);  
                        $.each(iObj.find("td[class='Wwday']"),  
                                function(i, n) {  
                                    $("#hdnwwdata" + (k + 1)).val($("#hdnwwdata" + (k + 1)).val() + "," + $(n).text());  
                                }  
                               );  
                        var selday = iObj.find("td[class='Wselday']");  
                        if ($("#hdnwwdata" + (k + 1)).val().charAt(1) == "7") {  
                            selday.attr("class", "Wwday");  
                            selday.mouseover(function(event) { this.className = 'WwdayOn' })  
                            selday.mouseout(function(event) { this.className = 'Wwday' });  
                        } else if ($("#hdnwwdata" + (k + 1)).val().charAt(1) == "2" && $("#hdnwwdata" + (k + 1)).val().charAt(3) != "3") {  
                            selday.attr("class", "Wwday");  
                            selday.mouseover(function(event) { this.className = 'WwdayOn' })  
                            selday.mouseout(function(event) { this.className = 'Wwday' });  
                        } else {  
                            selday.attr("class", "Wday");  
                            selday.mouseover(function(event) { this.className = 'WdayOn' })  
                            selday.mouseout(function(event) { this.className = 'Wday' });  
                        }  
                    }  
                } catch (err) {  
                    document.getElementById("lblMsg").innerText = "Initialize data fail, please try it again";  
                } finally {  
                    //$.unblockUI();  
                }  
                for (k = 1; k < 13; k++) {//去掉第一个逗号  
                    $("#hdnwwdata" + k).val($("#hdnwwdata" + k).val().substring(1));  
                    if ($("#hdnwwdata" + k).val().substring(0, 1) == "2" || $("#hdnwwdata" + k).val().substring(0, 1) == "7") {//如果当月的1号是星期天，或者是星期六则选不中，需要手动添加  
                        $("#hdnwwdata" + k).val("1," + $("#hdnwwdata" + k).val())  
                    }  
                }  
            }  
        }  
        
        //添加休假日  
        function AddWwDate_Click() {        
            if (check()) {  
                var obj = currentIframeDocument.find("td[class='Wselday']");  
                alert(obj.text());
                obj.attr("class", "Wwday");  
                obj.mouseover(function(event) { this.className = 'WwdayOn' })  
                obj.mouseout(function(event) { this.className = 'Wwday' });  
                AddFreeDay();  
                  
            } else {  
                document.getElementById("lblMsg").innerText = "please select date";  
            }  
        }  
        //添加工作日  
        function AddWDate_Click() {  
            if (check()) {  
                var obj = currentIframeDocument.find("td[class='Wselday']");  
                obj.attr("class", "Wday");  
                obj.mouseover(function(event) { this.className = 'WdayOn' })  
                obj.mouseout(function(event) { this.className = 'Wday' });  
                AddWorkDay();  
                $("#txtSelectDate").val("");  
            } else {  
                document.getElementById("lblMsg").innerText = "please select date";  
            }  
        }  
        function check() {  
            if ($("#txtSelectDate").val() == "") {  
                return false;  
            } else {  
                return true;  
            }  
        }  
        function AddWorkDay() {  
            var freeDays = $("#hdnwwdata" + currentDataM).val().split(",");  
            var i = 0;  
            var count = freeDays.length;  
            for (i = 0; i < freeDays.length; i++) {  
                if (currentDataD == freeDays[i]) {  
                    freeDays.splice(i, 1);  
                    break;  
                }  
            }  
            $("#hdnwwdata" + currentDataM).val(freeDays);  
        }  
  
        function AddFreeDay() {  
            var freeDays = $("#hdnwwdata" + currentDataM).val().split(",");  
            var i = 0;  
            var count = freeDays.length;  
            for (i = 0; i < freeDays.length; i++) {  
                if (currentDataD == freeDays[i]) {  
                    break;  
                }  
            }  
            if (i == count) {  
                freeDays.push(currentDataD);  
            }  
            $("#hdnwwdata" + currentDataM).val(freeDays);  
  
        }  
        function UpdateFreeDay() {  
            var freeDays = $("#hdnwwdata" + currentDataM).val().split(",");  
            var i = 0;  
            var count = freeDays.length;  
            for (i = 0; i < freeDays.length; i++) {  
                if (currentDataD == freeDays[i]) {  
                    freeDays.splice(i, 1);  
                    break;  
                }  
            }  
            if (i == count) {  
                document.getElementById("lblMsg").innerText = "add " + currentDataD;  
                freeDays.push(currentDataD);  
            }  
            $("#hdnwwdata" + currentDataM).val(freeDays);  
  
        }   
</script>
</body>
</html>