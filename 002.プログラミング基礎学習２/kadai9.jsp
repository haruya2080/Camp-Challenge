<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	HashMap<String, String> hMap = new HashMap<String, String>();
	
	hMap.put("1", "AAA");
	hMap.put("hello", "world");
	hMap.put("soeda", "33");
	hMap.put("20", "20");
	
	// 確認のため、出力
	for (Map.Entry entry : hMap.entrySet()) {
		out.print(entry.getKey());
		out.print("->");
		out.print(entry.getValue());
		out.print("/");
		out.print("<br>");
	}
	
%>