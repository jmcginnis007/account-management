package com.fbm.account.jpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fbm.account.model.Authorities;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority{

	private static final long serialVersionUID = -6616256219712102292L;

	@JsonIgnore
	@EmbeddedId
	private AuthorityIdenity authorityIdentity;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private User user;
	
	@Column(name = "update_ts")
	private Date updateTs;
	
	@Column(name = "insert_ts")
	private Date insertTs;

	public Date getUpdateTs() {
		return updateTs;
	}

	public Date getInsertTs() {
		return insertTs;
	}
	
	public AuthorityIdenity getAuthorityIdentity() {
		return authorityIdentity;
	}
	
	//for creating a new user
	public Authority(User user, Authorities authority) {
		super();
		this.authorityIdentity = new AuthorityIdenity(user.getUserId(), authority.toString());
		this.insertTs = new Date();
		this.updateTs = new Date();
		this.user = user;
	}
	
	//for JPA only
	protected Authority() {}

	@Override
	public String getAuthority() {
		return authorityIdentity.getAuthority();
	}

	@Override
	public String toString() {
		return "Authority [authorityIdentity=" + authorityIdentity + ", user=" + user + ", updateTs=" + updateTs
				+ ", insertTs=" + insertTs + "]";
	}
}
