package vo;

public class GoodsImg {
	private int goodsCode;
	private String filename;
	private String originName;
	private String contentType;
	private String createdate;
	public GoodsImg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsImg(int goodsCode, String filename, String originName, String contentType, String createdate) {
		super();
		this.goodsCode = goodsCode;
		this.filename = filename;
		this.originName = originName;
		this.contentType = contentType;
		this.createdate = createdate;
	}
	public int getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(int goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "GoodsImg [goodsCode=" + goodsCode + ", filename=" + filename + ", originName=" + originName
				+ ", contentType=" + contentType + ", createdate=" + createdate + "]";
	}
	
}
