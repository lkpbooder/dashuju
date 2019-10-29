package po;

public class Address {
private Point point;
private String name;
public Point getPoint() {
	return point;
}
public void setPoint(Point point) {
	this.point = point;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Address() {
	super();
	// TODO Auto-generated constructor stub
}
public Address(Point point, String name) {
	super();
	this.point = point;
	this.name = name;
}

}
