<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>批量地址</title>
<style type="text/css">
body,html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#l-map {
	height: 300px;
	width: 100%;
}

#r-result {
	width: 100%;
	font-size: 14px;
	line-height: 20px;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=SGdwd6tOShorgPV6NHxgApXhkpOrDLwp"></script>
</head>
<body>
	<div id="l-map"></div>
	<div id="r-result">
		<input type="button" value="批量地址解析" onclick="bdGEO()" />
		<form action="getPoint" method="post">
			<input type="button" value="传到后台" onclick="sendMess()" />
			<div id="result"></div>
		</form>


	</div>
</body>
</html>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	map.centerAndZoom(new BMap.Point(117.269945, 31.86713), 13);
	map.enableScrollWheelZoom(true);
	var index = 0;
	var myGeo = new BMap.Geocoder();
	var sendD = [];
	var sendA = {};
	var allData = ${data};
	//alert(allData[0].logisticCode);
	var adds = [];
	for (var i = 0; i < allData.length; i++) {
			//	alert(allData[i].traces[j].AcceptStation);
			adds.push(allData[i]);
		}
	
	// alert(allData[0].traces[0].AcceptStation);
	// alert(adds);
	/*   var adds= [ "包河区金寨路1号（金寨路与望江西路交叉口）", "庐阳区凤台路209号（凤台路与蒙城北路交叉口）",
			"蜀山区金寨路217号(近安医附院公交车站)", "蜀山区梅山路10号(近安徽饭店) ",
			"蜀山区 长丰南路159号铜锣湾广场312室", "合肥市寿春路93号钱柜星乐町KTV（逍遥津公园对面）",
			"庐阳区长江中路177号", "新站区胜利路89" ]; */
	function bdGEO() {
		var add = adds[index];
		geocodeSearch(add);
		index++;

	}
	function geocodeSearch(add) {
		if (index < adds.length) {
			setTimeout(window.bdGEO, 1000);
		}
		myGeo.getPoint(add, function(point) {
			if (point) {
				document.getElementById("result").innerHTML += index + "、"
						+ add + ":" + point.lng + "," + point.lat + "</br>";
				var address = new BMap.Point(point.lng, point.lat);
				/* addMarker(address, new BMap.Label(index + ":" + add, {
					offset : new BMap.Size(20, -10)
				})); */
				var p=[];
				p.push(point.lng,point.lat);
				sendD.push(address);
				
				sendA[add]=p;

			}
		});
	}
	// 编写自定义函数,创建标注
	function addMarker(point, label) {
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.setLabel(label);
	}
	function sendMess() {
		//alert(JSON.stringify(sendA));
		$.ajax({
			async : false,
			type : "POST",
			url : "getPoint.do",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(sendA),
			/* data : {
				"type" : "ys"
			}, */
			dataType : "json",
			success : function(data) {
				alert("success");
			},
			error : function(data) {
				//alert("failed");
			},
		});
		
		
	}
</script>
