package dropdash;

import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;

public interface HttpConfigurationStrategy<T extends Configuration> {
	
	HttpConfiguration getHttp(T configuration);
	
	String getAuthenticationRealm(T configuration);
	String getAuthenticationUser(T configuration);
	String getAuthenticationPassword(T configuration);

}
