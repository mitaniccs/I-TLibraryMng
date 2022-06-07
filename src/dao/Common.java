package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Common {
	/**
	 * DB connectionを取得する
	 * @return
	 * @throws DAOException
	 */
	public static Connection getConnection() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("com.mysql.cj.jdbc.Driver");

			// URL、ユーザ名、パスワードの設定
			String url = "jdbc:mysql://localhost:3306/librarydb";
			String user = "user";
			String pass = "user";

			// データベースへの接続
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("接続に失敗しました。");
		}
	}

	/**
	 * DB connectionをcloseする
	 * @param con
	 * @throws SQLException
	 */
	public static void close(Connection con) throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}


}
