package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Goods;

public class GoodsDao {
	// 상품 수정(INNER JOIN UPDATE로 변경해보기)
	public HashMap<String, Integer> modifyGoods(Connection conn, Goods goods) throws Exception {
		int row = 0;
		String sql = "UPDATE goods SET goods_name = ?, goods_price = ?"
				+ 	" , soldout = ?, hit = ?, createdate = NOW() WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, goods.getGoodsName());
		stmt.setInt(2, goods.getGoodsPrice());
		stmt.setString(3, goods.getSoldout());
		stmt.setString(4, goods.getHit());
		stmt.setInt(5, goods.getGoodsCode());
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
	
	// 상품 목록(INNER JOIN)
	public ArrayList<HashMap<String, Object>> selectItemList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT gs.goods_code goodsCode, gs.goods_name goodsName, gs.goods_price goodsPrice, img.filename filename"
				+ 	" FROM goods gs INNER JOIN goods_img img"
				+ 	" ON gs.goods_code = img.goods_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("filename", rs.getString("filename"));
			list.add(m);
		}
		return list;
	}
	
	// 상품 상세정보(INNER JOIN)
	public ArrayList<HashMap<String, Object>> selectGoodsOne(Connection conn, int goodsCode) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String sql = "SELECT gs.goods_code goodsCode, gs.goods_name goodsName"
					+ 	" , gs.goods_price goodsPrice, gs.hit hit, gs.soldout soldout"
					+ 	" , img.filename filename, img.content_type contentType, img.origin_name originName"
					+ 	" FROM goods gs INNER JOIN goods_img img"
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
			m.put("filename", rs.getString("filename"));
			m.put("contentType", rs.getString("contentType"));
			m.put("hit", rs.getString("hit"));
			m.put("originName", rs.getString("originName"));
			m.put("soldout", rs.getString("soldout"));
			list.add(m);
		}
		return list;
	}
	
	/*
	// 상품 상세보기
	public Goods selectGoodsOne(Connection conn, int goodsCode) throws Exception {
		Goods goods = new Goods();
		String sql = "SELECT goods_code goodsCode, goods_name goodsName, goods_price goodsPrice, goods_memo goodsMemo, createdate FROM goods WHERE goods_code=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			goods.setGoodsCode(rs.getInt("goodsCode"));
			goods.setGoodsName(rs.getString("goodsName"));
			goods.setGoodsMemo(rs.getString("goodsMemo"));
			goods.setGoodsPrice(rs.getInt("goodsPrice"));
			goods.setCreatedate(rs.getString("createdate"));
		}
		return goods;
	}
	*/
	
	// 상품추가
	public HashMap<String, Integer> insertItem(Connection conn, Goods goods) throws Exception {
		String sql = "INSERT INTO goods(goods_name, goods_price, soldout, emp_id, createdate) VALUES(?,?,?,?,?,NOW())";
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
