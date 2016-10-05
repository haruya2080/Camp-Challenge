/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.blackjack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author niwayama
 */
public class PlayBlackjack extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String output = "";
        output += "<h2>初期持ち札</h2>";
        
        // ディーラーを生成
        Dealer dealer = new Dealer();
        
        // ディーラー自身にカードを配る
        dealer.setCard(dealer.deal());
        
        // ディーラーのカードを詳細表示
        output += dealer.ToStringMyCards();
        
        // ユーザーを格納しておくリスト
        ArrayList<User> userList = new ArrayList<User>();
        
        int userCount = 4;
        
        // ユーザーの数だけループする
        for (int i=0; i<userCount; i++) {
            // 新しいユーザーを生成
            User user = new User();
            // ユーザーにカードを配る
            user.setCard(dealer.deal());
            
            // ユーザーをリストに追加
            userList.add(user);
            
            // ユーザーの持ちカードを詳細表示する
            output += user.ToStringMyCards(i+1) + "<br>";
        }
        
        int endUserCount = 0;
        int turn = 1;
        while (endUserCount < userCount + 1) {
            endUserCount = 0;
            
            output += "<h2>ターン" + turn + "</h2>";
            
            // ディーラーが引く必要があるか
            if (dealer.checkSum()) {
                dealer.setCard(dealer.hit());
                // ディーラーのカードを詳細表示
                output += dealer.ToStringMyCards();
            } else {
                endUserCount++;
            }
            
            int num = 1;
            for (User user : userList) {
                // ユーザーがまだカードを引きたい場合
                if (user.checkSum()) {
                    user.setCard(dealer.hit());
                    // ユーザーの持ちカードを詳細表示する
                    output += user.ToStringMyCards(num) + "<br>";
                } else {
                    endUserCount++;
                }
                
                num++;
            }
            
            turn++;
        }
        
        // 最終結果を表示する
        output += "<h2>最終結果</h2>";
        
        // ディーラーの持ちカードを詳細表示する
        output += dealer.ToStringMyCards();
        
        // ディーラーがバストしているか
        boolean isDealerBust = (dealer.open() > 21);
        
        // 勝ち負けを判別
        int num = 1;
        for (User user : userList) {
            // ユーザーがバストしているか
            boolean isUserBust = (user.open() > 21);
            String result = "";
            // 負けの場合、(ユーザーがバスト)
            if (isUserBust) {
                result = "<FONT COLOR=\"#FF0000\">「負け」</FONT>";
            }
            // 勝ちの場合、（ディーラーがバスト）、
            // （ユーザーがバストしておらず、ユーザーの点数の方が高い）
            else if (isDealerBust || user.open() > dealer.open()) {
                result = "<FONT COLOR=\"#0000FF\">「勝ち」</FONT>";
            }
            // 負けの場合、(ディーラーがバストしておらず、ディーラーの点数の方が高い)
            else if (user.open() < dealer.open()) {
                result = "<FONT COLOR=\"#FF0000\">「負け」</FONT>";
            }
            // 引き分けの場合、（それ以外）
            else {
                result = "「引き分け」";
            }
            
            // ユーザーの持ちカードを詳細表示する
            output += user.ToStringMyCards(num) + result + "<br>";
            num++;
        }
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ブラックジャック</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ブラックジャック</h1>");
            out.println(output);    // アウトプット
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
