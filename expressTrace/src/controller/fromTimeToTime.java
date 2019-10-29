package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.NoRepeatPath;
import po.OrderOnlyHasLogi;
import prepro.FileManipulation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.AddressDao;

public class fromTimeToTime extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public fromTimeToTime() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*String ordermap = new FileManipulation().readJson("OrderMap.json",
				"utf-8");
		String noRepeatPath = new FileManipulation().readJson(
				"norepeatPath.json", "utf-8");
		List<OrderOnlyHasLogi> ordermaps = JSON.parseArray(ordermap,
				OrderOnlyHasLogi.class);
		int sizeO = ordermaps.size();
		List<NoRepeatPath> noRepeatPaths = JSON.parseArray(noRepeatPath,
				NoRepeatPath.class);
		int sizeP = noRepeatPaths.size();
		System.out.println("sizeO=" + sizeO);
		Map<Integer, Integer[]> allroadvec = new HashMap<Integer, Integer[]>();
		for (int i = 0; i < sizeP; i++) {
			NoRepeatPath norepeat = noRepeatPaths.get(i);
			Integer[] roadvec = new Integer[1000];
			System.out.println(roadvec.length);
			for (int j = 0; j < sizeO; j++) {
				roadvec[j] = (new AddressDao().selectZeroOrOne(
						norepeat.getFromP(), norepeat.getEndP(),
						ordermaps.get(j).getLogisticCode()));
			}
			allroadvec.put(i, roadvec);
		}
		new FileManipulation().WriteJson("guijishuju.json",
				JSON.toJSONString(allroadvec));

		List<String> logs = new AddressDao().selectAllLos();
		JSONArray big = new JSONArray();
		for (String LogisticCode : logs) {
			JSONArray small = new JSONArray();
			List<Integer> data = new AddressDao().selectPathId(LogisticCode);
			for (Integer pathid : data) {
				small.add(pathid.toString());
			}
			big.add(small);
		}
		new FileManipulation().WriteJson("wordtovec.json",
				JSON.toJSONString(big));*/
		
		//用来做所有路径的bundle
		List<String> los=new AddressDao().selectAllLos();
		System.out.println("los.size()="+los.size());;
		
		int wordvec[][]=new int[1000][1990];
		for(int i=0;i<wordvec.length;i++){
				//找出关键路径，0-1hot point
			//keypath:表示那些是关键路径
				List <String> keypath=new AddressDao().selectFromLujing2ForD(los.get(i));
				for(int j=0;j<keypath.size();j++){
				Integer index=Integer.parseInt(keypath.get(j));
				System.out.println("index="+index);
				wordvec[i][index]=1;
				}
		    }
		new FileManipulation().WriteJson("hotpoint.json",
				JSON.toJSONString(wordvec));
		}
	

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
