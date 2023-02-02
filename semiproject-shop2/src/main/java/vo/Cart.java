package vo;

public class Cart {

	private int goodsCode;
	private String customerId;
	private int cartQuantity;
	private String createdate;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int goodsCode, String customerId, int cartQuantity, String createdate) {
		super();
		this.goodsCode = goodsCode;
		this.customerId = customerId;
		this.cartQuantity = cartQuantity;
		this.createdate = createdate;
	}

	public int getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Cart [goodsCode=" + goodsCode + ", customerId=" + customerId + ", cartQuantity=" + cartQuantity
				+ ", createdate=" + createdate + "]";
	}
	
	
	
}
