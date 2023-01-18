package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Goods;

public class CartDao {

	// CartList : 장바구니에 추가한 상품의 리스트 출력
	// 사용하는 곳 : CartListController
	public ArrayList<HashMap<String, Object>> selectCartList(Connection conn, Goods goods) throws Exception {
		
		ArrayList<HashMap<String, Object>> list = null;
		
		String sql = "SELECT g.goods_code goodsCode"
				+ "		, g.goods_name goodsName"
				+ "		, g.goods_price goodsPrice"
				+ "		, g.soldout soldout"
				+ "		, g.emp_id empId"
				+ "		, gi.filename fileName"
				+ "	 FROM goods g"
				+ "		 INNER JOIN goods_img gi"
				+ "		 ON g.goods_code = gi.goods_code"
				+ "	 WHERE g.goods_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, goods.getGoodsCode());
		
		ResultSet rs = stmt.executeQuery();
		
		list = new ArrayList<HashMap<String, Object>>();
		
		while(rs.next()) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("goodsCode", rs.getInt("goodsCode"));
			map.put("goodsName", rs.getString("goodsName"));
			map.put("goodsPrice", rs.getInt("goodsPrice"));
			map.put("soldout", rs.getString("soldout"));
			map.put("empId", rs.getString("empId"));
			map.put("fileName", rs.getString("fileName"));
			
			list.add(map);
			
		}
		
		
		
		return list;
		
	}
	
	
	
}
