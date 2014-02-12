package dropdash;

import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;

public class DropdashConfiguration extends Configuration {

	HttpConfiguration http;
	
	String authenticationRealm;

	String authenticationUser;
	
	String authenticationPassword;

	public HttpConfiguration getHttp() {
		return http;
	}

	public void setHttp(HttpConfiguration http) {
		this.http = http;
	}

	public String getAuthenticationRealm() {
		return authenticationRealm;
	}

	public String getAuthenticationUser() {
		return authenticationUser;
	}

	public String getAuthenticationPassword() {
		return authenticationPassword;
	}

}
