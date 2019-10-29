package po;

import java.util.ArrayList;

public class Point {
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	private String lat;
	private String lng;

	public Point(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public Point(){
		super();
	}


	
	

}
