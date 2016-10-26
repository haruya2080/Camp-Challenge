<%@page import="jums.UserDataBeans"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.JumsHelper" %>
<%
	HttpSession hs = request.getSession(true);
	UserDataBeans userData = (UserDataBeans)hs.getAttribute("user_data");

	// userDataがnullのとき、userDataを新しく作成し、空ユーザー情報とする
	if (userData == null) {
		userData = new UserDataBeans(true);
	}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
    </head>
    <body>
    <form action="insertconfirm" method="POST">
        名前:
        <input type="text" name="name" value=<%=userData.getName() %>><br>
        <font color="#ff0000">
        	<%=userData.getMessage_InvalidName() %>
        </font><br>
        生年月日:
        <select name="year">
            <option value="-1">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            	<option value="<%=i%>" <%=userData.getSelectTag4Year(i)%>><%=i%></option>
            <% } %>
        </select>年
        <select name="month">
            <option value="-1">--</option>
            <% for(int i = 1; i<=12; i++){ %>
            	<option value="<%=i%>" <%=userData.getSelectTag4Month(i)%>><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="-1">--</option>
            <% for(int i = 1; i<=31; i++){ %>
            	<option value="<%=i%>" <%=userData.getSelectTag4Day(i)%>><%=i%></option>
            <% } %>
        </select>日
        <br>

       	<font color="#ff0000">
       		<%=userData.getMessage_InvalidBirthday() %>
       	</font><br>

        種別:
        <br>
        <input type="radio" name="type" value="1" <%=userData.getCheckTag4Type(1) %>>エンジニア<br>
        <input type="radio" name="type" value="2" <%=userData.getCheckTag4Type(2) %>>営業<br>
        <input type="radio" name="type" value="3" <%=userData.getCheckTag4Type(3) %>>その他<br>
       	<font color="#ff0000">
       		<%=userData.getMessage_InvalidType() %>
       	</font><br>

        電話番号:
        <input type="text" name="tell" value=<%=userData.getTell() %>><br>
       	<font color="#ff0000">
       		<%=userData.getMessage_InvalidTell() %>
       	</font><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%=userData.getComment() %></textarea>
        <br>
        <font color="#ff0000">
        	<%=userData.getMessage_InvalidComment() %>
        </font><br>

        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
