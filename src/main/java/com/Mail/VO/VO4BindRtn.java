package com.Mail.VO;

/**
 * Created by Lhy on 2017/5/14 0014.
 */
public class VO4BindRtn {
    private Integer id;
    private String username;
    private Integer userId;
    private Integer userMail;
    private String mailAcc;
    private String mailName;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserMail() {
        return userMail;
    }

    public void setUserMail(Integer userMail) {
        this.userMail = userMail;
    }

    public String getMailAcc() {
        return mailAcc;
    }

    public void setMailAcc(String mailAcc) {
        this.mailAcc = mailAcc;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }
}
