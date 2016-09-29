<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	long num = 1;
	
	for (int i=0; i<20; i++) {
		num *= 8;
		// out.print((i+1) + "回掛けた値 : " + num + "<br>");
	}
	
	out.print("20回掛けた値 : " + num);
	
%>