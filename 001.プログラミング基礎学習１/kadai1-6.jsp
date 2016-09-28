<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int num = 1;
	if (num == 1) {
		out.print("1です！");
	} else if (num == 2) {
		out.print("プログラミングキャンプ！");
	} else {
	 	out.print("その他です！");
	}
%>