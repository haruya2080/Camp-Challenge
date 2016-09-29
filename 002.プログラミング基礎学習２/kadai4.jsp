<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String text = "";
	
	for (int i=0; i<30; i++) {
		text += "A";
		/*
		out.print((i+1) + "回Aを連結した文字列 : " + text);
		out.print("<br>");
		*/
	}
	
	out.print("30回Aを連結した文字列 : " + text);
	
%>