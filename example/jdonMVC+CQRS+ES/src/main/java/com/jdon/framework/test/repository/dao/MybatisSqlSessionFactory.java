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
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;

import com.jdon.annotation.Component;
import com.jdon.framework.test.Constants;
import com.jdon.framework.test.test2jspIF;
import com.jdon.framework.test.query.QueryIF;
import com.jdon.framework.test.repository.UploadRepository;
import com.jdon.framework.test.repository.UserRepository;

@Component("sqlSessionFactory")
public class MybatisSqlSessionFactory{
	
	private SqlSessionFactory sqlSessionFactory;
	
	public  MybatisSqlSessionFactory(Constants constants) throws IOException{
		
		//XML方式
		//String resource = "mybatis-config.xml";
		//InputStream inputStream = Resources.getResourceAsStream(resource);
		//this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		//System.out.println("new SqlSessionFactoryBuilder().build(mybatis-config.xml);");
		
		try {
			Context ic = new InitialContext();
			DataSource dataSource = (DataSource) ic.lookup(constants.getJndiname());
			
			TransactionFactory transactionFactory = new ManagedTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration configuration = new Configuration(environment);
			
			configuration.addMapper(test2jspIF.class);
			configuration.addMapper(UserRepository.class);
			configuration.addMapper(QueryIF.class);
			configuration.addMapper(UploadRepository.class);
			
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
			
		}catch (NamingException e) {
			e.printStackTrace();
	
		}
		
	}
	
	public SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
	
		
}