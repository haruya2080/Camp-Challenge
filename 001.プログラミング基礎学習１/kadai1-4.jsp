<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	final int constNum = 8;
	int num = 4;
	
	out.print("<br>～定義～<br><br>");
	out.print("定数値 : " + constNum + "<br>");
	out.print("変数値 : " + num + "<br>");
	
	out.print("<br>～結果～<br><br>");
	out.print("足し算 : " + (constNum + num) + "<br>");
	out.print("引き算 : " + (constNum - num) + "<br>");
	out.print("掛け算 : " + (constNum * num) + "<br>");
	out.print("割り算 : " + (constNum / num) + "<br>");
	out.print("余り算 : " + (constNum % num) + "<br>");
%>