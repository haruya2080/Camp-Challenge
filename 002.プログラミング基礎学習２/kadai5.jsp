<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int num = 0;
	
	for (int i=1; i<=100; i++) {
		num += i;
		/*
		out.print("0から" + i + "をすべて足した値 : " + num);
		out.print("<br>");
		*/
	}
	
	out.print("0から100をすべて足した値 : " + num);
	
%>