package com.Mail.VO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lhy on 2017/5/14 0014.
 */
public class VO4ActionRtn {
    private Integer id;
    private Integer userId;
    private String userAction;
    private Date date;
    private String username;

    public Integer getId() {
        return id;
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

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getDate()


    {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
