<%@page import="java.util.ArrayList"%>
<%@page import="jums.MySessionNames"%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	UserDataBeans userData = (UserDataBeans)session.getAttribute(
			MySessionNames.ResultDataUD);
	Integer id = (Integer)session.getAttribute("userID");
	ArrayList<String> chkList = userData.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS変更画面</title>
        <link rel="stylesheet" type="text/css" href="css/default.css">
    </head>
    <body>
	<h1>ユーザー情報更新</h1>

    <form action="UpdateResult" method="POST">
    <table class="simple">
	    <tr>
	    	<td>名前<br>
	    		<font color="#ff0000"><%=jh.chkinputName(chkList) %></font>
	    	</td>
	    	<td><input type="text" class="shadow" name="name" value=<%=userData.getName() %>></td>
	    </tr>

	    <tr>
	    	<td>生年月日<br>
	    		<font color="#ff0000"><%=jh.chkinputYear(chkList) %></font>
	    		<font color="#ff0000"><%=jh.chkinputMonth(chkList) %></font>
	    		<font color="#ff0000"><%=jh.chkinputDay(chkList) %></font>
	    	</td>
		    <td>
	    	<select name="year" class="shadow">
	            <option value="0">----</option>
	            <% for(int i=1950; i<=2010; i++){ %>
	            <option value="<%=i%>" <%=jh.chkSelected(i, userData.getYear()) %>><%=i%></option>
	            <% } %>
	        </select> 年
	        <select name="month" class="shadow">
	            <option value="0">--</option>
	            <% for(int i = 1; i<=12; i++){ %>
	            <option value="<%=i%>" <%=jh.chkSelected(i, userData.getMonth()) %>><%=i%></option>
	            <% } %>
	        </select> 月
	        <select name="day" class="shadow">
	            <option value="0">--</option>
	            <% for(int i = 1; i<=31; i++){ %>
	            <option value="<%=i%>" <%=jh.chkSelected(i, userData.getDay()) %>><%=i%></option>
	            <% } %>
	        </select> 日</td>
	    </tr>

	    <tr>
	    	<td>種別<br>
	    		<font color="#ff0000"><%=jh.chkinputType(chkList) %></font>
	    	</td>
	    	<td>
	    	<% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>" <%=
            	jh.chkChecked(i, userData.getType())
            %>><%=jh.exTypenum(i)%><br>
            <% } %>
	    	</td>
	    </tr>
	    <tr>
	    	<td>電話番号<br>
	    		<font color="#ff0000"><%=jh.chkinputTell(chkList) %></font>
	    	</td>
	    	<td><input type="text" class="shadow" name="tell" value="<%=userData.getTell()%>"></td>
	    </tr>
	    <tr>
	    	<td>自己紹介文<br>
	    		<font color="#ff0000"><%=jh.chkinputComment(chkList) %></font>
	    	</td>
	    	<td>
	    	<textarea name="comment" class="shadow" rows=10 cols=50 wrap="hard"><%=
        		userData.getComment()
        	%></textarea>
	    	</td>
	    </tr>
    </table>
        <br><br>

        <input type="submit" name="btnSubmit" value="送信">
        <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
    </form>

    <form action="ResultDetail" method="POST">
    	<input type="submit" name="btnSubmit" value="詳細画面へ戻る">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
