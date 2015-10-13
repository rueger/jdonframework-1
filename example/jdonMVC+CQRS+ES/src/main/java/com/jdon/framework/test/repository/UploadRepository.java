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
package com.jdon.framework.test.repository;

import com.jdon.framework.test.domain.UploadFile;

public interface UploadRepository {

	public abstract UploadFile getUploadFile(String objectId);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.jivejdon.dao.UploadFileDao#createUploadFile(com.jdon.strutsutil
	 * .file.UploadFile)
	 */
	public abstract void createUploadFile(UploadFile uploadFile);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jdon.jivejdon.dao.UploadFileDao#deleteUploadFile(java.lang.String)
	 */
	public abstract void deleteUploadFile(String objectId);

}