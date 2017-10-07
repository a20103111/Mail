package com.Mail.Servlet;


import com.Mail.Service.ActionService;
import com.Mail.Service.MailService;
import com.Mail.Service.ReceviceMail;
import com.Mail.Util.POP3Help;
import com.Mail.Util.PaginationUtil;
import com.Mail.VO.VO4BindAndMail;
import com.Mail.VO.VO4MailRtn;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 * Created by Lhy on 2017/5/4 0004.
 */
public class ReceviceMailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.print(action);

         if("page".equals(action)){

            try {
                pagemail(req, resp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
        else if("page_pre".equals(action)){

            try {
                page_pre(req,resp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
        else if("page_next".equals(action)){

            try {
                page_next(req,resp);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }else if("delete".equals(action)){

             delteMail(req,resp);



         }


    }



    private void page_next(HttpServletRequest req, HttpServletResponse resp) throws IOException, MessagingException {

        HttpSession session = req.getSession();

        PaginationUtil pu = (PaginationUtil)session.getAttribute("pu");

        if(pu!=null){
            int totalRows = pu.getTotalRows();
            int currentPage = pu.getCurrentPage();
            pu = new PaginationUtil(totalRows, 8, currentPage+1);
            session.setAttribute("pu", pu);
        }
        new ActionService().queryaction((Integer) session.getAttribute("user_id"),"获取下一页分页");
        pagemail(req, resp);



    }

    private void page_pre(HttpServletRequest req, HttpServletResponse resp) throws IOException, MessagingException {
        HttpSession session = req.getSession();

        PaginationUtil pu = (PaginationUtil)session.getAttribute("pu");

        if(pu!=null){
            int totalRows = pu.getTotalRows();
            int currentPage = pu.getCurrentPage();
            pu = new PaginationUtil(totalRows, 8, currentPage-1);
            session.setAttribute("pu", pu);
        }
        new ActionService().queryaction((Integer) session.getAttribute("user_id"),"获取上一页分页");
        pagemail(req, resp);


    }

    private void getBox(HttpServletRequest req, HttpServletResponse resp,VO4BindAndMail vo) throws MessagingException {

        HttpSession session = req.getSession();
        Folder folder = POP3Help.getFolder(vo);
        Message[] messages = folder.getMessages();
        session.setAttribute("folder", folder);
        session.setAttribute("messages", messages);
        new ActionService().deleteaction((Integer) session.getAttribute("user_id"),"获取收件箱");



    }
    private void delteMail(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Integer message_id = Integer.parseInt(req.getParameter("mess_id"))+1;
        Integer bind_id = (Integer)session.getAttribute("bindid");
        VO4BindAndMail vo = new MailService().querySingleBind(bind_id);
        POP3Help.deleteMail(vo,message_id);

        try {


new ActionService().deleteaction((Integer) session.getAttribute("user_id"),"删除邮件");
                getBox(req, resp, vo);
                pagemail(req, resp);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getMail(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        Folder folder = (Folder) session.getAttribute("folder");
        Message[] messages = (Message[]) session.getAttribute("messages");

    }

    private void pagemail(HttpServletRequest req, HttpServletResponse resp) throws MessagingException, IOException {

        HttpSession session = req.getSession();
        //前端选择不同的邮箱收件的时候和第一次进入收件箱的时候，都执行该判断
        if(req.getParameter("bindid")!=null||session.getAttribute("folder") == null || session.getAttribute("messages") == null) {

                Integer bind_id = Integer.parseInt(req.getParameter("bindid"));
                session.setAttribute("bindid", bind_id);
                VO4BindAndMail vo = new MailService().querySingleBind(bind_id);
                getBox(req, resp, vo);





        }
        //获取收件箱
        Folder folder = (Folder) session.getAttribute("folder");
       // Message[] messages = (Message[]) session.getAttribute("messages");
        int mailcount = folder.getMessageCount();

        PaginationUtil pu = (PaginationUtil)session.getAttribute("pu");

        if(pu==null){//第一次访问收件箱
            pu  = new PaginationUtil(mailcount, 8, 1);
            System.out.println("开始邮件数:"+pu.getPageStartRow());
            System.out.println("结束邮件数:"+pu.getPageEndRow());
        }else{
            Integer currentPage = pu.getCurrentPage();
            System.out.println("开始邮件数:"+pu.getPageStartRow());
            System.out.println("结束邮件数:"+pu.getPageEndRow());

            if(mailcount!=pu.getTotalRows()){ //在两次查看收件箱间新收到了邮件

                pu = new PaginationUtil(mailcount, 8, currentPage); //在当前页面的基础上重构分页对象

            }

        }
        List<VO4MailRtn> List = new ReceviceMail().pageMail(pu,folder);

        session.setAttribute("pu", pu);
        session.setAttribute("list",List );
        new ActionService().queryaction((Integer) session.getAttribute("user_id"),"查询分页");
     resp.sendRedirect(req.getContextPath()+"/maillist.jsp");


    }

}