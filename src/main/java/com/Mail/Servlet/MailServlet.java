package com.Mail.Servlet;

import com.Mail.DTO.TMail;
import com.Mail.Service.ActionService;
import com.Mail.Service.MailService;
import com.Mail.Service.UserService;
import com.Mail.VO.VO4BindAndMail;
import com.Mail.VO.VO4BindRtn;
import com.Mail.VO.VO4Count;
import com.Mail.VO.VO4User;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Lhy on 2017/5/10 0010.
 */
public class MailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
       System.out.print(action);
        if("bind".equals(action)){

            bindMail(req,resp);


        }
        else if("queryBind".equals(action)){

            queryBind(req,resp);

        }
        else if("deleteBind".equals(action)) {

            deleteBind(req, resp);
        }else if("page".equals(action)){

            page(req,resp);

        }else if("page_pre".equals(action)){

            page_pre(req,resp);

        }else if("page_next".equals(action)){

            page_next(req,resp);


        }else if("admindeletebind".equals(action)){

            admindeletebind(req,resp);

        }else if("countbind".equals(action)){

            countbind(req,resp);


        }

    }

    private void countbind(HttpServletRequest req, HttpServletResponse resp) {
        MailService mailService = new MailService();
        resp.setContentType("text/html; charset=utf-8");
         JSONObject array = new JSONObject();
       array = mailService.countBind();
        try {
        System.out.println(array.toString());
        PrintWriter out = resp.getWriter();
        out.println(array);                   //把json返回到界面
        out.flush();
        out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void admindeletebind(HttpServletRequest req, HttpServletResponse resp) {

        Integer bind_id = Integer.parseInt(req.getParameter("bind_id"));
        MailService mailService = new MailService();
       String flag = mailService.deleteBind(bind_id);
        if("0".equals(flag)){

            try {
                page(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void page_next(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PageInfo<VO4User> page = (PageInfo<VO4User>) session.getAttribute("bindpage");
        if(page!=null){
            page.setPageNum(page.getNextPage());
            session.setAttribute("bindpage",page);




        }
        try {
            page(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void page_pre(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PageInfo<VO4User> page = (PageInfo<VO4User>) session.getAttribute("bindpage");

        if(page!=null){
            page.setPageNum(page.getPrePage());
            session.setAttribute("bindpage",page);




        }
        try {
            page(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void page(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        //判断第一次查看分页
        if(session.getAttribute("bindpage")==null||session.getAttribute("bindlist")==null){
            Map<String,Object> map = new HashMap<String,Object>();
            PageInfo<VO4BindRtn> page = new PageInfo<VO4BindRtn>();
            page.setPageNum(1);
            page.setPageSize(3);


            List<VO4BindRtn> binds = new ArrayList<VO4BindRtn>();
            map = new MailService().queryAllBind(page);

            page = (PageInfo<VO4BindRtn>) map.get("bindpage");

            binds = (List<VO4BindRtn>) map.get("bindlist");

            session.setAttribute("bindpage",page);
            session.setAttribute("bindlist",binds);

        }

        PageInfo<VO4BindRtn> page = (PageInfo<VO4BindRtn>) session.getAttribute("bindpage");
        List<VO4BindRtn> binds = new ArrayList<VO4BindRtn>();
        Map<String,Object> map = new HashMap<String,Object>();
        map = new MailService().queryAllBind(page);
        page = (PageInfo<VO4BindRtn>) map.get("bindpage");
        binds = (List<VO4BindRtn>) map.get("bindlist");

        session.setAttribute("bindpage",page);
        session.setAttribute("bindlist",binds);

        resp.sendRedirect(req.getContextPath()+"/adminbindlist.jsp");


    }

    private void deleteBind(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer bind_id = Integer.parseInt(req.getParameter("bind_id"));
        System.out.print(bind_id);
        MailService mailService = new MailService();
        String flag = mailService.deleteBind(bind_id);

        HttpSession session = req.getSession();
        Integer user_id =(Integer) session.getAttribute("user_id");

        List<VO4BindAndMail> bindlist = mailService.queryBindMailByUserId(user_id);
        session.setAttribute("bindlist",bindlist);

        if("0".equals(flag)){
            new ActionService().deleteaction((Integer) session.getAttribute("user_id"),"删除邮箱绑定");
            resp.sendRedirect(req.getContextPath()+"/bindlist.jsp");

        }



    }

    private void queryBind(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        Integer user_id = (Integer) session.getAttribute("user_id");
        List<VO4BindAndMail>  bindlist = new MailService().queryBindMailByUserId(user_id);
        session.setAttribute("bindlist",bindlist);
        new ActionService().queryaction((Integer) session.getAttribute("user_id"),"查询邮箱绑定");
        resp.sendRedirect(req.getContextPath()+"/bindlist.jsp");





    }

    private void bindMail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        Integer user_id = (Integer) session.getAttribute("user_id");
        String mail_acc = req.getParameter("mail_acc");
        Integer mail_id = Integer.parseInt(req.getParameter("mail_id"));
        String mail_auth = req.getParameter("mail_auth");
        System.out.print(mail_id);

//        String  emailcheck = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
//        Pattern pattern = Pattern.compile(emailcheck);
//        Matcher matcher = pattern.matcher(mail_acc);
        if(mail_id==null)
        {
            out.print("请选择一个邮箱服务器！");
            return;
        }
//        else if(mail_acc==""||mail_acc==null|| !matcher.matches()){
//
//            out.print("请输入有效的绑定帐号！");
//            return;
//
//
//        }
        else if(mail_auth==""||mail_auth==null){

            out.print("请输入有效的授权码！");
            return;
        }

        MailService mailService = new MailService();


        VO4BindAndMail mail = new VO4BindAndMail();
        mail.setMail_acc(mail_acc);
        mail.setUser_id(user_id);
        mail.setUser_mail(mail_id);
        mail.setMail_auth(mail_auth);
        mail.setMail_pop3(mailService.queryMailByMailId(mail_id).getMail_pop3());
        mail.setMail_pop3_port(mailService.queryMailByMailId(mail_id).getMail_pop3_port());
        System.out.print(mail.getUser_mail());


        List<VO4BindAndMail> bindlist = new ArrayList<VO4BindAndMail>();
        bindlist = mailService.queryBindMailByUserId(user_id);
        String flag = mailService.BindMail(mail);

        session.setAttribute("bindlist",bindlist);
        if("0".equals(flag)){
        out.write("绑定成功!");
            new ActionService().insertaction((Integer) session.getAttribute("user_id"),"绑定邮箱");

        }else if("2".equals(flag)){
            out.write("无此邮箱!");
            new ActionService().queryaction((Integer) session.getAttribute("user_id"),"绑定邮箱失败");

        }
        else {
            new ActionService().queryaction((Integer) session.getAttribute("user_id"),"绑定邮箱失败");
            out.write("绑定失败!");
        }

    }
}
