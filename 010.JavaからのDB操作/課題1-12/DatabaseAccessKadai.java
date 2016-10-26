package org.camp.javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DatabaseAccessKadai {
	private Connection db_con;

	public DatabaseAccessKadai (
			String url, String dbName, String userName, String passward) {

		// 課題1 エラーハンドリングを含んだ接続の確立
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			db_con = DriverManager.getConnection(
					"jdbc:mysql:" + url + "/" + dbName, userName, passward);

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
	 * 	プロフィールデータをString型で取得
	 * @param db_data
	 * @return
	 */
	private String getProfilesData (ResultSet db_data) {
		String output = "";

		try {
			output += "<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">";

			// テーブルの最初の列を指定
			output += "<tr>"
					+ "<td>profilesID</td>"
					+ "<td>name</td>"
					+ "<td>tell</td>"
					+ "<td>age</td>"
					+ "<td>birthday</td>"
					+ "</tr>";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			// すべてのレコードをテーブルに格納
			while (db_data.next()) {
				output += "<tr>"
						+"<td>" + db_data.getInt("profilesID") + "</td>"
						+ "<td>" + db_data.getString("name") + "</td>"
						+ "<td>" + db_data.getString("tell") + "</td>"
						+ "<td>" + db_data.getInt("age") + "</td>"
						+ "<td>" + sdf.format(db_data.getDate("birthday")) + "</td>"
						+ "</tr>";
			}

			output += "</table>";
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return output;
	}

	/**
	 * 課題2　「profiles」に自由なメンバー情報を格納する処理
	 */
	public void insertToProfiles (
			int profilesID, String name, String tell, int age, String birthday) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) { return; }

		PreparedStatement db_st = null;

		try {
			String sql = "insert into profiles values(?, ?, ?, ?, ?)";
			db_st = db_con.prepareStatement(sql);
			db_st.setInt(1, profilesID);
			db_st.setString(2, name);
			db_st.setString(3, tell);
			db_st.setInt(4, age);
			db_st.setString(5, birthday);

			db_st.executeUpdate();

			db_st.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 課題3　「profiles」の全情報を表示
	 */
	public String selectAllFromProfiles () {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return "";
		}

		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {
			// 出力用文字列
			String output = "";

			db_st = db_con.prepareStatement("select * from profiles");

			db_data = db_st.executeQuery();

			output += getProfilesData(db_data);

			db_data.close();
			db_st.close();

			return output;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 課題4　指定したprofilesIDのユーザー情報の取得
	 * @return
	 */
	public String selectProfilesID (int profilesID) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return "";
		}

		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {
			// 出力用文字列
			String output = "";
			System.out.print("Output : " + output);
			db_st = db_con.prepareStatement("select * from profiles where profilesID = ?");
			db_st.setInt(1, profilesID);

			db_data = db_st.executeQuery();

			output += getProfilesData(db_data);

			db_data.close();
			db_st.close();

			return output;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.print("tryに入ってません。");
		return "";
	}

	public String selectFromPatternName (String pattern) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return "";
		}

		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {
			// 出力用文字列
			String output = "";

			db_st = db_con.prepareStatement("select * from profiles where name like ?");
			db_st.setString(1, pattern);

			db_data = db_st.executeQuery();

			output += getProfilesData(db_data);

			db_data.close();
			db_st.close();

			return output;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



		System.out.print("tryに入ってません。");
		return "";
	}

	/**
	 * 課題6　指定したprofilesIDのユーザーを削除する
	 * @param profilesID
	 * @return
	 */
	public String deleteFromProfilesID (int profilesID) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return "";
		}

		PreparedStatement db_st = null;

		try {
			// 出力用文字列
			String output = "";

			db_st = db_con.prepareStatement("delete from profiles where profilesID = ?");
			db_st.setInt(1, profilesID);

			db_st.executeUpdate();

			db_st.close();

			return output;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.print("tryに入ってません。");
		return "";
	}

	/**
	 * 課題7　profilesIDからユーザーを指定してアップデート
	 * @param profilesID
	 */
	public void updateFromProfilesID (
			int profilesID, String name, String tell, int age, String birthday) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return;
		}

		PreparedStatement db_st = null;

		try {
			db_st = db_con.prepareStatement(
					"update profiles set name = ?, tell = ?, age = ?, birthday = ? where profilesID = ?");

			db_st.setString(1, name);
			db_st.setString(2, tell);
			db_st.setInt(3, age);
			db_st.setString(4, birthday);
			db_st.setInt(5, profilesID);

			db_st.executeUpdate();

			db_st.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		System.out.print("tryに入ってません。");
	}

	/**
	 * 課題12 複合検索用メソッド
	 * @param name
	 * @param age
	 * @param birthday
	 * @return
	 */
	public String compoundRetrieval (
			String name, int age, String birthday) {
		// DBへのConnectionがnullの場合、そこで終了
		if (db_con == null) {
			System.out.println("db_conがnullです。");
			return "";
		}

		PreparedStatement db_st = null;
		ResultSet db_data = null;

		try {
			// 出力用文字列
			String output = "";

			db_st = db_con.prepareStatement(
					"select * from profiles where name = ? && age = ? && birthday = ?");
			db_st.setString(1, name);
			db_st.setInt(2, age);
			db_st.setString(3, birthday);

			db_data = db_st.executeQuery();

			output += getProfilesData(db_data);

			db_data.close();
			db_st.close();

			return output;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



		System.out.print("tryに入ってません。");
		return "";
	}
}
