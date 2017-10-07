package com.Mail.Servlet;

import com.Mail.Service.ActionService;
import com.Mail.Service.MailService;
import com.Mail.Service.UserService;
import com.Mail.VO.VO4BindAndMail;
import com.Mail.VO.VO4User;
import com.Mail.VO.VO4UserLogin;
import com.Mail.VO.VO4UserReg;
import com.github.pagehelper.PageInfo;

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

/**
 * Created by Lhy on 2017/5/9 0009.
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.print(action);
        if("regist".equals(action)){
            reg(req,resp);
        }
        else if("login".equals(action)){
            login(req,resp);


        }
        else if("changpwd".equals(action)){

            changpwd(req,resp);

        }else if("quit".equals(action)){

            quit(req,resp);

        }else if("page".equals(action)){

            page(req,resp);

        }else if("page_pre".equals(action)){
            
            page_pre(req,resp);
            
        }else if("page_next".equals(action)){
            
            page_next(req,resp);
            
            
        }else if("delete".equals(action)){

            delete(req,resp);

        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        Integer user_id = Integer.parseInt(req.getParameter("user_id"));
        UserService userService  = new UserService();
        String flag = userService.deleteUserAndOtherByUserId(user_id);
        if ("0".equals(flag)){


            try {
                page(req, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void page_next(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PageInfo<VO4User> page = (PageInfo<VO4User>) session.getAttribute("pageinfo");
        if(page!=null){
            page.setPageNum(page.getNextPage());
            session.setAttribute("pageinfo",page);




        }
        try {
            page(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void page_pre(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PageInfo<VO4User> page = (PageInfo<VO4User>) session.getAttribute("pageinfo");

        if(page!=null){
            page.setPageNum(page.getPrePage());
            session.setAttribute("pageinfo",page);




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
        if(session.getAttribute("pageinfo")==null||session.getAttribute("userlist")==null){
            Map<String,Object> map = new HashMap<String,Object>();
            PageInfo<VO4User> page = new PageInfo<VO4User>();
            page.setPageNum(1);
            page.setPageSize(5);


            List<VO4User> users = new ArrayList<VO4User>();
            map = new UserService().queryAllUser(page);

            page = (PageInfo<VO4User>) map.get("pageinfo");

            users = (List<VO4User>) map.get("userlist");

            session.setAttribute("pageinfo",page);
            session.setAttribute("userlist",users);

        }

        PageInfo<VO4User> page = (PageInfo<VO4User>) session.getAttribute("pageinfo");
        List<VO4User> users = new ArrayList<VO4User>();
        Map<String,Object> map = new HashMap<String,Object>();
        map = new UserService().queryAllUser(page);
        page = (PageInfo<VO4User>) map.get("pageinfo");
        users = (List<VO4User>) map.get("userlist");

        session.setAttribute("pageinfo",page);
        session.setAttribute("userlist",users);

        resp.sendRedirect(req.getContextPath()+"/userlist.jsp");


    }

    private void quit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);//防止创建Session
        if(session == null){
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
            return;
        }

        session.removeAttribute("user_id");
        session.removeAttribute("folder");
        session.removeAttribute("messages");
        session.removeAttribute("count");
        session.removeAttribute("username");
        resp.sendRedirect(req.getContextPath()+"/login.jsp");


    }

    private void changpwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = (String) req.getSession().getAttribute("username");
        String oldpwd = req.getParameter("oldpwd");
        String newpwd=req.getParameter("newpwd");
        String newpwdre = req.getParameter("newpwdre");
        PrintWriter out = resp.getWriter();
System.out.print(username);

    if("".equals(oldpwd)||oldpwd==null){

        out.print("请输入旧密码");
        return;
    }
    else if("".equals(newpwd)||newpwd==null){

        out.print("请输入新密码");
        return;
    }
    else if("".equals(newpwdre)||newpwdre==null) {

        out.print("请输入重复密码");
        return;
    }
    else if(newpwd.equals(oldpwd)){
        out.print("新旧密码不能相同！");
        return;


    }
        else if(!newpwd.equals(newpwdre))
    {
        out.print("两次密码不同！");
        return;

    }


        UserService userService = new UserService();
       String flag = userService.changpwd(oldpwd,newpwd,username);
        if("0".equals(flag)){
            new ActionService().updateaction((Integer)req.getSession().getAttribute("user_id"),"更改密码成功");
            out.print("更改成功！");
        }
        else{
            new ActionService().updateaction((Integer)req.getSession().getAttribute("user_id"),"更改密码失败");
            out.print("更改失败！");
        }

    }

    //登录方法
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserService userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String randomCode = req.getParameter("randomCode");
        resp.setCharacterEncoding("utf-8");
        String code= (String) req.getSession().getAttribute("randomCode");



//        if("".equals(username)||username==null){
//
//
//          resp.sendRedirect(req.getContextPath()+"/login.jsp");
//
//        }
//         if("".equals(password)||password==null){
//
//
//            resp.sendRedirect(req.getContextPath()+"/login.jsp");
//
//        }
//         if("".equals(randomCode)||randomCode==null){
//
//
//            resp.sendRedirect(req.getContextPath()+"/login.jsp");
//            return;
//        }




VO4UserLogin usr =  userService.queryUser(username);

        if(usr!=null){
      if("admin".equals(username)&&("admin".equals(password))){
            MailService mailService = new MailService();
            ActionService actionService = new ActionService();
          HttpSession session = req.getSession();
          session.setAttribute("username","admin");
          session.setAttribute("usercount",userService.countUser());
          session.setAttribute("actioncount",actionService.countAction());
          session.setAttribute("bindcount",mailService.getBindCount());
          session.setAttribute("mailcount",mailService.getMailCount());
          resp.sendRedirect(req.getContextPath()+"/AdminIndex.jsp");

        return;


      }



        if(password.equals(usr.getPassword())){

            HttpSession session = req.getSession();
            MailService mailService = new MailService();
            List<VO4BindAndMail> bindlist = new ArrayList<VO4BindAndMail>();
            bindlist = mailService.queryBindMailByUserId(usr.getUser_id());
            int count = mailService.countBind(usr.getUser_id());
            session.setAttribute("user_id",usr.getUser_id());
            session.setAttribute("username",usr.getUsername());
            session.setAttribute("bindlist",bindlist);
            session.setAttribute("count",count);
            new ActionService().queryaction(usr.getUser_id(),"登录");
            resp.sendRedirect(req.getContextPath()+"/index.jsp");


        }
        }

        else{
            resp.sendRedirect(req.getContextPath()+"/login.jsp");

        }


    }

    private void reg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserService userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        VO4UserReg user = new VO4UserReg();
        user.setUsername(username);
        user.setPassword(password);

        String flag = userService.regist(user);
        PrintWriter out = resp.getWriter();
        if(username==null||"".equals(username)||password==null||"".equals(password)) {
            resp.sendRedirect(req.getContextPath() + "/reg.jsp");

        }
        else{

            if ("1".equals(flag)) {

                resp.sendRedirect(req.getContextPath() + "/reg.jsp");
            } else {
                HttpSession session = req.getSession();
                VO4UserReg usr = new VO4UserReg();
                usr = (VO4UserReg) userService.queryByUserName(username);
                session.setAttribute("user_id", usr.getUser_id());
                session.setAttribute("username", usr.getUsername());
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        }
    }



}
