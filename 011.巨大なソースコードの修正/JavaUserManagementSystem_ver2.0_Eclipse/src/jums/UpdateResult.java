package jums;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class UpdateResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	request.setCharacterEncoding("UTF-8");
        	// セッションの作成
        	HttpSession hs = request.getSession(true);

            //フォームからの入力を取得して、JavaBeansに格納
            UserDataBeans updateData = new UserDataBeans();
            updateData.setName(request.getParameter("name"));
            updateData.setYear(request.getParameter("year"));
            updateData.setMonth(request.getParameter("month"));
            updateData.setDay(request.getParameter("day"));
            updateData.setType(request.getParameter("type"));
            updateData.setTell(request.getParameter("tell"));
            updateData.setComment(request.getParameter("comment"));

            // 無効な値がないかチェック
            if (updateData.chkproperties().size() == 0) {
            	// DBアクセスするためにDTO作成、マッピング
            	UserDataDTO udd = new UserDataDTO();
            	updateData.UD2DTOMapping(udd);

            	// セッションに保持していたIDをセット（アップデート対象を設定するため）
            	udd.setUserID((Integer)hs.getAttribute("userID"));
            	// 現在時刻をセット（セッションに保持する方にも必要なのでここでセット１）
            	udd.setNewDate(new Timestamp(System.currentTimeMillis()));

            	// アップデート実行
            	UserDataDAO.getInstance().update(udd);
            	// 更新したため、セッションにある詳細結果も更新
            	hs.setAttribute(MySessionNames.ResultDataDTO, udd);
            	// 再検索が必要なので検索結果をセッションから削除
            	hs.removeAttribute(MySessionNames.ResultDatas);
                // 結果画面に遷移
                request.getRequestDispatcher("/updateresult.jsp").forward(request, response);
            } else {
            	// セッションの更新
            	hs.setAttribute(MySessionNames.ResultDataUD, updateData);
            	// 入力に不備があった場合、Update画面に戻す
            	response.sendRedirect("Update");
            }
        } catch (Exception e) {
        	//何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
