<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctxStatic}/seat-charts/jquery.seat-charts.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/seat-charts/jquery.seat-charts.js"></script>
    <title>座次安排</title>

    <style type="text/css">
        .front{width: 800px; height: 40px;margin: 5px 10px 20px 20px; padding: 3px;padding-left:150px;border-radius: 5px;}
        .booking_area {width:300px;}
        .booking_area h3 {margin: 5px 5px 0 0;font-size: 16px;}
        .booking_area p{line-height:26px; font-size:16px; color:#999}
        .booking_area p span{color:#666}
        div.seatCharts-cell {color: #182C4E;height: 25px;width: 25px;line-height: 25px;margin: 3px;float: left;text-align: center;outline: none;font-size: 13px;}
        div.seatCharts-seat {color: #fff;cursor: pointer;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;}
        div.seatCharts-row {height: 35px;}
        div.seatCharts-seat.available {background-color: #B9DEA0;}
        div.seatCharts-seat.focused {background-color: #76B474;border: none;}
        div.seatCharts-seat.selected {background-color: #E6CAC4;}
        div.seatCharts-seat.unavailable {background-color: #472B34;cursor: not-allowed;}
        /*div.seatCharts-container {border-right: 1px dotted #adadad;width: 950px;padding: 20px;float: left;}*/

        div.seatCharts-legend {padding-left: 0px;position: absolute;bottom: 16px;}
        ul.seatCharts-legendList {padding-left: 0px;}
        .seatCharts-legendItem{float:left; width:90px;margin-top: 10px;line-height: 2;}
        span.seatCharts-legendDescription {margin-left: 5px;line-height: 30px;}
        .checkout-button {display: block;width:80px; height:24px; line-height:20px;margin: 10px auto;border:1px solid #999;font-size: 14px; cursor:pointer}
        #seats_chose {max-height: 250px;overflow-y: auto;overflow-x: none;width: 1000px;}
        #seats_chose li{float:left; width:72px; height:26px; line-height:26px; border:1px solid #d3d3d3; background:#f7f7f7; margin:6px; font-size:14px; font-weight:bold; text-align:center}
    </style>
</head>
<body>

<div class="demo clearfix">
    <!---主席台----->
    <div id="top_area" class="front"></div>
    <!---参会席----->
    <div id="seat_area">
    </div>
</div>

<!---选座信息----->
<div class="booking_area">
    <p>&nbsp;&nbsp;已选座位：<span id="seats_num">0</span>个
        &nbsp;&nbsp;<input  type="button" value="安排就座人员"  onclick="chooseJoin()"/>
    </p>

    <ul id="seats_chose"></ul>
    <p></p>
</div>

<br/><br/>

<c:if test="${not empty tbMeetingPlace.platform}">
    <table BORDER="1" style="display:table;table-layout:fixed;background: #9acfea" align="center">
        <tr style="height: 25px">
            <c:forEach var="plat" begin="1" end="${tbMeetingPlace.platform}">
                <th style="width:65px;text-align:center;">
                    <c:set var="key1" value="0_${plat}"  />
                    ${seatMap[key1]}
                </th>
            </c:forEach>
        </tr>
    </table>
</c:if>

<table BORDER="1" style="width:100%;display:table;table-layout:fixed;">
    <c:forEach var="col" begin="1" end="${tbMeetingPlace.regionRow}">
    <tr style="height: 25px">
        <c:forEach items="${rowList}" var="row">
            <c:if test="${empty row}">
                <td style="width:30px;border-top:none;border-bottom:none">
                </td>
            </c:if>
            <c:if test="${not empty row}">
                <c:set var="key2" value="${col}_${row}"  />
                <c:set var="len" value="${fn:length(seatMap[key2])}"  />

                <c:if test="${not empty seatMap[key2]}">
                    <td style="width:50px;text-align:center;background: #B9DEA0 " title="${seatMap[key2]}">
                        <c:if test="${len > 3}">
                            ${fn:substring(seatMap[key2], 0, 2)}...
                        </c:if>
                        <c:if test="${len <= 3}">
                            ${seatMap[key2]}
                        </c:if>
                    </td>
                </c:if>
                <c:if test="${empty seatMap[key2]}">
                    <td style="width:55px;text-align:center;background: #DEDEDE ">
                    </td>
                </c:if>
            </c:if>
        </c:forEach>
    </tr>
    </c:forEach>
</table>


<script type="text/javascript">
    var $cart = $('#seats_chose'), //参会席
        $seats_num = $('#seats_num'); //座位数
    var st,sc;

    $(document).ready(function() {
        var platform = "${tbMeetingPlace.platform}";
        if(platform!="") {
            var plat = parseInt(platform);
            var plats = [''];
            for (var i = 0; i < plat; i++) {
                plats[0] += 'c';
            }
            createTop(plats);
        }

        var row = parseInt("${tbMeetingPlace.regionRow}");
        var col = "${tbMeetingPlace.region}";

        var dataList = [];
        var cols = [];

        for(var i=0;i<row;i++){
            dataList[i] = col;
        }

        var num = 1;
        var cs = col.split('');
        for(var i=0;i<cs.length;i++){
            cols[i] = '='==cs[i]?"":(''+num++);
        }

        createSeat(dataList,cols);
    });

    function createTop(dataList){
        st = $('#top_area').seatCharts({
            map: dataList,
            naming: {//设置行列等信息
                top: false, //不显示顶部横坐标（行）
                left: false,
                getId  : function(character, row, column) {
                    return '0_' + column;
                },
                getLabel: function(character, row, column) { //返回座位信息
                    return column;
                }
            },
            click: function() {
                if (this.status() == 'available') { //若为可选座状态，添加座位
                    if(typeof(sc)!="undefined" && sc.find('selected').length>0){
                        alert("请先将参会席已选择的座次安排人员！");
                        return 'available';
                    }
                    else{
                        $('<li>' + '主席台' + this.settings.label + '座</li>')
                            .attr('id', 'cart-item-' + this.settings.id)
                            .data('seatId', this.settings.id)
                            .appendTo($cart);
                        $seats_num.text(st.find('selected').length + 1); //统计选座数量
                        return 'selected';
                    }
                } else if (this.status() == 'selected') { //若为选中状态
                    $seats_num.text(st.find('selected').length - 1);//更新选座数量
                    $('#cart-item-' + this.settings.id).remove();//删除已选座位
                    return 'available';
                } else if (this.status() == 'unavailable') { //若为已安排状态
                    var seatnum = (this.settings.row + 1) + '排' + this.settings.label + '座';
                    if(confirm(seatnum+"已安排，是否取消？")){
                        removeSeat(this.settings.id);
                        return 'available';
                    }
                    return 'unavailable';
                } else {
                    return this.style();
                }
            }

        });
        //设置已安排的座位
        st.get([${stNo}]).status('unavailable');
    }

    function createSeat(dataList,cols){
        sc = $('#seat_area').seatCharts({
            map: dataList,
            naming: {//设置行列等信息
                top: false, //不显示顶部横坐标（行）
                columns: cols,
                getLabel: function(character, row, column) { //返回座位信息
                    return column;
                }
            },
            click: function() {
                if (this.status() == 'available') { //若为可选座状态，添加座位
                    if(typeof(st)!="undefined" && st.find('selected').length>0){
                        alert("请先将主席台已选择的座次安排人员！");
                        return 'available';
                    }
                    else{
                        $('<li>' + (this.settings.row + 1) + '排' + this.settings.label + '座</li>')
                            .attr('id', 'cart-item-' + this.settings.id)
                            .data('seatId', this.settings.id)
                            .appendTo($cart);
                        $seats_num.text(sc.find('selected').length + 1); //统计选座数量
                        return 'selected';
                    }
                } else if (this.status() == 'selected') { //若为选中状态
                    $seats_num.text(sc.find('selected').length - 1);//更新选座数量
                    $('#cart-item-' + this.settings.id).remove();//删除已选座位
                    return 'available';
                } else if (this.status() == 'unavailable') { //若为已安排状态
                    var seatnum = (this.settings.row + 1) + '排' + this.settings.label + '座';
                    if(confirm(seatnum+"已安排，是否取消？")){
                        removeSeat(this.settings.id);
                        return 'available';
                    }
                    return 'unavailable';
                } else {
                    return this.style();
                }
            }
        });
        //设置已安排的座位
        sc.get([${scNo}]).status('unavailable');
    }

    function removeSeat(seatNo){
        $.getJSON("${ctx}/meeting/meet/tbAgendaSeat/delete?agendaId=${param.agendaId}&seatNo="+seatNo,function(data){
        });
        window.location.reload();
    }

    function chooseJoin(){
        var seats = "";
        var num = 0;
        if(typeof(st)!="undefined" && st.find('selected').length>0){
            seats = st.find('selected').seatIds;
            num = st.find('selected').length;
        }
        else if(typeof(sc)!="undefined" && sc.find('selected').length>0){
            seats = sc.find('selected').seatIds;
            num = sc.find('selected').length;
        }
        else{
            alert("请先选择座位！");
            return;
        }

        var path = "${ctx}/meeting/meet/tbJoin/chooseJoinList?num="+num+"&seats="+seats+"&agendaId=${param.agendaId}";
        window.open(path,'_blank','width=700,height=600,top=120px,left=300px,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
    }

</script>
</body>
</html>