package com.tan.managers.model;

public class ResponseObject<T> {
	private String code = "200";
	private T data;
	private String message ;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseObject [code=" + code + ", data=" + data + ", message=" + message + "]";
	}
	
}
