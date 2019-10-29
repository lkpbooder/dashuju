package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.AddressDao;
import po.Address;
import po.Root;
import po.lujingData;
import prepro.FileManipulation;

public class MakePoint2 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MakePoint2() {
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
		doPost(request, response);

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

/*		AddressDao addressDao = new AddressDao(); // Vector<List<lujingData>>
		// v = new Vector<List<lujingData>>();
		for (int i = 0; i < 12; i++) {
			String hourstr = "";
			Integer h = new Integer(2 * i + 1);
			if (h < 10)
				hourstr = "0" + h.toString();
			else
				hourstr = h.toString();
			hourstr = hourstr + ":00:00";
			String time = "2018-04-25 " + hourstr;
			System.out.println(time);
			List<lujingData> lujing = addressDao.selectTimePath(time);
			Map<String, Integer> map = addressDao.selectBiggestTen();
			JSONArray big = new JSONArray();
			for (int j = 0; j < lujing.size(); j++) {
				lujingData lujingdata = lujing.get(j);
				JSONArray medium = new JSONArray();
				JSONArray between = new JSONArray();
				JSONObject lu1 = new JSONObject();
				JSONObject lu2 = new JSONObject();
				lu1.put("name", lujingdata.getFrom());
				lu1.put("time", lujingdata.getFromtime());
				if (map.containsKey(lujingdata.getFrom()))
					lu1.put("value", map.get(lujingdata.getFrom()));
				else
					lu1.put("value", 30);

				lu1.put("LogisticCode", lujingdata.getLogisticCode());
				lu2.put("name", lujingdata.getEnd());
				lu2.put("time", lujingdata.getEndtime());

				if (map.containsKey(lujingdata.getEnd()))
					lu2.put("value", map.get(lujingdata.getEnd()));
				else
					lu2.put("value", 30);
				lu2.put("LogisticCode", lujingdata.getLogisticCode());
				medium.add(lu1);
				medium.add(lu2);
				between.add(medium);
				big.add(between);
			}
			new FileManipulation().WriteJson(new Integer(2 * i + 1).toString()
					+ ".json", JSON.toJSONString(big));
		}
		List<Address>addresses=new AddressDao().selectAllAddress();
		JSONObject big=new JSONObject();
		for(Address address:addresses){
			JSONArray small=new JSONArray();
			small.add(Double.parseDouble(address.getPoint().getLng()));
			small.add(Double.parseDouble(address.getPoint().getLat()));
		    big.put(address.getName(), small);
		}
		new FileManipulation().WriteJson("SimplelngAndLat.json", JSON.toJSONString(big));*/
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String path = new FileManipulation().readJson("1.json");
		String address = new FileManipulation()
				.readJson("SimplelngAndLat.json");
		String path2 = new FileManipulation().readJson("3.json");
		String path3 = new FileManipulation().readJson("5.json");
		String path4 = new FileManipulation().readJson("7.json");
		String path5 = new FileManipulation().readJson("9.json");
		String path6 = new FileManipulation().readJson("11.json");
		String path7 = new FileManipulation().readJson("13.json");
		String path8 = new FileManipulation().readJson("15.json");
		String path9 = new FileManipulation().readJson("17.json");
		String path10 = new FileManipulation().readJson("19.json");
		String path11 = new FileManipulation().readJson("21.json");
		String path12 = new FileManipulation().readJson("23.json");

		/*
		 * ArrayList<Trace> data1=new ArrayList<Trace>(); for (int i = 0; i <
		 * 100; i++) { System.out.println(roots.get(i).getTraces().get(0)); }
		 */

		// System.out.println(JSON.toJSONString(roots));
		request.getSession().setAttribute("data", path);
		request.getSession().setAttribute("data2", path2);
		request.getSession().setAttribute("data3", path3);
		request.getSession().setAttribute("data4", path4);
		request.getSession().setAttribute("data5", path5);
		request.getSession().setAttribute("data6", path6);
		request.getSession().setAttribute("data7", path7);
		request.getSession().setAttribute("data8", path8);
		request.getSession().setAttribute("data9", path9);
		request.getSession().setAttribute("data10", path10);
		request.getSession().setAttribute("data11", path11);
		request.getSession().setAttribute("data12", path12);

		request.getSession().setAttribute("address", address);
		RequestDispatcher rd = request.getRequestDispatcher("example.jsp");
		rd.forward(request, response);

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
