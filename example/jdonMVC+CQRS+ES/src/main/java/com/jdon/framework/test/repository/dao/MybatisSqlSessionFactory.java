package com.jdon.framework.test.repository.dao;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.jdon.annotation.Component;
import com.jdon.framework.test.Constants;
import com.jdon.framework.test.test2jspIF;

@Component("sqlSessionFactory")
public class MybatisSqlSessionFactory{
	
	private SqlSessionFactory sqlSessionFactory;
	
	private DataSource dataSource;
	
	public  MybatisSqlSessionFactory(Constants constants) throws IOException{
		
		//XML方式
		//String resource = "mybatis-config.xml";
		//InputStream inputStream = Resources.getResourceAsStream(resource);
		//this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		//System.out.println("new SqlSessionFactoryBuilder().build(mybatis-config.xml);");
		
		try {
			Context ic = new InitialContext();
			this.dataSource = (DataSource) ic.lookup(constants.getJndiname());
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration configuration = new Configuration(environment);
			configuration.addMapper(test2jspIF.class);
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
			
		}catch (NamingException e) {
			e.printStackTrace();
	
		}
		
	}
	
	public SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
	
		
}