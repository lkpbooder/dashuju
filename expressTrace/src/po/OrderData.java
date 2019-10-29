package po;

public class OrderData {
	private String eBusinessID;
	private String logisticCode;
	private String orderCode;
	private String state;
	private String success;

	public String geteBusinessID() {
		return eBusinessID;
	}

	public void seteBusinessID(String eBusinessID) {
		this.eBusinessID = eBusinessID;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}
