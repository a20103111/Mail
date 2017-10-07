<%--
  Created by IntelliJ IDEA.
  User: Lhy
  Date: 2017/4/29 0029
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

     <iframe src="DisplayHead?msgnum=<%=request.getParameter("msgnum")%>" scrolling="no"></iframe>
    <!-- <iframe src="DisplayContent?msgnum=<%=request.getParameter("msgnum")%>" scrolling="no"></iframe> -->

</body>
</html>
