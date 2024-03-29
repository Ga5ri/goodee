package goodee.gdj58.booking_c.mapper.myPage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.booking_c.vo.Customer;
import goodee.gdj58.booking_c.vo.CustomerImg;
import goodee.gdj58.booking_c.vo.PaySaveHistory;
import goodee.gdj58.booking_c.vo.Report;

@Mapper
public interface MyPageMapper {
	int updateCustomerImg(CustomerImg customerImg); // 고객 프로필사진 수정
	int updateCustomerOne(Customer Customer); // 고객 정보 수정
	int updatePay(PaySaveHistory paySaveHistory); // 회원 탈퇴 시 보유 페이 0
	int deactiveCustomer(Customer customer); // 회원 탈퇴
	int payCnt(Map<String, Object> paramMap); // 페이징용 페이 리스트 데이터 개수
	List<Map<String, Object>> payList(Map<String, Object> paramMap); // 예약 + 충전 관련 페이 리스트
	int pointCnt(Map<String, Object> paramMap); // 페이징용 포인트 리스트 데이터 개수
	List<Map<String, Object>> pointList(Map<String, Object> paramMap); // 예약 + 이벤트 관련 포인트 리스트
	int reviewCnt(String customerId); // 페이징용 리뷰 리스트 데이터 개수
	List<Map<String, Object>> reviewList(Map<String, Object> paramMap); // 리뷰 리스트
	int insertReport(Report report); // 예약에 대한 신고
	int bookingCnt(Map<String, Object> paramMap); // 페이징용 예약 내역 데이터 개수
	List<Map<String, Object>> bookingList(Map<String, Object> paramMap); // 예약 내역 리스트
	Map<String, Object> customerOne(String customerId); // 고객 상세정보
}