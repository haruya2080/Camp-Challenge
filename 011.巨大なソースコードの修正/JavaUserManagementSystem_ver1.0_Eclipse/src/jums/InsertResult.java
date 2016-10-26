package jums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * insertresultと対応するサーブレット
 * フォームから入力された値をセッション経由で受け取り、データベースにinsertする
 * 直接アクセスした場合はerror.jspに振り分け
 * @author hayashi-s
 */
public class InsertResult extends HttpServlet {

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
        	/* 課題2 修正箇所 */
        	//セッションスタート
        	HttpSession session = request.getSession(true);
	        request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
	        String accesschk = request.getParameter("ac");
	        if (accesschk == null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
	            throw new Exception("不正なアクセスです");
	        }

        	if (request.getParameter("yes") != null) {
        		//ユーザー情報に対応したJavaBeansオブジェクトに格納していく
                UserDataDTO userdataDTO = new UserDataDTO();
                UserDataBeans userData = (UserDataBeans)session.getAttribute("user_data");

                // nullチェック
                if (userData != null) {
                	userdataDTO.setUserID(userData.getUserID());
                	userdataDTO.setName(userData.getName());
                	userdataDTO.setBirthday(userData.getBirthday());
                	userdataDTO.setType(userData.getType());
                	userdataDTO.setTell(userData.getTell());
                	userdataDTO.setComment(userData.getComment());
                }

                //DBへデータの挿入
                UserDataDAO.getInstance().insert(userdataDTO);

                // 課題7 ユーザー情報セッションを削除
                session.removeAttribute("user_data");

                // セッションは削除されているので、jspにはリクエストで送る
                request.setAttribute("user_data", userData);
                request.getRequestDispatcher("/insertresult.jsp").forward(request, response);
        	} else {

        	}
        }catch(Exception e){
            //データ挿入に失敗したらエラーページにエラー文を渡して表示
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
