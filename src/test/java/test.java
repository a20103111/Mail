import com.Mail.DAO.TBindMapper;
import com.Mail.DAO.TMailMapper;
import com.Mail.DAO.TUserMapper;
import com.Mail.DTO.*;
import com.Mail.VO.VO4User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lhy on 2017/5/8 0008.
 */
public class test {
    SqlSessionFactory sqlSessionFactory = null;
    @Before
    public void init() {
        // 加载mybatis_config.xml文件
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("Mybatis.xml");

        // 构建SqlSessionFactory对象
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testInsert(){
        SqlSession session = sqlSessionFactory.openSession();
        TUser user  = new TUser();
        user.setUserInfo("11");
        user.setPassword("123123456");
        user.setUsername("a20103111");
        user.setUserPic("1111");
        int result = session.insert("com.Mail.DAO.TUserMapper.insert",user);
        session.commit();
        session.close();


    }
    @Test
    public void testSelect(){
        SqlSession session = sqlSessionFactory.openSession();
        TUserExample example = new TUserExample();
        example.createCriteria().andUsernameEqualTo("a20103111");
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        List<TUser> users =  mapper.selectByExample(example);

        if(users.size()>0){


            System.out.print("账号已存在");
        }
        else{

            System.out.print("注册成功");
        }

    }
    @Test
    public void insertMail(){
        SqlSession session = sqlSessionFactory.openSession();
        TBind bind = new TBind();
        bind.setMailAcc("13422083527@163.com");
        bind.setMailAuth("123123456q");
        bind.setUserMail(1);
        bind.setUserId(2);

        int result = session.insert("com.Mail.DAO.TBindMapper.insert",bind);
        session.commit();
        session.close();

    }
    @Test
    public void deleteBind(){

        SqlSession session = sqlSessionFactory.openSession();

        TBindMapper mapper = session.getMapper(TBindMapper.class);
        int result = mapper.deleteByPrimaryKey(8);
        session.commit();
        session.close();


    }
    @Test
    public void countBind(){
        SqlSession session = sqlSessionFactory.openSession();
        TBindExample tbind = new TBindExample();
        tbind.createCriteria().andUserIdEqualTo(3);
        TBindMapper mapper = session.getMapper(TBindMapper.class);

        int count =  mapper.countByExample(tbind);

        System.out.print(count);

    }
    @Test
    public void insertAction(){


        SqlSession session = sqlSessionFactory.openSession();
        //TActionMapper mapper = session.getMapper(TActionMapper.class);


        TAction tAction = new TAction();
        tAction.setUserId(1);
        tAction.setDate(new Date());
        tAction.setUserAction("插入数据");
        int result = session.insert("com.Mail.DAO.TActionMapper.insert",tAction);
        session.commit();
        session.close();
        System.out.print(result);
    }
    @Test
    public void queryAllUser(){
        SqlSession session = sqlSessionFactory.openSession();
        TUserExample example = new TUserExample();
        example.createCriteria().andUserIdIsNotNull();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        PageHelper.startPage(1, 2);

        List<TUser> tUsers = mapper.selectByExample(example);
        PageInfo<TUser> p =new  PageInfo<TUser>(tUsers);
       System.out.print( p.getPages());
        System.out.print("这是第"+p.getPageNum()+"页，"+"共有"+p.getPages()+"页");
System.out.print(p.isHasNextPage()+"下一页");
        for (int i=0;i<tUsers.size();i++){

           System.out.print(tUsers.get(i).getUsername());

       }



    }
    @Test
    public void count2Bind(){

        SqlSession session = sqlSessionFactory.openSession();
        TBindMapper tBindMapper = session.getMapper(TBindMapper.class);

        TMailExample tMailExample = new TMailExample();
        TMailMapper tMailMapper = session.getMapper(TMailMapper.class);
//    System.out.print(tMailMapper.selectByPrimaryKey(1).getMailName());

        tMailExample.createCriteria().andIdIsNotNull();
        List<TMail> tMails = tMailMapper.selectByExample(tMailExample);
       System.out.print(tMails.size());
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
    System.out.print(array);


    }

}
