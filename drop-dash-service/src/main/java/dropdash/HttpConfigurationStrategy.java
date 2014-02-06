package dropdash;

import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;

public interface HttpConfigurationStrategy<T extends Configuration> {
	
	HttpConfiguration getHttp(T configuration);
}
