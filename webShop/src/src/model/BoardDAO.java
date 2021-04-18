package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
// ctrl + shift + o -> import 전부 가져오기
public class BoardDAO {

	// 1. 모두 조회
	// 2. 상세 조회 (Board 번호로 상세보기)\
	// 3. 입력
	// 4. 수정
	// 5. 삭제
	
	public static int insertBoard(BoardVO board) {
		int result = 0;
		
		Connection conn;
		PreparedStatement ps = null;
		String sql = "INSERT INTO BOARD VALUES(board_autonum.nextval, ?, ?, ?, sysdate, 0, ?, ?) ";

		
		conn = DBUtil.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getBoard_title());
			ps.setString(2, board.getBoard_contents());
			ps.setInt(3, board.getBoard_writer());
			ps.setString(4, board.getBoard_password());
			ps.setString(5, board.getBoard_image());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(null, ps, conn);
			
		}
	
		return result;
	}
	
	public static int updateBoard(BoardVO board) {
		int result = 0;
		Connection conn;
		PreparedStatement ps = null;
		
		String sql = " UPDATE BOARD SET " +
					 " board_title = ?, " +
					 " board_contents = ?, " +
					 " board_date = SYSDATE, " +
					 " board_password = ?, " +
					 " board_image = ? " +
					 " where board_seq = ? ";
					 
		conn = DBUtil.getConnection();
		
		try {
//			ps = LoggableStatement(conn,sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getBoard_title());
			ps.setString(2, board.getBoard_contents());
			ps.setString(3, board.getBoard_password());
			ps.setString(4, board.getBoard_image());
			ps.setInt(5, board.getBoard_seq());
//			System.out.println("SQL문 확인:" +
//			          ((LoggableStatement) st).getQueryString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(null, ps, conn);
		}
		return result;
	}
	
	

	public static int deleteBoard(int boardNo, String passwd) {
		int result = 0;
		Connection conn;
		PreparedStatement ps = null;

		String sql = "DELETE FROM BOARD WHERE board_seq = ? and board_password = ?";
		conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardNo);
			ps.setString(2, passwd);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(null, ps, conn);
		}
		return result;
	}
	
	
	
	
	
	// 모든 데이터 조회
	public static List<BoardVO> selectAllBoard(){ // 여러 건 return이므로 당연히 List로 return을 받는다!
		List<BoardVO> boardlist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD ORDER BY 1 ";
		
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				BoardVO board = makeBoard(rs);
				boardlist.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}
		
		return boardlist;
		
	}
	
	
	
	
	
	// 번호 Sequence 조회
	public static BoardVO selectByNo(int boardNo) {
		BoardVO board = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null, st2 = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD WHERE board_seq = ? ";
		String sql2 = "UPDATE BOARD SET board_viewcount = nvl(board_viewcount, 0)+1 WHERE board_seq = ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, boardNo);
			rs = st.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				st = conn.prepareStatement(sql2);
				st.setInt(1, boardNo);
				int result = st.executeUpdate();
				System.out.println(result > 0 ? "board_viewcount 수정":"board_viewcount 수정X");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}

		return board;
	}
	

	
	// ID
	public List<BoardVO> selectById(String id){
		List<BoardVO> boardlist = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD WHERE board_id = ? ";
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs = st.executeQuery();
			while(rs.next()) {
				BoardVO board = makeBoard(rs);
				boardlist.add(board);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbclose(rs, st, conn);
		}

		return boardlist;
	}
	
	
	
	private static BoardVO makeBoard(ResultSet rs) throws SQLException {
		//ResultSet에서 읽어서 VO객체를 만든다
		BoardVO bvo = new BoardVO();
		bvo.setBoard_seq(rs.getInt("board_seq"));
		bvo.setBoard_title(rs.getString("board_title"));
		bvo.setBoard_contents(rs.getString("board_contents"));
		bvo.setBoard_writer(rs.getInt("board_writer"));
		bvo.setBoard_date(rs.getDate("board_date"));
		bvo.setBoard_viewcount(rs.getInt("board_viewcount"));
		bvo.setBoard_password(rs.getString("board_password"));
		bvo.setBoard_image(rs.getString("board_image"));
		return bvo;
	}

}
