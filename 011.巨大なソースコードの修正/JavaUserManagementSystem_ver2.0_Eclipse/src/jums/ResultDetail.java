package jums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class ResultDetail extends HttpServlet {

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
        try{
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            // セッションの作成、取得
            HttpSession session = request.getSession(true);

            UserDataDTO resultData = (UserDataDTO)session.getAttribute(
            		MySessionNames.ResultDataDTO);

            // 検索結果がセッションに存在するか
            if (resultData == null) {
            	UserDataDTO searchData = new UserDataDTO();
            	searchData = new UserDataDTO();
            	//DTOオブジェクトにマッピング。DB専用のパラメータに変換
                int id = Integer.parseInt(request.getParameter("id"));
                searchData.setUserID(id);

                resultData = UserDataDAO.getInstance().searchByID(searchData);

                // フォームで使用する。セッション保持用
                UserDataBeans userData = new UserDataBeans();
                // DTOからUDへマッピング
                resultData.DTO2UDMapping(userData);

                // 詳細情報をセッションに格納
                session.setAttribute(MySessionNames.ResultDataUD, userData);
                session.setAttribute(MySessionNames.ResultDataDTO, resultData);

                session.setAttribute("userID", Integer.valueOf(resultData.getUserID()));
            }

            session.setAttribute("ac", (int) (Math.random() * 1000));
            request.getRequestDispatcher("/resultdetail.jsp").forward(request, response);
        }catch(Exception e){
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
