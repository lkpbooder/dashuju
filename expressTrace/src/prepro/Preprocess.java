package prepro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import po.*;

import com.alibaba.fastjson.*;

import dao.AddressDao;

public class Preprocess implements Process {

	public void doProcess() {
		// TODO Auto-generated method stub
		String content = new FileManipulation().readJson("result.json");

		List<Root> roots = JSON.parseArray(content, Root.class);
		// System.out.println(roots.size());
		CityPreprocess cityPreprocess = new CityPreprocess();
		for (int i = 0; i < roots.size(); i++) {
			List<Trace> traces = roots.get(i).getTraces();
			for (int j = 0; j < traces.size(); j++) {
				String[] temp = cityPreprocess.extractCity(traces.get(j)
						.getAcceptStation());
				traces.get(j).setAcceptStation(temp[0] + temp[1]);
			}
		}
		String rootsStr = JSON.toJSONString(roots);
		new FileManipulation().WriteJson("step1.json", rootsStr);
	}

    public static void main(String[] args) {
        AddressDao addressDao=new AddressDao();
        String time[]={"2018-04-25 02:00:00","2018-04-25 04:00:00","2018-04-25 06:00:00","2018-04-25 08:00:00","2018-04-25 10:00:00","2018-04-25 12:00:00","2018-04-25 14:00:00","2018-04-25 16:00:00","2018-04-25 18:00:00","2018-04-25 20:00:00","2018-04-25 22:00:00"};
        for(String tim:time){
        List<lujingData> lujing= addressDao.selectTimePath(tim);
        String content=JSON.toJSONString(lujing);
        new FileManipulation().WriteJson(tim+"json", content);
        }
        
        
	}

	public static void doPath(ArrayList<ArrayList<String>> lists) {

		JSONArray bigarray = new JSONArray();
		// JSONObject bigJsonObject=new JSONObject();

		for (ArrayList<String> list : lists) {
			JSONArray middlearray = new JSONArray();
			int count = 0;
			for (String path : list) {
				JSONArray smallarray = new JSONArray();
				if (count + 1 < list.size()) {
					JSONObject jsonObjectfrom = new JSONObject();
					JSONObject jsonObjectend = new JSONObject();
					jsonObjectfrom.put("name", list.get(count));
					jsonObjectfrom.put("value", 20);
					jsonObjectend.put("name", list.get(count + 1));
					jsonObjectend.put("value", 20);
					smallarray.add(jsonObjectfrom);
					smallarray.add(jsonObjectend);
					middlearray.add(smallarray);
				}
				count++;
			}
			bigarray.add(middlearray);
		}
		String rootsStr = JSON.toJSONString(bigarray);
		System.out.println(rootsStr);
		new FileManipulation().WriteJson("finalpath.json", rootsStr);

	}

	public void aa() {
		// public static void main(String[] args) {
		String content = new FileManipulation().readJson("simpleless.json");
		// System.out.println("content:="+content);
		List<SimpleStyle> roots = JSON.parseArray(content, SimpleStyle.class);
		String lngAndLats = new FileManipulation()
				.readJson("simplelngAndLat.json");
		// System.out.println("lngAndLats:="+lngAndLats);
		List<Point> points = JSON.parseArray(lngAndLats, Point.class);
		// System.out.println(roots.size());
		// System.out.println(points.size());
		// List<Root> rootscopy = new ArrayList<Root>(roots);

		// 这个是用来遭路径的如["beijing","shanghai","shenyang"]
		// ArrayList<ArrayList<String>> lists=new
		// ArrayList<ArrayList<String>>();

		ArrayList<SimplePath> lists = new ArrayList<SimplePath>();
		// ArrayList<RootAndPoint> rootAndPList = new ArrayList<RootAndPoint>();
		int count = 0;

		for (SimpleStyle root : roots) {
			SimplePath path = new SimplePath();
			SimpleStyle simpleStyle = new SimpleStyle();
			ArrayList<String> pathNames = new ArrayList<String>();
			// RootAndPoint rootAndPoint = new RootAndPoint();
			// ArrayList<Trace> newTraces = new ArrayList<Trace>();
			ArrayList<String> lats = new ArrayList<String>();
			ArrayList<String> lngs = new ArrayList<String>();
			// newTraces.add(root.getTraces().get(0));
			lats.add(points.get(count).getLat());
			lngs.add(points.get(count).getLng());
			pathNames.add(root.getAcceptStations().get(0));
			for (int j = 0; j < root.getAcceptStations().size(); j++) {
				if (j + 1 < root.getAcceptStations().size()) {
					if (!points.get(count).getLng()
							.equals(points.get(count + 1).getLng())) {
						lats.add(points.get(count + 1).getLat());
						lngs.add(points.get(count + 1).getLng());
						// newTraces.add(root.getTraces().get(j+1));
						pathNames.add(root.getAcceptStations().get(j + 1));
						/*
						 * System.out.println(root.getTraces().get(j)
						 * .getAcceptStation() + "lat:" +
						 * points.get(j).getLat());
						 */
					}
				}
				count++;/*
						 * else if (j == root.getTraces().size() - 1) {
						 * lats.add(points.get(j).getLat());
						 * lngs.add(points.get(j).getLng());
						 * newTraces.add(root.getTraces().get(j));
						 * System.out.println(root.getTraces().get(j)
						 * .getAcceptStation()); }
						 */

			}
			// root.setTraces(newTraces);
			// rootAndPoint.setRoot(root);
			// rootAndPoint.setLats(lats);
			// rootAndPoint.setLngs(lngs);
			// rootAndPList.add(rootAndPoint);
			simpleStyle.setAcceptStations(pathNames);
			path.setLats(lats);
			path.setLngs(lngs);
			path.setSimpleStyle(simpleStyle);
			lists.add(path);
		}

		// String str = JSON.toJSONString(rootAndPList);
		// new FileManipulation().WriteJson("originalPath.json", str);
		// doPath(lists);
		String str2 = JSON.toJSONString(lists);
		new FileManipulation().WriteJson("SimpleoriginalPathStep2.json", str2);

		/*
		 * JSONObject object=new JSONObject(); for (RootAndPoint rap :
		 * rootAndPList) { int i = 0; for (Trace tce :
		 * rap.getRoot().getTraces()) { ArrayList<Double>v=new
		 * ArrayList<Double>(); v.add(Double.parseDouble(rap.getLngs().get(i)));
		 * v.add(Double.parseDouble(rap.getLats().get(i)));
		 * object.put(tce.getAcceptStation(), v); i++; } }
		 */

		/*
		 * JSONArray bjsonArray = new JSONArray(); for (RootAndPoint rap :
		 * rootAndPList) { JSONArray jsonArray = new JSONArray(); int i = 0; for
		 * (int j = 0; j + 1 < rap.getRoot().getTraces().size(); j++) { Trace
		 * trS = rap.getRoot().getTraces().get(j); Trace trE =
		 * rap.getRoot().getTraces().get(j + 1); JSONObject joS = new
		 * JSONObject(); JSONObject joE = new JSONObject(); joS.put("name",
		 * trS.getAcceptStation()); joS.put("value", 50); joE.put("name",
		 * trE.getAcceptStation()); joE.put("value", 50); jsonArray.add(joS);
		 * i++; } bjsonArray.add(jsonArray); }
		 */

		/*
		 * String rootsStr = JSON.toJSONString(rootAndPList);
		 * System.out.println(rootsStr);
		 */
		// String rootsStr = JSON.toJSONString(object);
		// System.out.println(rootsStr);
		// new FileManipulation().WriteJson("address.json", rootsStr);

	}

}
