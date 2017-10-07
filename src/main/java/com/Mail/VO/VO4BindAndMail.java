package com.Mail.VO;

/**
 * Created by Lhy on 2017/5/10 0010.
 */
public class VO4BindAndMail {
    private Integer bind_id;
    private Integer user_id;
    private Integer user_mail;
    private String mail_acc;
    private String mail_auth;
    private String mail_smtp;
    private String mail_pop3;
    private String mail_pop3_port;
    private String mail_smtp_port;
    private String mail_name;

    public String getMail_name() {
        return mail_name;
    }

    public Integer getBind_id() {
        return bind_id;
    }

    public void setBind_id(Integer bind_id) {
        this.bind_id = bind_id;
    }

    public void setMail_name(String mail_name) {
        this.mail_name = mail_name;
    }

    public String getMail_smtp() {
        return mail_smtp;
    }

    public void setMail_smtp(String mail_smtp) {
        this.mail_smtp = mail_smtp;
    }

    public String getMail_pop3() {
        return mail_pop3;
    }

    public void setMail_pop3(String mail_pop3) {
        this.mail_pop3 = mail_pop3;
    }

    public String getMail_pop3_port() {
        return mail_pop3_port;
    }

    public void setMail_pop3_port(String mail_pop3_port) {
        this.mail_pop3_port = mail_pop3_port;
    }

    public String getMail_smtp_port() {
        return mail_smtp_port;
    }

    public void setMail_smtp_port(String mail_smtp_port) {
        this.mail_smtp_port = mail_smtp_port;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(Integer user_mail) {
        this.user_mail = user_mail;
    }

    public String getMail_acc() {
        return mail_acc;
    }

    public void setMail_acc(String mail_acc) {
        this.mail_acc = mail_acc;
    }

    public String getMail_auth() {
        return mail_auth;
    }

    public void setMail_auth(String mail_auth) {
        this.mail_auth = mail_auth;
    }
}
