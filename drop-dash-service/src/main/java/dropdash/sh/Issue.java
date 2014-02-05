package dropdash.sh;

import java.io.IOException;

public class Issue extends FilteredCommand {

	public Issue() {
		super("/usr/bin/lsb_release -ds;/bin/uname -r");
	}

	@Override
	public String apply(String input) {
		try {
			return json(input);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
