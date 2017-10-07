package com.Mail.Service;

import com.Mail.DAO.TBindMapper;
import com.Mail.DAO.TMailMapper;
import com.Mail.DAO.TUserMapper;
import com.Mail.DTO.*;
import com.Mail.Util.POP3Help;
import com.Mail.VO.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONObject;

import javax.mail.*;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Lhy on 2017/5/10 0010.
 */
public class MailService {

    // 加载mybatis_config.xml文件
    private InputStream is =this.getClass().getClassLoader().getResourceAsStream("Mybatis.xml");
    // 构建SqlSessionFactory对象
    private SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

    //删除绑定
    public String deleteBind(Integer bind_id){
        SqlSession session = sqlSessionFactory.openSession();

        TBindMapper mapper = session.getMapper(TBindMapper.class);
        int result = mapper.deleteByPrimaryKey(bind_id);
        session.commit();
        session.close();
        if(result>0){

            return "0";
        }else{

            return "1";
        }



    }


//添加绑定
    public String BindMail(VO4BindAndMail vo4BindAndMail){

        if(isConnect(vo4BindAndMail)){




        SqlSession session = sqlSessionFactory.openSession();
        TBind bind = new TBind();
        bind.setUserId(vo4BindAndMail.getUser_id());
        bind.setUserMail(vo4BindAndMail.getUser_mail());
        bind.setMailAuth(vo4BindAndMail.getMail_auth());
        bind.setMailAcc(vo4BindAndMail.getMail_acc());
        int result = session.insert("com.Mail.DAO.TBindMapper.insert",bind);
        session.commit();
        session.close();
        if(result>0){
            return "0";
        }
        else{
            return "1";
        }
        }
        else{
            return "2";
        }
    }
    //判断绑定的账号是否正确
    public boolean isConnect(VO4BindAndMail vo4BindAndMail){
        return POP3Help.isConnect(vo4BindAndMail);
    }
//通过邮件表id获取邮件的smtp和pop3的信息
    public VO4BindAndMail queryMailByMailId(int mailid){
        SqlSession session = sqlSessionFactory.openSession();

        TMailExample example = new TMailExample();
        example.createCriteria().andIdEqualTo(mailid);
        TMailMapper mapper = session.getMapper(TMailMapper.class);
        List<TMail> mail =  mapper.selectByExample(example);

        VO4BindAndMail mail_rtn = new VO4BindAndMail();
         mail_rtn.setMail_name(mail.get(0).getMailName());
        mail_rtn.setMail_pop3(mail.get(0).getMailPop3());
        mail_rtn.setMail_pop3_port(mail.get(0).getMailPop3Port());
        mail_rtn.setMail_smtp(mail.get(0).getMailSmtp());
        mail_rtn.setMail_smtp_port(mail.get(0).getMailSmtpPort());

        return mail_rtn;

    }
    //查询该用户绑定的所有邮箱
    public List<VO4BindAndMail> queryBindMailByUserId(Integer id){
        SqlSession session = sqlSessionFactory.openSession();
        TBindExample tbind = new TBindExample();
        tbind.createCriteria().andUserIdEqualTo(id);
        TBindMapper mapper = session.getMapper(TBindMapper.class);
        List<VO4BindAndMail> volist = new ArrayList<VO4BindAndMail>();
        List<TBind> list = mapper.selectByExample(tbind);
    for(int i=0;i<list.size();i++){
        VO4BindAndMail vo = new VO4BindAndMail();
        vo.setMail_acc(list.get(i).getMailAcc());
        vo.setMail_name(queryTMailById(list.get(i).getUserMail()).getMailName());
        vo.setBind_id(list.get(i).getId());
        vo.setMail_auth(list.get(i).getMailAuth());

        volist.add(vo);


    }
        return volist;


    }
    //根据绑定id来查询绑定的邮箱信息
    public VO4BindAndMail querySingleBind(Integer id){
        SqlSession session = sqlSessionFactory.openSession();


        TBindMapper mapper = session.getMapper(TBindMapper.class);

        TBind tbind = mapper.selectByPrimaryKey(id);
        VO4BindAndMail mail = queryMailByMailId(tbind.getUserMail());

        mail.setUser_id(tbind.getUserId());
        mail.setMail_acc(tbind.getMailAcc());
        mail.setMail_auth(tbind.getMailAuth());
        mail.setUser_mail(tbind.getUserMail());
        return mail;






    }



    public TMail queryTMailById(Integer id){
        SqlSession session = sqlSessionFactory.openSession();
        TMailMapper tMailMapper = session.getMapper(TMailMapper.class);
      TMail tmail =  tMailMapper.selectByPrimaryKey(id);

        return  tmail;


    }
    public Boolean deleteMail(Integer message_id,VO4BindAndMail vo) throws MessagingException {
        Folder folder = POP3Help.getFolder(vo);

        Message message = folder.getMessage(message_id);
        message.setFlag(Flags.Flag.DELETED, true);    // true为设置，false为取消

        folder.close(true);
        return true;




    }
    public Integer countBind(Integer user_id){
        SqlSession session = sqlSessionFactory.openSession();
        TBindExample tbind = new TBindExample();
        tbind.createCriteria().andUserIdEqualTo(user_id);
        TBindMapper mapper = session.getMapper(TBindMapper.class);

       int count =  mapper.countByExample(tbind);

        return count;

    }
    public Map<String,Object> queryAllBind(PageInfo<VO4BindRtn> pg){
        SqlSession session = sqlSessionFactory.openSession();
        TBindExample example = new TBindExample();
        example.createCriteria().andUserIdIsNotNull();
        TBindMapper mapper = session.getMapper(TBindMapper.class);

        PageHelper.startPage(pg.getPageNum(), pg.getPageSize());

        List<TBind> tBinds = mapper.selectByExample(example);
        List<VO4BindRtn> binds = new ArrayList<VO4BindRtn>();
        for(int i=0;i<tBinds.size();i++){
            VO4BindRtn bind = new VO4BindRtn();
            bind.setId(tBinds.get(i).getId());
            bind.setMailAcc(tBinds.get(i).getMailAcc());
            bind.setMailName(new MailService().queryMailByMailId(tBinds.get(i).getUserMail()).getMail_name());
            bind.setUserId(tBinds.get(i).getUserId());
            bind.setUsername(new UserService().queryUserNameByUserId(tBinds.get(i).getUserId()));

            binds.add(bind);

        }
        PageInfo<TBind> page = new PageInfo<TBind>(tBinds);
        System.out.print("这是第"+page.getPageNum()+"页，"+"共有"+page.getPages()+"页");
        System.out.print(page.isHasNextPage());

        Map <String,Object> map = new HashMap<String, Object>();
        map.put("bindpage",page);
        map.put("bindlist",binds);
        return map;

    }
    public JSONObject countBind(){

        SqlSession session = sqlSessionFactory.openSession();
        TMail tmail = new TMail();
        TMailExample tMailExample = new TMailExample();
        TMailMapper tMailMapper = session.getMapper(TMailMapper.class);
        TBindMapper tBindMapper = session.getMapper(TBindMapper.class);
        tMailExample.createCriteria().andIdIsNotNull();
        List<TMail> tMails = tMailMapper.selectByExample(tMailExample);
    List<String> namelist = new ArrayList<String>();
        List<Integer> countlist = new ArrayList<Integer>();

        JSONObject array = new JSONObject();
        for(int i=0;i<tMails.size();i++) {
            TBindExample tBindExample = new TBindExample();

            tBindExample.createCriteria().andUserMailEqualTo(tMails.get(i).getId());
            String mailname = tMails.get(i).getMailName();
           int mailcount =  tBindMapper.countByExample(tBindExample);
//           array.put("name",mailname);
//            array.put("count",mailcount);
            namelist.add(mailname);
            countlist.add(mailcount);

        }
        array.put("name",namelist);
        array.put("count",countlist);

        return  array;



    }
    public Integer getBindCount(){
        SqlSession session = sqlSessionFactory.openSession();
        TBindExample tBindExample = new TBindExample();
        TBindMapper tBindMapper = session.getMapper(TBindMapper.class);
        tBindExample.createCriteria().andIdIsNotNull();
        return tBindMapper.countByExample(tBindExample);
    }

    public Integer getMailCount(){
        SqlSession session = sqlSessionFactory.openSession();
        TMailExample tMailExample = new TMailExample();
        TMailMapper tMailMapper = session.getMapper(TMailMapper.class);
        tMailExample.createCriteria().andIdIsNotNull();
        return tMailMapper.countByExample(tMailExample);




    }

}
