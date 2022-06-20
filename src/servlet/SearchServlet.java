package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ReturnBean;
import dao.BookDAO;
import dao.Common;
import dao.DAOException;
import dao.MemberDAO;
import dao.ReturnDAO;

@WebServlet("/SearchServlet/*")
public class SearchServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("SearchServletのdoPost入場");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Connection con = null;

		String strMember_Id = request.getParameter("member_Id");
		System.out.println("searchservlet  strMember_Id = " + strMember_Id);

		String strDetail_Id = request.getParameter("detail_Id");
		//System.out.println("ReturnBtnServlet　：　date準備");

		int member_Id = Integer.parseInt(strMember_Id);
		//System.out.println("ReturnBtnServlet　：　title準備完了");
		int detail_Id = Integer.parseInt(strDetail_Id);
		//System.out.println("ReturnBtnServlet　：　date準備完了");

		try {
			System.out.println("Rental_due_dateのtryに入ったよ");
			if(detail_Id == 0 && member_Id != 0)
			{

				System.out.println("returns入場");
				List<ReturnBean> findMemberId = MemberDAO.findMemberId(member_Id);

				System.out.println("returnList = " + findMemberId);
				String page = "/return/return.jsp";
				gotoPage(request, response, page);
				System.out.println("returns処理終了");
				return;
			}

			if(member_Id == 0 && detail_Id != 0)
			{

				System.out.println("returns入場");
				List<ReturnBean> findDetailId = BookDAO.findBookId(detail_Id);
				System.out.println("returnList = " + findDetailId);
				String page = "/return/return.jsp";
				gotoPage(request, response, page);
				System.out.println("returns処理終了");
				return;
			}

			if(detail_Id == 0 && member_Id == 0) {
				System.out.println("returns入場");
				List<ReturnBean> findAll = ReturnDAO.findAll();
				System.out.println("returnList = " + findAll);
				String page = "/return/return.jsp";
				gotoPage(request, response, page);
				System.out.println("returns処理終了");
				return;
			}

			if(detail_Id != 0 && member_Id != 0) {
				System.out.println("returns入場");
				List<ReturnBean> findOnly = ReturnDAO.findOnly(member_Id, detail_Id);
				System.out.println("returnList = " + findOnly);
				String page = "/return/return.jsp";
				gotoPage(request, response, page);
				System.out.println("returns処理終了");
				return;
			}


		} catch (DAOException e) {
			request.setAttribute("errorMessage", "エラー発生");
			gotoPage(request, response, "/login.jsp");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (con != null) {
					Common.close(con);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "システムエラー発生。ログを確認してください！");
				gotoPage(request, response, "/Error.jsp");
			}
			}
			System.out.println("try終わったよ");
		}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}


