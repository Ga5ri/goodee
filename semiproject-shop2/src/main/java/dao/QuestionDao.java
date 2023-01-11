package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Question;

public class QuestionDao {
	
	// questionListUser 출력
	// 사용하는 곳 : questionListUserController
	public ArrayList<HashMap<String, Object>> selectQuestionLisUsertByPage(Connection conn, int beginRow, int rowPerPage, Customer loginCustomer) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.questionCode questionCode, r.ordersCode orderCode, r.category category, r.commentCreatedate commentCreatedate"
				+ "			, r.questionMemo questionMemo, r.createdate createdate, r.commentMemo commentMemo"
				+ "	FROM "
				+ "		(SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category"
				+ "				, r.question_memo questionMemo, r.createdate createdate, qc.comment_memo commentMemo, qc.createdate commentCreatedate "
				+ "				FROM "
				+ "					(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "					 		, question_code, orders_Code, category, question_memo, createdate FROM question ) r"
				+ "							LEFT OUTER JOIN question_comment qc"
				+ "							ON r.question_code = qc.question_code) r"
				+ "			LEFT OUTER JOIN orders o"
				+ "			ON r.ordersCode=o.order_code		"
				+ " WHERE o.customer_id = ? ORDER BY r.createdate DESC LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, loginCustomer.getCustomerId());
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("ordersCode", rs.getInt("orderCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			list.add(q);
		}
		return list;
	}
		
	// modifyQuestion (문의글 수정) 답변 달리기 전까지만 가능
	// 사용하는 곳 : modifyQuestionController	
	public int modifyQuestion(Connection conn, Question question) throws Exception {
		int resultRow = 0;
		String sql = " UPDATE question "
				+ " 	SET category = ?, question_memo = ?, createdate = now()"
				+ " where question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, question.getCategory());
		stmt.setString(2, question.getQuestionMemo());
		stmt.setInt(3, question.getQuestionCode());
		resultRow = stmt.executeUpdate();
		stmt.close();
		return resultRow;
	}
		
	// removeQuestion (문의글 삭제) 답변 달리기 전까지만 가능
	// 사용하는 곳 : removeQuestionController	
	public int removeQuestion(Connection conn, Customer loginCustomer, int questionCode) throws Exception {
		int resultRow = 0;
		String sql = "DELETE q "
				+ " FROM question q "
				+ "		INNER JOIN orders o "
				+ "		ON q.orders_code = o.order_code "
				+ " WHERE q.question_code = ? AND o.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		stmt.setString(2, loginCustomer.getCustomerId());
		resultRow = stmt.executeUpdate();
		stmt.close();
		return resultRow;
	}
	
	// questionOne (수정,삭제 메뉴 활성/비활성 = 세션의 로그인아이디와 오더코드의 작성자 아이디 일치시)  
	// 사용하는 곳 : questionOneController
	public String selectQuestionOneCustomerIdByOrderCode(Connection conn, int ordersCode) throws Exception {
		String customerId = null;
		String sql = " SELECT o.customer_id customerId FROM question q"
				+ "			INNER JOIN orders o"
				+ "			ON q.orders_code = o.order_code"
				+ "	WHERE q.orders_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ordersCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			customerId = rs.getString("customerId");
		}
		return customerId;
	}
	 
	// questionOne 출력
	// 사용하는 곳 : questionOneController, question
	public HashMap<String, Object> selectQuestionOne(Connection conn, int questionCode) throws Exception {
		HashMap<String, Object> q = null;
		String sql = "SELECT q.question_code questionCode, q.orders_code ordersCode, q.category category, q.question_memo questionMemo"
				+ "		, q.createdate createdate, qc.comment_memo commentMemo, qc.createdate commentCreatedate "
				+ "	 FROM question q "
				+ "		LEFT OUTER JOIN question_comment qc "
				+ "		ON q.question_code = qc.question_code "
				+ " WHERE q.question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("ordersCode", rs.getInt("ordersCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
		}
		return q;
	}
	
	// addQuestion (문의글 추가)
	// 사용하는 곳 : addQuestionController	
	public int addQuestion(Connection conn, Question addQuestion) throws Exception {
		int resultRow=0;
		String sql = "INSERT INTO question (orders_code, category, question_memo, createdate)"
				+ " VALUES(?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, addQuestion.getOrderCode());
		stmt.setString(2, addQuestion.getCategory());
		stmt.setString(3, addQuestion.getQuestionMemo());
		resultRow = stmt.executeUpdate();
		return resultRow;
	}

	// addQuestion (ordersCode 조회)
	// 사용하는 곳 : addQuestionController	
	public ArrayList<Question> selectOrdersCode(Connection conn, Customer loginCustomer) throws Exception{
		ArrayList<Question> list = null;
		String sql = "SELECT orders_code ordersCode"
				+ "	 FROM question q"
				+ "		 INNER JOIN orders o"
				+ "		 ON q.orders_code = o.order_code"
				+ "	 WHERE o.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, loginCustomer.getCustomerId());
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<Question>();
	    while(rs.next()) {
	    	Question q = new Question();
	    	q.setOrderCode(rs.getInt("ordersCode"));
	    	list.add(q);

	    }
		return list;
	}
	
	// questionList 출력
	// 사용하는 곳 : questionListController
	public ArrayList<HashMap<String, Object>> selectQuestionListByPage(Connection conn, int beginRow, int rowPerPage) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category, r.question_memo questionMemo, r.createdate createdate"
				+ " , qc.comment_memo commentMemo"
				+ " 	FROM (SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "				, question_code, orders_Code, category, question_memo, createdate FROM question ) r"
				+ "			LEFT OUTER JOIN question_comment qc"
				+ "			ON r.question_code = qc.question_code"
				+ " 	ORDER BY createdate DESC LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("ordersCode", rs.getInt("ordersCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			list.add(q);
		}
		return list;
	}
	
	
	// questionList 페이징
	// 사용하는 곳 : questionListController
	public int count(Connection conn) throws Exception{
		int cnt = 0; // 전체 행의 수
		String sql = "SELECT COUNT(*) cnt FROM question";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    	cnt = rs.getInt("cnt");
	    }
		return cnt;
	}
}
