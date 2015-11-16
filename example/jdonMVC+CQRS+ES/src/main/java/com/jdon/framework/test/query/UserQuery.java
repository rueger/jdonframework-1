/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon.framework.test.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.jdon.annotation.Component;
//import com.jdon.controller.model.PageIterator;
import com.jdon.framework.test.domain.UserModel;
import com.jdon.framework.test.repository.ModelCacheManager;
import com.jdon.framework.test.repository.UserRepository;
import com.jdon.framework.test.repository.dao.MybatisSqlSessionFactory;
import com.jdon.framework.test.web.ResourceManagerContext;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@Component("userQuery")
public class UserQuery {

	private final static Logger logger = Logger.getLogger(UserQuery.class);
	
	private final UserRepository userRepository;

	private final ModelCacheManager modelCacheManager;

	private final MybatisSqlSessionFactory mybatisSqlSessionFactory;
	
	public UserQuery(UserRepository userRepository, ModelCacheManager modelCacheManager,MybatisSqlSessionFactory mybatisSqlSessionFactory) {
		this.userRepository = userRepository;
		this.modelCacheManager = modelCacheManager;
		this.mybatisSqlSessionFactory = mybatisSqlSessionFactory;

	}

	public List getUserList() {
		List<UserModel> list = new ArrayList();
		List ids = getUsers();
		for (Object o : ids) {
			String userId = (String) o;
	
			list.add(userRepository.getUser(userId));
			
		}
			
		return list;
	}

	/*
	public List getUsers() {
		String GET_ALL_ITEMS_ALLCOUNT = "select count(1) from testuser ";
		String GET_ALL_ITEMS = "select userId  from testuser ";
		PageIterator pageIterator = modelCacheManager.getPageIteratorSolverOfUser().getDatas("", GET_ALL_ITEMS_ALLCOUNT, GET_ALL_ITEMS, 0, 9999999);
		return Arrays.asList(pageIterator.getKeys());
	}
	*/
	
	public List getUsers() {
		
        SqlSession sqlsession = mybatisSqlSessionFactory.getSqlSessionFactory().openSession();
		
		List<String> userIdlist = new ArrayList();
				
		try {
			   QueryIF mapper = sqlsession.getMapper(QueryIF.class);  
			   userIdlist = mapper.getUsersId();
		} finally {
		      sqlsession.close();
		}
		
		return userIdlist;
	}

}
