package com.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
	public static void main(String[] args) {
		Configuration config=new Configuration();
		//���������ļ�
		
		//Ĭ�Ͻ���hibernate.cfg.xml�ļ�
		Configuration conf=config.configure();
	
		//���ݽ��������ȡsessionFactory
		SessionFactory sessionFactory=conf.buildSessionFactory();
		
		//����SessionFactory��ȡSession����
		Session session=sessionFactory.openSession();
		
		//��ѯvip��
		Query q=session.createQuery("from Vip");
		List list=q.list();
		
		System.out.println(list);
		
		session.close();
	}
}
