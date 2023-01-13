package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Question;

public class QuestionDao {
	
	// questionListUser 출력
	// 사용하는 곳 : questionListUserController
	public ArrayList<HashMap<String, Object>> selectQuestionLisUsertByPage(Connection conn, int beginRow, int rowPerPage, Customer loginCustomer, String word) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "			, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate"
				+ "			, r.order_code orderCode, g.goods_code goodsCode, g.goods_name goodsName"
				+ "	FROM"
				+ "			(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo"
				+ "					, r.createdate, r.comment_memo, r.commentCreatedate, o.order_code, o.goods_code"
				+ "			FROM"
				+ "					(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo"
				+ "							, r.createdate, qc.comment_memo, qc.createdate commentCreatedate "
				+ "							FROM "
				+ "									(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum, question_code"
				+ "											, orders_code, category, question_memo, createdate FROM question ) r"
				+ "								LEFT OUTER JOIN question_comment qc"
				+ "								ON r.question_code = qc.question_code) r"
				+ "				INNER JOIN orders o"
				+ "				ON r.orders_code = o.order_code"
				+ "			WHERE o.customer_id = ?) r"
				+ "		INNER JOIN goods g"
				+ "		ON r.goods_code = g.goods_code"
				+ "		WHERE g.goods_name LIKE ? ORDER BY r.createdate DESC LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, loginCustomer.getCustomerId());
		stmt.setString(2, "%"+word+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("orderCode", rs.getInt("orderCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("goodsCode", rs.getInt("goodsCode"));
			q.put("goodsName", rs.getString("goodsName"));
			list.add(q);
		}
		return list;
	}
	
	// questionList 페이징
	// 사용하는 곳 : questionListController
	public int countUser(Connection conn, Customer loginCustomer) throws Exception{
		int cnt = 0; // 전체 행의 수
		String sql = "SELECT COUNT(orderCode) cnt"
				+ "		FROM"
				+ "			(SELECT q.orders_code orderCode"
				+ "				FROM"
				+ "						(SELECT order_code"
				+ "				 			FROM orders"
				+ "						WHERE customer_id=?) r"
				+ "				LEFT outer JOIN question q"
				+ "				ON r.order_code = q.orders_code) r";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, loginCustomer.getCustomerId());
		ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    	cnt = rs.getInt("cnt");
	    }
		return cnt;
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
	public String selectQuestionOneCustomerIdByOrderCode(Connection conn, int orderCode) throws Exception {
		String customerId = null;
		String sql = " SELECT o.customer_id customerId FROM question q"
				+ "			INNER JOIN orders o"
				+ "			ON q.orders_code = o.order_code"
				+ "	WHERE q.orders_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
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
		String sql = "SELECT r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "				, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate"
				+ "				, r.order_code orderCode, g.goods_code goodsCode, g.goods_Name goodsName"
				+ "		FROM "
				+ "				(SELECT r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "						, r.commentCreatedate commentCreatedate, o.order_code, o.goods_code"
				+ "				FROM "
				+ "						(SELECT q.question_code, q.orders_code, q.category, q.question_memo"
				+ "								, q.createdate, qc.comment_memo, qc.createdate commentCreatedate"
				+ "						FROM question q "
				+ "							LEFT OUTER JOIN question_comment qc "
				+ "							ON q.question_code = qc.question_code "
				+ "						WHERE q.question_code = ?) r"
				+ "					INNER JOIN orders o"
				+ "					ON r.orders_code = o.order_code) r"
				+ "			INNER JOIN goods g"
				+ "			ON r.goods_code = g.goods_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("orderCode", rs.getInt("orderCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("goodsCode", rs.getInt("goodsCode"));
			q.put("goodsName", rs.getString("goodsName"));
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

	// addQuestion (ordersCode, goodsName 조회)
	// 사용하는 곳 : addQuestionController	
	public ArrayList<HashMap<String, Object>> selectOrdersCode(Connection conn, Customer loginCustomer) throws Exception{
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.order_code orderCode, g.goods_name goodsName"
				+ "		FROM "
				+ "			(SELECT o.order_code, o.goods_code, o.createdate"
				+ "				FROM question q"
				+ "					INNER JOIN orders o"
				+ "					ON q.orders_code = o.order_code"
				+ "			WHERE o.customer_id = ?) r"
				+ "		INNER JOIN goods g"
				+ "		ON r.goods_code = g.goods_code"
				+ "		ORDER BY r.createdate DESC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, loginCustomer.getCustomerId());
		ResultSet rs = stmt.executeQuery();
	    while(rs.next()) {
	    	HashMap<String, Object> q = new HashMap<String, Object>();
	    	q.put("orderCode", rs.getInt("orderCode"));
	    	q.put("goodsName", rs.getString("goodsName"));
	    	
	    	list.add(q);
	    }
		return list;
	}
	
	// questionList 출력
	// 사용하는 곳 : questionListController
	public ArrayList<HashMap<String, Object>> selectQuestionListByPage(Connection conn, int beginRow, int rowPerPage, String word) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo "
				+ "			, r.createdate createdate, r.comment_memo commentMemo, r.order_code orderCode "
				+ "			, g.goods_code goodsCode, g.goods_name goodsName"
				+ "		FROM"
				+ "			(SELECT r.rnum , r.question_code, r.category , r.question_memo , r.createdate, r.comment_memo"
				+ "					, o.order_code, o.goods_code"
				+ "				 FROM"
				+ "				 	(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate"
				+ "					 		, qc.comment_memo"
				+ "				 		FROM (SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "									, question_code, orders_Code, category, question_memo, createdate "
				+ "									FROM question ) r"
				+ "							LEFT OUTER JOIN question_comment qc"
				+ "							ON r.question_code = qc.question_code) r"
				+ "					INNER JOIN orders o "
				+ "					ON r.orders_code = o.order_code) r"
				+ "			INNER JOIN  goods g"
				+ "			ON r.goods_code = g.goods_code"
				+ "		WHERE g.goods_name LIKE ? "
				+ "			ORDER BY createdate DESC LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("orderCode", rs.getInt("orderCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("goodsCode", rs.getInt("goodsCode"));
			q.put("goodsName", rs.getString("goodsName"));
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
