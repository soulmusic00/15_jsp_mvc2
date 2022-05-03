package step1_00_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step1_00_login.dao.MemberDao;
import step1_00_login.dto.MemberDto;

@WebServlet("/join")
public class Join extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/02_join.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(request.getParameter("id"));
		memberDto.setPw(request.getParameter("pw"));
		memberDto.setName(request.getParameter("name"));
		memberDto.setTel(request.getParameter("tel"));
		memberDto.setEmail(request.getParameter("email"));
		
		boolean isJoin = MemberDao.getInstance().joinMember(memberDto);
		
		request.setAttribute("isJoin", isJoin);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/03_joinAction.jsp");
		dis.forward(request, response);
	
	}

}




