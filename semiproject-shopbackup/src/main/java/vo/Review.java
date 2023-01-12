package vo;

public class Review {
	private int orederCode;
	private String reviewMemo;
	private String createdate;
	
	public Review() {
		super();
	}

	public Review(int orederCode, String reviewMemo, String createdate) {
		super();
		this.orederCode = orederCode;
		this.reviewMemo = reviewMemo;
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "review [orederCode=" + orederCode + ", reviewMemo=" + reviewMemo + ", createdate=" + createdate + "]";
	}

	public int getOrederCode() {
		return orederCode;
	}

	public void setOrederCode(int orederCode) {
		this.orederCode = orederCode;
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
}
