package dropdash;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;

import dropdash.auth.SimpleAuthenticator;
import dropdash.auth.Subject;
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
		
		String realm = getAuthenticationRealm(configuration);
		String user = getAuthenticationUser(configuration);
		String password = getAuthenticationPassword(configuration);
		Authenticator<BasicCredentials, Subject> authenticator = new SimpleAuthenticator(user, password);
		BasicAuthProvider<Subject> authProvider = new BasicAuthProvider<Subject>(authenticator, realm);
		environment.addFilter(new BasicHttpAuthenticationFilter(), "/dash/*");
		environment.addProvider(authProvider);
	}


}
