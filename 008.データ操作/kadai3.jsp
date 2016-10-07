<%-- 
    Document   : kadai3
    Created on : 2016/10/05, 14:56:58
    Author     : niwayama
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>データ操作の課題3　出力結果</h1>
        <%
            Cookie[] cookies = request.getCookies();
            
            final String lastAccessName = "LastAccess";
            
            // クッキーを見つけた場合のフラグ
            boolean isFind = false;
            
            // クッキー
            if (cookies != null) {
                // すべてのクッキーを見ていく
                for (Cookie cookie : cookies) {
                    // クッキーに最終アクセス時間がある場合
                    if (cookie.getName().equals(lastAccessName)) {
                        out.print("前回アクセスした時間：" + cookie.getValue());
                        isFind = true;
                    }
                }
            }
            
            // クッキーが発見されなかった場合
            if (!isFind) {
                out.print("前回アクセスした時間はありません。");
            } 
            
            // ラストアクセスの日時を追加するクッキーを作成
            Cookie addedCookie = new Cookie(lastAccessName, new Date().toString());
            // クッキーを追加
            response.addCookie(addedCookie);
        %>
    </body>
</html>
