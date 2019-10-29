package controller;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import po.Address;
import po.OrderData;
import po.PathData;
import po.Point;
import po.Root;
import po.SimpleStyle;
import po.Trace;
import po.lujingData;
import prepro.CityPreprocess;
import prepro.FileManipulation;
import dao.AddressDao;

public class AddressDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddressDo() {
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
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		AddressDao addressDao = new AddressDao();
		String addressStr = new FileManipulation()
				.readJson("SimplelngAndLat.json");
		JSONObject jsonObject = JSON.parseObject(addressStr);
		List<Address> addresses = new ArrayList<Address>();
		for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
			String name = entry.getKey();
			JSONArray p = (JSONArray) entry.getValue();
			Point point = new Point(p.get(1).toString(), p.get(0).toString());
			Address address = new Address(point, name);
			addresses.add(address);
		}
		addressDao.addAddress(addresses);*/
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

		String content = new FileManipulation().readJson("step1.json",
				"utf-8");
		List<Root> roots = JSON.parseArray(content, Root.class);
		roots=roots.subList(0, 1000);
		// System.out.println(roots.size());
		CityPreprocess cityPreprocess = new CityPreprocess();
		JSONArray big = new JSONArray();
		List<lujingData> newlujdata =new ArrayList<lujingData>();
		for (int i = 0; i < roots.size(); i++) {
		    Root root=roots.get(i);
			List<Trace> traces = roots.get(i).getTraces();
			for (int j = 0; j+1< traces.size(); j++) {
				if(!traces.get(j).getAcceptStation().equals(traces.get(j+1).getAcceptStation())){
					lujingData luj=new lujingData();
					luj.setFrom(traces.get(j).getAcceptStation());
					luj.setEnd(traces.get(j+1).getAcceptStation());
					luj.setFromtime(traces.get(j).getAcceptTime());
					luj.setEndtime(traces.get(j+1).getAcceptTime());
					luj.setLogisticCode(root.getLogisticCode());
					newlujdata.add(luj);
				}
			}
		}
		new FileManipulation().WriteJson("lujingdata.txt", JSON.toJSONString(newlujdata));
		new AddressDao().addLuj(newlujdata);
		//System.out.println();
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
