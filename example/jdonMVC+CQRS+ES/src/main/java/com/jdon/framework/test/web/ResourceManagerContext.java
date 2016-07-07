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
package com.jdon.framework.test.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.jdon.domain.dci.RoleAssigner;

import com.jdon.framework.test.domain.UploadFile;
import com.jdon.framework.test.domain.UserModel;
import com.jdon.framework.test.domain.command.UpdateCommand;
import com.jdon.framework.test.domain.event.UploadDeletedEvent;
import com.jdon.framework.test.domain.event.UserCreatedEvent;
import com.jdon.framework.test.domain.event.UserDeletedEvent;
import com.jdon.framework.test.query.UserQuery;
import com.jdon.framework.test.repository.EntityFactory;

import com.jdon.mvc.annotations.Path;
import com.jdon.mvc.annotations.In;
import com.jdon.mvc.http.FormFile;
import com.jdon.mvc.ioc.BeanType;
import com.jdon.mvc.represent.Html;
import com.jdon.mvc.represent.Image;
import com.jdon.mvc.represent.Represent;
import com.jdon.mvc.represent.Redirect;
import com.jdon.mvc.validation.Validation;
import com.jdon.mvc.validation.infrastructure.NotEmail;
import com.jdon.mvc.validation.infrastructure.NotEqual;
import com.jdon.mvc.validation.infrastructure.NotNull;

public class ResourceManagerContext {
	private final static Logger logger = Logger.getLogger(ResourceManagerContext.class);

	
	@In
	private HttpServletRequest request;

	
	@In
	private Validation validation;

	@In(value = "entityFactory", type = BeanType.component)
	private EntityFactory entityFactory;

	@In(value = "userQuery", type = BeanType.component)
	private UserQuery userQuery;

	@In(value = "roleAssigner", type = BeanType.component)
	private RoleAssigner roleAssigner;

	@In(value = "commandHandler", type = BeanType.component)
	private CommandHandler commandHandler;

	@Path("/")
	public Represent index() {
		
		return new Html("index.jsp");

	}
	
	@SuppressWarnings("unchecked")
	@RequiresAuthentication
	@Path("/user")
	public Represent userindex() {
		logger.debug(" enter index ");
		
		List<UserModel> userList = userQuery.getUserList();
		

		
		return new Html("/WEB-INF/jsp/index.jsp", "userList", userList);

	}

	@Path("/result")
	public Represent result() {
		
		return new Html("/WEB-INF/jsp/result.jsp");

	}
	
	@RequiresAuthentication
	@RequiresPermissions("dummy:admin")
	@Path("/newUser")
	public Represent newUser() {
		
		return new Html("/WEB-INF/jsp/newUser.jsp");

	}
	
	@Path("/single_upload")
	public Represent single_upload() {
		
		return new Html("/WEB-INF/jsp/single_upload.jsp");

	}
	
	@RequiresAuthentication
	@Path("/user/:userId")
	public Represent get(int userId) {
		UserModel user = getUser(Integer.toString(userId));
		return new Html("/WEB-INF/jsp/editUser.jsp", "user", user);
	}

	@RequiresAuthentication
	@RequiresPermissions("dummy:admin")
	@Path("post:/users")
	public Represent post(UserModel user) {
		if (validate(user))
			return new Html("/WEB-INF/jsp/newUser.jsp", "user", user);
		String userId = Integer.toString(user.hashCode());
		user.setUserId(userId);
		roleAssigner.assignDomainEvents(user);

		UploadFile uploadFile = new UploadFile();
		FormFile file = (FormFile) request.getSession().getAttribute("formFile");
		if (file != null) {
			uploadFile.setParentId(userId);
			uploadFile.setData(file.getFileData());
			uploadFile.setName(file.getOriginalFilename());
			uploadFile.setContentType(file.getContentType());
			uploadFile.setDescription(file.getOriginalFilename() + " description");
			uploadFile.setSize(file.getFileSize());
			
		}
		user.es.created(new UserCreatedEvent(user, uploadFile));
		return new Redirect("/result");
	}

	private boolean validate(UserModel user) {
		validation.use(new NotNull(user.getUsername())).message("name", "不能为空");
		validation.use(new NotEmail(user.getEmail())).message("email", "邮件格式不对");
		validation.use(new NotEqual(user.getPassword(), user.getVerifypassword())).message("password", "两次输入不一致");
		return validation.hasErrors() ? true : false;
	}

	public UserModel getUser(String userId) {
		logger.debug(" enter UserModel getUser(String userId) ");
		UserModel userModel = entityFactory.getUser(userId);
		return userModel;
	}

	@Path("/showUpload")
	public Represent showUpload(String pid) {
		UserModel user = entityFactory.getUser(pid);
		Image image = new Image();
		image.setData(user.getUploadFile().getData(), user.getUploadFile().getContentType());
		return image;
	}

	@RequiresAuthentication
	@Path("put:/user")
	public Represent update(UserModel user) {
		UserModel userold = getUser(user.getUserId());
		if (userold == null)
			return new Redirect("/");

		UserModel oldUser = this.getUser(user.getUserId());
		FormFile file = (FormFile) request.getSession().getAttribute("formFile");
		//UploadVO uploadVO = null;
		//if (file != null) {
		//	uploadVO = new UploadVO(file.getFileName(), file.getFileData(), file.getContentType());
		//}
		UploadFile uploadFile = new UploadFile();
		if (file != null) {
			uploadFile.setParentId(oldUser.getUserId());
			uploadFile.setData(file.getFileData());
			uploadFile.setName(file.getOriginalFilename());
			uploadFile.setContentType(file.getContentType());
			uploadFile.setDescription(file.getOriginalFilename() + " description");
			uploadFile.setSize(file.getFileSize());
			
		}

		commandHandler.saveUser(oldUser, new UpdateCommand(user, uploadFile));
		return new Redirect("/result");
	}

	@RequiresAuthentication
	@Path("delete:/user/:userId")
	public Represent delete(UserModel user) {
		UserModel oldUser = this.getUser(user.getUserId());
		oldUser.es.deleted(new UserDeletedEvent(user.getUserId()));
		return new Redirect("/result");
	}
	
	
	@RequiresAuthentication
	@Path("delete:/showUpload/:userId")
	public Represent deleteUpload(UserModel user) {
		UserModel oldUser = this.getUser(user.getUserId());
		
		oldUser.getAttachment().setUploadFile(null);
		oldUser.getAttachment().picSetNull();
		
		
		oldUser.es.deleteUpload(new UploadDeletedEvent(user.getUserId()));
		return new Redirect("/result");
	}
	
}
