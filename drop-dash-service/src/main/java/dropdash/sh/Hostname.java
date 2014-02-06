package dropdash.sh;



public class Hostname extends FilteredCommand {

	public Hostname() {
		super("hostname");
	}

	@Override
	public String apply(String input) {
		return json(input);
	}

}
