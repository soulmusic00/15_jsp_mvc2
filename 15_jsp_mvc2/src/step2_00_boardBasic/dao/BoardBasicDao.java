package step2_00_boardBasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import step2_00_boardBasic.dto.BoardBasicDto;

public class BoardBasicDao {

	private BoardBasicDao() {}
	private static BoardBasicDao instance = new BoardBasicDao();
	public static BoardBasicDao getInstance() {
		return instance;
	}

	Connection conn         = null;
	PreparedStatement pstmt = null;
	ResultSet rs            = null;

	public Connection getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool2");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 전체 게시글을 조회하는 DAO
	public ArrayList<BoardBasicDto> getAllBoard() {

		ArrayList<BoardBasicDto> boardList = new ArrayList<BoardBasicDto>();
		BoardBasicDto model = null;
		
		try {
			
			conn  = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {

				model = new BoardBasicDto();
				model.setNum(rs.getInt("NUM"));
				model.setWriter(rs.getString("WRITER"));
				model.setEmail(rs.getString("EMAIL"));
				model.setSubject(rs.getString("SUBJECT"));
				model.setPassword(rs.getString("PASSWORD"));
				model.setRegDate(rs.getDate("REG_DATE").toString());
				model.setReadCount(rs.getInt("READ_COUNT"));
				model.setContent(rs.getString("CONTENT"));

				boardList.add(model);
				
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
	
	// 한개의 게시글을 조회하는 DAO
	public BoardBasicDto getOneBoard(int num) {

		BoardBasicDto model = new BoardBasicDto();

		try {
			
			conn = getConnection();

			pstmt = conn.prepareStatement("UPDATE BOARD SET READ_COUNT=READ_COUNT+1 WHERE NUM=?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt("NUM"));
				model.setWriter(rs.getString("WRITER"));
				model.setEmail(rs.getString("EMAIL"));
				model.setSubject(rs.getString("SUBJECT"));
				model.setPassword(rs.getString("PASSWORD"));
				model.setRegDate(rs.getDate("REG_DATE").toString());
				model.setReadCount(rs.getInt("READ_COUNT"));
				model.setContent(rs.getString("CONTENT"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return model;
		
	}

	// 업데이트할 데이터를 조회하는 DAO
	public BoardBasicDto getOneUpdateBoard(int num) {

		BoardBasicDto model = new BoardBasicDto();

		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				model.setNum(rs.getInt(1));
				model.setWriter(rs.getString(2));
				model.setEmail(rs.getString(3));
				model.setSubject(rs.getString(4));
				model.setPassword(rs.getString(5));
				model.setRegDate(rs.getDate(6).toString());
				model.setReadCount(rs.getInt(7));
				model.setContent(rs.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)    {try {rs.close();}    catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return model;
		
	}

	
	// 비밀번호가 일치하는지 검증하는 DAO
	public boolean validMemberCheck(BoardBasicDto boardDTO) {

		boolean isValidMember = false;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement( "SELECT * FROM BOARD WHERE NUM=? AND PASSWORD=?");
			pstmt.setInt(1, boardDTO.getNum());
			pstmt.setString(2, boardDTO.getPassword());
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
	
	// 게시글을 추가하는 DAO
	public void insertBoard(BoardBasicDto boardDto) {

		try {
			
				conn = getConnection();
				String sql = "INSERT INTO BOARD(WRITER,EMAIL,SUBJECT,PASSWORD,REG_DATE,READ_COUNT,CONTENT)";
					   sql += "VALUES(?, ?, ?, ?, now(), 0, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardDto.getWriter());
				pstmt.setString(2, boardDto.getEmail());
				pstmt.setString(3, boardDto.getSubject());
				pstmt.setString(4, boardDto.getPassword());
				pstmt.setString(5, boardDto.getContent());
				pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}	
		
	}
	
	// 게시글을 수정하는 DAO
	public boolean updateBoard(BoardBasicDto boardDto) {

		boolean isUpdate = false;
		
		try {
			
			if (validMemberCheck(boardDto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("UPDATE board SET SUBJECT=?, CONTENT=? WHERE NUM=?");
				pstmt.setString(1, boardDto.getSubject());
				pstmt.setString(2, boardDto.getContent());
				pstmt.setInt(3, boardDto.getNum());
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

	// 게시글을 삭제하는 DAO
	public boolean deleteBoard(BoardBasicDto boardDto) {

		boolean isDelete = false;
		
		try {
			
			if (validMemberCheck(boardDto)) {
				conn = getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?");
				pstmt.setInt(1, boardDto.getNum());
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
	
	
}
