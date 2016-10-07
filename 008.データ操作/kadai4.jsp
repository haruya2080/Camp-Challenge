<%-- 
    Document   : kadai4
    Created on : 2016/10/05, 15:30:52
    Author     : niwayama
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>データ操作の課題4　出力結果</title>
    </head>
    <body>
        <h1>データ操作の課題4　出力結果</h1>
        <%
            // 現在の時刻を取得
            Date date = new Date();
            // セッションがセットされていない場合、作成して取得する
            HttpSession httpSession = request.getSession(true);
            
            final String lastAccessName = "LastAccess";
            
            Object lastAccess = httpSession.getAttribute(lastAccessName);
            
            // セッションに最終アクセス時間が保存されている場合
            if (lastAccess != null) {
                out.print("前回アクセス：" + lastAccess.toString());
            }
            // 保存されていない
            else {
                out.print("セッションには最終アクセスの時刻が保存されていません。");
            }
                        // セッションのセットを行う
            httpSession.setAttribute(lastAccessName, date.toString());
        %>
        
    </body>
</html>
