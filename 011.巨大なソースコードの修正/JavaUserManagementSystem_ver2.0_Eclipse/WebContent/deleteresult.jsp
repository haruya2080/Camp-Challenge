
<%@page import="jums.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除結果画面</title>
    </head>
    <body>
    <h1>削除確認</h1>
    	削除しました。<br>
    <form action="SearchResult" method="POST">
    	<input type="submit" name="back" value="検索結果へ戻る"style="width:200px">
    	<input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
    </form>
    <br>
    <%=JumsHelper.getInstance().home() %>
    </body>
</html>
