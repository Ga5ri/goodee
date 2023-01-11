package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Emp;
import vo.Question;
import vo.QuestionComment;

public class QuestionCommentDao {
	/* 답변 페이지는 관리자만 사용가능*/
	
	// modifyQuestionComment (답변글 수정)
	// 사용하는 곳 : modifyQuestionCommentController
	public int modifyQuestionComment(Connection conn, QuestionComment questionComment, Emp loignEmp) throws Exception {
		int resultRow = 0;
		String sql = " UPDATE question_comment "
				+ " 	SET comment_memo = ?, createdate = now()"
				+ " where comment_code = ? AND emp_id = ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, questionComment.getCommentMemo());
		stmt.setInt(2, questionComment.getCommentCode());
		stmt.setString(3, loignEmp.getEmpId());
		resultRow = stmt.executeUpdate();
		stmt.close();
		return resultRow;
	}
			
	// modifyQuestionComment (답변글 수정 폼 정보 불러오기)  
	// 사용하는 곳 : modifyQuestionCommentController
	public HashMap<String, Object> selectCommentCodeByComment(Connection conn, int commentCode) throws Exception {
		HashMap<String, Object> q = null;
		String sql = " SELECT qc.comment_code commentCode, qc.emp_id empId, qc.comment_memo commentMemo"
				+ "				, qc.createdate commentCreatedate, q.question_code questionCode"
				+ "				, q.orders_code ordersCode, q.category category, q.question_memo questionMemo"
				+ "				, q.createdate createdate"
				+ "		FROM question_comment qc"
				+ "			INNER JOIN question q"
				+ "			ON qc.question_code = q.question_code"
				+ "	WHERE qc.comment_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, commentCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			q = new HashMap<String, Object>();
			q.put("commentCode", rs.getInt("commentCode"));
			q.put("empId", rs.getString("empId"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("ordersCode", rs.getInt("ordersCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
		}
		return q;
	}
		
	// removeQuestionComment (답변글 삭제) 
	// 사용하는 곳 : removeQuestionCommentController	
	public int removeQuestionComment(Connection conn, int commentCode, Emp loginEmp) throws Exception {
		int resultRow = 0;
		String sql = "DELETE qc "
				+ " 		FROM question_comment qc "
				+ "				INNER JOIN question q "
				+ "				ON qc.question_code = q.question_code "
				+ " WHERE qc.comment_code = ? AND qc.emp_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, commentCode);
		stmt.setString(2, loginEmp.getEmpId());
		resultRow = stmt.executeUpdate();
		stmt.close();
		return resultRow;
	}
		
	// questionCommentOne (수정,삭제 메뉴 활성/비활성 = 세션의 로그인아이디와 comment코드의 작성자 아이디 일치시)  
	// 사용하는 곳 : questionCommentOneController
	public String selectQuestionOneEmpIdByCommentCode(Connection conn, int commentCode) throws Exception {
		String empId = null;
		String sql = " SELECT emp_id empId FROM question_comment"
				+ "	WHERE comment_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, commentCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			empId = rs.getString("empId");
		}
		return empId;
	}
	
	// questionCommentOne 출력
	// 사용하는 곳 : questionCommentOneController
	public HashMap<String, Object> selectQuestionOne(Connection conn, int questionCode) throws Exception {
		HashMap<String, Object> q = null;
		String sql = "SELECT q.question_code questionCode, q.orders_code ordersCode, q.category category, q.question_memo questionMemo"
				+ "		, q.createdate createdate, qc.comment_code commentCode, qc.comment_memo commentMemo, qc.createdate commentCreatedate "
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
			q.put("commentCode", rs.getInt("commentCode"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
		}
		return q;
	}
		
	// addQuestionComment (문의글에 대한 답변추가)
	// 사용하는 곳 : addQuestionCommentController	
	public int addQuestionComment(Connection conn, int questionCode, String empId, String commentMemo) throws Exception {
		int resultRow=0;
		String sql = "INSERT INTO question_comment (question_code, emp_id, comment_memo, createdate)"
				+ " VALUES(?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		stmt.setString(2, empId);
		stmt.setString(3, commentMemo);
		resultRow = stmt.executeUpdate();
		return resultRow;
	}
		
	// addQuestionComment (question 정보조회)
	// 사용하는 곳 : addQuestionCommentController	
	public Question selectOrdersCode(Connection conn, int questionCode) throws Exception{
		Question returnQuestion = null;
		String sql = "SELECT question_code questionCode, orders_code ordersCode, category"
				+ "				, question_memo questionMemo, createdate"
				+ "	 FROM question"
				+ "	 WHERE question_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    	returnQuestion = new Question();
	    	returnQuestion.setQuestionCode(rs.getInt("questionCode"));
	    	returnQuestion.setOrderCode(rs.getInt("ordersCode"));
	    	returnQuestion.setCategory(rs.getString("category"));
	    	returnQuestion.setQuestionMemo(rs.getString("questionMemo"));
	    	returnQuestion.setCreatedate(rs.getString("createdate"));
	    }
	    rs.close();
		stmt.close();
		return returnQuestion;
	}
	
	// questionCommentList 출력
	// 사용하는 곳 : questionCommentListController
	public ArrayList<HashMap<String, Object>> selectQuestionListByPage(Connection conn, int beginRow, int rowPerPage, String word, String search, String category) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql=null;
		PreparedStatement stmt=null;
		if(word.equals("") || word == null) {
			word=("");
		} 
		if(category.equals("") || category == null) {
			category=("");
		} 
		System.out.println(word);
		System.out.println(search);
		System.out.println(category);
		
		if(search.equals("") || search == null ) {
			search=("");
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category, r.question_memo questionMemo"
					+ "			, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "			, o.customer_id customerId"
					+ "		FROM "
					+ "			(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "					, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "					FROM "
					+ "						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							LEFT OUTER JOIN question_comment qc"
					+ "							ON r.question_code = qc.question_code) r"
					+ "				LEFT OUTER JOIN orders o"
					+ "				ON r.orders_code = o.order_code"
					+ "		WHERE (r.orders_code LIKE ? OR o.customer_id LIKE ? OR r.emp_id LIKE ?) AND r.category LIKE ?"
					+ "			 ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+word+"%");
			stmt.setString(3, "%"+word+"%");
			stmt.setString(4, "%"+category+"%");
			stmt.setInt(5, beginRow);
			stmt.setInt(6, rowPerPage);
		}
		if(search.equals("ordersCode")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category, r.question_memo questionMemo"
					+ "			, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "			, o.customer_id customerId"
					+ "		FROM "
					+ "			(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "					, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "					FROM "
					+ "						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							LEFT OUTER JOIN question_comment qc"
					+ "							ON r.question_code = qc.question_code) r"
					+ "				LEFT OUTER JOIN orders o"
					+ "				ON r.orders_code = o.order_code"
					+ "		WHERE r.orders_code = ? AND r.category LIKE ? ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
		} else if(search.equals("customerId")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category, r.question_memo questionMemo"
					+ "			, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "			, o.customer_id customerId"
					+ "		FROM "
					+ "			(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "					, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "					FROM "
					+ "						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							LEFT OUTER JOIN question_comment qc"
					+ "							ON r.question_code = qc.question_code) r"
					+ "				LEFT OUTER JOIN orders o"
					+ "				ON r.orders_code = o.order_code"
					+ "		WHERE o.customer_id LIKE ? AND r.category LIKE ? ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
		} else if(search.equals("empId")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.orders_code ordersCode, r.category category, r.question_memo questionMemo"
					+ "			, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "			, o.customer_id customerId"
					+ "		FROM "
					+ "			(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "					, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "					FROM "
					+ "						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							LEFT OUTER JOIN question_comment qc"
					+ "							ON r.question_code = qc.question_code) r"
					+ "				LEFT OUTER JOIN orders o"
					+ "				ON r.orders_code = o.order_code"
					+ "		WHERE r.emp_id LIKE ? AND r.category LIKE ? ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
		}
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("ordersCode", rs.getInt("ordersCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("empId", rs.getString("empId"));
			q.put("customerId", rs.getString("customerId"));
			list.add(q);
		}
		return list;
	}
	
	
	// questionCommentList 페이징
	// 사용하는 곳 : questionCommentListController
	public int count(Connection conn) throws Exception{
		int cnt = 0; // 전체 행의 수
		String sql = "SELECT COUNT(*) cnt FROM question_comment";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    	cnt = rs.getInt("cnt");
	    }
		return cnt;
	}
}
