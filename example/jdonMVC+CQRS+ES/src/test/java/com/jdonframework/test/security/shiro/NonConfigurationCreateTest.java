package com.jdonframework.test.security.shiro;



import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.jdon.controller.AppUtil;

public class NonConfigurationCreateTest {

	@Test
    public void test() {
		
		AppUtil appUtil = new AppUtil();
		
		appUtil.getComponentInstance("SecurityManager");
	
		Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
        
        appUtil.clear();
		
	}

}
