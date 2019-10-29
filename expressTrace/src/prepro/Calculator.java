package prepro;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.AddressDao;
import po.Point;
import po.NoRepeatPath;
import po.OrderOnlyHasLogi;

public class Calculator {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		public void a(){ 
		String a = new FileManipulation().readJson("out.json", "utf-8");
		Calculator calculator = new Calculator();
		// 原来读的数据
		JSONArray paths = JSON.parseArray(a);
		// 新打开的数据
		JSONArray newpaths = new JSONArray();
		for (int i = 0; i < paths.size(); i++) {
			JSONObject object = paths.getJSONObject(i);
			JSONObject newobject = new JSONObject();
			JSONArray path = object.getJSONArray("Paths");
			JSONArray newpath = new JSONArray();

			for (int j = 0; j < path.size(); j++) {
				JSONObject between = path.getJSONObject(j);
				JSONObject newbetween = new JSONObject();
				newbetween.put("Source", between.get("Source"));
				newbetween.put("Destination", between.get("Destination"));
				String SourceIndex = between.getString("SourceIndex");
				String DestinationIndex = between.getString("DestinationIndex");
				Point start = new Point(SourceIndex.split(",")[1],
						SourceIndex.split(",")[0]);
				Point end = new Point(DestinationIndex.split(",")[1],
						DestinationIndex.split(",")[0]);
				String Distance = calculator.getLatLngDistance(start, end);
				String DuringTime = between.getString("DuringTime");
				String category = calculator.doFilter(Distance, DuringTime);
				newbetween.put("category", category);
				newbetween.put("DuringTime", DuringTime);
				newpath.add(newbetween);
				newobject.put("Paths", newpath);
			}
			newpaths.add(newobject);
		}
		String content = newpaths.toJSONString();
		System.out.println(content);
		new FileManipulation().WriteJson("outWithCategory.json", content);
	}

	public String getLatLngDistance(Point start, Point end) {
		/*
		 * String url = "http://api.map.baidu.com/routematrix/v2/riding";// 骑行接口
		 * // 接口参数 String param = "coord_type=gcj02&output=json&origins=" +
		 * start.getLat() + "," + start.getLng() + "&destinations=" +
		 * end.getLat() + "," + end.getLng() +
		 * "&ak=VGDs0twlaDEdMMvD49SM1xp6zZAIWeXD"; Integer m =
		 * EstimateUtils.getEstimatedDistance(url, param); Double km = m /
		 * 1000d; System.out.println(km); return (km.toString());
		 */
		Double Lat1 = rad(Double.parseDouble(start.getLat())); // 纬度
		Double Lat2 = rad(Double.parseDouble(end.getLat()));
		Double Lng1 = rad(Double.parseDouble(start.getLng()));
		Double Lng2 = rad(Double.parseDouble(end.getLng()));
		Double a = Math.abs(Lat1 - Lat2);// 两点纬度之差
		Double b = Math.abs(Lng1 - Lng2); // 经度之差
		Double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(Lat1) * Math.cos(Lat2)
				* Math.pow(Math.sin(b / 2), 2)));// 计算两点距离的公式
		s = s * 6378137.0;// 弧长乘地球半径（半径为米）
		s = Math.round(s * 10000d) / 10000d / 1000;// 精确距离的数值
		System.out.println(s.toString());
		return s.toString();
	}

	// 直接用来显示时间和跨度
	public String doFilter(String Distance, String DuringTime) {

		if (Double.parseDouble(Distance) / Double.parseDouble(DuringTime)
				* 3600 > 120)
			return "空运";
		else
			return "货车";
	}

	public String[] spiltStr(String origin) {

		String[] str = origin.split(",");
		return str;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.00; // 角度转换成弧度
	}
	public static void main(String[] args) {

     
	}
	
	

}
