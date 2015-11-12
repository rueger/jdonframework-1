package com.jdonframework.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jdon.controller.AppUtil;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MybatisBuilderTest extends TestCase {
	
	SqlSessionFactory sqlSessionFactory;
	
	public static void main(String[] args) {

		MybatisBuilderTest mybatisBuilderTest = new MybatisBuilderTest();
		
		mybatisBuilderTest.testSqlSessionFactoryBuilder();
		
		mybatisBuilderTest.testSelectUserID();

	}

	public void testSqlSessionFactoryBuilder() {
		
		AppUtil appUtil = new AppUtil();
		
		appUtil.getComponentInstance("SqlSessionFactory");
		
		System.out.println("sqlSessionFactory ok ");
		
		appUtil.clear();
	}
	
	public void testSelectUserID() {
		
		AppUtil appUtil = new AppUtil();
		
		MybatisBuilder mybatisBuilder = (MybatisBuilder) appUtil.getComponentInstance("SqlSessionFactory");
		
		SqlSessionFactory sqlSessionFactory = mybatisBuilder.getSqlSessionFactory();
		
		SqlSession session = sqlSessionFactory.openSession();
		
		String userid;
        
        try {
        	  userid = (String) session.selectOne("com.jdon.framework.test.user.selectuseridbyid", 1);
        } finally {
          session.close();
        }
        
        Assert.assertEquals(userid, "1");
        
        System.out.println("userid = " + userid);
        
        System.out.println("testSelectUserID() ok ");
		
		appUtil.clear();
	}

}
