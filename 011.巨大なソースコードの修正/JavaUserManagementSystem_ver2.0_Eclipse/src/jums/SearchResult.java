package jums;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class SearchResult extends HttpServlet {

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

            // セッションを作成
            HttpSession hs = request.getSession(true);
            // セッションから詳細結果を削除（残っていると前回のデータにアクセスしてしまうため）
            hs.removeAttribute(MySessionNames.ResultDataDTO);

            // セッションから結果を取得
            ArrayList<UserDataDTO> resultDatas
            	= (ArrayList<UserDataDTO>)hs.getAttribute(MySessionNames.ResultDatas);

            // 結果がセッションにない場合(再検索)
            if (resultDatas == null) {
            	UserDataDTO searchData = (UserDataDTO)hs.getAttribute(MySessionNames.SearchData);

            	// 検索データがセッションにない場合(新しい検索データを使用して検索)
            	if (searchData == null) {
            		// フォームからの入力を取得して、JavaBeansに格納
                    UserDataBeans udb = new UserDataBeans();
                    udb.setName(request.getParameter("name"));
                    udb.setYear(request.getParameter("year"));
                    udb.setType(request.getParameter("type"));

                    //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                    searchData = new UserDataDTO();
                    udb.UD2DTOMapping(searchData);

                    hs.setAttribute(MySessionNames.SearchData, searchData);
            	}
            	// 検索実行
                resultDatas = UserDataDAO.getInstance().search(searchData);
                // 複数の結果をセッションに保存
                hs.setAttribute(MySessionNames.ResultDatas, resultDatas);
            }

            request.getRequestDispatcher("/searchresult.jsp").forward(request, response);
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
