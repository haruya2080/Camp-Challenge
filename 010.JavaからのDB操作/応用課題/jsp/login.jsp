<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginStatus = (String)request.getAttribute("loginStatus");
	// ログイン失敗でこの画面に戻ってきたのかを判定
	boolean loginFailed = loginStatus.equals("failed");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>在庫管理 ログイン</title>
    <link rel="stylesheet" type="text/css" href="css/login.css" media="all" />
</head>
<body>

<div id="form">
    <p class="form-title">在庫管理</p>
    <form action="Login" method="post">
        <p>ユーザーID</p>
        <p class="user-id"><input type="text" name="userID" placeholder="IDを入力してください。" /></p>
        <p>パスワード</p>
        <p class="pass"><input type="password" name="password" /></p>

        <% if (loginFailed) { %>
			<font color="#ff0000">
				ユーザーIDかパスワードが間違っています。<br>
				ログインに失敗しました。
			</font>
		<% } %>

        <p class="submit">
        	<button type="submit" name="action" value="login">ログイン</button>
        </p>
    </form>
</div>
</body>
</html>