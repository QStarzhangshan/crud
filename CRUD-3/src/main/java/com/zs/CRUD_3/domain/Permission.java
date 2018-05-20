package com.zs.CRUD_3.domain;

import java.util.List;

public class Permission {
	
	private Integer id;
	private String permission_name;
	private String permission_url;
	private List<Role> roles;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public String getPermission_url() {
		return permission_url;
	}
	public void setPermission_url(String permission_url) {
		this.permission_url = permission_url;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
