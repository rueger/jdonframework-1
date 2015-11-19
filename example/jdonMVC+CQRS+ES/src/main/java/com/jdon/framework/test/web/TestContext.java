package com.jdon.framework.test.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.ibatis.session.SqlSession;

import com.jdon.framework.test.test2jspIF;
import com.jdon.framework.test.domain.UploadFile;
import com.jdon.framework.test.domain.UserModel;
import com.jdon.framework.test.query.QueryIF;
import com.jdon.framework.test.query.UserQuery;
import com.jdon.framework.test.repository.UploadRepository;
import com.jdon.framework.test.repository.UserRepository;
import com.jdon.framework.test.repository.dao.MybatisSqlSessionFactory;
import com.jdon.mvc.annotations.In;
import com.jdon.mvc.ioc.BeanType;
import com.jdon.mvc.represent.Html;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.represent.State;
import com.jdon.mvc.represent.Text;

public class TestContext {
	private @Context
	HttpServletRequest request;
	
	@In(value = "sqlSessionFactory", type = BeanType.COMPONENT)
	private  MybatisSqlSessionFactory  mybatisSqlSessionFactory;
	
	@In(value = "userQuery", type = BeanType.COMPONENT)
	private UserQuery userQuery;
	
	@Path("/test")
	public Represent test() {
		//System.out.println("test");
		String show = "test";
		return new Text(show);
	}
	
	@Path("/test2")
	public Represent test2() {
		
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();


	    String userid;

	    try {
		   test2jspIF mapper = sqlsession.getMapper(test2jspIF.class);  
		   userid = (String) mapper.selectUserId(1);
	    } finally {
	      sqlsession.close();
	    }
		
	    
		
		return new Text(userid);
	}
	
	@Path("/test3")
	public Represent test3() {
		
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();


	    String username;

	    try {
		   test2jspIF mapper = sqlsession.getMapper(test2jspIF.class);  
		   username = (String) mapper.selectUsername(1);
	    } finally {
	      sqlsession.close();
	    }
		
	    
		
		return new Text(username);
	}
	
	@Path("/test4")
	public Represent test4() {
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		UserModel user;
		
		try {
			   UserRepository mapper = sqlsession.getMapper(UserRepository.class);  
			   user = (UserModel) mapper.getUser("1");
		    } finally {
		      sqlsession.close();
		    }
		
		return new Text("user.userId = " + user.getUserId() + " user.username = " + user.getUsername());
	}
	
	@Path("/test5")
	public Represent test5() {
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		List<String> userIdlist = new ArrayList();
		String userId = "";
		
		try {
			   QueryIF mapper = sqlsession.getMapper(QueryIF.class);  
			   userIdlist = mapper.getUsersId();
		} finally {
		      sqlsession.close();
		}
		
		for (Object o : userIdlist) {
			
			userId = userId + " " + (String) o;
				
		}
		
		return new Text(userId);
	}
	
	@Path("/test6")
	public Represent test6() {
		
		//System.out.println("test6");
		
		List<UserModel> userList = userQuery.getUserList();
		
		return new Html("/WEB-INF/index.jsp", "userList", userList);
				
	}
	
	@Path("/test7")
	public Represent test7() throws Exception {
	
		System.out.println("test7");
		
		UserModel user = new UserModel();
		String userId = Integer.toString(user.hashCode());
		user.setUserId(userId);
		user.setUsername("bytest7");
		
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		try {
			   UserRepository mapper = sqlsession.getMapper(UserRepository.class);  
			   mapper.save(user);
		} finally {
		       sqlsession.close();
		}
		
		return new Text("test7 ok");
	}
	
	@Path("/test8")
	public Represent test8() throws Exception {
		
		System.out.println("test8");
		
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		try {
			   UserRepository mapper = sqlsession.getMapper(UserRepository.class);  
			   mapper.delete("2");
		} finally {
		       sqlsession.close();
		}
		
		
		return new Text("test8 ok");
	}
	
	@Path("/test9")
	public Represent test9() throws Exception {
		
		System.out.println("test9");
		
		UploadFile uploadFile = new UploadFile();
		String Id = Integer.toString(uploadFile.hashCode());
		
		uploadFile.setId(Id);
		uploadFile.setParentId("1");
		//uploadFile.setData(file.getFileData());
		uploadFile.setName("qq.jpg");
		uploadFile.setContentType("image/jpeg");
		uploadFile.setDescription("description");
		uploadFile.setSize(2048);
		
		
		SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		try {
			   UploadRepository mapper = sqlsession.getMapper(UploadRepository .class);  
			   mapper.createUploadFile(uploadFile);
		} finally {
		       sqlsession.close();
		}
		
		return new Text("test9 ok");
	}
	
}
