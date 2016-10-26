package org.camp.javadb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormControllerServlet
 */
@WebServlet("/FormControllerServlet")
public class FormControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String jspPath = "WEB-INF/jsp/FormJavaDB.jsp";
		String output = "";

		// ボタンのタイプを取得
		String actionTxt = request.getParameter("action");

		// ボタンタイプのnullチェック
		if (actionTxt != null) {
			// DBにアクセス
			DatabaseAccessKadai dbAccess = new DatabaseAccessKadai(
					"//localhost:3306", "challenge_db", "niwayama", "5416");

			// 課題8 検索を行う指示が出た場合
			if (actionTxt.equals("search")) {
				String name = request.getParameter("name");

				System.out.println("name : " + name);

				// DBから名前パターン検索
				output += dbAccess.selectFromPatternName("%"+name+"%");

				// jspに検索結果を送信
				request.setAttribute("ResultSearch", output);
			}

			// 課題9 追加を行う場合
			else if (actionTxt.equals("insert")) {
				int profilesID = Integer.parseInt(
						request.getParameter("profilesID"));
				String name = request.getParameter("name");
				String tell = request.getParameter("tell");
				int age = Integer.parseInt(
						request.getParameter("age"));
				String birthday = request.getParameter("birthday");

				dbAccess.insertToProfiles(profilesID, name, tell, age, birthday);
			}

			// 課題10 削除を行う場合
			else if (actionTxt.equals("delete")) {
				int profilesID = Integer.parseInt(
						request.getParameter("profilesID"));
				dbAccess.deleteFromProfilesID(profilesID);
			}

			// 上書きを行う場合
			else if (actionTxt.equals("update")) {
				int profilesID = Integer.parseInt(
						request.getParameter("profilesID"));
				String name = request.getParameter("name");
				String tell = request.getParameter("tell");
				int age = Integer.parseInt(
						request.getParameter("age"));
				String birthday = request.getParameter("birthday");
				dbAccess.updateFromProfilesID(profilesID, name, tell, age, birthday);
			}

			// 複合検索の場合
			else if (actionTxt.equals("andSearch")) {
				String name = request.getParameter("name");
				int age = Integer.valueOf(request.getParameter("age"));
				String birthday = request.getParameter("birthday");

				System.out.println("name : " + name);

				// DBから名前パターン検索
				output += dbAccess.compoundRetrieval(name, age, birthday);

				// jspに検索結果を送信
				request.setAttribute("ResultSearch", output);
			}

			// DBから切断
			dbAccess.closeDB_Connection();
		}

		RequestDispatcher rd = request.getRequestDispatcher(jspPath);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
