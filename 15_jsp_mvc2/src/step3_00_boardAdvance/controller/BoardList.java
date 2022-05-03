package step3_00_boardAdvance.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step3_00_boardAdvance.dao.BoardAdvanceDao;
import step3_00_boardAdvance.dto.BoardAdvanceDto;

@WebServlet("/boardList")
public class BoardList extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchKeyword = request.getParameter("searchKeyword");
		if (searchKeyword == null) searchKeyword = "total";
		
		String searchWord = request.getParameter("searchWord");
		if (searchWord == null) searchWord = "";
		
		
		// 화면에 보여질 게시글의 개수를 지정
		int onePageViewCount = 10;
		
		if (request.getParameter("onePageViewCount") != null) {
			onePageViewCount = Integer.parseInt(request.getParameter("onePageViewCount"));
		}
		
		// 현재 보여지고 있는 페이지의 number 값을 읽어들임
		String temp = request.getParameter("currentPage");
		// null 처리
		if(temp == null) {
			temp = "1";
		}
		// 현재 보여지고 있는 페이지 문자를 숫자로 형변환
		int currentPage = Integer.parseInt(temp);
		
		// 전체 게시글의 개수
		int allBoardCount = BoardAdvanceDao.getInstance().getAllBoardCount(searchKeyword , searchWord);
		
		int allPageCount = allBoardCount / onePageViewCount + 1;
		
		if (allBoardCount % onePageViewCount == 0) allPageCount--;
		
		int startPage = (currentPage - 1)  / 10  * 10 + 1;
		if (startPage == 0) {
			startPage = 1;
		}
		
		int endPage = startPage + 9;
		
		if (endPage > allPageCount) endPage = allPageCount;
		
		
		// 현재 보여질 페이지 시작 번호를 설정
		int startRow = (currentPage - 1) * onePageViewCount;
		
		System.out.println("============================");
		System.out.println("onePageViewCount : "+ onePageViewCount);
		System.out.println("allBoardCount : "+ allBoardCount);
		System.out.println("currentPage : "+ currentPage);
		System.out.println("startPage : " + startPage);
		System.out.println("startRow : " + startRow);
		System.out.println("endPage : " + endPage);
		System.out.println("allPageCount : " + allPageCount);
		System.out.println("searchKeyword : " + searchKeyword);
		System.out.println("searchWord : " + searchWord);
		System.out.println("============================");
		
		ArrayList<BoardAdvanceDto> boardList = BoardAdvanceDao.getInstance().getAllBoard(startRow , onePageViewCount , searchKeyword , searchWord);
		request.setAttribute("boardList"        , boardList); 
		request.setAttribute("onePageViewCount" , onePageViewCount);
		request.setAttribute("allBoardCount"    , allBoardCount);
		request.setAttribute("currentPage"      , currentPage);
		request.setAttribute("startPage"        , startPage);
		request.setAttribute("endPage"          , endPage);
		request.setAttribute("allPageCount"    , allPageCount);
		request.setAttribute("searchKeyword"    , searchKeyword);
		request.setAttribute("searchWord"       , searchWord);

		RequestDispatcher dis = request.getRequestDispatcher("step3_01_boardAdvanceEx/boardList.jsp");
		dis.forward(request, response);

	}

}
