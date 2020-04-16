package com.douzone.mysite.dto;

public class JsonResult {
	private String result;  /* "success" or "fail" */
	private Object data;    /* if success Data set */
	private String message; /* if fail, Error Message set */

	private JsonResult() {
	}	 
	
	
	private JsonResult(Object data) {
		this.result = "success";
		this.data = data;
	} 
	
	private JsonResult(String message) {
		this.result = "fail";
		this.message = message;
	}
	
	public static JsonResult success(Object data) {
		/*
		 * JsonResult jsonResult = new JsonResult();
		 * jsonResult.setResult("success"); 
		 * jsonResult.setMessage(data);
		 */		
		return new JsonResult(data);
	}
	
	public static JsonResult fail(String message) {
		
		/*
		 * JsonResult jsonResult = new JsonResult();
		 * jsonResult.setResult("fail"); 
		 * jsonResult.setMessage(message);
		 */
		return new JsonResult(message);
	}	
	
	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	
	
	
}
