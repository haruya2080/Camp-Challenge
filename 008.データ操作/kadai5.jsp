<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	final String checked = "checked";
	final String nameKey = "Name";
	final String sexTypeKey = "SexType";
	final String hobbyKey = "Hobby";

	String name = "名前を入力してください。";
	String checkedMan = checked;	// 男性のラジオボタンのvalue
	String checkedWomen = "";		// 女性のラジオボタンのvalue
	String sexType = "Man";
	String hobby = "ここに趣味を書いてください。";

	request.setCharacterEncoding("UTF-8");
	HttpSession hs = request.getSession(true);

	// System.out.print(request.getParameter("action"));

	String actionTxt = request.getParameter("action");

	// saveボタンが押されていない場合(更新、普通にアクセスされた場合)
	if (actionTxt == null) {
		// セッションに保存されている情報を取得する
		Object nameObj = hs.getAttribute(nameKey);
		if (nameObj != null) { name = (String)nameObj; }

		Object sexTypeObj = hs.getAttribute(sexTypeKey);
		if (sexTypeObj != null) { sexType = (String)sexTypeObj; }

		Object hobbyObj = hs.getAttribute(hobbyKey);
		if (hobbyObj != null) { hobby = (String)hobbyObj; }
	}

	// saveボタンが押された場合
	else if (request.getParameter("action").equals("save")) {
		// 入力した情報をhtmlから取得
		name = request.getParameter("txtName");
		sexType = request.getParameter("typeSex");
		hobby = request.getParameter("txtHobby");

		// セッションに入力された情報を保存
		hs.setAttribute(nameKey, name);
		hs.setAttribute(sexTypeKey, sexType);
		hs.setAttribute(hobbyKey, hobby);

		System.out.print("セーブが実行されました\n");
	}

	// 性別のタイプ別にラジオボタンのvalueを設定
	if (sexType != null) {
		// 男性の場合
		if (sexType.equals("Man")) {
			checkedMan = checked;
			checkedWomen = "";
		}
		// 女性の場合
		else if (sexType.equals("Women")) {
			checkedMan = "";
			checkedWomen = checked;
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>データ操作 課題5</title>
</head>
<body>
	<form action="./kadai5.jsp" method="post">
		<h1>データ操作 課題5</h1>
		<h3>名前</h3>
		<input type="text" name="txtName" value="<%=name%>"><br>
		<h3>性別</h3>
		男性<input type="radio" name="typeSex" value="Man" <%=checkedMan%>>
		女性<input type="radio" name="typeSex" value="Women" <%=checkedWomen%>><br>
		<h3>趣味</h3>
		<textarea name="txtHobby" rows="4" cols="24"><%=hobby%></textarea><br><br>
		<button type="submit" name="action" value="save">保存</button>
	</form>
</body>
</html>