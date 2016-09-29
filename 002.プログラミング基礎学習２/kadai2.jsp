<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String text = "A";
	
	switch (text) {
	case "A" :
		out.print("英語");
		break;
	case "B" :
		out.print("日本語");
		break;
	}
	
%>