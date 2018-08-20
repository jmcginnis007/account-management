package com.fbm.account.jpa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="users")
public class User {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "user")
	private Set<Authority> authorities = new HashSet<>();
	
	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "new_document_email_enabled")
	private Boolean newDocumentEmailEnabled;
	
	@Column(name = "insert_ts")
	private Date insertTs;
	
	@Column(name = "update_ts")
	private Date updateTs;
	
	@Column(name = "last_login_ts")
	private Date lastLoginTs;
	
	@Column(name = "source_system")
	private String sourceSystem;
	
	@Column(name = "source_user")
	private String sourceUser;

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getNewDocumentEmailEnabled() {
		return newDocumentEmailEnabled;
	}

	public Date getInsertTs() {
		return insertTs;
	}

	public Date getUpdateTs() {
		return updateTs;
	}

	public Date getLastLoginTs() {
		return lastLoginTs;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public String getSourceUser() {
		return sourceUser;
	}
	
	public void addAuthority(Authority newAuthority) {
		this.authorities.add(newAuthority);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setNewDocumentEmailEnabled(Boolean newDocumentEmailEnabled) {
		this.newDocumentEmailEnabled = newDocumentEmailEnabled;
	}

	// for creating a new user
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = true;
		this.insertTs = new Date();
		this.updateTs = new Date();
		this.newDocumentEmailEnabled = false;
	}

	//for JPA only
	protected User() {}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", newDocumentEmailEnabled=" + newDocumentEmailEnabled + ", insertTs=" + insertTs + ", updateTs="
				+ updateTs + ", lastLoginTs=" + lastLoginTs + ", sourceSystem=" + sourceSystem + ", sourceUser="
				+ sourceUser + "]";
	}
}
