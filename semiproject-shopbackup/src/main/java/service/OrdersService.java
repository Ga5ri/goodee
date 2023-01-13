package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.OrderDao;
import dao.PointDao;
import util.DBUtil;
import vo.Customer;
import vo.CustomerAddress;
import vo.Goods;
import vo.Goods;
import vo.Orders;
import vo.PointHistory;

public class OrdersService {
	private OrderDao orderDao;
	private PointDao pointDao;
	
	// 주문목록
	public ArrayList<Orders> getOrderListByPage(int currentPage, int rowPerPage, String customerId) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Orders> list = null;
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;			
			conn = DBUtil.getConnection();
			orderDao = new OrderDao();
			list = orderDao.selectOrderListByPage(conn, beginRow, endRow, customerId);
			System.out.println(list + "list 확인");
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
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
	
	// 주문목록 검색추가
	public ArrayList<Orders> getOrderListByPage(int currentPage, int rowPerPage, String customerId, String word) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Orders> list = null;
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;
			conn = DBUtil.getConnection();
			orderDao = new OrderDao();
			list = orderDao.selectOrderListByPage(conn, beginRow, endRow, customerId, word);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
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
	
	// 페이징을 위한 OrderList 수 수하기
	public int cntOrderListServie(String customerId) {
		int resultCnt = 0;
		orderDao = new OrderDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultCnt = orderDao.cntOrderList(conn, customerId);
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
		return resultCnt;
	}
	
	// 페이징+검색을 위한 OrderList 수 수하기
	public int cntOrderListServie(String customerId, String word) {
		int resultCnt = 0;
		orderDao = new OrderDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultCnt = orderDao.cntOrderList(conn, word, customerId);
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
		return resultCnt;
	}
	
	// 주문 시 필요 : 고객정보
	public Customer getCustomerInfoForOrderService(String customerId) {
		orderDao = new OrderDao();
		Connection conn = null;
		Customer customer = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			customer = orderDao.selectCustomerInfoForOrder(conn, customerId);
			System.out.println("customer service : " + customer);
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
		return customer;
	}
	// 주문 시 필요 : 고객주소
	public ArrayList<CustomerAddress> getCustomerAddressForOrderService(String customerId) {
		orderDao = new OrderDao();
		Connection conn = null;
		ArrayList<CustomerAddress> list = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			list = orderDao.selectCustomerAddressForOrder(conn, customerId);
			System.out.println("customerAddress service : " + list);
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
		return list;
	}
	// 주문 시 필요 : 주문할 상품 정보 조회
	public Goods getGoodsForOrderService(int goodsCode) {
		orderDao = new OrderDao();
		Connection conn = null;
		Goods goods = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			goods = orderDao.selectGoodsForOrder(conn, goodsCode);
			System.out.println("goods service : " + goods);
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
		return goods;
	}
	
	// 주문상세보기 -- 수정중
	public Orders getOtderOne(int boardNo,  String customerId) {
		orderDao = new OrderDao();
		Connection conn = null;
		Orders orders = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			orders = orderDao.selectOrderOne(conn, boardNo, customerId);
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
		return orders;
	}
	
	// 주문하기
	public int addOrderService(Orders orders, PointHistory pointHistory) {
		orderDao = new OrderDao();
		pointDao = new PointDao();
		int orderCode = 0;
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = orderDao.addOrder(conn, orders);
			System.out.println(row + " : 1차 주문");
			orderCode = orderDao.selectOrderForPoint(conn, orders.getCustomerId());
			System.out.println(orderCode + " : 2차 주문생성 후 주문번호 가져오기");
			pointHistory.setOrderCode(orderCode);
			row = pointDao.addPointHistory(conn, pointHistory);
			System.out.println(row + " : 3차 포인트 기록");
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
	
	// 주문취소(배송 전까지만 가능)
	public int deleteOrderService(Orders orders, String customerId) {
		orderDao = new OrderDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = orderDao.deleteOrderList(conn, orders, customerId);
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
}