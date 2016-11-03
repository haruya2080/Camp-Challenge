<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserDataBeans" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataBeans udb = null;
    boolean reinput = false;
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        reinput = true;
        udb = (UserDataBeans)hs.getAttribute("udb");
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
        <link rel="stylesheet" type="text/css" href="css/default.css">
    </head>
    <body>
    <h1>ユーザー情報登録</h1>
    <form action="insertconfirm" method="POST">
    <table class="simple">
    	<tr>
	    	<td>名前</td>
	    	<td>
	    		<input type="text" name="name" value="<% if(reinput){out.print(udb.getName());}%>">
	    	</td>
    	</tr>

    	<tr>
	    	<td>生年月日</td>
	    	<td>
	    		<select name="year">
		            <option value="">----</option>
		            <% for(int i=1950; i<=2010; i++){ %>
		            <option value="<%=i%>" <% if(reinput && udb.getYear() == i){out.print("selected = \"selected\"");}%>><%=i%></option>
		            <% } %>
		        </select>年
		        <select name="month">
		            <option value="">--</option>
		            <% for(int i = 1; i<=12; i++){ %>
		            <option value="<%=i%>" <% if(reinput && udb.getMonth() == i){out.print("selected = \"selected\"");}%>><%=i%></option>
		            <% } %>
		        </select>月
		        <select name="day">
		            <option value="">--</option>
		            <% for(int i = 1; i<=31; i++){ %>
		            <option value="<%=i%>"<% if(reinput && udb.getDay() == i){out.print("selected = \"selected\"");}%>><%=i%></option>
		            <% } %>
		        </select>日
	    	</td>
    	</tr>

    	<tr>
	    	<td>種別</td>
	    	<td>
				<% for(int i = 1; i<=3; i++){ %>
	            <input type="radio" name="type" value="<%=i%>"<%if(reinput && udb.getType() == i){out.print("checked = \"checked\"");}%>><%=jh.exTypenum(i)%><br>
	            <% } %>
	    	</td>
    	</tr>

    	<tr>
	    	<td>電話番号</td>
	    	<td>
				<input type="text" name="tell" value="<% if(reinput){out.print(udb.getTell());}%>">
	    	</td>
    	</tr>

    	<tr>
	    	<td>自己紹介</td>
	    	<td>
				<textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%
					if(reinput){out.print(udb.getComment());}
				%></textarea><br><br>
	    	</td>
    	</tr>
    </table>

        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
