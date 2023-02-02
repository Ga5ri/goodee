package vo;

public class PwHistory {

	private String customerId;
	private String pw;
	private String createdate;
	
	public PwHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PwHistory(String customerId, String pw, String createdate) {
		super();
		this.customerId = customerId;
		this.pw = pw;
		this.createdate = createdate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "PwHistory [customerId=" + customerId + ", pw=" + pw + ", createdate=" + createdate + "]";
	}
	
	
	
	
	
}
