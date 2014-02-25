package dropdash.sh;



public class Time extends FilteredCommand {

	public Time() {
		super("/bin/date");
	}

	@Override
	public String apply(String input) {
		return json(input.trim());
	}

}
