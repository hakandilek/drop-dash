package dropdash;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.HttpConfiguration;

public class DropdashService extends Service<DropdashConfiguration> {

	@Override
	public void initialize(Bootstrap<DropdashConfiguration> bootstrap) {
		bootstrap.addBundle(new DropdashBundle<DropdashConfiguration>() {
			@Override
			public HttpConfiguration getHttp(DropdashConfiguration configuration) {
				return configuration.getHttp();
			}
		});
	}

	@Override
	public void run(DropdashConfiguration configuration, Environment environment) throws Exception {
	}

}
