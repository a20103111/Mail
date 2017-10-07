package com.Mail.VO;

/**
 * Created by Lhy on 2017/5/7 0007.
 */
public class VO4MailRtn {
    private Integer mail_number;
    private String mail_from;
    private String mail_subject;
    private String mail_date;
    private String mail_content;




    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public void setMail_subject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    public String getMail_date() {
        return mail_date;
    }

    public void setMail_date(String mail_date) {
        this.mail_date = mail_date;
    }

    public String getMail_content() {
        return mail_content;
    }

    public void setMail_content(String mail_content) {
        this.mail_content = mail_content;
    }

    public Integer getMail_number() {
        return mail_number;
    }

    public void setMail_number(Integer mail_number) {
        this.mail_number = mail_number;
    }
}
