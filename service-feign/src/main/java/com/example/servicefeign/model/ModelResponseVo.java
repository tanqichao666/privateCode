package com.example.servicefeign.model;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ModelResponseVo<T> {
	
	private String errorMessage = "";
	private int status = 0;
	private T datas;
	private int dataCount;
	public T getDatas() {
		return datas;
	}
	public void setDatas(T datas) {
		this.datas = datas;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
