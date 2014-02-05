package dropdash.ui;

import com.yammer.dropwizard.assets.AssetsBundle;

public class DropdashUIBundle extends AssetsBundle {

	public DropdashUIBundle() {
		super("/res", "/dash", "index.html");
	}

}
