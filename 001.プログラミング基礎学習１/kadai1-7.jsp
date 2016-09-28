<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	// リクエストから総額、個数、種別
	String totalParam = request.getParameter("total");
	String constParam = request.getParameter("count");
	String typeParam = request.getParameter("type");
	
	// 文字列を整数値に変換していく
	int total = Integer.parseInt(totalParam);
	int count = Integer.parseInt(constParam);
	int type = Integer.parseInt(typeParam);
	
	out.print("～商品種別～<br>");
	
	// 種別ごとに出力
	if (type == 1) {
		out.print("１：雑貨");
	} else if (type == 2) {
		out.print("２：生鮮食品");
	} else if (type == 3) {
		out.print("３：その他");
	} else {
		out.print("０：種別判定不明");
	}
	
	out.print("<br><br>");
	
	// 1個当たり値段を求める
	int value = total / count;
	
	int point = 0;
	
	if (total >= 5000) {
		point = total * 5 / 100;
	} else if (total >= 3000) {
		point = total * 4 / 100;
	}
	
	out.print("～1個当たりの値段～<br>" + value + "<br><br>");
	out.print("～ポイント～<br>" + point);
	
%>