<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>百度地图</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=RG3ybaaMUA9rksCZfmjXeLyO9WG5QRqI"></script>
    <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
    <style type="text/css">
        #l-map {
            height: 100%;
            width: 100%;
        }
    </style>

</head>
<body>

<div id="l-map"></div>

</body>
</html>

<script type="text/javascript">
    var marker;
    var time = 0;
    var pointX = $('#pointX', window.parent.document);
    var pointY = $('#pointY', window.parent.document);

    var removeMarker = function(e, ee, marker) {//删除标记
        console.log("删除成功");
        //成功的话向地图上创建标记和标注
        map.removeOverlay(marker);
        time = time - 1;
        pointX.val("");
        pointY.val("");
    }

    // 百度地图API功能
    var map = new BMap.Map("l-map");
    var x = pointX.val()==""?116.403694:pointX.val();
    var y = pointY.val()==""?39.916817:pointY.val();

    var point = new BMap.Point(x,y); //北京市
    map.centerAndZoom(point, 15);

    if(pointX.val()!=""){
        createMaker(point);
    }

    map.addControl(new BMap.NavigationControl()); //平移缩放控件
    map.addControl(new BMap.ScaleControl());      //比例尺控件

    map.enableScrollWheelZoom();   	//启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    	//启用地图惯性拖拽，默认禁用
    var local = new BMap.LocalSearch(map, {
        renderOptions:{map: map}
    });

    map.addEventListener("click", function(e) {
        if (time >= 1) {
            return;
        } else {
            if(confirm("确定在此标记么?")){
                popLayer(e);
            }
        }
    });

    function popLayer(e) {
        //将经纬度存入form中
        pointX.val(e.point.lng);
        pointY.val(e.point.lat);

        console.log("成功");
        //成功的话向地图上创建标记和标注
        createMaker(e.point);
    }

    function createMaker(point){
        var icons = "http://api.map.baidu.com/img/markers.png"; //这个是你要显示坐标的图片的相对路径
        marker = new BMap.Marker(point);
        var icon = new BMap.Icon(icons, new BMap.Size(23, 25), {
            offset: new BMap.Size(10, 25), // 指定定位位置
            imageOffset: new BMap.Size(0, 0 - 10 * 25) // 设置图片偏移
        });

        marker.setIcon(icon);//设置标签的图标为自定义图标

        map.addOverlay(marker);
        map.panTo(point);
        marker.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的标记
        //创建右键菜单
        if("${param.flag}"=="edit") {
            var markerMenu = new BMap.ContextMenu();
            markerMenu.addItem(new BMap.MenuItem('删除标记', removeMarker.bind(marker)));
            marker.addContextMenu(markerMenu);
        }

        //标注
        var text = "<p style='color:red;font-weight: bold;'>&nbsp;&nbsp;标记位置在此，可点击鼠标右键删除该标记！</p>";

        var label = new BMap.Label(text, {
            offset : new BMap.Size(-85, -70)
        });
        //设置label(标注的样式)
        label.setStyle({
            color : "black",
            fontSize : "12px",
            height : "40px",
            //lineHeight : "20px",
            fontFamily : "微软雅黑",
            maxWidth : "none",
            border : "none"
        });
        time = time + 1;
    }

</script>
