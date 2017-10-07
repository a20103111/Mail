<%@ page import="com.Mail.Service.ReceviceMail" %>
<%@ page import="javax.mail.Message" %>
<%@ page import="com.Mail.Util.HandleReceivedMail" %>
<%@ page import="javax.mail.internet.MimeMessage" %>
<%@ page import="javax.mail.Part"%>
<%@ page import="javax.mail.Folder" %>
<%@ page import="javax.mail.Flags" %>

<%--
  Created by IntelliJ IDEA.
  User: Lhy
  Date: 2017/5/5 0005
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css"
	media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/adminstyle.css"
	media="all">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/personal.css" media="all">

    <title>Title</title>


</head>
<body>
<%
    String mailid = request.getParameter("mailid");
   // ReceviceMail receviceMail = new ReceviceMail();
    //Message message = receviceMail.getSingleMail(mailid);
        Integer id = Integer.parseInt(mailid);
        Message[] messages = (Message[]) request.getSession().getAttribute("messages");
        HandleReceivedMail handleOneMail = new HandleReceivedMail((MimeMessage) messages[id]);
        messages[id].setFlag(Flags.Flag.SEEN, true);
        StringBuffer content = new StringBuffer();
    content.append("<html>");


%>
<section class="layui-larry-box">
    <div class="larry-personal" style="">

        <div class="larry-personal-body clearfix" >
            <form class="layui-form" action="" method="post">

                <div class="layui-form-item">
                <label class="layui-form-label">邮件主题</label>
                <div class="layui-input-block">
                    <input style="width: 90%;" type="text" name="username"  autocomplete="off" class="layui-input" value="<%=handleOneMail.getSubject()%>">
                </div>
            </div>
                <%

                if(handleOneMail.isContainAttach((Part)messages[id])){
                    StringBuffer file = new StringBuffer();
                    handleOneMail.getFile(messages[id],file);
                %>
                <div class="layui-form-item">
                    <label class="layui-form-label">附件：</label>
                    <div class="layui-input-block">
                       <%=file%>
                    </div>
                </div>
                <%}%>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮件内容</label>
                    <div class="layui-input-block">
                        <%handleOneMail.getMailTextContent((Part)messages[id],content);
                        content.append("</html>");
                        %>
                       <div style="width: 90%;padding-right: 10px;"><%=content.toString()%></div>

                    </div>
                </div>


            </form>
        </div>
    </div>
</section>




</body>
</html>
