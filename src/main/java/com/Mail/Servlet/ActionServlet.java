package com.Mail.Servlet;

import com.Mail.Service.ActionService;
import com.Mail.Service.UserService;
import com.Mail.VO.VO4ActionRtn;
import com.Mail.VO.VO4User;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lhy on 2017/5/14 0014.
 */
public class ActionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

         if("page".equals(action)){

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
        Integer id = Integer.parseInt(req.getParameter("id"));
        ActionService actionService = new ActionService();
        String flag = actionService.deleteAction(id);
        if("0".equals(flag)){

            try {
                page(req,resp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }




    }

    private void page_next(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PageInfo<VO4ActionRtn> page = (PageInfo<VO4ActionRtn>) session.getAttribute("actionpage");
        if(page!=null){
            page.setPageNum(page.getNextPage());
            session.setAttribute("actionpage",page);




        }
        try {
            page(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void page_pre(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        PageInfo<VO4ActionRtn> page = (PageInfo<VO4ActionRtn>) session.getAttribute("actionpage");

        if(page!=null){
            page.setPageNum(page.getPrePage());
            session.setAttribute("actionpage",page);




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
        if(session.getAttribute("actionpage")==null||session.getAttribute("actionlist")==null){
            Map<String,Object> map = new HashMap<String,Object>();
            PageInfo<VO4ActionRtn> page = new PageInfo<VO4ActionRtn>();
            page.setPageNum(1);
            page.setPageSize(10);


            List<VO4ActionRtn> actions = new ArrayList<VO4ActionRtn>();
            map = new ActionService().queryAllAction(page);

            page = (PageInfo<VO4ActionRtn>) map.get("actionpage");

            actions = (List<VO4ActionRtn>) map.get("actionlist");
            System.out.print("返回集合大小"+actions.size());

            session.setAttribute("actionpage",page);
            session.setAttribute("actionlist",actions);

        }

        PageInfo<VO4ActionRtn> page = (PageInfo<VO4ActionRtn>) session.getAttribute("actionpage");
        List<VO4ActionRtn> actions = new ArrayList<VO4ActionRtn>();
        Map<String,Object> map = new HashMap<String,Object>();
        map = new ActionService().queryAllAction(page);
        page = (PageInfo<VO4ActionRtn>) map.get("actionpage");
        actions = (List<VO4ActionRtn>) map.get("actionlist");
        System.out.print("返回集合大小"+actions.size());
        session.setAttribute("actionpage",page);
        session.setAttribute("actionlist",actions);

        resp.sendRedirect(req.getContextPath()+"/actionlist.jsp");


    }

}
