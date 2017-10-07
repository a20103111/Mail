package com.Mail.Service;

import com.Mail.Util.HandleReceivedMail;
import com.Mail.Util.PaginationUtil;
import com.Mail.VO.VO4MailRtn;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lhy on 2017/5/2 0002.
 */
public class ReceviceMail {

//    public Folder getMail(String username,String password) throws MessagingException {
//
//       Folder folder =  POP3Help.getFolder(username,password);
//
//        return folder;
//
//    }
//    public Message getSingleMail(String mailid,message) throws MessagingException {
//
//
//        Folder folder =  POP3Help.getFolder("13422083527@163.com","123123456q");
//        Message message = folder.getMessage(Integer.parseInt(mailid));
//        return message;
//
//
//
//
//
//    }

    public Message[] pageMessage(Message[] messages,int pageno,int pagesize){

        Message[] pageMessage = new Message[pagesize];

            for(int j=0;j<pagesize;j++){
                pageMessage[j] = messages[messages.length-1-(pageno*pagesize)-j];



            }
            return  pageMessage;


        }
    public List<VO4MailRtn> pageMail(PaginationUtil pu,Folder folder) throws MessagingException {

        int startrow = folder.getMessageCount()-pu.getPageEndRow();
        int endrow = folder.getMessageCount()-pu.getPageStartRow();
        Message[] messages = folder.getMessages(startrow,endrow);
        List<VO4MailRtn> list = new ArrayList<VO4MailRtn>();
        for(int i=messages.length-1;i>0;i--){
            HandleReceivedMail handleReceivedMail = new HandleReceivedMail((MimeMessage) messages[i]);
            VO4MailRtn vo = new VO4MailRtn();
            vo.setMail_number(handleReceivedMail.getMsgnum(messages[i-1]));
            vo.setMail_date(handleReceivedMail.getSendDate());
            vo.setMail_from(handleReceivedMail.getFrom());
            vo.setMail_subject(handleReceivedMail.getSubject());

            list.add(vo);

        }

            return list;
    }



}
