package vo;

public class Notice {
	private int noticeCode;
	private String noticeTitle;
	private String noticeContent;
	private String empId;
	private String createdate;
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int noticeCode, String noticeTitle, String noticeContent, String empId, String createdate) {
		super();
		this.noticeCode = noticeCode;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.empId = empId;
		this.createdate = createdate;
	}
	public int getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Notice [noticeCode=" + noticeCode + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", empId=" + empId + ", createdate=" + createdate + "]";
	}
}
