package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.GoodsImg;

public class GoodsImgDao {
	public int insertItem(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql = "INSERT INTO goods_img(goods_code, filename, content_type, createdate) VALUES(?,?,?,NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsImg.getGoodsCode());
		stmt.setString(2, goodsImg.getFilename());
		stmt.setString(3, goodsImg.getContentType());
		
		row = stmt.executeUpdate();
		return row;
	}
}
