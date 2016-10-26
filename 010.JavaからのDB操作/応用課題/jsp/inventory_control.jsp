<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String output = (String)request.getAttribute("output");
	output = (output != null) ? output : "商品が存在しません。";

	String userID = (String)request.getAttribute("userID");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>在庫管理</title>
	<link rel="stylesheet" type="text/css" href="css/header_menu.css" media="all" />
	<style type="text/css">

	<!--

	table {
		width: 500px;
		border-collapse: collapse;
	}

	th {
		padding: 3px;
		text-align: center;
		width: 20%;
		color: #3366CC;
		font-weight: bold;
		border-bottom: 1px dashed #999999;
	}

	td.line {
		border-bottom: 3px solid #3366CC;
	}

	h1 {
		position: relative;
		padding: .25em 0 .5em .75em;
		border-left: 6px solid #3498db;
	}
	h1::after {
		position: absolute;
		left: 0;
		bottom: 0;
		content: '';
		width: 100%;
		height: 0;
		border-bottom: 1px solid #ccc;
	}

	#logout button {
	  font-family: Arial;
	  color: #ffffff;
	  font-size: 16px;
	  padding-top: 10px;
	  padding-right: 20px;
	  padding-bottom: 10px;
	  padding-left: 20px;
	  text-decoration: none;
	  -webkit-border-radius: 10px;
	  -moz-border-radius: 10px;
	  border-radius: 10px;
	  -webkit-box-shadow: 0px 8px 6px #e3e3e3;
	  -moz-box-shadow: 0px 8px 6px #e3e3e3;
	  box-shadow: 0px 8px 6px #e3e3e3;
	  border: solid #f5fdff 4px;
	  background: -webkit-gradient(linear, 0 0, 0 100%, from(#61c7e0), to(#418da8));
	  background: -moz-linear-gradient(top, #61c7e0, #418da8);
	}

	//-->

	</style>
</head>
<body>
	<form action="InventoryControl" method="post">
	<ul id="menu">
		<li><a href="#">さんログイン中です。</a></li>
	</ul>
	<ul id="logout">
		<button type="submit" name="action" value="logout">ログアウト</button>
	</ul>

	</form>

	<h1>商品登録</h1>
	<form action="InventoryControl" method="post">
		<table>
			<tr>
				<td>商品名</td>
				<td><input type="text" name=name></td>
			</tr>

			<tr>
				<td>在庫</td>
				<td><input type="number" name="stock"></td>
			</tr>

			<tr>
				<td>価格</td>
				<td><input type="number" name="price"></td>
				<td>
					<button type="submit" name="action" value="register">登録</button>
				</td>
			</tr>
		</table>
	</form>

	<h1>在庫編集</h1>
	<form action="InventoryControl" method="post">
		<table>
			<tr>
				<td>商品ID</td>
				<td><input type="number" name="id"></td>
			</tr>

			<tr>
				<td>在庫</td>
				<td><input type="number" name="stock"></td>
			</tr>
		</table>

		<button type="submit" name="action" value="stock_update">更新</button>
	</form>

	<h1>商品一覧</h1>
	<table>
		<tr>
			<td class="line">ID</td>
			<td class="line">Name</td>
			<td class="line">Price</td>
			<td class="line">Stock</td>
		</tr>
		<%=output%>
		<tr>
			<td class="line">-</td>
			<td class="line">-</td>
			<td class="line">-</td>
			<td class="line">-</td>
		</tr>
	</table>
</body>
</html>