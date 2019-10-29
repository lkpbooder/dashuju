package po;

import java.util.List;

import po.Trace;
public class Root {
	private String LogisticCode;

	private String ShipperCode;

	private List<Trace> Traces;

	private String State;

	private String OrderCode;

	private String EBusinessID;

	private boolean Success;

	public void setLogisticCode(String LogisticCode) {
		this.LogisticCode = LogisticCode;
	}

	public String getLogisticCode() {
		return this.LogisticCode;
	}

	public void setShipperCode(String ShipperCode) {
		this.ShipperCode = ShipperCode;
	}

	public String getShipperCode() {
		return this.ShipperCode;
	}

	public void setTraces(List<Trace> Traces) {
		this.Traces = Traces;
	}

	public List<Trace> getTraces() {
		return this.Traces;
	}

	public void setState(String State) {
		this.State = State;
	}

	public String getState() {
		return this.State;
	}

	public void setOrderCode(String OrderCode) {
		this.OrderCode = OrderCode;
	}

	public String getOrderCode() {
		return this.OrderCode;
	}

	public void setEBusinessID(String EBusinessID) {
		this.EBusinessID = EBusinessID;
	}

	public String getEBusinessID() {
		return this.EBusinessID;
	}

	public void setSuccess(boolean Success) {
		this.Success = Success;
	}

	public boolean getSuccess() {
		return this.Success;
	}
	
}