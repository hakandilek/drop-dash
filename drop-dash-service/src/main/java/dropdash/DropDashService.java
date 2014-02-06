package dropdash;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class DropDashService extends Service<DropdashConfiguration> {

	@Override
	public void initialize(Bootstrap<DropdashConfiguration> bootstrap) {
		bootstrap.addBundle(new DropdashBundle());
	}

	@Override
	public void run(DropdashConfiguration configuration, Environment environment) throws Exception {
	}

}
