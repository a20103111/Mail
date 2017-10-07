package com.Mail.Service;

import com.Mail.DAO.TActionMapper;
import com.Mail.DAO.TBindMapper;
import com.Mail.DAO.TUserMapper;
import com.Mail.DTO.*;
import com.Mail.VO.VO4User;
import com.Mail.VO.VO4UserLogin;
import com.Mail.VO.VO4UserReg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lhy on 2017/5/8 0008.
 */
public class UserService {
    // 加载mybatis_config.xml文件
     private  InputStream is =this.getClass().getClassLoader().getResourceAsStream("Mybatis.xml");
    // 构建SqlSessionFactory对象
    private  SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);



    public String regist(VO4UserReg user) throws IOException {


        SqlSession session = sqlSessionFactory.openSession();

        TUserExample example = new TUserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        List<TUser> users =  mapper.selectByExample(example);

        if(users.size()>0){

            return "1";
        }
        else{

         return  insertUser(user);
        }

    }
    public String insertUser(VO4UserReg user){

        SqlSession session = sqlSessionFactory.openSession();
        TUser t_user  = new TUser();
        t_user.setUsername(user.getUsername());
        t_user.setPassword(user.getPassword());
        int result = session.insert("com.Mail.DAO.TUserMapper.insert",t_user);
        session.commit();
        session.close();
        if(result>0){
            return "0";

        }else{


            return "1";
        }



    }

    public VO4UserReg queryByUserName(String username){
        SqlSession session = sqlSessionFactory.openSession();

        TUserExample example = new TUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        List<TUser> users =  mapper.selectByExample(example);

        VO4UserReg user = new VO4UserReg();
        user.setUser_id(users.get(0).getUserId());
        user.setUsername(users.get(0).getUsername());

        return user;



    }
    public VO4UserLogin queryUser(String username){
        SqlSession session = sqlSessionFactory.openSession();

        TUserExample example = new TUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        List<TUser> users =  mapper.selectByExample(example);
        if(users.size()>0){

            VO4UserLogin user = new VO4UserLogin();
            user.setUser_id(users.get(0).getUserId());
            user.setUsername(users.get(0).getUsername());
            user.setPassword(users.get(0).getPassword());
            System.out.print(user.getUser_id() + "----"+user.getUsername());
            return user;

        }
       else{

            return null;
        }



    }
    public String changpwd(String oldpwd,String newpwd,String username){
        SqlSession session = sqlSessionFactory.openSession();

        TUserExample example = new TUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        List<TUser> users =  mapper.selectByExample(example);


          TUser  user= users.get(0);
        user.setPassword(newpwd);

           Integer result =  mapper.updateByPrimaryKey(user);
        session.commit();
        session.close();

        if(result>0){
            return "0";


        }else{
            return "1";


        }

    }


    //管理员查询所有用户
        public Map<String,Object> queryAllUser(PageInfo<VO4User> pg){
            SqlSession session = sqlSessionFactory.openSession();
            TUserExample example = new TUserExample();
            example.createCriteria().andUserIdIsNotNull();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        PageHelper.startPage(pg.getPageNum(), pg.getPageSize());

        List<TUser> tUsers = mapper.selectByExample(example);
        List<VO4User> users = new ArrayList<VO4User>();
        for(int i=0;i<tUsers.size();i++){
            VO4User user = new VO4User();
            user.setUserId(tUsers.get(i).getUserId());
            user.setUsername(tUsers.get(i).getUsername());
            user.setUserInfo(tUsers.get(i).getUserInfo());
            user.setUserPic(tUsers.get(i).getUserPic());
            users.add(user);

        }
        PageInfo<TUser> page = new PageInfo<TUser>(tUsers);
    System.out.print("这是第"+page.getPageNum()+"页，"+"共有"+page.getPages()+"页");
        System.out.print(page.isHasNextPage());

Map <String,Object> map = new HashMap<String, Object>();
            map.put("pageinfo",page);
            map.put("userlist",users);
            return map;

    }

public String queryUserNameByUserId(Integer user_id){

    SqlSession session = sqlSessionFactory.openSession();
    TUserMapper mapper = session.getMapper(TUserMapper.class);
    TUser tUser = mapper.selectByPrimaryKey(user_id);

    return tUser.getUsername();

}
    public String deleteUserAndOtherByUserId(Integer user_id){
        SqlSession session = sqlSessionFactory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        TBindMapper bindMapper = session.getMapper(TBindMapper.class);
        TActionMapper actionMapper = session.getMapper(TActionMapper.class);

        TBindExample tBindExample = new TBindExample();
        tBindExample.createCriteria().andUserIdEqualTo(user_id);
        TActionExample tActionExample = new TActionExample();
        tActionExample.createCriteria().andUserIdEqualTo(user_id);
       if( bindMapper.selectByExample(tBindExample).size()>0){

           bindMapper.deleteByExample(tBindExample);
       }

        if(actionMapper.selectByExample(tActionExample).size()>0){

            actionMapper.deleteByExample(tActionExample);
        }

       int result = mapper.deleteByPrimaryKey(user_id);
        session.commit();
        session.close();
        if(result>0){
            return "0";
        }else{

            return "1";
        }



    }
    public Integer countUser(){

        SqlSession session = sqlSessionFactory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        TUserExample tUserExample = new TUserExample();
        tUserExample.createCriteria().andUserIdIsNotNull();
        return mapper.countByExample(tUserExample);




    }

}
