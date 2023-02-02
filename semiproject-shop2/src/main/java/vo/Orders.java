package vo;

public class Orders {
	private int orderCode;
	private int goodsCode;
	private String goodsName;
	private int goodsPrice;
	private String soldout;
	private String customerId;
	private String customerName;
	private String customerPhone;
	private int addressCode;
	private String address;
	private int orderQuantity;
	private int orderPrice;
	private String orderState;
	private String createdate;
	private String pointKind;
	private int customerPoint;
	private int point;
	private String filename;
	
	public Orders() {
		super();
	}

	public Orders(int orderCode, int goodsCode, String goodsName, int goodsPrice, String soldout, String customerId,
			String customerName, String customerPhone, int addressCode, String address, int orderQuantity,
			int orderPrice, String orderState, String createdate, String pointKind, int customerPoint, int point,
			String filename) {
		super();
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.soldout = soldout;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.addressCode = addressCode;
		this.address = address;
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
		this.orderState = orderState;
		this.createdate = createdate;
		this.pointKind = pointKind;
		this.customerPoint = customerPoint;
		this.point = point;
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Orders [orderCode=" + orderCode + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", goodsPrice=" + goodsPrice + ", soldout=" + soldout + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", addressCode=" + addressCode
				+ ", address=" + address + ", orderQuantity=" + orderQuantity + ", orderPrice=" + orderPrice
				+ ", orderState=" + orderState + ", createdate=" + createdate + ", pointKind=" + pointKind
				+ ", customerPoint=" + customerPoint + ", point=" + point + ", filename=" + filename + "]";
	}

	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getSoldout() {
		return soldout;
	}
	public void setSoldout(String soldout) {
		this.soldout = soldout;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public int getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(int addressCode) {
		this.addressCode = addressCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getPointKind() {
		return pointKind;
	}
	public void setPointKind(String pointKind) {
		this.pointKind = pointKind;
	}
	public int getCustomerPoint() {
		return customerPoint;
	}
	public void setCustomerPoint(int customerPoint) {
		this.customerPoint = customerPoint;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}

