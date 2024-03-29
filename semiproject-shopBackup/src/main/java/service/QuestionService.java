package service;

import java.io.File;
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
	public ArrayList<HashMap<String, Object>> getQuestionListUserByPage(int beginRow, int rowPerPage, Customer loginCustomer, String word) {
		this.questionDao = new QuestionDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			list = questionDao.selectQuestionLisUsertByPage(conn, beginRow, rowPerPage, loginCustomer, word);
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
		public int countUser(Customer loginCustomer) {
			  this.questionDao = new QuestionDao();
			  int cnt = 0;
		      Connection conn = null;
		      try {
		         conn = DBUtil.getConnection();
		         questionDao = new QuestionDao();
		         cnt = questionDao.countUser(conn, loginCustomer);
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
	// modifyQuestion (문의글 수정)
	// 사용하는 곳 : modifyQuestionController	
	public int modifyQuestion(Question modifyQuestion, String dir) {
		HashMap<String, Object> q = null;
		int resultRow=0;
		this.questionDao = new QuestionDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			// 수정되는 파일이 있을사 기존 파일 삭제 후 업데이트
			int questioncode = modifyQuestion.getQuestionCode();
			q = questionDao.selectQuestionOne(conn, questioncode);
			File f = new File(dir+"\\"+(String)q.get("questionImg"));
			if(f.exists()) {
				f.delete();
			}
			questionDao.modifyQuestion(conn, modifyQuestion);
			resultRow = questionDao.modifyQuestion(conn, modifyQuestion);
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				// db작업에 실패시 이미 업로드되어 버린 파일 불러와 삭제
				File f = new File(dir+"\\"+modifyQuestion.getQuestionImg());
				if(f.exists()) {
					f.delete();
				}
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
	public String getQuestionOneCustomerIdByOrderCode(int orderCode) {
		this.questionDao = new QuestionDao();
		String customerId = null;
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			customerId = questionDao.selectQuestionOneCustomerIdByOrderCode(conn, orderCode);
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
	
	// questionOne 출력, modifyQuestion 문의정보값 
	// 사용하는 곳 : questionOneController, modifyQuestionController
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
	public int addQuestion(Question addQuestion, String dir) {
		int resultRow = 0;
		this.questionDao = new QuestionDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultRow = questionDao.addQuestion(conn, addQuestion);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				// db작업에 실패시 이미 업로드되어 버린 파일 불러와 삭제
				File f = new File(dir+"\\"+addQuestion.getQuestionImg());
				if(f.exists()) {
					f.delete();
				}
			} catch(SQLException e1) {
				e1.printStackTrace();
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
	
	// addQuestion (orderCode, goodsName 조회)
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
	public ArrayList<HashMap<String, Object>> getQuestionListByPage(int beginRow, int rowPerPage, String word) {
		this.questionDao = new QuestionDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionDao = new QuestionDao();
			list = questionDao.selectQuestionListByPage(conn, beginRow, rowPerPage, word);
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
