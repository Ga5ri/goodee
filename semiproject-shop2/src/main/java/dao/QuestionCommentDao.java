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
	
	// questionCommentOne 출력, modifyQuestionComment (답변글 수정 폼 정보 불러오기)  
	// 사용하는 곳 : questionCommentOneController,  modifyQuestionCommentController
	public HashMap<String, Object> selectQuestionOne(Connection conn, int questionCode) throws Exception {
		HashMap<String, Object> q = null;
		String sql = "SELECT r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "			, r.createdate createdate, r.comment_code commentCode, r.comment_memo commentMemo, r.question_img questionImg"
				+ "			, r.commentCreatedate commentCreatedate, r.order_code orderCode, r.customer_id customerId"
				+ "			, r.emp_id empId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "		FROM "
				+ "			(SELECT r.question_code, r.orders_code, r.category, r.question_memo, r.createdate, r.comment_code"
				+ "			, r.comment_memo, r.question_img, r.commentCreatedate, r.emp_id , o.order_code, o.goods_code, o.customer_id"
				+ "				FROM "
				+ "					(SELECT q.question_code , q.orders_code , q.category , q.question_memo, q.question_img "
				+ "				 			, q.createdate , qc.comment_code , qc.comment_memo , qc.createdate commentCreatedate, qc.emp_id "
				+ "			 			 FROM question q "
				+ "			 		LEFT OUTER JOIN question_comment qc "
				+ "			 		ON q.question_code = qc.question_code "
				+ "			  WHERE q.question_code = ?) r"
				+ "		INNER JOIN orders o "
				+ "		ON r.orders_code = o.order_code) r"
				+ " INNER JOIN goods g"
				+ " ON r.goods_code = g.goods_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, questionCode);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			q = new HashMap<String, Object>();
			q.put("questionCode", rs.getInt("questionCode"));
			q.put("orderCode", rs.getInt("orderCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("questionImg", rs.getString("questionImg"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentCode", rs.getInt("commentCode"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("customerId", rs.getString("customerId"));
			q.put("empId", rs.getString("empId"));
			q.put("goodsCode", rs.getInt("goodsCode"));
			q.put("goodsName", rs.getString("goodsName"));
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
		
	// addQuestionComment  (question 정보조회)
	// 사용하는 곳 : addQuestionCommentController
	public HashMap<String, Object> selectOrderCode(Connection conn, int questionCode) throws Exception{
		HashMap<String, Object> q = null;
		String sql = "SELECT r.question_code questionCode, r.category category, r.question_memo questionMemo, r.question_img questionImg"
				+ "			, r.createdate createdate, r.order_code orderCode, g.goods_code goodsCode, r.customer_id customerId, g.goods_name goodsName"
				+ "		FROM "
				+ "				(SELECT r.question_code, r.orders_code, r.category, r.question_memo"
				+ "						, r.question_img, r.createdate, o.order_code, o.goods_code, o.customer_id"
				+ "					FROM "
				+ "							(SELECT question_code, orders_code, category"
				+ "									, question_memo, question_img, createdate"
				+ "								FROM question"
				+ "							WHERE question_code = ?) r"
				+ "						INNER JOIN orders o"
				+ "						ON r.orders_code = o.order_code ) r"
				+ "			INNER JOIN goods g "
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
			q.put("questionImg", rs.getString("questionImg"));
			q.put("createdate", rs.getString("createdate"));
			q.put("customerId", rs.getString("customerId"));
			q.put("goodsCode", rs.getInt("goodsCode"));
			q.put("goodsName", rs.getString("goodsName"));
			
	    }
	    rs.close();
		stmt.close();
		return q;
	}
	
	// questionCommentList 출력
	// 사용하는 곳 : questionCommentListController
	public ArrayList<HashMap<String, Object>> selectQuestionListByPage(Connection conn, int beginRow, int rowPerPage, String word, String search, String category, String sort) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql=null;
		PreparedStatement stmt=null;
		if(search.equals("search") || word == null) {
			search=("");
		} 
		if(word.equals("") || word == null) {
			word=("");
		} 
		if(category.equals("") || category == null) {
			category=("");
		} 
		if(sort.equals("sort") || sort == null) {
			sort=("");
		} 
		// sort 전체, 검색값 전체 (문의날짜 기준으로 정렬)
		if(sort.equals("") &&(search.equals("") || search == null) ) {
			search=("");
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM 					 			"
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE (r.order_code LIKE ? OR r.customer_id LIKE ? OR r.emp_id LIKE ? OR r.question_memo LIKE ? OR g.goods_name LIKE ?) AND r.category LIKE ?"
					+ "	ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+word+"%");
			stmt.setString(3, "%"+word+"%");
			stmt.setString(4, "%"+word+"%");
			stmt.setString(5, "%"+word+"%");
			stmt.setString(6, "%"+category+"%");
			stmt.setInt(7, beginRow);
			stmt.setInt(8, rowPerPage);
			
		// sort 전체, 검색값 주문번호 (문의날짜 기준으로 정렬)
		} else if(sort.equals("") && search.equals("orderCode")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM 					 			"
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE r.order_code = ? AND r.category LIKE ?"
					+ "	ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
		
			// sort 전체, 검색값 상품명 (문의날짜 기준으로 정렬)
			} else if(sort.equals("") && search.equals("goodsName")) {
				sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
						+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
						+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
						+ "	FROM 					 			"
						+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
						+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
						+ "				FROM "
						+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
						+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
						+ "							FROM "
						+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
						+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
						+ "							 	LEFT OUTER JOIN question_comment qc"
						+ "							 	ON r.question_code = qc.question_code) r"
						+ "					INNER JOIN orders o"
						+ "					ON r.orders_code = o.order_code)r"
						+ "		INNER JOIN goods g"
						+ "		ON r.goods_code = g.goods_code"
						+ " WHERE g.goods_name LIKE ? AND r.category LIKE ?"
						+ "	ORDER BY createdate DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+word+"%");
				stmt.setString(2, "%"+category+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				
		// sort 전체, 검색값 고객ID (문의날짜 기준으로 정렬)
		} else if(sort.equals("") && search.equals("customerId")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM 					 			"
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE r.customer_id LIKE ? AND r.category LIKE ?"
					+ "	ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
		// sort 전체, 검색값 사원ID (문의날짜 기준으로 정렬)
		} else if(sort.equals("") && search.equals("empId")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM "
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE r.emp_id LIKE ? AND r.category LIKE ?"
					+ "	ORDER BY createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
		// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 전체	
		} else if(sort.equals("asc") && (search.equals("") || search == null )) {
			search=("");
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM "
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code"
					+ "							WHERE qc.comment_memo IS NULL) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE (r.order_code LIKE ? OR r.customer_id LIKE ? OR r.emp_id LIKE ? OR r.question_memo LIKE ? OR g.goods_name LIKE ?) AND r.category LIKE ?"
					+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+word+"%");
			stmt.setString(3, "%"+word+"%");
			stmt.setString(4, "%"+word+"%");
			stmt.setString(5, "%"+word+"%");
			stmt.setString(6, "%"+category+"%");
			stmt.setInt(7, beginRow);
			stmt.setInt(8, rowPerPage);
			
		// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 주문번호	
		} else if(sort.equals("asc") && search.equals("orderCode")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM "
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code"
					+ "							WHERE qc.comment_memo IS NULL) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE r.order_code = ? AND r.category LIKE ?"
					+ "	ORDER BY comment_memo " +sort +", createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
			// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 상품명	
			} else if(sort.equals("asc") && search.equals("goodsName")) {
				sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
						+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
						+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
						+ "	FROM "
						+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
						+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
						+ "				FROM "
						+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
						+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
						+ "							FROM "
						+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
						+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
						+ "							 	LEFT OUTER JOIN question_comment qc"
						+ "							 	ON r.question_code = qc.question_code"
						+ "							WHERE qc.comment_memo IS NULL) r"
						+ "					INNER JOIN orders o"
						+ "					ON r.orders_code = o.order_code)r"
						+ "		INNER JOIN goods g"
						+ "		ON r.goods_code = g.goods_code"
						+ " WHERE g.goods_name LIKE ? AND r.category LIKE ?"
						+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+word+"%");
				stmt.setString(2, "%"+category+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			
		// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 고객ID			
		} else if(sort.equals("asc") && search.equals("customerId")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM 					 			"
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	LEFT OUTER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code"
					+ "							WHERE qc.comment_memo IS NULL) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code)r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE r.customer_id LIKE ? AND r.category LIKE ?"
					+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			
			
		// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 전체
		} else if(sort.equals("desc") && (search.equals("") || search == null )) {
		search=("");
		sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "	FROM 					 			"
				+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "				FROM "
				+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "							FROM "
				+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 	INNER JOIN question_comment qc"
				+ "							 	ON r.question_code = qc.question_code) r"
				+ "					INNER JOIN orders o"
				+ "					ON r.orders_code = o.order_code)r"
				+ "		INNER JOIN goods g"
				+ "		ON r.goods_code = g.goods_code"
				+ " WHERE (r.order_code LIKE ? OR r.customer_id LIKE ? OR r.emp_id LIKE ? OR r.question_memo LIKE ? OR g.goods_name LIKE ?) AND r.category LIKE ?"
				+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setString(2, "%"+word+"%");
		stmt.setString(3, "%"+word+"%");
		stmt.setString(4, "%"+word+"%");
		stmt.setString(5, "%"+word+"%");
		stmt.setString(6, "%"+category+"%");
		stmt.setInt(7, beginRow);
		stmt.setInt(8, rowPerPage);
		
	// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 주문번호
	} else if(sort.equals("desc") && search.equals("orderCode")) {
		sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "	FROM 					 			"
				+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "				FROM "
				+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "							FROM "
				+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 	INNER JOIN question_comment qc"
				+ "							 	ON r.question_code = qc.question_code) r"
				+ "					INNER JOIN orders o"
				+ "					ON r.orders_code = o.order_code) r"
				+ "		INNER JOIN goods g"
				+ "		ON r.goods_code = g.goods_code"
				+ " WHERE r.order_code = ? AND r.category LIKE ?"
				+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, word);
		stmt.setString(2, "%"+category+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		
		// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 상품명
		} else if(sort.equals("desc") && search.equals("goodsName")) {
			sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "	FROM 					 			"
					+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "				FROM "
					+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "							FROM "
					+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 	INNER JOIN question_comment qc"
					+ "							 	ON r.question_code = qc.question_code) r"
					+ "					INNER JOIN orders o"
					+ "					ON r.orders_code = o.order_code) r"
					+ "		INNER JOIN goods g"
					+ "		ON r.goods_code = g.goods_code"
					+ " WHERE g.goods_name LIKE ? AND r.category LIKE ?"
					+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);	
			
	// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 고객ID			
	} else if(sort.equals("desc") && search.equals("customerId")) {
		sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "	FROM 					 			"
				+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "				FROM "
				+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "							FROM "
				+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 	INNER JOIN question_comment qc"
				+ "							 	ON r.question_code = qc.question_code) r"
				+ "					INNER JOIN orders o"
				+ "					ON r.orders_code = o.order_code) r"
				+ "		INNER JOIN goods g"
				+ "		ON r.goods_code = g.goods_code"
				+ " WHERE r.customer_id LIKE ? AND r.category LIKE ?"
				+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setString(2, "%"+category+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		
	// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 사원ID				
	} else if(sort.equals("desc") && search.equals("empId")) {
		sql = "SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "		, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "		, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "	FROM 					 			"
				+ "			(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "					, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "				FROM "
				+ "						(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "							 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "							FROM "
				+ "							 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 	INNER JOIN question_comment qc"
				+ "							 	ON r.question_code = qc.question_code) r"
				+ "					INNER JOIN orders o"
				+ "					ON r.orders_code = o.order_code) r"
				+ "		INNER JOIN goods g"
				+ "		ON r.goods_code = g.goods_code"
				+ " WHERE r.emp_id LIKE ? AND r.category LIKE ?"
				+ "	ORDER BY comment_memo " +sort+ " , createdate DESC LIMIT ?, ?";
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
			q.put("orderCode", rs.getInt("orderCode"));
			q.put("category", rs.getString("category"));
			q.put("questionMemo", rs.getString("questionMemo"));
			q.put("createdate", rs.getString("createdate"));
			q.put("commentMemo", rs.getString("commentMemo"));
			q.put("commentCreatedate", rs.getString("commentCreatedate"));
			q.put("empId", rs.getString("empId"));
			q.put("customerId", rs.getString("customerId"));
			q.put("goodsCode", rs.getInt("goodsCode"));
			q.put("goodsName", rs.getString("goodsName"));
			list.add(q);
		}
		return list;
	}
	
	
	
	
	// questionCommentList 페이징
	// 사용하는 곳 : questionCommentListController
	public int count(Connection conn, String word, String search, String category, String sort) throws Exception {
		int cnt = 0;
		String sql=null;
		PreparedStatement stmt=null;
		if(search.equals("search") || word == null) {
			search=("");
		} 
		if(word.equals("") || word == null) {
			word=("");
		} 
		if(category.equals("") || category == null) {
			category=("");
		} 
		if(sort.equals("sort") || sort == null) {
			sort=("");
		} 
		// sort 전체, 검색값 전체 (문의날짜 기준으로 정렬)
		if(sort.equals("") &&(search.equals("") || search == null) ) {
			search=("");
			sql = " SELECT COUNT(r.questionCode) cnt"
			+ "		FROM "
			+ " 			(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
			+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
			+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
			+ "					FROM 					 			"
			+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
			+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
			+ "								FROM "
			+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
			+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
			+ "											FROM "
			+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
			+ "							 						, question_code, orders_Code, category, question_memo, createdate FROM question) r"
			+ "							 					LEFT OUTER JOIN question_comment qc"
			+ "							 					ON r.question_code = qc.question_code) r"
			+ "									INNER JOIN orders o"
			+ "									ON r.orders_code = o.order_code)r"
			+ "						INNER JOIN goods g"
			+ "						ON r.goods_code = g.goods_code"
			+ " 			WHERE (r.order_code LIKE ? OR r.customer_id LIKE ? OR r.emp_id LIKE ? OR r.question_memo LIKE ? OR g.goods_name LIKE ?) AND r.category LIKE ? "
			+ "				ORDER BY createdate DESC) r ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+word+"%");
			stmt.setString(3, "%"+word+"%");
			stmt.setString(4, "%"+word+"%");
			stmt.setString(5, "%"+word+"%");
			stmt.setString(6, "%"+category+"%");
			
		// sort 전체, 검색값 주문번호 (문의날짜 기준으로 정렬)
		} else if(sort.equals("") && search.equals("orderCode")) {
			sql = " SELECT COUNT(r.questionCode) cnt"
			+ " 	FROM"
			+ "				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
			+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
			+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
			+ "					FROM 					 			"
			+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
			+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
			+ "								FROM "
			+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
			+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
			+ "											FROM "
			+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
			+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
			+ "							 					LEFT OUTER JOIN question_comment qc"
			+ "							 					ON r.question_code = qc.question_code) r"
			+ "									INNER JOIN orders o"
			+ "									ON r.orders_code = o.order_code)r"
			+ "						INNER JOIN goods g"
			+ "						ON r.goods_code = g.goods_code"
			+ " 				WHERE r.order_code = ? AND r.category LIKE ?"
			+ "					ORDER BY createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, "%"+category+"%");
		
			// sort 전체, 검색값 상품명 (문의날짜 기준으로 정렬)
			} else if(sort.equals("") && search.equals("goodsName")) {
				sql = " SELECT COUNT(r.questionCode) cnt"
				+ " 	FROM"
				+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "					FROM 					 			"
				+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "								FROM "
				+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "											FROM "
				+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 					LEFT OUTER JOIN question_comment qc"
				+ "											 	ON r.question_code = qc.question_code) r"
				+ "									INNER JOIN orders o"
				+ "									ON r.orders_code = o.order_code)r"
				+ "						INNER JOIN goods g"
				+ "						ON r.goods_code = g.goods_code"
				+ " 				WHERE g.goods_name LIKE ? AND r.category LIKE ?"
				+ "					ORDER BY createdate DESC) r";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+word+"%");
				stmt.setString(2, "%"+category+"%");
				
		// sort 전체, 검색값 고객ID (문의날짜 기준으로 정렬)
		} else if(sort.equals("") && search.equals("customerId")) {
			sql = " SELECT COUNT(r.questionCode) cnt"
			+ "		FROM "
			+ "			(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
			+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
			+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
			+ "					FROM 					 			"
			+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
			+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
			+ "								FROM "
			+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
			+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
			+ "											FROM "
			+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
			+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
			+ "							 					LEFT OUTER JOIN question_comment qc"
			+ "											 	ON r.question_code = qc.question_code) r"
			+ "									INNER JOIN orders o"
			+ "									ON r.orders_code = o.order_code)r"
			+ "						INNER JOIN goods g"
			+ "						ON r.goods_code = g.goods_code"
			+ " 				WHERE r.customer_id LIKE ? AND r.category LIKE ?"
			+ "					ORDER BY createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			
		// sort 전체, 검색값 사원ID (문의날짜 기준으로 정렬)
		} else if(sort.equals("") && search.equals("empId")) {
			sql = " SELECT COUNT(r.questionCode) cnt"
			+ "		FROM "
			+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
			+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
			+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
			+ "					FROM "
			+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
			+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
			+ "								FROM "
			+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
			+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
			+ "											FROM "
			+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
			+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
			+ "							 					LEFT OUTER JOIN question_comment qc"
			+ "							 					ON r.question_code = qc.question_code) r"
			+ "									INNER JOIN orders o"
			+ "									ON r.orders_code = o.order_code)r"
			+ "						INNER JOIN goods g"
			+ "						ON r.goods_code = g.goods_code"
			+ " 				WHERE r.emp_id LIKE ? AND r.category LIKE ?"
			+ "					ORDER BY createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			
		// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 전체	
		} else if(sort.equals("asc") && (search.equals("") || search == null )) {
			search=("");
			sql = " SELECT COUNT(r.questionCode) cnt"
			+ "		FROM "
			+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
			+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
			+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
			+ "					FROM "
			+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
			+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
			+ "								FROM "
			+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
			+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
			+ "											FROM "
			+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
			+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
			+ "							 					LEFT OUTER JOIN question_comment qc"
			+ "							 					ON r.question_code = qc.question_code"
			+ "											WHERE qc.comment_memo IS NULL) r"
			+ "									INNER JOIN orders o"
			+ "									ON r.orders_code = o.order_code)r"
			+ "						INNER JOIN goods g"
			+ "						ON r.goods_code = g.goods_code"
			+ " 				WHERE (r.order_code LIKE ? OR r.customer_id LIKE ? OR r.emp_id LIKE ? OR r.question_memo LIKE ? OR g.goods_name LIKE ?) AND r.category LIKE ?"
			+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+word+"%");
			stmt.setString(3, "%"+word+"%");
			stmt.setString(4, "%"+word+"%");
			stmt.setString(5, "%"+word+"%");
			stmt.setString(6, "%"+category+"%");
			
		// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 주문번호	
		} else if(sort.equals("asc") && search.equals("orderCode")) {
			sql = " SELECT COUNT(r.questionCode) cnt"
			+ "		FROM "
			+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
			+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
			+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
			+ "					FROM "
			+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
			+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
			+ "								FROM "
			+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
			+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
			+ "											FROM "
			+ "											 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
			+ "											 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
			+ "											 	LEFT OUTER JOIN question_comment qc"
			+ "											 	ON r.question_code = qc.question_code"
			+ "											WHERE qc.comment_memo IS NULL) r"
			+ "									INNER JOIN orders o"
			+ "									ON r.orders_code = o.order_code)r"
			+ "						INNER JOIN goods g"
			+ "						ON r.goods_code = g.goods_code"
			+ "				 WHERE r.order_code = ? AND r.category LIKE ?"
			+ "				ORDER BY comment_memo " +sort +", createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, "%"+category+"%");
			
			// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 상품명	
			} else if(sort.equals("asc") && search.equals("goodsName")) {
				sql = " SELECT COUNT(r.questionCode) cnt"
				+ "		FROM "
				+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "					FROM "
				+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "								FROM "
				+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "											FROM "
				+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 					LEFT OUTER JOIN question_comment qc"
				+ "							 					ON r.question_code = qc.question_code"
				+ "											WHERE qc.comment_memo IS NULL) r"
				+ "									INNER JOIN orders o"
				+ "									ON r.orders_code = o.order_code)r"
				+ "						INNER JOIN goods g"
				+ "						ON r.goods_code = g.goods_code"
				+ " 				WHERE g.goods_name LIKE ? AND r.category LIKE ?"
				+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+word+"%");
				stmt.setString(2, "%"+category+"%");
			
		// 답변전 sort:ASC(commentMemo없음 null값위로) ->createdate DESC, 검색값 고객ID			
		} else if(sort.equals("asc") && search.equals("customerId")) {
			sql = " SELECT COUNT(r.questionCode) cnt"
					+ "		FROM "
					+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "					FROM 					 			"
					+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "								FROM "
					+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "											FROM "
					+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 					LEFT OUTER JOIN question_comment qc"
					+ "							 					ON r.question_code = qc.question_code"
					+ "											WHERE qc.comment_memo IS NULL) r"
					+ "									INNER JOIN orders o"
					+ "									ON r.orders_code = o.order_code)r"
					+ "						INNER JOIN goods g"
					+ "						ON r.goods_code = g.goods_code"
					+ " 				WHERE r.customer_id LIKE ? AND r.category LIKE ?"
					+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			
			
		// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 전체
		} else if(sort.equals("desc") && (search.equals("") || search == null )) {
		search=("");
		sql = " SELECT COUNT(r.questionCode) cnt"
				+ "		FROM "
				+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "					FROM 					 			"
				+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "								FROM "
				+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "											FROM "
				+ "											 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 					INNER JOIN question_comment qc"
				+ "											 	ON r.question_code = qc.question_code) r"
				+ "									INNER JOIN orders o"
				+ "									ON r.orders_code = o.order_code)r"
				+ "						INNER JOIN goods g"
				+ "						ON r.goods_code = g.goods_code"
				+ "				 WHERE (r.order_code LIKE ? OR r.customer_id LIKE ? OR r.emp_id LIKE ? OR r.question_memo LIKE ? OR g.goods_name LIKE ?) AND r.category LIKE ?"
				+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setString(2, "%"+word+"%");
		stmt.setString(3, "%"+word+"%");
		stmt.setString(4, "%"+word+"%");
		stmt.setString(5, "%"+word+"%");
		stmt.setString(6, "%"+category+"%");
		
	// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 주문번호
	} else if(sort.equals("desc") && search.equals("orderCode")) {
		sql = " SELECT COUNT(r.questionCode) cnt"
				+ "		FROM "
				+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "					FROM 					 			"
				+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "								FROM "
				+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "											FROM "
				+ "											 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "											 				, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "											 	INNER JOIN question_comment qc"
				+ "											 	ON r.question_code = qc.question_code) r"
				+ "									INNER JOIN orders o"
				+ "									ON r.orders_code = o.order_code) r"
				+ "						INNER JOIN goods g"
				+ "						ON r.goods_code = g.goods_code"
				+ " 				WHERE r.order_code = ? AND r.category LIKE ?"
				+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, word);
		stmt.setString(2, "%"+category+"%");
		
		// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 상품명
		} else if(sort.equals("desc") && search.equals("goodsName")) {
			sql = " SELECT COUNT(r.questionCode) cnt"
					+ "		FROM "
					+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
					+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
					+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
					+ "					FROM 					 			"
					+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
					+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
					+ "								FROM "
					+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
					+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
					+ "											FROM "
					+ "											 		(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
					+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
					+ "							 					INNER JOIN question_comment qc"
					+ "							 					ON r.question_code = qc.question_code) r"
					+ "									INNER JOIN orders o"
					+ "									ON r.orders_code = o.order_code) r"
					+ "						INNER JOIN goods g"
					+ "						ON r.goods_code = g.goods_code"
					+ " 				WHERE g.goods_name LIKE ? AND r.category LIKE ?"
					+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+word+"%");
			stmt.setString(2, "%"+category+"%");
			
	// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 고객ID			
	} else if(sort.equals("desc") && search.equals("customerId")) {
		sql = " SELECT COUNT(r.questionCode) cnt"
				+ "		FROM "
				+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
				+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
				+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
				+ "					FROM 					 			"
				+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
				+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
				+ "								FROM "
				+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
				+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
				+ "											FROM "
				+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
				+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
				+ "							 					INNER JOIN question_comment qc"
				+ "							 					ON r.question_code = qc.question_code) r"
				+ "									INNER JOIN orders o"
				+ "									ON r.orders_code = o.order_code) r"
				+ "						INNER JOIN goods g"
				+ "						ON r.goods_code = g.goods_code"
				+ " 				WHERE r.customer_id LIKE ? AND r.category LIKE ?"
				+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setString(2, "%"+category+"%");
		
	// 답변완료 sort:DESC(commentMemo있음) ->commentCreatedate DESC, 검색값 사원ID				
	} else if(sort.equals("desc") && search.equals("empId")) {
		sql = " SELECT COUNT(r.questionCode) cnt"
		+ "		FROM "
		+ " 				(SELECT r.rnum rnum, r.question_code questionCode, r.category category, r.question_memo questionMemo"
		+ "						, r.createdate createdate, r.comment_memo commentMemo, r.commentCreatedate commentCreatedate, r.emp_id empId"
		+ "						, r.order_code orderCode , r.customer_id customerId, g.goods_code goodsCode, g.goods_name goodsName"
		+ "					FROM 					 			"
		+ "							(SELECT r.rnum, r.question_code, r.category, r.question_memo, r.createdate, r.comment_memo"
		+ "									, r.commentCreatedate, r.emp_id, o.order_code, o.goods_code, o.customer_id"
		+ "								FROM "
		+ "										(SELECT r.rnum, r.question_code, r.orders_code, r.category, r.question_memo, r.createdate createdate"
		+ "											 	, qc.comment_memo, qc.createdate commentCreatedate, qc.emp_id"
		+ "											FROM "
		+ "							 						(SELECT ROW_NUMBER() OVER(ORDER BY question_code DESC) rnum"
		+ "							 								, question_code, orders_Code, category, question_memo, createdate FROM question) r"
		+ "							 					INNER JOIN question_comment qc"
		+ "							 					ON r.question_code = qc.question_code) r"
		+ "									INNER JOIN orders o"
		+ "									ON r.orders_code = o.order_code) r"
		+ "						INNER JOIN goods g"
		+ "						ON r.goods_code = g.goods_code"
		+ " 				WHERE r.emp_id LIKE ? AND r.category LIKE ?"
		+ "					ORDER BY comment_memo " +sort+ " , createdate DESC) r";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setString(2, "%"+category+"%");
	}
		ResultSet rs = stmt.executeQuery();
	    if(rs.next()) {
	    	cnt = rs.getInt("cnt");
	    }
	    // 디버깅
  		System.out.println(cnt + " <-- resultcnt");
		return cnt;
	}
}
