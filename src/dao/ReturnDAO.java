package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ReturnBean;

public class ReturnDAO {


	private Connection getConnection() throws DAOException{
		System.out.println("getConnection()メソッド入場");
		Connection conn = null;

		try {
			System.out.println("ReturnBeanのgetConnection()メソッド入場");
			//		JDBCドライバの登録
			Class.forName("com.mysql.cj.jdbc.Driver");
			//		URL、ユーザー名、パスワードの設定
			final String url = "jdbc:mysql://localhost:3306/librarydb";
			//		DB権限を与える
			final String user = "user";
			final String pass = "user";
			//		データベースへの接続
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println(conn);
			System.out.println("ReturnBeanのgetConnection()メソッド退場");
		} catch(Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
		System.out.println("getConnectionメソッド退場");
		return conn;
	}

	//資料返却画面
	//	全件検索
	public List<ReturnBean> findAll() throws DAOException{
		System.out.println("bookList()メソッド入場");

		List<ReturnBean> list = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン


			String sql =
					"select detail_id, member_id, rental_date, rental_due_date, "
					+ "from rentalTbl order by rental_due_date DESC";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) { //1レコード読み込み //get資料型（"フィールド名")
				int detail_Id = rs.getInt("detai_id");
				int member_Id = rs.getInt("member_id");
				Date rental_date = rs.getDate("rental_date");
				Date rental_due_date = rs.getDate("rental_due_date");


				ReturnBean returnBean = new ReturnBean(detail_Id, member_Id, rental_date, rental_due_date);
				System.out.println(returnBean);
				list.add(returnBean); //リストに追加
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("リストの取得に失敗しました。");
		} finally {
			//			トレースしやすくするため
			try {
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new DAOException("ResulutSetオブジェクトの開放に失敗しました。");

			}
			try {
				if(pstmt != null) pstmt.close();
			} catch(Exception e3) {
				e3.printStackTrace();
				throw new DAOException("PreparedStatementオブジェクトの開放に失敗しました。");
			}
			try {
				if(conn != null) conn.close();
			} catch (Exception e4) {
				e4.printStackTrace();
				throw new DAOException("Connectionオブジェクトの開放に失敗しました。");
			}
		}
		System.out.println("list()メソッド退場");
		return list; //リストをリターン
	}



	//	資料情報更新
	public void update(ReturnBean returnBean) throws DAOException{
		System.out.println("update()メソッド入場");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			if(conn == null)
				conn = getConnection(); //db接続Connectionリターン
//TODO sql文更新
//			idは資料返却画面の返却ボタンが押された時にゲットメソッドで持ってくる
			String sql
			="update rentalTbl set returned_date=? where id=";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, returnBean.getReturned_date());
			pstmt.executeUpdate(); //レコード修正
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("データの更新に失敗しました。");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new DAOException("PreparedStatementオブジェクトの開放に失敗しました。");
			}
			try {
				if(conn != null) conn.close();
			} catch(Exception e3) {
				e3.printStackTrace();
				throw new DAOException("Connectionオブジェクトの開放に失敗しました。");
			}
		}
		System.out.println("update()メソッド退場");
	}

//	資料返却履歴画面
	public List<ReturnBean> findAllResult() throws DAOException{
		System.out.println("bookList()メソッド入場");

		List<ReturnBean> resultList = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン

			String sql =
					"select detail_id, member_id, rental_date, rental_due_date, returned_date"
					+ "from rentalTbl order by rental_due_date DESC";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) { //1レコード読み込み //get資料型（"フィールド名")
				int detail_Id = rs.getInt("detai_id");
				int member_Id = rs.getInt("member_id");
				Date rental_date = rs.getDate("rental_date");
				Date rental_due_date = rs.getDate("rental_due_date");
				Date returned_date = rs.getDate("returned_date");


				ReturnBean returnBean = new ReturnBean(detail_Id, member_Id, rental_date,
						rental_due_date, returned_date);
				System.out.println(returnBean);
				resultList.add(returnBean); //リストに追加
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("リストの取得に失敗しました。");
		} finally {
			//			トレースしやすくするため
			try {
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new DAOException("ResulutSetオブジェクトの開放に失敗しました。");

			}
			try {
				if(pstmt != null) pstmt.close();
			} catch(Exception e3) {
				e3.printStackTrace();
				throw new DAOException("PreparedStatementオブジェクトの開放に失敗しました。");
			}
			try {
				if(conn != null) conn.close();
			} catch (Exception e4) {
				e4.printStackTrace();
				throw new DAOException("Connectionオブジェクトの開放に失敗しました。");
			}
		}
		System.out.println("bookList()メソッド退場");
		return resultList; //リストをリターン
	}

}
