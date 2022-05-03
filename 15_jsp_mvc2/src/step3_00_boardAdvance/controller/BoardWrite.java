package step3_00_boardAdvance.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step3_00_boardAdvance.dao.BoardAdvanceDao;
import step3_00_boardAdvance.dto.BoardAdvanceDto;

@WebServlet("/boardWrite")
public class BoardWrite extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardWrite.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();
		boardAdvanceDto.setWriter(request.getParameter("writer"));
		boardAdvanceDto.setSubject(request.getParameter("subject"));
		boardAdvanceDto.setEmail(request.getParameter("email"));
		boardAdvanceDto.setPassword(request.getParameter("password"));
		boardAdvanceDto.setContent(request.getParameter("content"));

		BoardAdvanceDao.getInstance().insertBoard(boardAdvanceDto);
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardWritePro.jsp");
		dis.forward(request, response);
		
	}
	
}
