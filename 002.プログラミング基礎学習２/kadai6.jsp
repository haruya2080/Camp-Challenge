<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int num = 1000;
	
	// 初期値の出力
	out.print("初期値 : " + num);
	out.print("<br>");
	
	// 100より小さくなるまでループ
	while (num >= 100) {
		num /= 2;
		
		// 経過を出力
		out.print("1/2した値 : " + num);
		out.print("<br>");
	}
	
	// 最終的な値を出力
	out.print("100より小さくなった時の値 : " + num);
	
%>