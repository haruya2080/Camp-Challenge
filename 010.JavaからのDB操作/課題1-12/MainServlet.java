package org.camp.javadb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DB_AccessTest
 */
@WebServlet("/DB_AccessTest")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String output = "";

		// 課題1　コンストラクタの中でDBに接続してる(エラーハンドリングも含めて)
		DatabaseAccessKadai dbAccess = new DatabaseAccessKadai(
				"//localhost:3306", "challenge_db", "niwayama", "5416");

		// 課題2　id3に以下のユーザーをinsertする
		dbAccess.insertToProfiles(3, "庭山悠也", "080-5414-5416", 23, "1993-02-10");

		// 課題3の出力結果
		output += "<h2>課題3の出力結果</h2>";
		output += dbAccess.selectAllFromProfiles();

		// 課題4の出力結果　引数は見つけたい「profilesID」である（1）
		output += "<h2>課題4の出力結果</h2>";
		output += dbAccess.selectProfilesID(1);

		// 課題5の出力結果　引数はパターンである("%茂%")
		output += "<h2>課題5の出力結果</h2>";
		output += dbAccess.selectFromPatternName("%茂%");

		// 課題6の出力結果　insertしたid(3)のユーザーを削除する
		output += "<h2>課題6の出力結果</h2>";
		dbAccess.deleteFromProfilesID(3);
		output += dbAccess.selectAllFromProfiles();

		// 課題7の出力結果　id(1)のユーザーを「松岡修造」に変更
		output += "<h2>課題7の出力結果</h2>";
		dbAccess.updateFromProfilesID(1, "松岡修造", null, 48, "1967-11-06");

		// DBから切断する処理
		dbAccess.closeDB_Connection();
		try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>JavaからのDBアクセス　課題</h1>");
            out.println(output);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}



}
