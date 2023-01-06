package service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.GoodsDao;
import dao.GoodsImgDao;
import util.DBUtil;
import vo.Goods;
import vo.GoodsImg;


public class GoodsService {
	private GoodsDao goodsDao;
	private GoodsImgDao goodsImgDao;
	// 상품 수정
	public int modifyGoods(Goods goods, GoodsImg goodsImg, String dir) {
		int row = 0;
		Connection conn = null;
		goodsDao = new GoodsDao();
		goodsImgDao = new GoodsImgDao();
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			HashMap<String, Integer> map = goodsDao.modifyGoods(conn, goods);
			
			goodsImg.setGoodsCode(map.get("autoKey"));
			row = goodsImgDao.modifyGoods(conn, goodsImg);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				File f = new File(dir+"//"+goodsImg.getFilename());
				if(f.exists()) {
					f.delete();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
		
	
	// 상품 리스트
	public ArrayList<HashMap<String, Object>> getItemList() {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsList)");
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			list = goodsDao.selectItemList(conn);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 상품 상세보기
	public ArrayList<HashMap<String, Object>> getGoodsOne(int goodsCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsList)");
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			list = goodsDao.selectGoodsOne(conn, goodsCode);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/*
	// 상품 상세보기
	public Goods getGoodsOne(int goodsCode) {
		goodsDao = new GoodsDao();
		Goods goods = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsOne)");
			conn.setAutoCommit(false);
			goods = goodsDao.selectGoodsOne(conn, goodsCode);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return goods;
	}
	*/
	
	// 상품 추가
	public int addItem(Goods goods, GoodsImg goodsImg, String dir) {
		int row = 0;
		goodsDao = new GoodsDao();
		goodsImgDao = new GoodsImgDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(addGoods)");
			conn.setAutoCommit(false);
			
			HashMap<String, Integer> map = goodsDao.insertItem(conn, goods);
				
			goodsImg.setGoodsCode(map.get("autoKey")); // map에서 받은 키값을 goodsImg타입에 저장
			// 키값을 goodsImg타입에 저장 후 goodsImgDao 호출
	        row = goodsImgDao.insertItem(conn, goodsImg); // goodsImg.getGoodsCode() --> 0
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				// 이미 업로드 되어있는 파일 삭제
				File f = new File(dir+"//"+goodsImg.getFilename());
				if(f.exists()) {
					f.delete();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
}
