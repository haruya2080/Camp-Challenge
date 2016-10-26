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
 * Servlet implementation class InventoryControlServlet
 */
@WebServlet("/InventoryControl")
public class InventoryControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processInventoryControl(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processInventoryControl(request, response);
	}

	private void processInventoryControl (
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ボタンのタイプを取得
		String actionTxt = request.getParameter("action");

		// DBへの接続
		InventoryControlDB_Access dbAccess = new InventoryControlDB_Access();

		// アクションがある場合
		if (actionTxt != null) {
			// System.out.print(actionTxt);

			// ログアウトアクション
			if (actionTxt.equals("logout")) {
				// セッションの作成
				HttpSession session = request.getSession(true);
				// セッション値の削除
				session.removeAttribute("loginUser");

				// ログインページに遷移
				response.sendRedirect(PagePathSet.loginServletPath);

				// DBから切断
				dbAccess.closeDB_Connection();
				return;
			}

			// テーブルにインサート処理
			else if (actionTxt.equals("register")) {
				String name = request.getParameter("name");
				int price = Integer.valueOf(request.getParameter("price"));
				int stock = Integer.valueOf(request.getParameter("stock"));

				dbAccess.InsertGoods(name, price, stock);
			}

			// 在庫を更新する
			else if (actionTxt.equals("stock_update"))  {
				int id = Integer.valueOf(request.getParameter("id"));
				int stock = Integer.valueOf(request.getParameter("stock"));

				dbAccess.updateGoods(id, stock);
			}
		}

		// アクションがない場合初めてのアクセス)
		else {

		}

		// 商品の一覧をテーブルの要素で取得
		String output = dbAccess.getAllGoodsTable();

		// 出力をoutputにセット
		request.setAttribute("output", output);

		// DBから切断
		dbAccess.closeDB_Connection();

		// 商品一覧ページに移動
		RequestDispatcher rd = request.getRequestDispatcher(
				PagePathSet.inventoryCtrlJspPath);
		rd.forward(request, response);
	}

}
