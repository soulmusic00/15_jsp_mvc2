package step3_00_boardAdvance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import step3_00_boardAdvance.dto.BoardAdvanceDto;

public class BoardAdvanceDao {

	private BoardAdvanceDao() {}
	private static BoardAdvanceDao instance = new BoardAdvanceDao();
	public static BoardAdvanceDao getInstance() {
		return instance;
	}

	Connection conn         = null;
	PreparedStatement pstmt = null;
	ResultSet rs            = null;

	
	public Connection getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool3");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public int getAllBoardCount(String searchKeyword , String searchWord) {
		
		int allBoardCount = 0;
		String sql = "";
		
		try {
			
			conn  = getConnection();
			if (searchKeyword.equals("total")) {
				if (searchWord.equals("")) {
					sql = "SELECT COUNT(*) FROM BOARD ";
					pstmt = conn.prepareStatement(sql);
				} 
				else {
					sql = "SELECT COUNT(*) FROM BOARD ";
					sql += "WHERE WRITER LIKE CONCAT ('%', ?, '%') ";
					sql += "OR SUBJECT LIKE CONCAT ('%', ?, '%')";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchWord);
					pstmt.setString(2, searchWord);
					
				}
				rs = pstmt.executeQuery();
			}
			else if (searchKeyword.equals("writer")) {
				sql = "SELECT COUNT(*) FROM BOARD WHERE WRITER LIKE CONCAT ('%', ?, '%')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1 , searchWord);
				rs = pstmt.executeQuery();
			}
			else if (searchKeyword.equals("subject")) {
				sql = "SELECT COUNT(*) FROM BOARD WHERE SUBJECT LIKE CONCAT ('%', ?, '%')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1 , searchWord);
				rs = pstmt.executeQuery();
			}
			
			if (rs.next()) {
				allBoardCount = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return allBoardCount;
		
	}
	
	
	public ArrayList<BoardAdvanceDto> getAllBoard(int startRow , int onePageViewCount , String searchKeyword , String searchWord) {

		ArrayList<BoardAdvanceDto> boardList = new ArrayList<BoardAdvanceDto>();
		BoardAdvanceDto boardAdvanceDto = null;
		
		try {
			
			conn  = getConnection();
			
			String sql = "";
			if (searchKeyword.equals("total")) {
				if (searchWord.equals("")) {
					sql = "SELECT * FROM BOARD ORDER BY REF DESC , RE_STEP ASC LIMIT ? , ?";					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, onePageViewCount);
				}
				else {
					sql = "SELECT * FROM BOARD ";
					sql += "WHERE WRITER LIKE CONCAT ('%', ?, '%') ";
					sql += "OR SUBJECT LIKE CONCAT ('%', ?, '%') ";
					sql += "ORDER BY REF DESC , RE_STEP ASC LIMIT ? , ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1 , searchWord);
					pstmt.setString(2 , searchWord);
					pstmt.setInt(3, startRow);
					pstmt.setInt(4, onePageViewCount);
				}
				rs = pstmt.executeQuery();
			}
			else if (searchKeyword.equals("writer")) {
				sql = "SELECT * FROM BOARD WHERE WRITER LIKE CONCAT ('%', ?, '%') ORDER BY REF DESC , RE_STEP ASC LIMIT ? , ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1 , searchWord);
				pstmt.setInt(2 , startRow);
				pstmt.setInt(3 , onePageViewCount);
				rs = pstmt.executeQuery();
			}
			else if (searchKeyword.equals("subject")) {
				sql = "SELECT * FROM BOARD WHERE SUBJECT LIKE CONCAT ('%', ?, '%') ORDER BY REF DESC , RE_STEP ASC LIMIT ? , ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1 , searchWord);
				pstmt.setInt(2 , startRow);
				pstmt.setInt(3 , onePageViewCount);
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {

				boardAdvanceDto = new BoardAdvanceDto();
				boardAdvanceDto.setNum(rs.getInt("NUM"));
				boardAdvanceDto.setWriter(rs.getString("WRITER"));
				boardAdvanceDto.setEmail(rs.getString("EMAIL"));
				boardAdvanceDto.setSubject(rs.getString("SUBJECT"));
				boardAdvanceDto.setPassword(rs.getString("PASSWORD"));
				boardAdvanceDto.setRegDate(rs.getDate("REG_DATE"));
				boardAdvanceDto.setRef(rs.getInt("REF"));
				boardAdvanceDto.setReStep(rs.getInt("RE_STEP"));
				boardAdvanceDto.setReLevel(rs.getInt("RE_LEVEL"));
				boardAdvanceDto.setReadCount(rs.getInt("READ_COUNT"));
				boardAdvanceDto.setContent(rs.getString("CONTENT"));
				boardList.add(boardAdvanceDto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return boardList;
		
	}
	
	
	public BoardAdvanceDto getOneBoard(int num) {

		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();

		try {
			
			conn = getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT=READ_COUNT+1 WHERE NUM=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				boardAdvanceDto.setNum(rs.getInt("NUM"));
				boardAdvanceDto.setWriter(rs.getString("WRITER"));
				boardAdvanceDto.setEmail(rs.getString("EMAIL"));
				boardAdvanceDto.setSubject(rs.getString("SUBJECT"));
				boardAdvanceDto.setPassword(rs.getString("PASSWORD"));
				boardAdvanceDto.setRegDate(rs.getDate("REG_DATE"));
				boardAdvanceDto.setRef(rs.getInt("REF"));
				boardAdvanceDto.setReStep(rs.getInt("RE_STEP"));
				boardAdvanceDto.setReLevel(rs.getInt("RE_LEVEL"));
				boardAdvanceDto.setReadCount(rs.getInt("READ_COUNT"));
				boardAdvanceDto.setContent(rs.getString("CONTENT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return boardAdvanceDto;
		
	}

	
	public BoardAdvanceDto getOneUpdateBoard(int num) {

		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();

		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				boardAdvanceDto.setNum(rs.getInt("NUM"));
				boardAdvanceDto.setWriter(rs.getString("WRITER"));
				boardAdvanceDto.setEmail(rs.getString("EMAIL"));
				boardAdvanceDto.setSubject(rs.getString("SUBJECT"));
				boardAdvanceDto.setPassword(rs.getString("PASSWORD"));
				boardAdvanceDto.setRegDate(rs.getDate("REG_DATE"));
				boardAdvanceDto.setReadCount(rs.getInt("READ_COUNT"));
				boardAdvanceDto.setContent(rs.getString("CONTENT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return boardAdvanceDto;
		
	}

	
	public boolean validMemberCheck(BoardAdvanceDto boardAdvanceDto) {

		boolean isValidMember = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM BOARD WHERE NUM=? AND PASSWORD=?");
			pstmt.setInt(1, boardAdvanceDto.getNum());
			pstmt.setString(2, boardAdvanceDto.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) 	isValidMember = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}

		return isValidMember;
		
	}
	
	
	public void insertBoard(BoardAdvanceDto boardAdvanceDto) {

		
		int maxRef = 0;
		try {
				conn = getConnection();
			
				pstmt = conn.prepareStatement("SELECT MAX(REF) + 1 FROM BOARD"); 
				rs = pstmt.executeQuery();
				if (rs.next()) {
					maxRef = rs.getInt(1);
				}
				
				String sql = "INSERT INTO BOARD(WRITER , EMAIL , SUBJECT , PASSWORD , REG_DATE , REF , RE_STEP , RE_LEVEL , READ_COUNT , CONTENT) ";
					   sql += "VALUES(?, ?, ?, ?, now(), ?, ?, ?, 0, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardAdvanceDto.getWriter());
				pstmt.setString(2, boardAdvanceDto.getEmail());
				pstmt.setString(3, boardAdvanceDto.getSubject());
				pstmt.setString(4, boardAdvanceDto.getPassword());
				pstmt.setInt(5, maxRef);
				pstmt.setInt(6, 1);
				pstmt.setInt(7, 1);
				pstmt.setString(8, boardAdvanceDto.getContent());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}	
		
	}
	
	
	public boolean updateBoard(BoardAdvanceDto boardAdvanceDto) {

		boolean isUpdate = false;
		
		try {
			
			if (validMemberCheck(boardAdvanceDto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT=?, CONTENT=? WHERE NUM=?");
				pstmt.setString(1, boardAdvanceDto.getSubject());
				pstmt.setString(2, boardAdvanceDto.getContent());
				pstmt.setInt(3, boardAdvanceDto.getNum());
				pstmt.executeUpdate();
				isUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isUpdate;
		
	}

	
	public boolean deleteBoard(BoardAdvanceDto boardAdvanceDto) {

		boolean isDelete = false;
		
		try {
			
			if (validMemberCheck(boardAdvanceDto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
				pstmt.setInt(1, boardAdvanceDto.getNum());
				pstmt.executeUpdate();
				isDelete = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isDelete;
		
	}
	
	public void makeDummyData(ArrayList<BoardAdvanceDto> dummyDataList) {

		
		try {
			
			conn = getConnection();
			for (BoardAdvanceDto boardAdvanceDto : dummyDataList) {
				String sql = "INSERT INTO BOARD(WRITER , EMAIL , SUBJECT , PASSWORD , REG_DATE , REF , RE_STEP , RE_LEVEL , READ_COUNT , CONTENT) ";
				   	   sql += "VALUES(?, ?, ?, ?, now(), ?, ?, ?, 0, ?)";
			    pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardAdvanceDto.getWriter());
				pstmt.setString(2, boardAdvanceDto.getEmail());
				pstmt.setString(3, boardAdvanceDto.getSubject());
				pstmt.setString(4, boardAdvanceDto.getPassword());
				pstmt.setInt(5, boardAdvanceDto.getRef());
				pstmt.setInt(6, boardAdvanceDto.getReStep());
				pstmt.setInt(7, boardAdvanceDto.getReLevel());
				pstmt.setString(8, boardAdvanceDto.getContent());
				pstmt.executeUpdate();
			}
			      
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
	}
	
	public void reWriteBoard(BoardAdvanceDto boardAdvanceDto) {

		try {
			
				conn = getConnection();
				
				pstmt = conn.prepareStatement("UPDATE BOARD SET RE_STEP = RE_STEP + 1 WHERE REF = ? AND RE_STEP > ?");
				pstmt.setInt(1, boardAdvanceDto.getRef());
				pstmt.setInt(2, boardAdvanceDto.getReStep());
				pstmt.executeUpdate();
				
				String sql = "INSERT INTO BOARD(WRITER , EMAIL , SUBJECT , PASSWORD , REG_DATE , REF , RE_STEP , RE_LEVEL , READ_COUNT , CONTENT) ";
					   sql += "VALUES(?, ?, ?, ?, now(), ?, ?, ?, 0, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardAdvanceDto.getWriter());
				pstmt.setString(2, boardAdvanceDto.getEmail());
				pstmt.setString(3, boardAdvanceDto.getSubject());
				pstmt.setString(4, boardAdvanceDto.getPassword());
				pstmt.setInt(5, boardAdvanceDto.getRef());
				pstmt.setInt(6, boardAdvanceDto.getReStep() + 1);
				pstmt.setInt(7, boardAdvanceDto.getReLevel() + 1);
				pstmt.setString(8, boardAdvanceDto.getContent());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}	
		
	}
	
}
