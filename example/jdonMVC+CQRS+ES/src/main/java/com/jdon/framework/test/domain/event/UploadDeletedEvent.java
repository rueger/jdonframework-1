package com.jdon.framework.test.domain.event;

public class UploadDeletedEvent {
	
	private final String userId;

	public UploadDeletedEvent(String userId) {
		super();
		this.userId = userId;
		
	}

	public String getUserId() {
		return userId;
	}

}
