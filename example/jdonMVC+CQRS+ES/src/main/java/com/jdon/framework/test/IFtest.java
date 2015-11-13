package com.jdon.framework.test;

import org.apache.ibatis.annotations.Select;

public interface IFtest {
	
		  @Select("select userId from testuser where userId = #{id}")
		  String selectUserId(int id);
		
}
