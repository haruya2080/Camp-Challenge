<%-- 
    Document   : kadai
    Created on : 2016/10/05, 11:39:54
    Author     : niwayama
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>データ操作の課題　出力結果</title>
    </head>
    <body>
        <h1>データ操作の課題　出力結果</h1>
        <%
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("txtName");
            String sexType = request.getParameter("typeMan");
            String hobby = request.getParameter("txtHobby");
            
            out.println("<h3>～名前～</h3>");
            out.println(name + "<br>");
            
            out.println("<h3>～性別～</h3>");
            out.println(sexType + "<br>");
            
            out.println("<h3>～趣味～</h3>");
            out.println(hobby + "<br>");
        %>
    </body>
</html>
