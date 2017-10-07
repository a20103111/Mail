package com.Mail.Servlet;

import java.io.*;
import java.net.URLDecoder;
import javax.mail.*;
import javax.mail.internet.MimeUtility;
import javax.servlet.*;
import javax.servlet.http.*;

// 处理邮件中的附件
public class HandleAttachments extends HttpServlet
{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;");

        HttpSession session = request.getSession();

        int msgnum = Integer.parseInt(request.getParameter("msgnum"));
        int bodynum = Integer.parseInt(request.getParameter("bodynum"));
        Folder folder = (Folder)session.getAttribute("folder");
         ServletOutputStream out = response.getOutputStream();
        try{
            Message message = folder.getMessage(msgnum);


            Multipart multipart = (Multipart)message.getContent();
            BodyPart bodypart = multipart.getBodyPart(bodynum);
             String filename = bodypart.getFileName();


            // 将消息头类型设置为附件类型
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + filename);
            InputStream input = bodypart.getInputStream();

            int temp = 0;
            while((temp = input.read()) != -1)
            {
                out.write(temp);
            }
            input.close();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
