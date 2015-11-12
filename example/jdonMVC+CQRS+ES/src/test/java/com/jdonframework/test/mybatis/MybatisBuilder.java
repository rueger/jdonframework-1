package com.jdonframework.test.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jdon.annotation.Component;

@Component("SqlSessionFactory")
public class MybatisBuilder{
	
	private SqlSessionFactory sqlSessionFactory;
	
	public  MybatisBuilder() throws IOException{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		//System.out.println("new SqlSessionFactoryBuilder().build(inputStream);");
	}
	
	public SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
	
		
}