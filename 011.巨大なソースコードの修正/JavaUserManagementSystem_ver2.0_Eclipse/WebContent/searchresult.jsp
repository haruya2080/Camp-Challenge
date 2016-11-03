<%@page import="jums.MySessionNames"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	ArrayList<UserDataDTO> uddList
		= (ArrayList<UserDataDTO>)session.getAttribute(MySessionNames.ResultDatas);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
        <link rel="stylesheet" type="text/css" href="css/default.css">
    </head>
    <body>
        <h1>検索結果</h1>
        <table class="simple">
            <tr>
                <th>名前</th>
                <th>生年</th>
                <th>種別</th>
                <th>登録日時</th>
            </tr>

            <% for(UserDataDTO udd : uddList) {%>
            <tr>
                <td><a href="ResultDetail?id=<%= udd.getUserID() %>"><%= udd.getName()%></a></td>
                <td><%= jh.birthdayFormat(udd.getBirthday()) %></td>
                <td><%= jh.exTypenum(udd.getType()) %></td>
                <td><%= udd.getNewDate() %></td>
            </tr>
            <% } %>
        </table>
		<form action="Search" method="POST">
			<input type="submit" name="btnSubmit" value="検索フォームへ戻る">
		</form>
        <br>
    	<%=jh.home()%>
    </body>
</html>
