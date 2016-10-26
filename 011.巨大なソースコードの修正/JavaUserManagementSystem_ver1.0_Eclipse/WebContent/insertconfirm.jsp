<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession hs = request.getSession();
	UserDataBeans userData = (UserDataBeans)hs.getAttribute("user_data");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JUMS登録確認画面</title>
	</head>
	<body>
	<% if (userData != null) { %>
		<h1>登録確認</h1>
		<table border="1" style="font-size : 20px">
			<tr>
				<td>名前</td>
				<td><%= userData.getName()%></td>
			</tr>
			<tr>
				<td>生年月日</td>
				<td><%= userData.getBirthdayText()%></td>
			</tr>
			<tr>
				<td>種別</td>
				<td><%= userData.getTypeText()%></td>
			</tr>
			<tr>
				<td>電話番号</td>
				<td><%= userData.getTell()%></td>
			</tr>
			<tr>
				<td>自己紹介</td>
				<td><%= userData.getComment().replace("\r\n", "<br>")%></td>
			</tr>
		</table>
		<br>
		上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
        	<input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>

    <% }else{ %>
        <h1>入力が不完全です</h1>
    <% } %>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
		<br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
