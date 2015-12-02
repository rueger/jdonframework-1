package com.jdonframework.test.security.shiro;



import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.jdon.controller.AppUtil;

public class NonConfigurationCreateTest {

	@Test
    public void testAuthentication() {
		
		AppUtil appUtil = new AppUtil();
		
		appUtil.getComponentInstance("SecurityManager");
	
		Subject subject = SecurityUtils.getSubject();

		AuthenticationToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
        
        appUtil.clear();
		
	}
	
	@Test 
	public void testAuthorization() {
		
        AppUtil appUtil = new AppUtil();
		
		appUtil.getComponentInstance("SecurityManager");
	
		Subject subject = SecurityUtils.getSubject();

		AuthenticationToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
        
        Assert.assertTrue(subject.isAuthenticated());
        
        //判断拥有权限：user:create
        Assert.assertTrue(subject.isPermitted("user:create"));
       

        
        
        appUtil.clear();
		
		
	}
	

}
