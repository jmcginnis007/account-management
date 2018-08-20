package com.fbm.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {
	
	private String issuerClaimVerifier;
	private String authServerUrl;
	private String clientId;
	private String clientSecret;
	private String jwtPublicKeyFilename;

	public String getIssuerClaimVerifier() {
		return issuerClaimVerifier;
	}

	public void setIssuerClaimVerifier(String issuerClaimVerifier) {
		this.issuerClaimVerifier = issuerClaimVerifier;
	}

	public String getAuthServerUrl() {
		return authServerUrl;
	}

	public void setAuthServerUrl(String authServerUrl) {
		this.authServerUrl = authServerUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getJwtPublicKeyFilename() {
		return jwtPublicKeyFilename;
	}

	public void setJwtPublicKeyFilename(String jwtPublicKeyFilename) {
		this.jwtPublicKeyFilename = jwtPublicKeyFilename;
	}
}
