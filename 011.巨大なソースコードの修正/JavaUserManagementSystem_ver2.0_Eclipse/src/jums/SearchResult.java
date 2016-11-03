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
        	//リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");

            // セッションを作成
            HttpSession session = request.getSession(true);

            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }

            // セッションから詳細結果を削除（残っていると前回のデータにアクセスしてしまうため）
            session.removeAttribute(MySessionNames.ResultDataDTO);

            // セッションから結果を取得
            ArrayList<UserDataDTO> resultDatas
            	= (ArrayList<UserDataDTO>)session.getAttribute(MySessionNames.ResultDatas);

            // 結果がセッションにない場合(再検索)
            if (resultDatas == null) {
            	UserDataDTO searchData = (UserDataDTO)session.getAttribute(MySessionNames.SearchData);

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

                    session.setAttribute(MySessionNames.SearchData, searchData);
            	}
            	// 検索実行
                resultDatas = UserDataDAO.getInstance().search(searchData);
                // 複数の結果をセッションに保存
                session.setAttribute(MySessionNames.ResultDatas, resultDatas);
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
