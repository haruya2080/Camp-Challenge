package org.camp.inventory_control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InventoryControlerServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		checkLoginStatus(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		checkLoginStatus(request, response);
	}

	/**
	 * ユーザーがログインしているかチェックする
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void checkLoginStatus (
    		HttpServletRequest request, HttpServletResponse response)
    				throws ServletException, IOException {
    	this.request = request;
    	this.response = response;

    	response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// セッションの作成
		HttpSession session = request.getSession(true);
		// ログイン状態の取得
		String loginStatus = (String)session.getAttribute("loginUser");

		System.out.print(loginStatus);

		if (loginStatus != null) {
			// ユーザー情報がある場合、ログイン済みなので在庫管理ページに遷移
			response.sendRedirect(PagePathSet.inventoryControlPath);
		} else {
			moveLoginPage();
		}
    }

	/**
	 * ログインページに遷移
	 * @throws IOException
	 * @throws ServletException
	 */
	private void moveLoginPage () throws IOException, ServletException {
		// ボタンのタイプを取得
		String actionTxt = request.getParameter("action");

		// アクションがある場合
		if (actionTxt != null) {
			// ログインボタンが押されたとき
			if (actionTxt.equals("login")) {
				String userID = request.getParameter("userID");
				String password = request.getParameter("password");

				if (userID != null && password != null) {
					// DBにアクセス
					InventoryControlDB_Access accessDB = new InventoryControlDB_Access();

					// ユーザー情報がDBにあるかチェック
					if (accessDB.checkUserInfo(userID, password)) {
						// ユーザー情報がある場合、ログイン成功

						// セッションの作成
						HttpSession session = request.getSession(true);
						session.setAttribute("loginUser", userID);

						// 在庫管理ページに遷移
						response.sendRedirect(PagePathSet.inventoryControlPath);
					} else {
						// 失敗したため、再度ログインページに移動
						request.setAttribute("loginStatus", "failed");
						RequestDispatcher rd = request.getRequestDispatcher(PagePathSet.loginJspPath);
						rd.forward(request, response);
					}

					// DBとの接続を切断する
					accessDB.closeDB_Connection();
				}
			}
		}

		// アクションがないため、初めてのアクセス
		else {
			// ログインページに移動
			request.setAttribute("loginStatus", "first");
			RequestDispatcher rd = request.getRequestDispatcher(PagePathSet.loginJspPath);
			rd.forward(request, response);
		}
	}
}
