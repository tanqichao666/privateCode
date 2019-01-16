package com.tan.managers.model;

import java.io.Serializable;

public class Function implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String functionName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
}
