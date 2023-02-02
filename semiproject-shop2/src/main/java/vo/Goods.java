package vo;

public class Goods {
	private int goodsCode;
	private String goodsName;
	private int goodsPrice;
	private String soldout;
	private String empId;
	private String hit;
	private String createdate;
	public Goods(int goodsCode, String goodsName, int goodsPrice, String soldout, String empId, String hit,
			String createdate) {
		super();
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.soldout = soldout;
		this.empId = empId;
		this.hit = hit;
		this.createdate = createdate;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Goods [goodsCode=" + goodsCode + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", soldout=" + soldout + ", empId=" + empId + ", hit=" + hit + ", createdate=" + createdate + "]";
	}
}
