<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String result = (String)request.getAttribute("ResultSearch");
	result = (result != null) ? result : "値が存在しません。";


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MySQL検索フォーム</title>
</head>
<body>
	<h2>～検索フォーム（課題8）～</h2>
	<form action="FormControllerServlet" method="post">
		name : <input type="text" name="name">
		<button type = "submit" name="action" value="search">search</button>
	</form>

	<h2>～検索フォーム（課題12）～</h2>
	<form action="FormControllerServlet" method="post">
		name : <input type="text" name="name">
		age : <input type="number" name="age">
		birthday : <input type="text" name="birthday">
		<button type = "submit" name="action" value="andSearch">search</button>
	</form>

	<h2>～検索結果～</h2>
	<%=result %>

	<h2>～DBへの追加（課題9）～</h2>
	<form action="FormControllerServlet" method="post">
		<table>
			<tr>
				<td>profilesID</td>
				<td><input type="text" name="profilesID" value="0"></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>tell</td>
				<td><input type="text" name="tell"></td>
			</tr>
			<tr>
				<td>age</td>
				<td><input type="text" name="age" value="18"></td>
			</tr>
			<tr>
				<td>birthday</td>
				<td><input type="text" name="birthday"></td>
			</tr>
		</table>
		<button type = "submit" name="action" value="insert">insert</button>
	</form>

	<h2>～DBから削除（課題10）～</h2>
	<form action="FormControllerServlet" method="post">
		profilesID : <input type="text" name="profilesID" value="0">
		<button type = "submit" name="action" value="delete">delete</button>
	</form>

	<h2>～DBの上書き（課題11）～</h2>
	<form action="FormControllerServlet" method="post">
		<table>
			<tr>
				<td>profilesID</td>
				<td><input type="text" name="profilesID" value="0"></td>
			</tr>
			<tr>
				<td>name</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>tell</td>
				<td><input type="text" name="tell"></td>
			</tr>
			<tr>
				<td>age</td>
				<td><input type="text" name="age" value="18"></td>
			</tr>
			<tr>
				<td>birthday</td>
				<td><input type="text" name="birthday"></td>
			</tr>
		</table>
		<button type = "submit" name="action" value="update">update</button>
	</form>
</body>
</html>
