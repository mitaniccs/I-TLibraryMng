package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.NameBean;
import bean.Rental_due_dateBean;
import bean.TitleBean;
import dao.Common;
import dao.DAOException;
import dao.ReturnDAO;

@WebServlet("/ReturnBtnServlet/*")
public class ReturnBtnServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		System.out.println("ReturnBtnServletのdoPost入場");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//	セッションの取得
		HttpSession session = request.getSession(false);
		System.out.println("ReturnBtnServletセッション準備完了");
		Connection con = null;
		String action = request.getParameter("action");
		System.out.println("ReturnBtnServlet　：　action = " + action);

		String strId = request.getParameter("id");

		String strMember_Id = request.getParameter("member_Id");

		String strDetail_Id = request.getParameter("detail_Id");

		int id = Integer.parseInt(strId);
		int member_Id = Integer.parseInt(strMember_Id);
		int detail_Id = Integer.parseInt(strDetail_Id);

		try {
			if(action.equals("confirm"))
			{
				System.out.println("confirm入場");

				NameBean name = ReturnDAO.Name(member_Id, id);
				session.setAttribute("name", name);
				System.out.println("servetに帰ってきたname" + name);

				TitleBean title = ReturnDAO.Title(detail_Id, id);
				session.setAttribute("title", title);
				System.out.println("servetに帰ってきたtitle" + title);

				Rental_due_dateBean rental_due_date = ReturnDAO.Rental_due_date(id);
				session.setAttribute("rental_due_date", rental_due_date);
				System.out.println("servetに帰ってきたrental_due_date" + rental_due_date);

				String page = "/return/returnConfirm.jsp";
				gotoPage(request, response, page);
				System.out.println("confirm退場");
				return;
			}

			if(action.equals("done"))
			{
				System.out.println("done入場");
				//現在の日付をtodaysDateに格納
		        LocalDate todaysDate = LocalDate.now();
		        //todaysDateをStringがたに変更
		        String returned_date1 = todaysDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				ReturnDAO.Returned(id, returned_date1);
				String page = "/return/returnDone.jsp";
				gotoPage(request, response, page);
				System.out.println("done退場");
				return;
			}

			//資料返却画面上での検索
			if(action.equals("search1")) {
				return;
			}

			//資料返却履歴画面での検索
			if(action.equals("search2")) {
				return;
			}

		} catch (DAOException e) {
			request.setAttribute("errorMessage", "エラー発生");
			gotoPage(request, response, "/login.jsp");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
				try
				{
					if (con != null) Common.close(con);
				} catch (Exception e)
				{
					e.printStackTrace();
					request.setAttribute("errorMessage", "システムエラー発生。ログを確認してください！");
					gotoPage(request, response, "/Error.jsp");
				}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
		{
			doPost(request, response);
		}

		private void gotoPage(HttpServletRequest request,HttpServletResponse response, String page)
			throws ServletException,IOException
		{
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
}