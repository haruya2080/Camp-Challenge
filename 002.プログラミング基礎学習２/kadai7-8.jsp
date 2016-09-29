<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String array[] = { "10", "100", "soeda", "hayashi", "-20", "118", "END" };
	
	for (int i=0; i<array.length; i++) {
		if (array[i] == "soeda") {
			array[i] = "33";
			break;
		}
	}
	
	// 確認のため、配列の要素を1つずつ出力
	for (String name : array) {
		out.print(name + "<br>");
	}
	
%>