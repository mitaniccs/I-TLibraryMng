package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.NameBean;
import bean.Rental_due_dateBean;
import bean.ReturnBean;
import bean.TitleBean;

public class ReturnDAO {


	private static Connection getConnection() throws DAOException
	{
		Connection conn = null;
		try {
			//System.out.println("getConnection()メソッド入場");
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
			System.out.println("getConnection()メソッド退場");
		} catch(Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
		return conn;
	}

	//資料返却画面 全件検索
	public static List<ReturnBean> findAll() throws DAOException{
		System.out.println("findAllメソッド()入場");

		List<ReturnBean> list = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン
			System.out.println("findAllメソッド内　getConnection()成功");
			//returned_date部分がnull以外のモノだけ表示するため
			String sql ="select id, detail_id, member_id, rental_date, rental_due_date "
					+ "from rentalTbl where returned_date is null";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
			{ //1レコード読み込み //get資料型（"フィールド名")
				int id = rs.getInt("id");
				int detail_Id = rs.getInt("detail_id");
				int member_Id = rs.getInt("member_id");
				String rental_date = rs.getString("rental_date");
				String rental_due_date = rs.getString("rental_due_date");


				ReturnBean returnBean =
						new ReturnBean(id, detail_Id, member_Id, rental_date, rental_due_date);
				//以下２行確認用
//				ReturnBean returnBean =
//						new ReturnBean(detail_Id, member_Id);

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
		System.out.println("list = " + list);
		System.out.println("findAll()メソッド退場");
		return list; //リストをリターン
	}

	//返却ボタンが押されたときに名前を取得するDAO
	public static NameBean Name(int member_Id,int id) throws DAOException{
		System.out.println("Name()メソッド入場");
		//ReturnBean returnBean = null;
		NameBean name = null;
		List<NameBean> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//rentalTblとmemberTblを合体
			String sql = "SELECT name FROM rentalTbl a"
					+ " INNER JOIN memberTbl b"
					+ " ON a.member_id= b.id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())//一旦変数nameの値をlist or 配列で格納して持ってきたmember_Idの部分だけ返したい
			{ //1レコード読み込み //get資料型（"フィールド名")
				String name1 = rs.getString("name");

				NameBean nameBean = new NameBean(name1);
				list.add(nameBean); //リストに追加
			}
			System.out.println("name = " + list);
			//idがよくない。プライマリーキーであるため
			name = list.get(id-1);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("データの取得（1件）に失敗しました");
		} finally {
			try {
				if(rs!=null)rs.close();
			}catch (Exception e2) {
					e2.printStackTrace();
					throw new DAOException("ResultSetオブジェクトの開放に失敗");
			}
			try {
				if(pstmt!=null)pstmt.close();
			}catch (Exception e3) {
					e3.printStackTrace();
					throw new DAOException("PreparedStatementオブジェクトの開放に失敗");
			}
			try {
				if(conn!=null)conn.close();
			}catch (Exception e4) {
					e4.printStackTrace();
					throw new DAOException("Connectionオブジェクトの開放に失敗");
			}
		}
		System.out.println("getConnection()メソッド退場");
		return name;
	}

	//返却ボタンが押されたときに資料名を取得するDAO
	public static TitleBean Title(int detail_Id, int id) throws DAOException{
		System.out.println("Title()メソッド入場");
		//ReturnBean returnBean = null;
		TitleBean title = null;
		List<TitleBean> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//rentalTblとdetailTblとbookTblを合体
			String sql = "SELECT title FROM rentalTbl a"
					+ " INNER JOIN detailTbl b"
					+ " ON a.detail_id= b.id"
					+ " INNER JOIN bookTbl c"
					+ " ON b.book_isbn = c.isbn";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{ //1レコード読み込み //get資料型（"フィールド名")
				String title1 = rs.getString("title");

				TitleBean titleBean = new TitleBean(title1);
				list.add(titleBean); //リストに追加
			}
			System.out.println("title = " + list);
			//idがよくない。プライマリーキーであるため
			title = list.get(id-1);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("データの取得（1件）に失敗しました");
		} finally {
			try {
				if(rs!=null)rs.close();
			}catch (Exception e2) {
					e2.printStackTrace();
					throw new DAOException("ResultSetオブジェクトの開放に失敗");
			}
			try {
				if(pstmt!=null)pstmt.close();
			}catch (Exception e3) {
					e3.printStackTrace();
					throw new DAOException("PreparedStatementオブジェクトの開放に失敗");
			}
			try {
				if(conn!=null)conn.close();
			}catch (Exception e4) {
					e4.printStackTrace();
					throw new DAOException("Connectionオブジェクトの開放に失敗");
			}
		}
		System.out.println("getConnection()メソッド退場");
		return title;
	}

	//返却ボタンが押されたときに返却期限を取得するDAO
	public static Rental_due_dateBean Rental_due_date(int id) throws DAOException{
		System.out.println("Rental_due_date()メソッド入場");
		//ReturnBean returnBean = null;
		Rental_due_dateBean rental_due_date = null;
		List<Rental_due_dateBean> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql ="select id, rental_due_date from rentalTbl ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{ //1レコード読み込み //get資料型（"フィールド名")
				int id1 = rs.getInt("id");
				String rental_due_date1 = rs.getString("rental_due_date");

				Rental_due_dateBean rental_due_dateBean = new Rental_due_dateBean(id1, rental_due_date1);
				list.add(rental_due_dateBean); //リストに追加
			}
			System.out.println("title = " + list);
			rental_due_date = list.get(id-1);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DAOException("データの取得（1件）に失敗しました");
		} finally {
			try {
				if(rs!=null)rs.close();
			}catch (Exception e2) {
					e2.printStackTrace();
					throw new DAOException("ResultSetオブジェクトの開放に失敗");
			}
			try {
				if(pstmt!=null)pstmt.close();
			}catch (Exception e3) {
					e3.printStackTrace();
					throw new DAOException("PreparedStatementオブジェクトの開放に失敗");
			}
			try {
				if(conn!=null)conn.close();
			}catch (Exception e4) {
					e4.printStackTrace();
					throw new DAOException("Connectionオブジェクトの開放に失敗");
			}
		}
		System.out.println("getConnection()メソッド退場");
		return rental_due_date;
	}

	//	資料情報更新
	public static void Returned(int id, String returned_date) throws DAOException{
		System.out.println("Returned()メソッド入場");
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			if(conn == null) conn = getConnection(); //db接続Connectionリターン


//			idは資料返却画面の返却ボタンが押された時にゲットメソッドで持ってくる
			String sql ="update rentalTbl set returned_date=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, returned_date);
			pstmt.setInt(2, id);
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
		System.out.println("Returned()メソッド退場");
	}

//	資料返却履歴画面
	public static List<ReturnBean> findAllResult() throws DAOException{
		System.out.println("findAllResult()メソッド入場");

		List<ReturnBean> resultList = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン

			String sql = "select * from rentalTbl" ;  //order by rental_due_date DESC;
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) { //1レコード読み込み //get資料型（"フィールド名")
				int id = rs.getInt("id");
				int detail_Id = rs.getInt("detail_id");
				int member_Id = rs.getInt("member_id");
				String rental_date = rs.getString("rental_date");
				String rental_due_date = rs.getString("rental_due_date");
				String returned_date = rs.getString("returned_date");

				ReturnBean returnBean =
					new ReturnBean(id, detail_Id, member_Id, rental_date, rental_due_date, returned_date);
				//System.out.println(returnBean);
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
		System.out.println("findAllResult()メソッド退場");
		return resultList; //リストをリターン
	}

	// 資料返却画面での１件検索
	public static List<ReturnBean> findOnly(int member_Id2, int detail_Id2) throws DAOException{
		System.out.println("findOnly()メソッド入場");
		List<ReturnBean> searchOnly = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン

			String sql ="select * from rentalTbl where detail_id = ? and member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_Id2);
			pstmt.setInt(2, detail_Id2);
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

				searchOnly.add(returnBean); //リストに追加
			}
			System.out.println("FindOnly()メソッド退場");
			return searchOnly;

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

	// 資料返却履歴画面での１件検索
	public static List<ReturnBean> findOnlyResult(String strMember_Id, String strDetail_Id) throws DAOException{
		System.out.println("findOnlyResult()メソッド入場");
		List<ReturnBean> resultOnly = new ArrayList<>();
		Connection conn = null; //db接続
		PreparedStatement pstmt = null; //sql実行
		ResultSet rs = null; //結果セット
		try {
			conn = getConnection(); //db接続Connectionリターン

			String sql ="select * from rentalTbl where detail_id = ? and member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strMember_Id);
			pstmt.setString(2, strDetail_Id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{ //1レコード読み込み //get資料型（"フィールド名")
				int id = rs.getInt("id");
				int detail_id = rs.getInt("detail_id");
				int member_id = rs.getInt("member_id");
				String rental_date = rs.getString("rental_date");
				String rental_due_date = rs.getString("rental_due_date");
				String returned_date = rs.getString("returned_date");

				ReturnBean returnBean =
						new ReturnBean(id, detail_id, member_id, rental_date, rental_due_date, returned_date);
				//以下２行確認用
//				ReturnBean returnBean =
//						new ReturnBean(detail_Id, member_Id);

				System.out.println(returnBean);

				resultOnly.add(returnBean); //リストに追加
			}
			System.out.println("FindOnlyResult()メソッド退場");
			return resultOnly;

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
}