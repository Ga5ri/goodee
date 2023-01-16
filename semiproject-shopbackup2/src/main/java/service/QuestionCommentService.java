package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.QuestionCommentDao;
import dao.QuestionDao;
import util.DBUtil;
import vo.Customer;
import vo.Emp;
import vo.Question;
import vo.QuestionComment;

public class QuestionCommentService {
	/* 답변 페이지는 관리자만 사용가능*/
	private QuestionCommentDao questionCommentDao;
	
	// modifyQuestionComment (답변글 수정) 
	// 사용하는 곳 : modifyQuestionCommentController
	public int modifyQuestionComment(QuestionComment questionComment, Emp loignEmp) {
		int resultRow=0;
		this.questionCommentDao = new QuestionCommentDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			resultRow = questionCommentDao.modifyQuestionComment(conn, questionComment, loignEmp);
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
	
	// removeQuestionComment (답변글 삭제) 
	// 사용하는 곳 : removeQuestionCommentController
	public int removeQuestionComment(int commentCode, Emp loginEmp) {
		int resultRow=0;
		this.questionCommentDao = new QuestionCommentDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			resultRow = questionCommentDao.removeQuestionComment(conn, commentCode, loginEmp);
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
		
	// questionCommentOne (수정,삭제 메뉴 활성/비활성 = 세션의 로그인아이디와 comment코드의 작성자 아이디 일치시)  
	// 사용하는 곳 : questionCommentOneController
	public String getQuestionOneEmpIdByCommentCode(int commentCode) {
		this.questionCommentDao = new QuestionCommentDao();
		String empId = null;
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionCommentDao = new QuestionCommentDao();
			empId = questionCommentDao.selectQuestionOneEmpIdByCommentCode(conn, commentCode);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
		return empId;
	}
		
	// questionCommentOne 출력,  modifyQuestionComment (답변글 수정 폼 정보 불러오기)  
	// 사용하는 곳 : questionCommentOneController, modifyQuestionCommentController
	public HashMap<String, Object> getQuestionOne(int questionCode) {
		this.questionCommentDao = new QuestionCommentDao();
		HashMap<String, Object> q = null;
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionCommentDao = new QuestionCommentDao();
			q = questionCommentDao.selectQuestionOne(conn, questionCode);
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
		
	// addQuestionComment (문의글에 대한 답변추가)
	// 사용하는 곳 : addQuestionCommentController	
	public int addQuestionComment(int questionCode, String empId, String commentMemo) {
		this.questionCommentDao = new QuestionCommentDao();
		int resultRow = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			questionCommentDao = new QuestionCommentDao();
			resultRow = questionCommentDao.addQuestionComment(conn, questionCode, empId, commentMemo);
		} catch(Exception e) {
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
	
	// addQuestionComment (question 정보조회)
	// 사용하는 곳 : addQuestionCommentController	
	public HashMap<String, Object> selectOrderCode(int questionCode) {
		this.questionCommentDao = new QuestionCommentDao();
		HashMap<String, Object> q = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			questionCommentDao = new QuestionCommentDao();
			q = questionCommentDao.selectOrderCode(conn, questionCode);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return q;
	}
	
	// questionList 출력
	// 사용하는 곳 : questionListController
	public ArrayList<HashMap<String, Object>> getQuestionListByPage(int beginRow, int rowPerPage, String word, String search, String category, String sort) {
		this.questionCommentDao = new QuestionCommentDao();
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn  = null;
		try {
			conn = DBUtil.getConnection();
			questionCommentDao = new QuestionCommentDao();
			list = questionCommentDao.selectQuestionListByPage(conn, beginRow, rowPerPage, word, search, category, sort);
			
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
			  this.questionCommentDao = new QuestionCommentDao();
			  int cnt = 0;
		      Connection conn = null;
		      try {
		         conn = DBUtil.getConnection();
		         questionCommentDao = new QuestionCommentDao();
		         cnt = questionCommentDao.count(conn);
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
