<%@page import="jums.MySessionNames"%>
<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" %>
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
        <title>削除確認画面</title>
        <link rel="stylesheet" type="text/css" href="css/default.css">
    </head>
    <body>
    <h1>削除確認</h1>
    	<font color="#ff0000">以下の内容を削除します。よろしいですか？</font>
	<table class="simple">
       	<tr><td>名前</td><td><%= udd.getName()%><td></tr>
       	<tr><td>生年月日</td><td><%=jh.birthdayFormat(udd.getBirthday())%><td></tr>
       	<tr><td>種別</td><td><%=jh.exTypenum(udd.getType())%><td></tr>
       	<tr><td>電話番号</td><td><%= udd.getTell()%><td></tr>
       	<tr><td>自己紹介</td><td><%= jh.exBrTagFromRN(udd.getComment())%><td></tr>
       	<tr><td>登録日時</td><td><%= udd.getNewDate()%><td></tr>
	</table><br>
    <form action="DeleteResult" method="POST">
      <input type="submit" name="YES" value="はい"style="width:100px">
      <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
    </form><br>
    <form action="ResultDetail" method="POST">
      <input type="submit" name="NO" value="いいえ"style="width:100px">
    </form>


    </body>
</html>
