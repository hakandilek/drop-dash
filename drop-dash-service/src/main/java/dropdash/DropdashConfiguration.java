package dropdash;

import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;

public class DropdashConfiguration extends Configuration {

	HttpConfiguration http;
	
	private int requestCacheLimit;
	private int reportCacheLimit;

	public int getRequestCacheLimit() {
		return requestCacheLimit;
	}

	public void setRequestCacheLimit(int requestCacheLimit) {
		this.requestCacheLimit = requestCacheLimit;
	}

	public int getReportCacheLimit() {
		return reportCacheLimit;
	}

	public void setReportCacheLimit(int reportCacheLimit) {
		this.reportCacheLimit = reportCacheLimit;
	}

	public HttpConfiguration getHttp() {
		return http;
	}

	public void setHttp(HttpConfiguration http) {
		this.http = http;
	}

}
