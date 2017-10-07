package com.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
	public static void main(String[] args) {
		Configuration config=new Configuration();
		//解析配置文件
		
		//默认解析hibernate.cfg.xml文件
		Configuration conf=config.configure();
	
		//根据解析对象获取sessionFactory
		SessionFactory sessionFactory=conf.buildSessionFactory();
		
		//根据SessionFactory获取Session对象
		Session session=sessionFactory.openSession();
		
		//查询vip表
		Query q=session.createQuery("from Vip");
		List list=q.list();
		
		System.out.println(list);
		
		session.close();
	}
}
