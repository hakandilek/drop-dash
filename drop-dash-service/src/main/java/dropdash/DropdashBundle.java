package dropdash;

import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;

import dropdash.resources.DropdashResource;
import dropdash.sh.DropdashShellService;
import dropdash.ui.DropdashUIBundle;

public abstract class DropdashBundle<T extends Configuration> implements ConfiguredBundle<T>, HttpConfigurationStrategy<T> {

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
		bootstrap.setName("drop-dash");
		bootstrap.addBundle(new DropdashUIBundle());
	}

	@Override
	public void run(T configuration, Environment environment) throws Exception {
		DropdashShellService service = new DropdashShellService();
		DropdashController controller = new DropdashController(service );
		environment.addResource(new DropdashResource(controller ));
	}


}
