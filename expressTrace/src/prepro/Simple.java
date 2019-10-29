package prepro;

import java.util.ArrayList;
import java.util.List;

import po.SimpleStyle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Simple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = new FileManipulation().readJson("less.json");
		 System.out.println("content:="+content);
		List<SimpleStyle> roots = JSON.parseArray(content, SimpleStyle.class);
		JSONArray bigarray=new JSONArray();
		for(SimpleStyle list :roots){
			JSONArray middlearray=new JSONArray();
			int count=0;
			for(String path: list.getAcceptStations()){
				JSONArray smallarray=new JSONArray();
				if(count+1<list.getAcceptStations().size()){
					JSONObject jsonObjectfrom=new JSONObject();
					JSONObject jsonObjectend=new JSONObject();
					jsonObjectfrom.put("name", list.getAcceptStations().get(count));
					jsonObjectfrom.put("value", 20);
					jsonObjectend.put("name", list.getAcceptStations().get(count+1));
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
		new FileManipulation().WriteJson("finalpath2.json", rootsStr);
		
	}
	

	
	

}
