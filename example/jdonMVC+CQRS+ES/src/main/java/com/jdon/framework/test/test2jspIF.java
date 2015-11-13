package com.jdon.framework.test;

import org.apache.ibatis.annotations.Select;

public interface test2jspIF {
	
		  @Select("select userId from testuser where userId = #{id}")
		  String selectUserId(int id);
		  
		  @Select("select name from testuser where userId = #{id}")
		  String selectUsername(int id);
		
}
