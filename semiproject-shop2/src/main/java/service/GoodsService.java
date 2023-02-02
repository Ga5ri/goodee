package service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.EmpDao;
import dao.GoodsDao;
import dao.GoodsImgDao;
import util.DBUtil;
import vo.Emp;
import vo.Goods;
import vo.GoodsImg;


public class GoodsService {
	private GoodsDao goodsDao;
	private GoodsImgDao goodsImgDao;
	// hit
	public int updateHit(Goods goods) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			GoodsDao goodsDao = new GoodsDao();
			row = goodsDao.updateHit(conn, goods);
			conn.commit();
		} catch (Exception e) {		
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
		return row;
	}
	
	// hit 상품 리스트
	// 상품 리스트
	public ArrayList<HashMap<String, Object>> getItemListByTop() {
		ArrayList<HashMap<String, Object>> topList = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsList)");
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			topList = goodsDao.selectItemListByTop(conn);
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
		return topList;
	}
	
	// 검색한 상품 리스트
	public ArrayList<HashMap<String, Object>> getItemListBySearch(int beginRow, int endRow, String searchWord, String category) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsList)");
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			list = goodsDao.selectSearchItemList(conn, beginRow, endRow, searchWord, category);
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
	// 상품 리스트
	public ArrayList<HashMap<String, Object>> getItemList(int beginRow, int endRow, String searchWord, String category) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsList)-Service");
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			list = goodsDao.selectItemList(conn, beginRow, endRow, category);
			System.out.println(category+"<---serivce카테고리");
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
	
	// 사업자용 상품 리스트
	public ArrayList<HashMap<String, Object>> getItemListByCompany(int beginRow, int rowPerPage, Goods goods) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsListByCompany)");
			conn.setAutoCommit(false);
			goodsDao = new GoodsDao();
			list = goodsDao.selectItemListByCompany(conn, beginRow, rowPerPage, goods);
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
	
	// 검색한 상품리스트 페이징
	public int count(String searchWord) {
		GoodsDao goodsDao = new GoodsDao();
		int cnt = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			goodsDao = new GoodsDao();
			cnt = goodsDao.goodsCount(conn, searchWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return cnt;
	}
	
	// 상품리스트 페이징
	public int count() {
		GoodsDao goodsDao = new GoodsDao();
		int cnt = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			goodsDao = new GoodsDao();
			cnt = goodsDao.goodsCount(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return cnt;
	}
	
	// 사업자용 상품리스트 페이징
	public int countByCompany(Goods goods) {
		GoodsDao goodsDao = new GoodsDao();
		int cnt = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			goodsDao = new GoodsDao();
			cnt = goodsDao.goodsCountByCompany(conn, goods);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return cnt;
	}
	
	// 상품 수정
	public int modifyGoods(Goods goods, GoodsImg goodsImg, String dir) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			GoodsDao goodsDao = new GoodsDao();
			GoodsImgDao goodsImgDao = new GoodsImgDao();
			row = goodsDao.modifyGoods(conn, goods);
			if(row == 1) {
				row = goodsImgDao.modifyGoodsImg(conn, goodsImg);
			}
			conn.commit();
		} catch (Exception e) {		
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
		return row;
	}
	
	// 상품 삭제
	public int deleteGoods(Goods goods, GoodsImg goodsImg) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			GoodsDao goodsDao = new GoodsDao();
			GoodsImgDao goodsImgDao = new GoodsImgDao();
			row = goodsImgDao.deleteGoodsImg(conn, goodsImg); // 트랜젝션-> 외래키로 img 테이블 먼저 삭제 진행
			if(row == 1) {
				row = goodsDao.deleteGoods(conn, goods);
			}
			conn.commit();
		} catch (Exception e) {		
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
		return row;
	}
	
	// 상품 상세보기
	public ArrayList<HashMap<String, Object>> getGoodsOne(int goodsCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsOne)");
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
	
	// 상품 추가
	public int addItem(Goods goods, GoodsImg goodsImg, String dir, String empId) {
		int row = 0;
		Connection conn = null;
		try {
			goodsDao = new GoodsDao();
			goodsImgDao = new GoodsImgDao();
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsService)");
			conn.setAutoCommit(false);
			
			HashMap<String, Integer> map = goodsDao.insertItem(conn, goods, empId);
				
			goodsImg.setGoodsCode(map.get("autoKey")); // map에서 받은 키값을 goodsImg타입에 저장
			// 키값을 goodsImg타입에 저장 후 goodsImgDao 호출
	        row = goodsImgDao.insertItem(conn, goodsImg); // goodsImg.getGoodsCode() --> 0
			System.out.println(row+"service row값");
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
