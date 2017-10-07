package com.Mail.Util;

import com.Mail.VO.VO4BindAndMail;

import java.util.Properties;

import javax.mail.*;

public class POP3Help {
    public static Folder getFolder(VO4BindAndMail vo4BindAndMail) {
        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol","pop3");
        prop.setProperty("mail.pop3.host",vo4BindAndMail.getMail_pop3());
        prop.setProperty("mail.pop3.port",vo4BindAndMail.getMail_pop3_port());

        Session mailSession = Session.getInstance(prop);
        mailSession.setDebug(true);

        try {
            Store store = mailSession.getStore("pop3");
            store.connect(vo4BindAndMail.getMail_pop3(),vo4BindAndMail.getMail_acc(),vo4BindAndMail.getMail_auth());

            //获取收件箱
            Folder folder = store.getFolder("INBOX");
            //返回收件箱对象
            folder.open(Folder.READ_WRITE);
            return folder;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public static boolean isConnect(VO4BindAndMail vo4BindAndMail){
        boolean flag = false;

        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol","pop3");
        prop.setProperty("mail.pop3.host",vo4BindAndMail.getMail_pop3());
        prop.setProperty("mail.pop3.port",vo4BindAndMail.getMail_pop3_port());


//        Properties properties = new Properties();
//        properties.put("mail.store.protocol", "pop3");
//        properties.put("mail.pop3.host", vo4BindAndMail.getMail_pop3());
//        properties.put("mail.pop3.port", "995");
//        properties.put("mail.pop3.starttls.enable", "true");
        Session mailSession = Session.getInstance(prop);
        mailSession.setDebug(true);

        try {
            Store store = mailSession.getStore("pop3");
            store.connect(vo4BindAndMail.getMail_pop3(),vo4BindAndMail.getMail_acc(),vo4BindAndMail.getMail_auth() );
            flag = store.isConnected();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    public static void deleteMail(VO4BindAndMail vo4BindAndMail,Integer message_id) {
        Properties prop = new Properties();
        prop.setProperty("mail.store.protocol","pop3");
        prop.setProperty("mail.pop3.host",vo4BindAndMail.getMail_pop3());
        prop.setProperty("mail.pop3.port",vo4BindAndMail.getMail_pop3_port());

        Session mailSession = Session.getInstance(prop);
        mailSession.setDebug(true);

        try {
            Store store = mailSession.getStore("pop3");
            store.connect(vo4BindAndMail.getMail_pop3(),vo4BindAndMail.getMail_acc(),vo4BindAndMail.getMail_auth());

            //获取收件箱
            Folder folder = store.getFolder("INBOX");
            //返回收件箱对象
            folder.open(Folder.READ_WRITE);

            Message message = folder.getMessage(message_id);
            message.setFlag(Flags.Flag.DELETED, true);//设置已删除状态为true
            if(message.isSet(Flags.Flag.DELETED))
                System.out.println("已经删除第"+message_id+"邮件。。。。。。。。。");

        folder.close(true);
        store.close();
            System.out.println("delete ok.................................");
        } catch (NoSuchProviderException e1) {
            e1.printStackTrace();
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }





    }




}
