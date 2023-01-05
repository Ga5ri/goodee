package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Goods;
import vo.GoodsImg;

public class GoodsImgDao {
	// 상품 추가
	public int insertItem(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql = "INSERT INTO goods_img(goods_code, filename, origin_name, content_type, createdate) VALUES(?,?,?,?,NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsImg.getGoodsCode());
		stmt.setString(2, goodsImg.getFilename());
		stmt.setString(3, goodsImg.getOriginName());
		stmt.setString(4, goodsImg.getContentType());
		
		row = stmt.executeUpdate();
		return row;
	}
	/*
	// 상품 상세보기 GoodsImg 가져오는거 아직 미완성
	public GoodsImg selectGoodsOne(Connection conn, int goodsCode) throws Exception {
		GoodsImg goodsImg = new GoodsImg();
		String sql = "SELECT content_type contentType, filename FROM goods_img WHERE goods_code =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			goodsImg.setContentType(rs.getString("contentType"));
			goodsImg.setFilename(rs.getString("filename"));
		}
		return goodsImg;
	}
	*/
}
