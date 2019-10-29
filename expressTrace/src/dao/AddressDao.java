package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import po.Address;
import po.OrderData;
import po.PathData;
import po.Point;
import po.lujingData;

public class AddressDao extends BaseDao {

	/*
	 * // public HouseEval selectHouseEval(Position pos) { String sql =
	 * "SELECT  * FROM house where lng=? and lat=? ;"; HouseEval result = new
	 * HouseEval(); List<String> params = new ArrayList<String>();
	 * params.add(pos.getLng()); params.add(pos.getLat()); ResultSet rs =
	 * this.executeQuery(sql, params); try { while (rs.next()) {
	 * result.setLng(rs.getString("lng")); result.setLat(rs.getString("lat"));
	 * result.setPrice(rs.getLong("aver"));
	 * result.setName(rs.getString("name")); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { this.close();
	 * } return result; }
	 * 
	 * // ����������� public List<Position> selectSubways() { String sql =
	 * "SELECT DISTINCT station.lng, station.lat FROM station ;"; List<Position>
	 * result = new ArrayList<Position>(); ResultSet rs = this.executeQuery(sql,
	 * null); try { while (rs.next()) { Position po = new Position();
	 * po.setLng(rs.getString("lng")); po.setLat(rs.getString("lat")); //
	 * po.setCount(rs.getString("aver")); result.add(po); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { this.close();
	 * } return result; }
	 */
	// ����������
	public Integer addAddress(List<Address> addresses) {
		String sql = "";
		List<String> params = new ArrayList<String>();
		for (Address address : addresses) {
			sql += " insert into address(lng,lat,name) values(?,?,?); ";
			params.add(address.getPoint().getLng());
			// System.out.println(house.getLng().toString());
			// System.out.println(house.getLat().toString());
			params.add(address.getPoint().getLat());
			params.add(address.getName());
		}
		Integer result = this.executeUpdate(sql, params);
		return result;
	}

	public Integer addOrder(List<OrderData> orderdatas) {

		String sql = "";
		List<String> params = new ArrayList<String>();
		for (OrderData orderdata : orderdatas) {
			sql += " insert into express.order(eBusinessID,logisticCode,orderCode,state,success) values(?,?,?,?,?); ";
			params.add(orderdata.geteBusinessID());
			// System.out.println(house.getLng().toString());
			// System.out.println(house.getLat().toString());
			params.add(orderdata.getLogisticCode());
			params.add(orderdata.getOrderCode());
			params.add(orderdata.getState());
			params.add(orderdata.getSuccess());
		}
		Integer result = this.executeUpdate(sql, params);
		return result;
	}

	public Integer addLuj(List<lujingData> lujdatas) {
		String sql = "";
		List<String> params = new ArrayList<String>();
		for (lujingData luj : lujdatas) {
			sql += " insert into express.lujing(fromP,endP,LogisticCode,fromtime,endtime) values(?,?,?,?,?); ";
			params.add(luj.getFrom());
			// System.out.println(house.getLng().toString());
			// System.out.println(house.getLat().toString());
			params.add(luj.getEnd());
			params.add(luj.getLogisticCode());
			params.add(luj.getFromtime());
			params.add(luj.getEndtime());
		}
		Integer result = this.executeUpdate(sql, params);
		return result;
	}

	public Integer addPath(List<PathData> pathdatas) {
		String sql = "";
		List<String> params = new ArrayList<String>();
		for (PathData pathdata : pathdatas) {
			sql += " insert into express.path(AcceptStation,AcceptTime,LogisticCode) values(?,?,?); ";
			params.add(pathdata.getTrace().getAcceptStation());
			// System.out.println(house.getLng().toString());
			// System.out.println(house.getLat().toString());
			params.add(pathdata.getTrace().getAcceptTime());
			params.add(pathdata.getLogisticCode());
		}
		Integer result = this.executeUpdate(sql, params);
		return result;
	}

	public List<String> selectSomeNaughtyAddress() {

		String sql = "SELECT DISTINCT AcceptStation FROM express.path WHERE AcceptStation NOT IN (SELECT  name FROM  address);";
		List<String> result = new ArrayList<String>();
		ResultSet rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				String AcceptStation;
				AcceptStation = rs.getString("AcceptStation");
				result.add(AcceptStation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}

	public List<lujingData> selectTimePath(String time) {
		String sql = "SELECT LogisticCode, fromP, endP, fromtime, endtime FROM express.lujing WHERE fromtime <= '"
				+ time + "' AND endtime >= '" + time +"'";
		List<lujingData> result = new ArrayList<lujingData>();
		ResultSet rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				lujingData lu = new lujingData();
				lu.setLogisticCode(rs.getString("LogisticCode"));
				lu.setFrom(rs.getString("fromP"));
				lu.setEnd(rs.getString("endP"));
				lu.setFromtime(rs.getString("fromtime"));
				lu.setEndtime(rs.getString("endtime"));
				result.add(lu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}
	
	public Map<String,Integer> selectBiggestTen() {
		String sql =" SELECT COUNT(AcceptStation) AS abb, AcceptStation FROM path GROUP BY AcceptStation HAVING abb > 50";
		Map<String,Integer> result = new HashMap<String,Integer>();
		ResultSet rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				result.put(rs.getString("AcceptStation"), rs.getInt("abb"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	} 
	
	public Integer selectZeroOrOne(String fromP,String endP,String LogisticCode){
		String sql ="SELECT * FROM lujing WHERE fromP = ? AND endP = ? AND LogisticCode = ?";
		List<String> params = new ArrayList<String>();
		params.add(fromP);
		params.add(endP);
		params.add(LogisticCode);
		ResultSet rs = this.executeQuery(sql, params);
		Integer result=0;
		try {
			while (rs.next()) {
				result=1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	} 
	public List<Integer> selectPathId(String LogisticCode){
		String sql ="SELECT pathid FROM lujing2 WHERE LogisticCode = ?";
		List<String> params = new ArrayList<String>();
		List<Integer> result=new ArrayList<Integer>();
		params.add(LogisticCode);
		ResultSet rs = this.executeQuery(sql, params);
		try {
			while (rs.next()) {
				result.add(rs.getInt("pathid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	} 
	
	public List<String> selectAllLos(){
		String sql ="SELECT LogisticCode FROM express.order";
		List<String> result = new ArrayList<String>();
		
		ResultSet rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				result.add(rs.getString("LogisticCode"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	} 
	
	public List<Address> selectAllAddress(){
		String sql ="SELECT * FROM express.address";
		List<Address> result=new ArrayList<Address>();
		ResultSet rs = this.executeQuery(sql, null);
		try {
			while (rs.next()) {
				Point point=new Point(rs.getString("lat"), rs.getString("lng"));
				Address address=new Address(point, rs.getString("name"));
				result.add(address);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}
	public List<String> selectFromLujing2ForD(String LogisticCode){
		String sql ="SELECT distinct pathid FROM express.lujing2 where LogisticCode=?;";
		List<String> result=new ArrayList<String>();
		List<String> params = new ArrayList<String>();
		params.add(LogisticCode);
		ResultSet rs = this.executeQuery(sql, params);
		try {
			while (rs.next()) {
				result.add(rs.getString("pathid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}
	
	
	/*
	 * 
	 * 
	 * // ���ڵõ����еľ�γ����Ϣ
	 * 
	 * 
	 * // ���ڵõ���ѡ�ĵľ�γ����Ϣ public List<Position> selectSuitableHouse(int
	 * minPrice, int maxPrice) { String sql =
	 * "SELECT DISTINCT house.lng, house.lat,house.aver FROM house WHERE house.lng > 0 and house.aver>="
	 * + String.valueOf(minPrice) + " and house.aver<=" +
	 * String.valueOf(maxPrice) + ";"; System.out.println(sql); List<Position>
	 * result = new ArrayList<Position>(); ResultSet rs = this.executeQuery(sql,
	 * null); try { while (rs.next()) { Position po = new Position();
	 * po.setLng(rs.getString("lng")); po.setLat(rs.getString("lat"));
	 * po.setCount(rs.getString("aver")); result.add(po); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { this.close();
	 * } return result; }
	 * 
	 * // ��ݵ��� public List<Position> selectHouseBySubway(String sql) {
	 * List<Position> result = new ArrayList<Position>(); ResultSet rs =
	 * this.executeQuery(sql, null); try { while (rs.next()) { Position po = new
	 * Position(); po.setLng(rs.getString("lng"));
	 * po.setLat(rs.getString("lat")); po.setCount(rs.getString("aver"));
	 * result.add(po); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { this.close();
	 * } return result; }
	 * 
	 * // ���ڸ��·��ӵľ�γ�� public Integer updateHouses(List<House> houses) {
	 * String sql = ""; List<String> params = new ArrayList<String>(); for
	 * (House house : houses) { sql +=
	 * " update house set lng=? , lat=? where id=? ; ";
	 * params.add(house.getLng().toString()); //
	 * System.out.println(house.getLng().toString()); //
	 * System.out.println(house.getLat().toString());
	 * params.add(house.getLat().toString());
	 * params.add(house.getId().toString()); } Integer result =
	 * this.executeUpdate(sql, params); return result; }
	 */
}