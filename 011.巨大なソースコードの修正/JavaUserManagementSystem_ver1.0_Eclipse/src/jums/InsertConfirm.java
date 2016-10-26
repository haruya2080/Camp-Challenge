package jums;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * insertconfirm.jspと対応するサーブレット
 * フォーム入力された情報はここでセッションに格納し、以降持ちまわることになる
 * 直接アクセスした場合はerror.jspに振り分け
 * @author hayashi-s
 */
public class InsertConfirm extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
            String accesschk = request.getParameter("ac");
            if(accesschk == null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }

            // フォームからの入力を取得し、ユーザー情報に格納
            UserDataBeans userData = new UserDataBeans();
            userData.setName(request.getParameter("name"));
            userData.setYear(request.getParameter("year"));
            userData.setMonth(request.getParameter("month"));
            userData.setDay(request.getParameter("day"));
            userData.setType(Integer.valueOf(request.getParameter("type")));
            userData.setTell(request.getParameter("tell"));
            userData.setComment(request.getParameter("comment"));

            // セッションにユーザー情報を格納
            session.setAttribute("user_data", userData);
            System.out.println("Session updated!!");

            // 課題4 無効な値の有無を判別
            if (userData.existInvalidParam()) {
            	// 入力画面に戻る
            	response.sendRedirect("insert");
            	// request.getRequestDispatcher("/insert.jsp").forward(request, response);
            } else {
                // セッションに直リンク防止用の値を格納
                session.setAttribute("ac", (int) (Math.random() * 1000));
                // 入力確認画面に遷移
                request.getRequestDispatcher("/insertconfirm.jsp").forward(request, response);
            }
        }catch(Exception e){
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
