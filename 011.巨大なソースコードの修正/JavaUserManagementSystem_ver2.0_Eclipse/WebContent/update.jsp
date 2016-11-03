<%@page import="jums.MySessionNames"%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.UserDataBeans"%>
<%@page import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	UserDataBeans userData = (UserDataBeans)session.getAttribute(
			MySessionNames.ResultDataUD);
	Integer id = (Integer)session.getAttribute("userID");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS変更画面</title>
    </head>
    <body>
    <form action="UpdateResult" method="POST">
        名前:
        <input type="text" name="name" value=<%=userData.getName() %>>
        <br><br>

        生年月日:　
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <%=jh.chkSelected(i, userData.getYear()) %>><%=i%></option>
            <% } %>
        </select>年
        <select name="month">
            <option value="">--</option>
            <% for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>" <%=jh.chkSelected(i, userData.getMonth()) %>><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="">--</option>
            <% for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>" <%=jh.chkSelected(i, userData.getDay()) %>><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>" <%=
            	jh.chkChecked(i, userData.getType())
            %>><%=jh.exTypenum(i)%><br>
            <% } %>
        <br>

        電話番号:
        <input type="text" name="tell" value="<%=userData.getTell()%>">
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%=
        	userData.getComment()
        %></textarea><br><br>

        <input type="submit" name="btnSubmit" value="送信">
    </form>

    <form action="ResultDetail" method="GET">
    	<input type="submit" name="btnSubmit" value="詳細画面へ戻る">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
