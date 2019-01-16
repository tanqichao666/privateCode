package com.tan.managers.model;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private String roleName;
	private String id;
	private List<Function> functions ;
	
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
