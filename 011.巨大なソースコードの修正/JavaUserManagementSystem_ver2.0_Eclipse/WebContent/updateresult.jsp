<%@page import="jums.MySessionNames"%>
<%@page import="jums.UserDataDTO"%>
<%@page import="jums.JumsHelper" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
	UserDataDTO udd = (UserDataDTO)session.getAttribute(
			MySessionNames.ResultDataDTO);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
        <link rel="stylesheet" type="text/css" href="css/default.css">
    </head>
    <body>
    	<table class="simple">
        	<tr><td>名前</td><td><%= udd.getName()%><td></tr>
        	<tr><td>生年月日</td><td><%=jh.birthdayFormat(udd.getBirthday())%><td></tr>
        	<tr><td>種別</td><td><%=jh.exTypenum(udd.getType())%><td></tr>
        	<tr><td>電話番号</td><td><%= udd.getTell()%><td></tr>
        	<tr><td>自己紹介</td><td><%= jh.exBrTagFromRN(udd.getComment())%><td></tr>
        	<tr><td>登録日時</td><td><%= udd.getNewDate()%><td></tr>
		</table><br>
		以上の内容で登録しました。<br>

	    <form action="ResultDetail" method="POST">
	    	<input type="submit" name="back" value="詳細画面へ戻る">
	    </form>
	    <br>
	    <%=jh.home()%>
    </body>
</html>
