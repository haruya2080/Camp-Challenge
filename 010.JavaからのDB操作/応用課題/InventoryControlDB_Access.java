package org.camp.inventory_control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryControlDB_Access {
	private Connection db_con;
	private final String url = "//localhost:3306";
	private final String dbName = "inventory_control_db";
	private final String userName = "niwayama";
	private final String password = "5416";

	/**
	 * コンストラクタ
	 * データベースへの接続を行う
	 */
	public InventoryControlDB_Access () {

		// 課題1 エラーハンドリングを含んだ接続の確立
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection(
					"jdbc:mysql:" + url + "/" + dbName, userName, password);

		} catch(SQLException e_sql) {
			System.out.print("接続時にエラーが発生しました：" + e_sql.toString());
		} catch (Exception e) {
			System.out.print("接続時にエラーが発生しました：" + e.toString());
		}
	}

	/**
	 * DBから切断する
	 */
	public void closeDB_Connection () {
		if (db_con != null) {
			try {
				db_con.close();
			} catch (Exception e_con) {
				// TODO: handle exception
				System.out.println(e_con.getMessage());
			}
		}
	}

	/**
	 * 指定したユーザー情報が存在するか
	 * @param userID
	 * @param password
	 * @return
	 */
	public boolean checkUserInfo (String userID, String password) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return false;
		}

		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {
			db_st = db_con.prepareStatement(
					"select * from users where userID = ? and password = ?");
			db_st.setString(1, userID);
			db_st.setString(2, password);

			db_data = db_st.executeQuery();

			// データがある場合
			if (db_data.next()) { return true; }
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 商品を登録するメソッド
	 * @param name
	 * @param price
	 * @param stock
	 */
	public void InsertGoods (String name, int price, int stock) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return;
		}

		PreparedStatement db_st = null;

		try {
			db_st = db_con.prepareStatement(
					"insert into goods values(?, ?, ?, ?)");
			db_st.setInt(1, 0);
			db_st.setString(2, name);
			db_st.setInt(3, price);
			db_st.setInt(4, stock);

			// insert実行
			db_st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * すべての商品の一覧をhtmlのテーブル要素で取得
	 * @return
	 */
	public String getAllGoodsTable () {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return "DBへの接続エラーです。";
		}

		String output = "";
		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {
			db_st = db_con.prepareStatement("select * from goods");

			// select実行
			db_data = db_st.executeQuery();

			// すべてのレコードをテーブルに格納
			while (db_data.next()) {
				output += "<tr>"
						+"<td>" + db_data.getInt("id") + "</td>"
						+ "<td>" + db_data.getString("name") + "</td>"
						+ "<td>" + db_data.getInt("price") + "</td>"
						+ "<td>" + db_data.getInt("stock") + "</td>"
						+ "</tr>";
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return output;
	}

	/**
	 * 在庫を更新する
	 * @param id
	 * @param stock
	 */
	public void updateGoods (int id, int stock) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
		}

		PreparedStatement db_st = null;

		try {
			db_st = db_con.prepareStatement(
					"update goods set stock = ? where id = ?");
			db_st.setInt(1, stock);
			db_st.setInt(2, id);

			// update実行
			db_st.executeUpdate();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
