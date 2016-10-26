<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%
	UserDataBeans userData = (UserDataBeans)request.getAttribute("user_data");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JUMS登録結果画面</title>
	</head>
	<body>
		<h1>登録結果</h1>
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
		以上の内容で登録しました。<br>

		<br>
	    <%=JumsHelper.getInstance().home()%>
    </body>
</html>
