package com.Mail.Util;

import com.sun.deploy.net.URLEncoder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lhy on 2017/5/4 0004.
 */
public class HandleReceivedMail {
    StringBuilder bodytext;
    MimeMessage  mimeMessage =null;

    public HandleReceivedMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }
    //解决编码问题
    public String changeEncode(String str){
        try {
            if (str == null || "".equals(str))
                str = "";
            else
                str = MimeUtility.decodeText(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getSubject(){
        String subject = "";
        try {
            subject = changeEncode(mimeMessage.getSubject());
            if(subject == null)
                subject = "";
            return subject;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSendDate(){
        try {
            Date sendDate = mimeMessage.getSentDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(sendDate);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getFrom(){
        try {
            InternetAddress[] address = (InternetAddress[]) mimeMessage.getFrom();
            String person = address[0].getPersonal();  //姓名
            if(person == null)
                person = "";
            String from = address[0].getAddress();  //地址
            if(from == null)
                from = "";
            return person+"["+from+"]";
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    public String getOnlyFrom(){
        try {
            InternetAddress[] address = (InternetAddress[]) mimeMessage.getFrom();


            String from = address[0].getAddress();  //地址
            if(from == null)
                from = "";
            return from;
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public String getMessageID(){
        try {
            return mimeMessage.getMessageID();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void getMailContent(Part part,StringBuilder bodytext){
        try {
            String contentType = part.getContentType();
            int nameIndex = contentType.indexOf("name");
            boolean isContainTextAttach = false;  //判断邮件中是否含有嵌套体
            if(nameIndex != -1)
                isContainTextAttach = true;
            if(part.isMimeType("text/plain") && !isContainTextAttach)
                bodytext.append((String)part.getContent());
            else if(part.isMimeType("text/html") && !isContainTextAttach)
                bodytext.append((String)part.getContent());  //对于HTML格式邮件，可以在前后加上标签<html><head>subject</head><body>bodytext</body></html>，保存为html格式文件（PS：未写）
            else if(part.isMimeType("message/rfc822"))
                getMailContent((Part) part.getContent(),bodytext);
            else if(part.isMimeType("multipart/*")){
                Multipart multipart = (Multipart) part.getContent();
                int count = multipart.getCount();
                for(int i=0;i<count;i++)
                    getMailContent(multipart.getBodyPart(i),bodytext);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getBodyText(){
        return bodytext.toString();
    }
    public  void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }

    /**
     * @param// 接卸包裹（含所有邮件内容(包裹+正文+附件)）
     * @throws Exception
     */
//    private void reMultipart(Multipart multipart,StringBuffer content) throws Exception {
//        //System.out.println("邮件共有" + multipart.getCount() + "部分组成");
//        // 依次处理各个部分
//        for (int j = 0, n = multipart.getCount(); j < n; j++) {
//            //System.out.println("处理第" + j + "部分");
//            Part part = multipart.getBodyPart(j);//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
//            // 也可能是另一个小包裹(MultipPart)
//            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
//            if (part.getContent() instanceof Multipart) {
//                Multipart p = (Multipart) part.getContent();// 转成小包裹
//                //递归迭代
//                reMultipart(p);
//            } else {
//                //rePart(part);
//            }
//        }
//    }
    public boolean isContainAttach(Part part){
        boolean attachflag = false;  //是否有附件
        try {
            String contentType = part.getContentType();
            if(part.isMimeType("multipart/*")){
                Multipart multipart = (Multipart) part.getContent();
                for(int i=0;i<multipart.getCount();i++){
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    String disposition = bodyPart.getDisposition();
                    if((disposition != null) && (disposition.equals(Part.ATTACHMENT) || disposition.equals(Part.INLINE)))
                        attachflag = true;
                    else if(bodyPart.isMimeType("multipart/*"))
                        attachflag = isContainAttach((Part)bodyPart);
                    else{
                        String bodytype = bodyPart.getContentType();
                        if(bodytype.toLowerCase().indexOf("application") !=-1)
                            attachflag = true;
                        if(bodytype.toLowerCase().indexOf("name") !=-1)
                            attachflag = true;
                    }

                }
            }
            else if(part.isMimeType("message/rfc822"))
                attachflag = isContainAttach((Part)part.getContent());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return attachflag;
    }
    public int getMsgnum(Message message){

        return message.getMessageNumber();

    }
    //返回附件连接
    public void getFile(Message message,StringBuffer file){
        try{
        if(message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            int bodyCounts = multipart.getCount();
            int msgnum = getMsgnum(message);
            for (int i = 0; i < bodyCounts; i++) {
                BodyPart bodypart = multipart.getBodyPart(i);
                // 如果该BodyPart对象包含附件，则应该解析出来
                if (bodypart.getDisposition() != null) {
                    String filename = bodypart.getFileName();
                    if (filename.startsWith("=?")) {
                        // 把文件名编码成符合RFC822规范
                        filename = MimeUtility.decodeText(filename);

                    }
                    // 生成打开附件的超链接
                    String url = java.net.URLEncoder.encode(filename,"utf-8");
                    file.append("<a href=HandleAttachments?msgnum="
                        + msgnum + "&&bodynum=" + i + ">" + filename + "</a></br>");
            }
            }
        }
    }catch(Exception e){
        e.printStackTrace();
    }


}





    }
