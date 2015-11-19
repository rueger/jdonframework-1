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
package com.jdon.framework.test.repository.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.sql.Blob;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.jdon.annotation.Component;
import com.jdon.framework.test.Constants;
import com.jdon.framework.test.domain.UploadFile;
import com.jdon.framework.test.repository.UploadRepository;
import com.jdon.framework.test.repository.UserRepository;
import com.jdon.util.Debug;

//@Component("uploadRepository")
@Component
public class UploadFileDaoMybatis implements UploadRepository {
	private final static Logger logger = Logger.getLogger(UploadFileDaoMybatis.class);

	private final SqlSessionFactory sqlSessionFactory;

	public UploadFileDaoMybatis(MybatisSqlSessionFactory mybatisSqlSessionFactory) {
		
		this.sqlSessionFactory = mybatisSqlSessionFactory.getSqlSessionFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.framework.test.repository.dao.UploadRepository#getUploadFile
	 * (java.lang.String)
	 */
	@Override
	public UploadFile getUploadFile(String parentId) {
		
		UploadFile ret = null;
		
		SqlSession sqlsession = sqlSessionFactory.openSession();
		
		try {
			   UploadRepository mapper = sqlsession.getMapper(UploadRepository.class);  
			   ret = mapper.getUploadFile(parentId);
		} finally {
		       sqlsession.close();
		}
		
				
		return ret;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.jivejdon.dao.UploadFileDao#createUploadFile(com.jdon.strutsutil
	 * .file.UploadFile)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.framework.test.repository.dao.UploadRepository#createUploadFile
	 * (com.jdon.framework.test.domain.UploadFile)
	 */
	
	public void createUploadFile(UploadFile uploadFile) {
		//logger.debug("enter createUploadFile uploadId =" + uploadFile.getId());
		
		//System.out.println("createUploadFile uploadid = " + uploadFile.getId());
		
		SqlSession sqlsession = sqlSessionFactory.openSession();
		
		try {
			   UploadRepository mapper = sqlsession.getMapper(UploadRepository.class);  
			   mapper.createUploadFile(uploadFile);
		} finally {
		       sqlsession.close();
		}
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.jivejdon.dao.UploadFileDao#deleteUploadFile(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.framework.test.repository.dao.UploadRepository#deleteUploadFile
	 * (java.lang.String)
	 */
	@Override
	public void deleteUploadFile(String parentId) {

		SqlSession sqlsession = sqlSessionFactory.openSession();
		
		try {
			   UploadRepository mapper = sqlsession.getMapper(UploadRepository.class);  
			   mapper.deleteUploadFile(parentId);
		} finally {
		       sqlsession.close();
		}

	}
}
