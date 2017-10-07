package com.Mail.Servlet;
import java.io.IOException;
import java.io.InputStream;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Created by Lhy on 2017/4/29 0029.
 */
public class HandleAttact extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        ServletOutputStream out = response.getOutputStream();

        int msgnum = Integer.parseInt(request.getParameter("msgnum"));
        int bodynum = Integer.parseInt(request.getParameter("bodynum"));
        String filename = request.getParameter("filename");
        Folder folder = (Folder) session.getAttribute("folder");

        try {
            Message msg = folder.getMessage(msgnum);

            // 将消息头类型设置为附件类型
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);

            Multipart multi = (Multipart) msg.getContent();
            BodyPart bodyPart = multi.getBodyPart(bodynum);

            InputStream is = bodyPart.getInputStream();
            int c = 0;
            while ((c = is.read()) != -1) {
                out.write(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
