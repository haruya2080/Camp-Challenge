/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.kadai3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author niwayama
 */
public class Kadai3ResultViewer extends HttpServlet {

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
        
        out.println("<h1>プログラミング基礎学習3の課題</h1>");
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>プログラミング基礎学習3の課題</title>");            
            out.println("</head>");
            out.println("<body>");
            
            // プログラミング基礎学習3の課題を包括したクラス
            Kadai3 kadai3 = new Kadai3(out);

            out.print("<h2>～課題1,4の出力結果～</h2>");

            // 課題1は10回呼び出される
            for (int i=0; i<10; i++) {
                boolean isDone = kadai3.viewProfile();
                out.print("<br>");
                out.print(isDone ? "この処理は正しく実行できました" : "正しく実行できませんでした");
                out.print("<br><br>");
            }

            out.print("<h2>～課題2の出力結果～</h2>");

            // 課題2　引数に2を代入している
            kadai3.discriminateNumberType(1);

            out.print("<h2>～課題3の出力結果～</h2>");

            // 課題3　デファルト値の挙動確認
            kadai3.kadai3_3(2, 4, true);
            kadai3.kadai3_3(2, 4);
            kadai3.kadai3_3(2, true);
            kadai3.kadai3_3(2);

            out.print("<h2>～課題5の出力結果～</h2>");

            // 課題5　プロフィールのデータがちゃんと取得できているか確認
            Kadai3.ProfileData data = kadai3.getProfileData();
            if (data != null) {
                out.print("名前：" + data.getName() + "<br>");
                out.print("生年月日：" + data.getBirthDay() + "<br>");
                out.print("住所：" + data.getAddress() + "<br>");
            }
            
            out.print("<h2>～課題6の出力結果～</h2>");
            
            // 課題6　プロフィールをidから見つけ、それを表示する
            Kadai3.ProfileData foundData = kadai3.findProfileData(2);
           
            if (foundData != null) {
                out.print("名前：" + foundData.getName() + "<br>");
                out.print("生年月日：" + foundData.getBirthDay() + "<br>");
                out.print("住所：" + foundData.getAddress() + "<br>");
            } else {
                out.print("そのidのプロフィールは存在しません。<br>");
            }
            
            out.print("<h2>～課題7,8の出力結果～</h2>");
            
            // 課題7,8　nullを除いてすべてのプロフィールを表示
            kadai3.viewAllProfileData();
            
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
