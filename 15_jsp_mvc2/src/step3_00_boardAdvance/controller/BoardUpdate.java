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


@WebServlet("/boardUpdate")
public class BoardUpdate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("boardAdvanceDto" , BoardAdvanceDao.getInstance().getOneUpdateBoard(Integer.parseInt(request.getParameter("num"))));
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardUpdate.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();
		boardAdvanceDto.setNum(Integer.parseInt(request.getParameter("num")));
		boardAdvanceDto.setWriter(request.getParameter("writer"));
		boardAdvanceDto.setSubject(request.getParameter("subject"));
		boardAdvanceDto.setEmail(request.getParameter("email"));
		boardAdvanceDto.setPassword(request.getParameter("password"));
		boardAdvanceDto.setContent(request.getParameter("content"));
		
		request.setAttribute("isUpdate", BoardAdvanceDao.getInstance().updateBoard(boardAdvanceDto));
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardUpdatePro.jsp");
		dis.forward(request, response);
		
	}
	


}
