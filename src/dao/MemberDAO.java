package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

	private Connection getConnection() throws DAOException {
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
}
