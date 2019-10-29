package prepro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class EstimateUtils {
	public static String getEstimatedData(String url,String param){
		String result="";//���ʷ��ؽ��
		BufferedReader read=null;//��ȡ���ʽ��
		try {
			//����url
		    URL realurl=new URL(url+"?"+param);
		    //������
		    URLConnection connection=realurl.openConnection();
		    // ����ͨ�õ���������
		    connection.setRequestProperty("accept", "*/*");
		    connection.setRequestProperty("connection", "Keep-Alive");
		    //��������
		    connection.connect();
		    // ���� BufferedReader����������ȡURL����Ӧ
		    read = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
		    String line;//ѭ����ȡ
		    while ((line = read.readLine()) != null) {
		    	result += line;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		  }finally{
			    if(read!=null){//�ر���
			    	try {
			    		read.close();
			    	} catch (IOException e) {
			    		e.printStackTrace();
			    	  }
			    }
		   }
		   return result; 
	}
//获得预估时间	
	public static Long getEstimatedTime(String url,String param){
		String result = getEstimatedData(url,param);
		JSONObject json = new JSONObject(result);
		String result2 = ((json.getJSONArray("result")).opt(0)).toString();
		JSONObject json2 = new JSONObject(result2);
		Long estimatedtime = (long) ((json2.getJSONObject("duration")).getInt("value"));
		return estimatedtime;
	}
//获得预估距离	
	public static int getEstimatedDistance(String url,String param){
		String result = getEstimatedData(url,param);
		JSONObject json = new JSONObject(result);
		String result2 = ((json.getJSONArray("result")).opt(0)).toString();
		JSONObject json2 = new JSONObject(result2);
		int estimateddistance =  ((json2.getJSONObject("distance")).getInt("value"));
		return estimateddistance;
	}
}
