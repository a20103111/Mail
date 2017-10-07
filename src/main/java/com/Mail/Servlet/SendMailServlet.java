package com.Mail.Servlet;

import com.Mail.Service.ActionService;
import com.Mail.Service.MailService;
import com.Mail.Service.SendMail;
import com.Mail.Util.HandleReceivedMail;
import com.Mail.Util.FileUploadUtil;
import com.Mail.VO.Sendinfo;
import com.Mail.VO.VO4BindAndMail;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lhy on 2017/5/12 0012.
 */
public class SendMailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.print(action);
        if("send".equals(action)){

            sendmail(req,resp);
        }
        if("reply".equals(action)){

            reply(req,resp);


        }




    }

    private void reply(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mess_id = req.getParameter("mess_id");
        HttpSession session = req.getSession();
        Message[] messages = (Message[]) session.getAttribute("messages");
        Integer bindid= (Integer) session.getAttribute("bindid");
        HandleReceivedMail handleOneMail = new HandleReceivedMail((MimeMessage) messages[Integer.parseInt(mess_id)]);

       MailService mailService = new MailService();
        VO4BindAndMail vo4BindAndMail = mailService.querySingleBind(bindid);
        String subject = "回复："+handleOneMail.getSubject();
        String from = handleOneMail.getOnlyFrom();
        String date = handleOneMail.getSendDate();


        req.setAttribute("subject",subject);
        req.setAttribute("from",from);
        req.setAttribute("date",date);
        req.setAttribute("send",vo4BindAndMail.getMail_acc());

        req.getRequestDispatcher("reply_single.jsp").forward(req,resp);
    }


    private void sendmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

		String realWebBase = this.getServletContext().getRealPath("/");

        MailService mailService = new MailService();
        //由于表单邮件，不能直接解析表单内容，需要通过commons工具解析
        Map<String, Object> information =  FileUploadUtil.saveAttachments(realWebBase, req, resp);
        //从工具类中返回表单内容的Map集合
        String email_subject =(String)information.get("email_subject");
        String email_content = (String)information.get("email_content");
        String email_recipientTo = (String)information.get("email_recipientTo");
        Integer email_from = Integer.parseInt((String)information.get("email_from"));

        ArrayList<String> email_attachments = (ArrayList<String>)information.get("attachments");
        //根据选择的邮箱id来创建相应的内容
        VO4BindAndMail vomail = new VO4BindAndMail();
        vomail = mailService.querySingleBind(email_from);
        vomail.setMail_smtp(mailService.queryMailByMailId(vomail.getUser_mail()).getMail_smtp());
        vomail.setMail_smtp_port(mailService.queryMailByMailId(vomail.getUser_mail()).getMail_smtp_port());
        System.out.println("邮件主题:"+email_subject);
        System.out.println("邮件内容:"+email_content);
        System.out.println("收件人:"+email_recipientTo);
        System.out.println("附件数量:"+email_attachments.size());

        //封装发送类，把要发送的邮件内容封装起来
        Sendinfo sendinfo = new Sendinfo();
        sendinfo.setEmail_subject(email_subject);
        sendinfo.setEmail_attachments(email_attachments);
        sendinfo.setEmail_from(vomail.getMail_acc());
        sendinfo.setEmail_content(email_content);
        sendinfo.setEmail_recipientTo(email_recipientTo);
        SendMail mail = new SendMail(vomail);
        Boolean flag = mail.sendMailService(sendinfo);



        //构建邮件信息


        if(flag){
             new ActionService().queryaction((Integer) session.getAttribute("user_id"),"发送邮件");
            resp.sendRedirect(req.getContextPath()+"/msg.jsp");
        }
    }


}
