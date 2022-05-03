package step2_00_boardBasic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step2_00_boardBasic.dao.BoardBasicDao;
import step2_00_boardBasic.dto.BoardBasicDto;


@WebServlet("/bUpdateAction")
public class _07_bUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardBasicDto boardDto = new BoardBasicDto();
		
		boardDto.setNum(Integer.parseInt(request.getParameter("num")));
		boardDto.setWriter(request.getParameter("writer"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setEmail(request.getParameter("email"));
		boardDto.setPassword(request.getParameter("password"));
		boardDto.setContent(request.getParameter("content"));
		
		boolean isUpdate = BoardBasicDao.getInstance().updateBoard(boardDto);
		request.setAttribute("isUpdate", isUpdate);
		
		RequestDispatcher dis = request.getRequestDispatcher("step2_01_boardBasicEx/07_bUpdatePro.jsp");
		dis.forward(request, response);
		
	}

}
