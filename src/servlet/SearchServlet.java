package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.NameBean;
import bean.Rental_due_dateBean;
import bean.ReturnBean;
import bean.TitleBean;
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
		String action = request.getParameter("action");
		System.out.println("ReturnBtnServlet　：　action = " + action);

		String strMember_Id = request.getParameter("member_Id");
		//System.out.println("ReturnBtnServlet　：　title準備");

		String strDetail_Id = request.getParameter("detail_Id");
		//System.out.println("ReturnBtnServlet　：　date準備");

		int member_Id = Integer.parseInt(strMember_Id);
		//System.out.println("ReturnBtnServlet　：　title準備完了");
		int detail_Id = Integer.parseInt(strDetail_Id);
		//System.out.println("ReturnBtnServlet　：　date準備完了");

		try {
			System.out.println("Rental_due_dateのtryに入ったよ");
			if(action.equals("s_member"))
			{
				System.out.println("s_member入場");


				TitleBean title = ReturnDAO.Title(detail_Id, id);
				
				System.out.println("servetに帰ってきたtitle" + title);

				Rental_due_dateBean rental_due_date = ReturnDAO.Rental_due_date(id);
				
				System.out.println("servetに帰ってきたrental_due_date" + rental_due_date);

				String page = "/return/returnConfirm.jsp";
				gotoPage(request, response, page);
				System.out.println("confirm退場");
				return;
			}

	}

}
