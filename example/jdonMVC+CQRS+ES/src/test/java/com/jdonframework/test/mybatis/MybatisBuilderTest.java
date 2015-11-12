package com.jdonframework.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jdon.controller.AppUtil;
import com.jdon.framework.test.domain.UserModel;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MybatisBuilderTest extends TestCase {
	
	SqlSessionFactory sqlSessionFactory;
	
	public static void main(String[] args) {

		MybatisBuilderTest mybatisBuilderTest = new MybatisBuilderTest();
		
		mybatisBuilderTest.testSqlSessionFactoryBuilder();
		
		mybatisBuilderTest.testSelectUserIdById();
		
		mybatisBuilderTest.testSelectUserByID();

	}

	public void testSqlSessionFactoryBuilder() {
		
		AppUtil appUtil = new AppUtil();
		
		appUtil.getComponentInstance("TestSqlSessionFactory");
		
		System.out.println("sqlSessionFactory ok ");
		
		appUtil.clear();
	}
	
	public void testSelectUserIdById() {
		
		AppUtil appUtil = new AppUtil();
		
		TestMybatisBuilder mybatisBuilder = (TestMybatisBuilder) appUtil.getComponentInstance("TestSqlSessionFactory");
		
		SqlSessionFactory sqlSessionFactory = mybatisBuilder.getSqlSessionFactory();
		
		SqlSession session = sqlSessionFactory.openSession();
		
		String userid;
        
        try {
        	  userid = (String) session.selectOne("com.jdonframework.test.user.selectuseridbyid", 1);
        } finally {
          session.close();
        }
        
        Assert.assertEquals(userid, "1");
        
        System.out.println("userid = " + userid);
        
        System.out.println("testSelectUserID() ok ");
		
		appUtil.clear();
	}
	
	
	
	public void testSelectUserByID() {
		
		AppUtil appUtil = new AppUtil();

		TestMybatisBuilder mybatisBuilder = (TestMybatisBuilder) appUtil.getComponentInstance("TestSqlSessionFactory");
		
        SqlSessionFactory sqlSessionFactory = mybatisBuilder.getSqlSessionFactory();
		
		SqlSession session = sqlSessionFactory.openSession();
		
		UserModel user = null;
		
		try {
		  user = session.selectOne("com.jdonframework.test.user.selectuserbyid", 1);
        } finally {
        session.close();
        }
		
		System.out.println("user.Userid = " + user.getUserId());
		
		System.out.println("user.username = " + user.getUsername());
		
		System.out.println("testSelectUserByID() ok ");
		
		appUtil.clear();
	}

}
