<%@page import="jums.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報検索画面</title>
        <link rel="stylesheet" type="text/css" href="css/default.css">
    </head>
    <body>
    <h1>ユーザー情報検索</h1>
	<form action="SearchResult" method="GET">

	<table class="simple">
		<tr>
			<td>名前</td>
			<td><input type="text" class="shadow" name="name" value=""></td>
		</tr>
		<tr>
			<td>生年</td>
			<td><select name="year" class="shadow">
	            <option value="">----</option>
	            <% for(int i=1950; i<=2010; i++){ %>
	            <option value="<%=i%>"><%=i%></option>
	            <% } %>
	        </select> 年生まれ</td>
		</tr>
		<tr>
			<td>種別</td>
			<td><% for(int i = 1; i<=3; i++){ %>
            	<input type="radio" name="type" value="<%=i%>"><%=jh.exTypenum(i)%><br>
            <% } %></td>
		</tr>
	</table>
        <br>

        <input type="submit" name="btnSubmit" value="検索">
        <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
