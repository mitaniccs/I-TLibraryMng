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

@WebServlet("/SearchRServlet/*")
public class SearchRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("SearchRServletのdoPost入場");
		//数字の正規表現
		String Number = "^[0-9]{1,100}";//

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Connection con = null;
		HttpSession session = request.getSession(false);

		String strMember_Id = request.getParameter("member_Id");
		if(strMember_Id.isEmpty()) {
			strMember_Id = "0";
		}
		//数字以外の文字の拒絶（正規表現）
		if(strMember_Id.matches(Number)) {
			//System.out.println("存在する会員IDが検索されたよ");
		}else {
			//System.out.println("存在しない会員IDが検索されたよ");
			//System.out.println("strMember_Id = " + strMember_Id);
			request.setAttribute("non_list_err", "数字を入力してください");
			String page = "/return/return.jsp";
			gotoPage(request, response, page);
			return ;
		}

		String strDetail_Id = request.getParameter("detail_Id");
		if(strDetail_Id.isEmpty()) {
			strDetail_Id = "0";
		}
		if(strDetail_Id.matches(Number)){
			System.out.println("存在する資料IDが検索されたよ");
		}else {
			System.out.println("存在しない資料IDが検索されたよ");
			System.out.println("strMember_Id = " + strMember_Id);
			request.setAttribute("non_list_err", "数字を入力してください");
			String page = "/return/return.jsp";
			gotoPage(request, response, page);
			return ;
		}

		int member_Id = Integer.parseInt(strMember_Id);
		int detail_Id = Integer.parseInt(strDetail_Id);

		try {
			if(detail_Id == 0 && member_Id != 0)
			{
				System.out.println("if(detail_Id == 0 && member_Id != 0)");
				session.setAttribute("non_list_err", "");
				List<ReturnBean> findMemberId = MemberDAO.findMemberResult(strMember_Id);
				session.setAttribute("returnList", findMemberId);
				if(findMemberId == null || findMemberId.size() == 0){
					System.out.println("member_Idが見つからなかったときの処理");
					request.setAttribute("non_list_err", "会員IDが存在しません。");
					String page = "/return/returnResult.jsp";
					gotoPage(request, response, page);
					return ;
				}
				String page = "/return/returnResult.jsp";
				gotoPage(request, response, page);
				return;
			}

			if(member_Id == 0 && detail_Id != 0)
			{
				System.out.println("if(member_Id == 0 && detail_Id != 0)");
				session.setAttribute("non_list_err", "");
				List<ReturnBean> findDetailId = BookDAO.findDetailResult(strDetail_Id);
				//System.out.println("returnList = " + findDetailId);
				session.setAttribute("returnList", findDetailId);
				if(findDetailId == null || findDetailId.size() == 0){
					System.out.println("detail_Idが見つからなかったときの処理");
					request.setAttribute("non_list_err", "資料IDが存在しません。");
					String page = "/return/returnResult.jsp";
					gotoPage(request, response, page);
					return ;
				}
				String page = "/return/returnResult.jsp";
				gotoPage(request, response, page);
				return;
			}

			if(detail_Id == 0 && member_Id == 0) {
				System.out.println("if(detail_Id == 0 && member_Id == 0)");
				List<ReturnBean> findAll = ReturnDAO.findAllResult();
				//System.out.println("returnList = " + findAll);
				session.setAttribute("returnList", findAll);
				request.setAttribute("non_list_err", "会員ID、資料IDの入力が無かったため全件検索しました");
				String page = "/return/returnResult.jsp";
				gotoPage(request, response, page);
				return;
			}

			if(detail_Id != 0 && member_Id != 0) {
				System.out.println("if(detail_Id != 0 && member_Id != 0)");
				List<ReturnBean> findOnly = ReturnDAO.findOnlyResult(strMember_Id, strDetail_Id);
				System.out.println("returnList = " + findOnly);
				session.setAttribute("returnList", findOnly);
				if(findOnly == null || findOnly.size() == 0){
					System.out.println("一致する会員ID、資料IDが見つからなかったときの処理");
					request.setAttribute("non_list_err", "一致する会員ID、資料IDが存在しません。");
					String page = "/return/returnResult.jsp";
					gotoPage(request, response, page);
					return ;
				}
				String page = "/return/returnResult.jsp";
				gotoPage(request, response, page);
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
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
