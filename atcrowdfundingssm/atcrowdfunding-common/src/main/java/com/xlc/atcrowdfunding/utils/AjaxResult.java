package com.xlc.atcrowdfunding.utils;

public class AjaxResult {
	
	private boolean success;
	
	private String message;
	
	private Page page;

	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	
}
