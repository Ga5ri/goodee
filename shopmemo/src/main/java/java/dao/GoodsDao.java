package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Goods;
import vo.GoodsImg;

public class GoodsDao {
	// 상품 수정 진행중~
	public int modifyGoods(Connection conn, Goods goods, String filename) throws Exception {
		int row = 0;
		String sql = "UPDATE goods gs INNER JOIN goods_img img"
				+ 	" ON gs.goods_code = img.goods_code"
				+ 	" SET gs.goods_name = ?, gs.goods_price = ?, gs.soldout = ?"
				+ 	" , gs.hit = ?, gs.createdate = NOW(), img.filename = ?"
				+ 	" WHERE gs.goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, goods.getGoodsName());
		stmt.setInt(2, goods.getGoodsPrice());
		stmt.setString(3, goods.getSoldout());
		stmt.setString(4, goods.getHit());
		stmt.setString(5, filename);
		stmt.setInt(6, goods.getGoodsCode());
		row = stmt.executeUpdate();
		System.out.println(row + " <-- goodsDao");
		return row;
	}
	
	// 검색 상품 목록
	public ArrayList<HashMap<String, Object>> selectSearchItemList(Connection conn, int beginRow, int rowPerPage, String searchWord) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.goods_code goodsCode, r.goods_name goodsName"
				+ 	" , r.goods_price goodsPrice, r.createdate createdate, img.filename filename"
				+ 		" FROM (SELECT ROW_NUMBER() OVER(ORDER BY goods_code DESC) rnum"
				+ 		" , goods_code, goods_name, goods_price, createdate "
				+ 				" FROM goods WHERE goods_name LIKE ?) r "
				+ 	" LEFT OUTER JOIN goods_img img"
				+ 	" ON r.goods_code = img.goods_code"
				+ 	" WHERE rnum BETWEEN ? AND ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("createdate", rs.getString("createdate"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	// 상품 목록(페이징)
	public ArrayList<HashMap<String, Object>> selectItemList(Connection conn, int beginRow, int rowPerPage) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT r.rnum rnum, r.goods_code goodsCode, r.goods_name goodsName"
				+ 	" , r.goods_price goodsPrice, r.createdate createdate, img.filename filename"
				+ 		" FROM (SELECT ROW_NUMBER() OVER(ORDER BY goods_code DESC) rnum"
				+ 			" , goods_code, goods_name, goods_price, createdate "
				+ 				" FROM goods) r LEFT OUTER JOIN goods_img img"
				+ 	" ON r.goods_code = img.goods_code"
				+ 	" ORDER BY createdate DESC LIMIT ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("createdate", rs.getString("createdate"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	// 검색된 상품 전체 수(페이징)
	public int goodsCount(Connection conn, String searchWord) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM goods WHERE goods_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		return cnt;
	}
	
	// 상품 전체 수(페이징)
	public int goodsCount(Connection conn) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM goods";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		return cnt;
	}
	
	// 상품 상세정보(INNER JOIN)
	public ArrayList<HashMap<String, Object>> selectGoodsOne(Connection conn, int goodsCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT gs.goods_code goodsCode, gs.goods_name goodsName"
					+ 	" , gs.goods_price goodsPrice, gs.emp_id empId, gs.hit hit, gs.soldout soldout"
					+ 	" , img.filename filename, img.content_type contentType, img.origin_name originName"
					+ 		" FROM goods gs INNER JOIN goods_img img"
					+ 	" ON gs.goods_code = img.goods_code"
					+ 	" WHERE gs.goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("empId", rs.getString("empId"));
			m.put("hit", rs.getString("hit"));
			m.put("soldout", rs.getString("soldout"));
			m.put("filename", rs.getString("filename"));
			m.put("contentType", rs.getString("contentType"));
			m.put("originName", rs.getString("originName"));
			list.add(m);
		}
		return list;
	}
	
	// 상품추가
	public HashMap<String, Integer> insertItem(Connection conn, Goods goods) throws Exception {
		String sql = "INSERT INTO goods(goods_name, goods_price, soldout, emp_id, createdate) VALUES(?,?,?,?,NOW())";
		// PreparedStatement.RETURN_GENERATED_KEYS 쿼리실행 후 생성된 auto_increment값을 ResultSet에 반환
		PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		stmt.setString(1, goods.getGoodsName());
		stmt.setInt(2, goods.getGoodsPrice());
		stmt.setString(3, goods.getSoldout());
		stmt.setString(4, goods.getEmpId());
		System.out.println(goods.getGoodsName()+"<-상품명");
		System.out.println(goods.getGoodsPrice()+"<-상품가격");
		System.out.println(goods.getSoldout()+"<-상품재고");
		System.out.println(goods.getEmpId()+"<-사원아이디");
		
		int row = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		int autoKey = 0;
		if(rs.next()) {
			autoKey = rs.getInt(1); // stmt.executeUpdate(); 생성된 auto_increment값이 대입
			System.out.println(autoKey+"<-오토키값");
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		return map;
	}
}
