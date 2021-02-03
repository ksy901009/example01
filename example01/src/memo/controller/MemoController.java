package memo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.model.dao.MemoDAO;
import memo.model.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		
		String pageNumber_ = "";
		pageNumber_ = request.getParameter("pageNumber");
		if (pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_ = "1";
		}
		int pageNumber = Integer.parseInt(pageNumber_);
		
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		MemoDAO dao = new MemoDAO();
		
		if(url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "memo_write");
			request.setAttribute("pageNumber", pageNumber);
			
			page = "/memo/write.jsp";
			rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("writeProc.do") != -1) {
			MemoDTO dto = new MemoDTO();
			
			dto.setWriter(writer);
			dto.setContent(content);
			
			int result = dao.insertMemo(dto);
			
			if(result > 0) {
//				System.out.println("등록되었습니다.");
			} else {
//				System.out.println("결과코드 : " + result);
			}
		} else if(url.indexOf("list.do") != -1) {
			MemoDTO dto = new MemoDTO();
			
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(dto);
			int jj = totalRecord - pageSize * (pageNumber - 1);
			
			int startRecord = pageSize * (pageNumber - 1) + 1;
			int lastRecord = pageSize * pageNumber;
			
			int totalPage = 0;
			int startPage = 1;
			int lastPage = 1;
			if(totalRecord > 0) {
				totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
				startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
				
				lastPage = startPage + blockSize - 1;
				if(lastPage > totalPage) {
					lastPage = totalPage;
				}
			}
			
			ArrayList<MemoDTO> list = dao.getListAll(dto, startRecord, lastRecord);
			request.setAttribute("list", list);
			request.setAttribute("menu_gubun", "memo_list");
			
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("jj", jj);
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			
			
			rd = request.getRequestDispatcher("/memo/list.jsp");
			rd.forward(request, response);
		}
	}
}