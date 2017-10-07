package com.Mail.Service;

import com.Mail.Util.SendMailUtil;
import com.Mail.VO.Sendinfo;
import com.Mail.VO.VO4BindAndMail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMail {

    private Properties props;// 系统属性
    private Session mailSession;// 邮件会话对象
    private MimeMessage mimeMsg; // MIME邮件对象

    //把绑定邮箱的信息传进来初始化
    public SendMail(VO4BindAndMail vo4BindAndMail) {
        Auth au = new Auth(vo4BindAndMail.getMail_acc(), vo4BindAndMail.getMail_auth());
        // 设置系统属性
        props = System.getProperties(); // 获得系统属性对象
        props.put("mail.smtp.host", vo4BindAndMail.getMail_smtp()); // 设置SMTP主机
        props.put("mail.smtp.port", vo4BindAndMail.getMail_smtp_port()); // 设置服务端口号
        props.put("mail.smtp.auth", "true");// 同时通过验证
        mailSession = Session.getInstance(props, au);
        // 获得邮件会话对象
    }


    public boolean sendMailService(Sendinfo send){



        //session.setDebug(true);
        mimeMsg = new MimeMessage(mailSession);


			/*创建并装配邮件*/

        try {
            mimeMsg.setFrom(new InternetAddress(send.getEmail_from()));
            mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(send.getEmail_recipientTo()));


            Multipart multipart = new MimeMultipart("mixed"); //创建复杂的邮件(必须假设其为复杂邮件)

            //装配附件
            if(send.getEmail_attachments().size()!=0){//通过list判断有无添加附件
                multipart = SendMailUtil.addAttachment(multipart, send.getEmail_attachments());
            }

            BodyPart contentBodyPart = new MimeBodyPart();
            contentBodyPart.setContent(send.getEmail_content(), "text/html;charset=UTF-8");

            multipart.addBodyPart(contentBodyPart);//把正文加入到复杂部件中



            mimeMsg.setSubject(send.getEmail_subject() ,"gb2312");

            mimeMsg.setContent(multipart); //把复杂部件组装到邮件中

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

			/*发送邮件*/
        try {
            Transport.send(mimeMsg);
        } catch (MessagingException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }








}

