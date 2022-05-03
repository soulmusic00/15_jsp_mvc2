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

@WebServlet("/boardDelete")
public class BoardDelete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("boardAdvanceDto", BoardAdvanceDao.getInstance().getOneUpdateBoard(Integer.parseInt(request.getParameter("num"))));
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardDelete.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardAdvanceDto boardAdvanceDto = new BoardAdvanceDto();

		boardAdvanceDto.setNum(Integer.parseInt(request.getParameter("num")));
		boardAdvanceDto.setPassword(request.getParameter("password"));
		
		request.setAttribute("isDelete" , BoardAdvanceDao.getInstance().deleteBoard(boardAdvanceDto));
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardDeletePro.jsp");
		dis.forward(request, response);
	}
	
	
}
