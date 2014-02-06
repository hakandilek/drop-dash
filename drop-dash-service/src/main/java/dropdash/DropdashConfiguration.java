package dropdash;

import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;

public class DropdashConfiguration extends Configuration {

	HttpConfiguration http;
	
	public HttpConfiguration getHttp() {
		return http;
	}

	public void setHttp(HttpConfiguration http) {
		this.http = http;
	}

}
