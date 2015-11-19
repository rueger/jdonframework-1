package com.jdon.framework.test.query;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface QueryIF {
	
	@Select("select userid from testuser")
	public abstract List<String> getUsersId();
	

}
