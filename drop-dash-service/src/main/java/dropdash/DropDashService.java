package dropdash;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import dropdash.resources.DropdashResource;
import dropdash.sh.DropdashShellService;
import dropdash.ui.DropdashUIBundle;

public class DropDashService extends Service<DropdashConfiguration> {

	@Override
	public void initialize(Bootstrap<DropdashConfiguration> bootstrap) {
		bootstrap.setName("drop-dash");
		bootstrap.addBundle(new DropdashUIBundle());
	}

	@Override
	public void run(DropdashConfiguration configuration, Environment environment) throws Exception {
		DropdashShellService service = new DropdashShellService();
		DropdashController controller = new DropdashController(service );
		environment.addResource(new DropdashResource(controller ));
	}

}
