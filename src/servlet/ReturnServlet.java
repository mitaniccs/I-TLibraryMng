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
import javax.servlet.http.HttpSession;

import bean.ReturnBean;
import dao.BookDAO;
import dao.Common;
import dao.DAOException;
import dao.MemberDAO;
import dao.ReturnDAO;

@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ReturnServletのdoPost入場");
		//	文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//	セッションの取得
		HttpSession session = request.getSession(false);

		//	今後複数回使うオブジェクトの宣言/初期化
		Connection con = null;
		String action = request.getParameter("action");
//		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
//		System.out.println("ReturnServletでのmemberBean: " + memberBean);

		try {
			//	ログインチェック
//			if (memberBean == null) {
//				//	ログインしていなければログイン画面へ
//				request.setAttribute("errorMessage", "ログインをしてから再度アクセスをしてください。");
//				gotoPage(request, response, "/login.jsp");
//				return;
//			}
			//	以下はログインチェック済の場合
			//	-> profileは取得できている

			// 会員

			if(action.equals("資料返却画面"))
			{
				showReturn(request, response, session, con) ;
				if(action.equals("検索")) {
				searchReturn(request, response, session, con) ;
				}
				return;
			}

			if(action.equals("資料返却確認画面"))
			{
				returnConfilm(request, response, session, con) ;
				return;
			}

			if(action.equals("資料返却完了画面"))
			{
				returnDone(request, response, session, con) ;
				return;
			}

			if (action.equals("資料返却履歴"))
			{
				returnedLog(request, response, session, con);
				if(action.equals("検索")) {
				searchLog(request, response, session, con);
				}
				return;
			}

			else {
				request.setAttribute("errorMessage", "不正な操作です。(action=" + action + ")");
				gotoPage(request, response, "/login.jsp");
				return;

			}


		} catch (DAOException e) {
			request.setAttribute("errorMessage", "システムエラー発生。ログを確認してください！");
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
	}

	// 資料返却のリストを表示
	private void showReturn(	HttpServletRequest request,
								HttpServletResponse response,
								HttpSession session,
								Connection con) throws DAOException, Exception
	{

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		ReturnDAO dao = new ReturnDAO();

		if(action == null || action.length() ==0)
		{
			List<ReturnBean> returnList = dao.findAll();
			session.setAttribute("retrunList", returnList);
			gotoPage(request, response, "/return/return.jsp");
			return;
		}

		else if(action.equals("返却")) {
			gotoPage(request, response, "/return/returnConfilm.jsp");
			return;
		}

		else if(action.equals("戻る")) {
			gotoPage(request, response, "/../../WebContent/menu.jsp");
			return;
		}

	}

	// 資料返却確認処理
	private void returnConfilm(	HttpServletRequest request,
									HttpServletResponse response,
									HttpSession session,
									Connection con) throws DAOException, Exception
	{
		// 返却を確認する情報の取得
		String action = request.getParameter("action");

		String memberName = request.getParameter("name");
		String detailTitle = request.getParameter("title");
		String rentalDueDate = request.getParameter("rental_due_date");

		con = Common.getConnection();
		ReturnDAO returnDao = new ReturnDAO();
		BookDAO bookDao = new BookDAO();
		MemberDAO memberDao = new MemberDAO();
		ReturnDAO dao = new ReturnDAO();

//		ReturnBean returnBean = dao.findAll(con, rentalDueDate);

		// セッションに格納
		session.setAttribute("name", memberName);
		session.setAttribute("title", detailTitle);
		session.setAttribute("rental_due_date", rentalDueDate);

		//	資料確認画面に表示
		session.setAttribute("message", "");
//		request.setAttribute("return", returnBean);
		gotoPage(request, response, "/return/returnConfirm.jsp");

		if(action.equals("返却")) {
			gotoPage(request, response, "/return/returnDone.jsp");
		}

		if(action.equals("戻る")) {
			gotoPage(request, response, "/return/return.jsp");
		}
	}

	// 資料返却完了画面処理
	private void returnDone(	HttpServletRequest request,
										HttpServletResponse response,
										HttpSession session,
										Connection con) throws DAOException, Exception
	{
		// 返却した情報を設定
		String action = request.getParameter("action");

		String memberName = request.getParameter("name");
		String detailTitle = request.getParameter("title");
		String rentalDueDate = request.getParameter("rental_due_date");

		con = Common.getConnection();
//		ReturnDAO returnDao = new ReturnDAO();
//		BookDAO bookDao = new BookDAO();
//		MemberDAO memberDao = new MemberDAO();
//		ReturnBean returnBean = dao.findByReturn(con, memberName, detailTitle, rentalDueDate);

		//	返却完了画面に表示
		session.setAttribute("message", "");
//		request.setAttribute("return", returnBean);
		gotoPage(request, response, "/return/returnDone.jsp");

		if(action.equals("戻る")) {
			gotoPage(request, response, "/return/return.jsp");
		}
	}

	// 資料返却履歴処理
	private void returnedLog(	HttpServletRequest request,
								HttpServletResponse response,
								HttpSession session,
								Connection con) throws DAOException, Exception
	{
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		ReturnDAO dao = new ReturnDAO();

		if(action == null || action.length() ==0)
		{
			List<ReturnBean> returnedList = dao.findAllResult();
			session.setAttribute("retrunedList", returnedList);
			gotoPage(request, response, "/return/returnRezult.jsp");
			return;
		}

		else if(action.equals("戻る")) {
			gotoPage(request, response, "/../../WebContent/menu.jsp");
			return;
		}
	}

	// 資料返却画面検索
	private void searchReturn(	HttpServletRequest request,
								HttpServletResponse response,
								HttpSession session,
								Connection con) throws DAOException, Exception
	{
		String action = request.getParameter("action");
		con = Common.getConnection();
		int memberId = Integer.parseInt(request.getParameter("member_Id"));
		int detailId = Integer.parseInt(request.getParameter("detail_Id"));
		ReturnDAO dao = new ReturnDAO();
//		ReturnBean returnBean = dao.findByReturnId(con, memberId, detailId);
		gotoPage(request, response, "/return/return.jsp");

		if(action.equals("戻る")) {
			gotoPage(request, response, "/../../WebContent/menu.jsp");
		}
	}

	// 資料返却履歴検索
	private void searchLog(	HttpServletRequest request,
								HttpServletResponse response,
								HttpSession session,
								Connection con) throws DAOException, Exception
	{
		String action = request.getParameter("action");
		con = Common.getConnection();
		int memberId = Integer.parseInt(request.getParameter("member_Id"));
		int detailId = Integer.parseInt(request.getParameter("detail_Id"));
		ReturnDAO dao = new ReturnDAO();
//		ReturnBean returnBean = dao.findAllResult(con, memberId, detailId);
		String page = "/return/returnLog.jsp";
		gotoPage(request, response, page);

		if(action.equals("戻る")) {
			gotoPage(request, response, "/../../WebContent/menu.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
