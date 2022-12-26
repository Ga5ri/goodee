package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.BoardDao;
import util.DBUtil;
import vo.Board;

// 서비스
// 1) 예외처리 -> db자원반납, 트랜잭션 처리
// 2) Connection객체를 dao에 넘겨주는 역활
// 3) 기타 dao안에 있어서는 안되는 비지니스로직(코드)
public class BoardService {
	private BoardDao boardDao;
	public ArrayList<Board> getBoardList()  {
		boardDao = new BoardDao();
		Connection conn = null;
		ArrayList<Board> list = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			list = boardDao.selectBoardList(conn);
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
	
	public Board getBoardOne(int no) {
		boardDao = new BoardDao();
		Connection conn = null;
		Board board = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			board = boardDao.selectBoardOne(conn, no);
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
		return board;
	}
	
	public int addBoardService(Board board) {
		boardDao = new BoardDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = boardDao.addBoardList(conn, board);
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
	
	public int modifyBoardService(Board board) {
		boardDao = new BoardDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = boardDao.modifyBoardList(conn, board);
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
	
	public int removeBoardService(Board board) {
		boardDao = new BoardDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = boardDao.removeBoardList(conn, board);
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