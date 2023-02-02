package vo;

public class Review {
	private int rnum;
	private int orderCode;
	private int goodsCode;
	private String goodsName;
	private String customerId;
	private int point;
	private String reviewMemo;
	private String createdate;
	private String filename;
	
	public Review() {
		super();
	}
	
	public Review(int rnum, int orderCode, int goodsCode, String goodsName, String customerId, int point,
			String reviewMemo, String createdate, String filename) {
		super();
		this.rnum = rnum;
		this.orderCode = orderCode;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.customerId = customerId;
		this.point = point;
		this.reviewMemo = reviewMemo;
		this.createdate = createdate;
		this.filename = filename;
	}
	
	@Override
	public String toString() {
		return "Review [rnum=" + rnum + ", orderCode=" + orderCode + ", goodsCode=" + goodsCode + ", goodsName="
				+ goodsName + ", customerId=" + customerId + ", point=" + point + ", reviewMemo=" + reviewMemo
				+ ", createdate=" + createdate + ", filename=" + filename + "]";
	}
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getReviewMemo() {
		return reviewMemo;
	}
	public void setReviewMemo(String reviewMemo) {
		this.reviewMemo = reviewMemo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
