package com.Mail.Service;

import com.Mail.DAO.TActionMapper;
import com.Mail.DAO.TBindMapper;
import com.Mail.DTO.TAction;
import com.Mail.DTO.TActionExample;
import com.Mail.DTO.TBindExample;
import com.Mail.VO.VO4ActionRtn;
import com.Mail.VO.VO4User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Lhy on 2017/5/14 0014.
 */
public class ActionService {
    // 加载mybatis_config.xml文件
    private InputStream is =this.getClass().getClassLoader().getResourceAsStream("Mybatis.xml");
    // 构建SqlSessionFactory对象
    private SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

    public void insertaction (Integer user_id,String msg){
        SqlSession session = sqlSessionFactory.openSession();
        //TActionMapper mapper = session.getMapper(TActionMapper.class);


        TAction tAction = new TAction();
        tAction.setUserId(user_id);
        tAction.setDate(new Date());
        tAction.setUserAction(msg);
        int result = session.insert("com.Mail.DAO.TActionMapper.insert",tAction);
        session.commit();
        session.close();
        System.out.print(result);

    }
    public void deleteaction (Integer user_id,String msg){
        SqlSession session = sqlSessionFactory.openSession();
        //TActionMapper mapper = session.getMapper(TActionMapper.class);


        TAction tAction = new TAction();
        tAction.setUserId(user_id);
        tAction.setDate(new Date());
        tAction.setUserAction(msg);
        int result = session.insert("com.Mail.DAO.TActionMapper.insert",tAction);
        session.commit();
        session.close();


    }
    public void updateaction (Integer user_id,String msg){
        SqlSession session = sqlSessionFactory.openSession();
        //TActionMapper mapper = session.getMapper(TActionMapper.class);


        TAction tAction = new TAction();
        tAction.setUserId(user_id);
        tAction.setDate(new Date());
        tAction.setUserAction(msg);
        int result = session.insert("com.Mail.DAO.TActionMapper.insert",tAction);
        session.commit();
        session.close();
        System.out.print(result);

    }

    public void queryaction (Integer user_id,String msg){
        SqlSession session = sqlSessionFactory.openSession();
        //TActionMapper mapper = session.getMapper(TActionMapper.class);


        TAction tAction = new TAction();
        tAction.setUserId(user_id);
        tAction.setDate(new Date());
        tAction.setUserAction(msg);
        int result = session.insert("com.Mail.DAO.TActionMapper.insert",tAction);
        session.commit();
        session.close();
        System.out.print(result);

    }
    public Map<String,Object> queryAllAction(PageInfo<VO4ActionRtn> pg){
        SqlSession session = sqlSessionFactory.openSession();
        TActionExample example = new TActionExample();
        example.createCriteria().andUserIdIsNotNull();
        TActionMapper mapper = session.getMapper(TActionMapper.class);
        PageHelper.startPage(pg.getPageNum(), pg.getPageSize());

        List<TAction> tActions = mapper.selectByExample(example);
        List<VO4ActionRtn> actions = new ArrayList<VO4ActionRtn>();
        for(int i=0;i<tActions.size();i++){
            VO4ActionRtn action  = new VO4ActionRtn();
            action.setId(tActions.get(i).getId());
            action.setUserId(tActions.get(i).getUserId());
            action.setUsername(new UserService().queryUserNameByUserId(tActions.get(i).getUserId()));
            action.setDate(tActions.get(i).getDate());
            action.setUserAction(tActions.get(i).getUserAction());
            actions.add(action);

        }
        PageInfo<TAction> page = new PageInfo<TAction>(tActions);
        System.out.print("这是第"+page.getPageNum()+"页，"+"共有"+page.getPages()+"页");
        System.out.print(page.isHasNextPage());

        Map <String,Object> map = new HashMap<String, Object>();
        map.put("actionpage",page);
        map.put("actionlist",actions);
        return map;

    }

    public String deleteAction(Integer id){
        SqlSession session = sqlSessionFactory.openSession();
        TActionMapper mapper = session.getMapper(TActionMapper.class);
        int result = mapper.deleteByPrimaryKey(id);
        session.commit();
        session.close();
        if(result>0){
            return "0";


        }else{

            return "1";
        }


    }

    public Integer countAction(){
        SqlSession session = sqlSessionFactory.openSession();
        TActionMapper mapper = session.getMapper(TActionMapper.class);
        TActionExample tActionExample = new TActionExample();
        tActionExample.createCriteria().andIdIsNotNull();
        return mapper.countByExample(tActionExample);




    }
}
