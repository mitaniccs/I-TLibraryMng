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
import dao.Common;
import dao.DAOException;
import dao.ReturnDAO;

@WebServlet("/ReturnServlet/*")
public class ReturnServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		System.out.println("ReturnServletのdoPost入場");
		//	文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//	セッションの取得
		HttpSession session = request.getSession(false);

		//	今後複数回使うオブジェクトの宣言/初期化
		Connection con = null;
		//String action = request.getParameter("action");
//		String url = request.getRequestURI();
		String action = request.getParameter("action");
		System.out.println("actionに格納された値：" + action);
		//以下のパラメータは資料返却ボタンに遷移する際は渡されない、よってエラーに似る
//		String strId = request.getParameter("id");
//		String strMember_Id = request.getParameter("member_Id");
//		String strDetail_Id = request.getParameter("detail_Id");
//		int id = Integer.parseInt(strId);
//		int member_Id = Integer.parseInt(strMember_Id);
//		int detail_Id = Integer.parseInt(strDetail_Id);


//		MemberBean memberBean = (MemberBean) session.getAttribute("memberBean");
//		System.out.println("ReturnServletでのmemberBean: " + memberBean);

		try {
			System.out.println("tryに入ったよ");
			//ReturnDAO returnDAO = new ReturnDAO();
			if(action.equals("returns"))//
			{

				System.out.println("returns入場");
				List<ReturnBean> findAll = ReturnDAO.findAll();
				//findAllをsession保管す
				session.setAttribute("returnList", findAll);
				System.out.println("returnList = " + findAll);
				String page = "/return/return.jsp";
				gotoPage(request, response, page);
				System.out.println("returns処理終了");
				return;

			}

			if(action.equals("result"))
			{
				System.out.println("result渡せた");
				List<ReturnBean> findAllResult = ReturnDAO.findAllResult();
				//findAllをsession保管する
				session.setAttribute("returnList", findAllResult);
				System.out.println("returnList = " + findAllResult);
				String page = "/return/returnResult.jsp";
				gotoPage(request, response, page);
				System.out.println("result処理終了");
				return;
			}

				//戻る画面に遷移（資料返却確認画面以外）
			if(action.equals("returnbtn")) {
				gotoPage(request, response, "./WebContent/test.jsp");
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