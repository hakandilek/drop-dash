package dropdash.sh;

import java.io.IOException;


public class Hostname extends FilteredCommand {

	public Hostname() {
		super("hostname");
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
