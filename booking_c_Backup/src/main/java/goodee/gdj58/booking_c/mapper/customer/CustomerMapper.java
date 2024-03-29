package goodee.gdj58.booking_c.mapper.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.booking_c.vo.Booking;
import goodee.gdj58.booking_c.vo.BookingOption;
import goodee.gdj58.booking_c.vo.Customer;
import goodee.gdj58.booking_c.vo.CustomerImg;
import goodee.gdj58.booking_c.vo.Review;
import goodee.gdj58.booking_c.vo.ReviewImg;
import goodee.gdj58.booking_c.vo.TotalId;

@Mapper
public interface CustomerMapper {
	// 1년전 날짜 가져오기
	public int customerLastLogin(String id);
	// 마지막 로그인 일자 가져오기
	List<Map<String, Object>> selectLastLogin();
	// 휴면계정 전환
	public int updateCustomerActive(TotalId totalId);
	// 마지막 로그인일자 업데이트
	public int updateCustomerLastLogin(String customerId);
	// 리뷰 입력(사진)
	public int insertReviewImg(ReviewImg reviewImg);
	// 리뷰 입력
	public int insertReview(Review review);
	// 예약 내역 상세보기
	ArrayList<Map<String, Object>> getBookingOne(String customerId, String bookingCompanyName, String bookingRequestDate);
	// 고객 비밀번호 찾기
	int updateCustomerPw(Customer customer);
	// 고객 아이디 찾기
	String selectCustomerId(Customer customer);
	// 고객 아이디/비밀번호 찾기시 이메일 조회
	int selectCustomerEmail(Customer customer);
	// 고객 로그인
	Customer selectCustomer(Customer customer);
	// 고객 회원가입(토탈ID)
	public int insertTotalId(TotalId totalId);
	// 고객 회원가입(사진)
	public int insertCustomerImg(CustomerImg customerImg);
	// 고객 회원가입
	public int insertCustomer(Customer customer);
	
	
	
	
	
	
	
	
	
	//예약 확정 페이지 상품 사진
	ArrayList<Map<String,Object>> bookingAfterProductImg(Map<String,Object> paramMap);
	//예약 확정시 포인트+페이 업데이트
	int updatePointAfterBooking(Map<String, Object> paramMap );
	
	//날짜형 예약시 기간 날짜 출력
	ArrayList<Map<String,Object>> bookingDayList(String startDate, String endDate);
	//날짜형 예약시 일 수 구하기
	int dayCalculation (String startDate, String endDate); 
	//결제 후 예약 내역 등록
	int addBooking(Booking booking);
	//옵션리스트
	ArrayList<BookingOption> getBookingOptionList();
	//결제페이지 - 사업자 정보
	Map<String, Object> getBookingPaymentCompany(String bkcId);
	//결제 페이지 상품 옵션 가격구하기
	Integer totalOptionPrice(int bkpNo, ArrayList<Integer> paramMap);
	//예약시 상품 시간 리스트
	ArrayList<Map<String, Object>> getBookingProductTimeList(String bkcId);
	//에약시 상품정보 확인
	Map<String,Object> getBookingProductInfo(String bkpName, String bkcId);
	//상품예약 옵션 리스트
	ArrayList<Map<String, Object>> getBookingProductOptionList(Map<String, Object> paramMap);
	//상품예약 시간 선택 페이지 
	ArrayList<Map<String, Object>> getBookingProductSelectTime(String bkcId);
	//예약업체 세부정보 지도
	ArrayList<Map<String, Object>> getBookingCompanyDetailMap(String bkcId);
	//예약업체 세부정보 리뷰
	ArrayList<Map<String, Object>> getBookingCompanyDetailReview(String bkcId);
	//예약업체 세부정보 예약
	ArrayList<Map<String, Object>> getBookingCompanyDetailBooking(String bkcId);
	//예약업체 세부정보 홈
	ArrayList<Map<String, Object>> getBookingCompanyDetailHome(String bkcId);
	//예약업체 세부정보 공통
	Map<String, Object> getBookingCompanyDetailCommon(Map<String, Object> paramMap);
	//예약업체 총 수
	int bookingCompanyCount(String searchWord);
	//예약업체 리스트 출력
	ArrayList<Map<String, Object>> getBookingCompanyList(Map<String, Object> paramMap);
}
