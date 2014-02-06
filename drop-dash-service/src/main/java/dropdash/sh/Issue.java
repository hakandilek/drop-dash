package dropdash.sh;


public class Issue extends FilteredCommand {

	public Issue() {
		super("/usr/bin/lsb_release -ds;/bin/uname -r");
	}

	@Override
	public String apply(String input) {
		return json(input);
	}

}
