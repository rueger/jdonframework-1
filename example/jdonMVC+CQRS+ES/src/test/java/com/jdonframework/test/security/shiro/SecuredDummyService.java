/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jdonframework.test.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.sql.Timestamp;



/**
 * Secured implementation of te dummy service that requires some permissions to execute.
 *
 */
public class SecuredDummyService implements DummyService {

    @RequiresAuthentication
    @RequiresPermissions("dummy:admin")
    public void change() {
    	//@RequiresAuthentication begin
    	if (!SecurityUtils.getSubject().isAuthenticated()) {
            throw new AuthorizationException("未认证");
        }
    	//@RequiresAuthentication end
    	// @RequiresPermissions begin
    	if (!SecurityUtils.getSubject().isPermitted("dummy:admin")) {
            throw new AuthorizationException("未授权");
        }
    	// @RequiresPermissions end
        retrieve();
        log("change");
        peek();
    }

    public void anonymous() {
        log("anonymous");
    }

    @RequiresGuest
    public void guest() {
    	//@RequiresUser begin
    	Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = (PrincipalCollection) currentUser.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            //known identity - not a guest:
            throw new AuthorizationException("@RequiresUser");
        }
        //@RequiresUser end
    	
    	
        log("guest");
    }

    @RequiresUser
    public void peek() {
    	//@RequiresUser begin
    	Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        if (principals == null || principals.isEmpty()) {
            //no identity - they're anonymous, not allowed:
            throw new AuthorizationException("@RequiresUser");
        }
    	//@RequiresUser end
        log("peek");
    }

    @RequiresPermissions("dummy:user")
    public void retrieve() {
    	// @RequiresPermissions begin
    	if (!SecurityUtils.getSubject().isPermitted("dummy:user")) {
            throw new AuthorizationException("未授权");
        }
    	// @RequiresPermissions end
        log("retrieve");
    }

    public void log(String aMessage) {
        if (aMessage != null) {
            System.out.println(new Timestamp(System.currentTimeMillis()).toString() + " [" + Thread.currentThread() + "] * LOG * " + aMessage);
        } else {
            System.out.println("\n\n");
        }
    }

}
