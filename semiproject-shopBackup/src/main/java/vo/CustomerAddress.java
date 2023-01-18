package vo;

public class CustomerAddress {

	private int addressCode;
	private String customerId;
	private String address;
	private String createdate;
	
	public CustomerAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerAddress(int addressCode, String customerId, String address, String createdate) {
		super();
		this.addressCode = addressCode;
		this.customerId = customerId;
		this.address = address;
		this.createdate = createdate;
	}

	public int getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "CustomerAddress [addressCode=" + addressCode + ", customerId=" + customerId + ", address=" + address
				+ ", createdate=" + createdate + "]";
	}
	
	
	
}
