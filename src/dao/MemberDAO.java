package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ReturnBean;

public class MemberDAO {

	private static Connection getConnection() throws DAOException {
		System.out.println("getConnection()メソッド入場");
		Connection conn = null;

		try {
			System.out.println("MemberDAOのgetConnection()メソッド入場");
			//JDBCドライバの登録
			Class.forName("com.mysql.cj.jdbc.Driver");
			//URL、ユーザー名、パスワード
			final String url = "jdbc:mysql://localhost:3306/libraryDB";
			final String user ="user";
			final String password = "user";
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
			System.out.println("MemberDAOのgetConnection()メソッド退場");
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
		System.out.println("getConnection()メソッド退場");
		return conn;
	}

	public String findName (String member_Id) throws DAOException {
		System.out.println("findName()メソッド入場");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "SELECT name from memberTBL where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_Id);
			String name = rs.getString("name");
			System.out.println("FindName()メソッド退場");
			return name;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("データ取得に失敗しました");
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			}catch (Exception e2) {
					e2.printStackTrace();
					throw new DAOException("PreparedStatementオブジェクトの開放に失敗");
			}
			try {
				if(conn!=null) conn.close();
			}catch (Exception e3) {
					e3.printStackTrace();
					throw new DAOException("Connectionオブジェクトの開放に失敗");
			}
		}
	}

	// 資料返却画面での検索
	public static List<ReturnBean> findMemberId(int member_Id) throws DAOException{
		System.out.println("findMemberId()メソッド入場");
		List<ReturnBean> searchMemList = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン

			String sql ="select * from rentalTbl where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_Id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{ //1レコード読み込み //get資料型（"フィールド名")
				int id = rs.getInt("id");
				int detail_id = rs.getInt("detail_id");
				int member_id = rs.getInt("member_id");
				String rental_date = rs.getString("rental_date");
				String rental_due_date = rs.getString("rental_due_date");


				ReturnBean returnBean =
						new ReturnBean(id, detail_id, member_id, rental_date, rental_due_date);
				//以下２行確認用
//				ReturnBean returnBean =
//						new ReturnBean(detail_Id, member_Id);

				System.out.println(returnBean);

				//re

				searchMemList.add(returnBean); //リストに追加
			}
			System.out.println("FindMemberId()メソッド退場");
			return searchMemList;

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
	}

//	資料返却履歴画面での検索
	public static List<ReturnBean> findMemberResult(String Member_Id) throws DAOException{
		System.out.println("findMemberResult()メソッド入場");

		List<ReturnBean> resultMember = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン

			String sql = "select * from rentalTbl where member_id = ?" ;  //order by rental_due_date DESC;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Member_Id);
			rs=pstmt.executeQuery();
			while(rs.next()) { //1レコード読み込み //get資料型（"フィールド名")
				int id = rs.getInt("id");
				int detail_id = rs.getInt("detail_id");
				int member_id = rs.getInt("member_id");
				String rental_date = rs.getString("rental_date");
				String rental_due_date = rs.getString("rental_due_date");
				String returned_date = rs.getString("returned_date");

				ReturnBean returnBean =
					new ReturnBean(id, detail_id, member_id, rental_date, rental_due_date, returned_date);
				//System.out.println(returnBean);
				resultMember.add(returnBean); //リストに追加
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
		System.out.println("findMemberResult()メソッド退場");
		return resultMember; //リストをリターン
	}
}