<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/seat-charts/jquery.seat-charts.js"></script>
    <title>座次安排</title>

    <style type="text/css">
        .front{width: 800px; height: 40px;margin: 5px 10px 20px 20px; padding: 3px;padding-left:150px;border-radius: 5px;}
        .booking_area {float: left;position: relative;width:300px;height: 450px; }
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
        #seats_chose {max-height: 250px;overflow-y: auto;overflow-x: none;width: 800px;}
        #seats_chose li{float:left; width:72px; height:26px; line-height:26px; border:1px solid #d3d3d3; background:#f7f7f7; margin:6px; font-size:14px; font-weight:bold; text-align:center}
    </style>
</head>
<body>
<div class="container">

    <div class="demo clearfix">
        <!---主席台----->
        <div id="top_area" class="front"></div>

        <!---参会席----->
        <div id="seat_area">

        </div>

    </div>

</div>

<script type="text/javascript">
    var st,sc;

    $(document).ready(function() {
        var platform = "${param.platform}";
        if(platform!="") {
            var plat = parseInt(platform);
            var plats = [''];
            for (var i = 0; i < plat; i++) {
                plats[0] += 'c';
            }
            createTop(plats);
        }

        var row = parseInt("${param.regionRow}");
        var col = "${param.region}";

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
                getLabel: function(character, row, column) { //返回座位信息
                    return column;
                }
            },
            click: function() {
                return this.style();
            }

        });

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
                return this.style();
            }
        });

    }

</script>
</body>
</html>