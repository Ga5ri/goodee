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
	
	// 주문상세보기
	public Orders getOrderOne(int orderCode, String customerId) {
		orderDao = new OrderDao();
		Connection conn = null;
		Orders orders = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			orders = orderDao.selectOrderOne(conn, orderCode, customerId);
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
	
	// 주문하기 - 포인트 적립만
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
	
	// 주문하기 - 포인트 사용
	public int addOrderService(Orders orders, PointHistory pointHistory, Customer customer) {
		orderDao = new OrderDao();
		pointDao = new PointDao();
		int orderCode = 0;
		int point = 0;
		Connection conn = null;
		int row = 0;
		int row2 = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			orderCode = orderDao.selectOrderForPoint(conn, customer.getCustomerId());
			System.out.println(orderCode + " : 1차 주문생성 후 주문번호 가져오기");
			pointHistory.setOrderCode(orderCode);
			row2 = pointDao.addPointHistory(conn, pointHistory);
			System.out.println(row2 + " : 2차 포인트 사용 기록");
			point = pointDao.updatePoint(conn, customer);
			System.out.println(point + " : 3차 사용 포인트 업데이트");
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
		return point;
	}
	
	// 주문취소(배송 전까지만 가능) - 포인트 사용 후, 적립만 했었다면 2차부터
	public int deleteOrderService(Orders orders, Customer customer) {
		orderDao = new OrderDao();
		pointDao = new PointDao();
		PointHistory pointHistory = new PointHistory();
		int orderCode = orders.getOrderCode();
		Connection conn = null;
		int row = 0;
		int row2 = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pointHistory = pointDao.selectPointHistoryForDelete(conn, orderCode);
			System.out.println(pointHistory.getPointKind() + "사용인가?");
			String pointKind = pointHistory.getPointKind();
			if(pointKind.equals("사용")) {
				System.out.println("1차 포인트 업데이트"); // selectPoint
				int returnPoint = customer.getPoint() + pointHistory.getPoint();
				System.out.println(returnPoint + "  " + customer.getPoint() + " " + pointHistory.getPoint());
				customer.setPoint(returnPoint);
				pointDao.updatePoint(conn, customer);
			}
			row = pointDao.deletePointHistory(conn, orderCode);
			System.out.println(row + " : 2차 포인트 기록삭제");
			row2 = orderDao.deleteOrderList(conn, orderCode, customer.getCustomerId());
			System.out.println(row2 + " : 3차 주문삭제");
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