package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ReturnBean;
import dao.Common;
import dao.ReturnDAO;

/**
 * 返却ボタンが押された場合のサーブレット
 */
@WebServlet("/ReturnBtnServlet")
public class ReturnBtnServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ReturnServletのdoPost入場");
		//	文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//	セッションの取得
		HttpSession session = request.getSession(false);

		Connection con = null;
		//ゲットメソッドで資料IDの受け取り


		try {
			int detail_Id = Integer.parseInt(request.getParameter("detail_Id"));
			System.out.println("tryに入ったよ");
			//ReturnDAO returnDAO = new ReturnDAO();


			System.out.println("返却ボタン用サーブレットの処理開始のサーブレット突入");

			LocalDateTime returned_date = LocalDateTime.now();
			//ReturnBean update = new ReturnBean(detail_Id, returned_date);
			String returned_date1 = returned_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			ReturnBean returnBean
				= new ReturnBean(returned_date1, detail_Id);
			ReturnDAO.update(returnBean);


//			request.setAttribute("rentalList", update);

		}  catch (Exception e) {
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