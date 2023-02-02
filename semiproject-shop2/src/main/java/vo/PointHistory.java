package vo;

public class PointHistory {
	private int orderCode;
	private String pointKind;
	private int point;
	private String createdate;
	
	public PointHistory() {
		super();
	}

	public PointHistory(int orderCode, String pointKind, int point, String createdate) {
		super();
		this.orderCode = orderCode;
		this.pointKind = pointKind;
		this.point = point;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "point_history [orderCode=" + orderCode + ", pointKind=" + pointKind + ", point=" + point
				+ ", createdate=" + createdate + "]";
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public String getPointKind() {
		return pointKind;
	}

	public void setPointKind(String pointKind) {
		this.pointKind = pointKind;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}