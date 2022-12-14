package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.QuestionDao;
import util.DBUtil;
import vo.Customer;
import vo.Question;

public class QuestionService {
	private QuestionDao questionDao;
	
	// questionListUser 출력
	// 사용하는 곳 : questionListUserController
	public ArrayList<HashMap<String, Object>> getQuestionListUserByPage(int beginRow, int rowPerPage, Customer loginCustomer) {
		this.questionDao = new QuestionDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			list = questionDao.selectQuestionLisUsertByPage(conn, beginRow, rowPerPage, loginCustomer);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return list;
	}
		
	// modifyQuestion (문의글 수정)
	// 사용하는 곳 : modifyQuestionController	
	public int modifyQuestion(Question modifyQuestion) {
		int resultRow=0;
		this.questionDao = new QuestionDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			resultRow = questionDao.modifyQuestion(conn, modifyQuestion);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return resultRow;
	}
	
	// removeQuestion (문의글 삭제)
	// 사용하는 곳 : removeQuestionController	
	public int removeQuestion(Customer loginCustomer, int questionCode) {
		int resultRow=0;
		this.questionDao = new QuestionDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			resultRow = questionDao.removeQuestion(conn, loginCustomer, questionCode);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return resultRow;
	}
	
	// questionOne (수정,삭제 메뉴 활성/비활성 = 세션의 로그인아이디와 오더코드의 작성자 아이디 일치시)  
	// 사용하는 곳 : questionOneController
	public String getQuestionOneCustomerIdByOrderCode(int ordersCode) {
		this.questionDao = new QuestionDao();
		String customerId = null;
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			customerId = questionDao.selectQuestionOneCustomerIdByOrderCode(conn, ordersCode);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return customerId;
	}
	
	// questionOne 출력
	// 사용하는 곳 : questionOneController
	public HashMap<String, Object> getQuestionOne(int questionCode) {
		this.questionDao = new QuestionDao();
		HashMap<String, Object> q = null;
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			q = questionDao.selectQuestionOne(conn, questionCode);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return q;
	}
	
	// addQuestion (문의글 추가)
	// 사용하는 곳 : addQuestionController	
	public int addQuestion(Question addQuestion) {
		int resultRow = 0;
		this.questionDao = new QuestionDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			resultRow = questionDao.addQuestion(conn, addQuestion);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return resultRow;
	}
	
	// addQuestion (ordersCode, goodsName 조회)
	// 사용하는 곳 : addQuestionController	
	public ArrayList<HashMap<String, Object>> selectOrdersCode(Customer loginCustomer) {
		this.questionDao = new QuestionDao();
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			list = new ArrayList<HashMap<String, Object>>();
			
			questionDao = new QuestionDao();
			list = questionDao.selectOrdersCode(conn, loginCustomer);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}
	// questionList 출력
	// 사용하는 곳 : questionListController
	public ArrayList<HashMap<String, Object>> getQuestionListByPage(int beginRow, int rowPerPage) {
		this.questionDao = new QuestionDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			list = questionDao.selectQuestionListByPage(conn, beginRow, rowPerPage);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return list;
	}
	
	// questionList 페이징
	// 사용하는 곳 : questionListController
		public int count() {
			  this.questionDao = new QuestionDao();
			  int cnt = 0;
		      Connection conn = null;
		      try {
		         conn = DBUtil.getConnection();
		         questionDao = new QuestionDao();
		         cnt = questionDao.count(conn);
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e1) {
		            e1.printStackTrace();
		         }
		      }
		      return cnt;
		}
}
