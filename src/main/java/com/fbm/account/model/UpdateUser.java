package com.fbm.account.model;

import javax.validation.constraints.Size;

public class UpdateUser {
	
	@Size(min=8, max=50, message="Password should have at least 8 characters but no more than 50")
	private String password;
	
	private Boolean enabled;
	private Boolean newDocumentEmailEnabled;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getNewDocumentEmailEnabled() {
		return newDocumentEmailEnabled;
	}
	public void setNewDocumentEmailEnabled(Boolean newDocumentEmailEnabled) {
		this.newDocumentEmailEnabled = newDocumentEmailEnabled;
	}
	
	@Override
	public String toString() {
		return "UpdateUser [password=" + password + ", enabled=" + enabled + ", newDocumentEmailEnabled="
				+ newDocumentEmailEnabled + "]";
	}

}
