package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.GoodsImg;

public class GoodsImgDao {
	// 상품 수정
	public int modifyGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql = "UPDATE goods_img"
				+ 	" SET filename = ?, content_type = ?, origin_name = ?"
				+ 	" WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, goodsImg.getFilename());
		stmt.setString(2, goodsImg.getContentType());
		stmt.setString(3, goodsImg.getOriginName());
		stmt.setInt(4, goodsImg.getGoodsCode());
		
		row = stmt.executeUpdate();
		
		return row;
	}
	
	// 상품 삭제
	public int deleteGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql = "DELETE FROM goods_img WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsImg.getGoodsCode());
		
		row = stmt.executeUpdate();
		System.out.println("img row값 :"+row);
		return row;
	}
	
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
}