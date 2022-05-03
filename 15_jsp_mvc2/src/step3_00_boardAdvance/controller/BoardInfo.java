package step3_00_boardAdvance.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step3_00_boardAdvance.dao.BoardAdvanceDao;


@WebServlet("/boardInfo")
public class BoardInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("boardAdvanceDto", BoardAdvanceDao.getInstance().getOneBoard(Integer.parseInt(request.getParameter("num"))));
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardInfo.jsp");
		dis.forward(request, response);
	
	}

}
