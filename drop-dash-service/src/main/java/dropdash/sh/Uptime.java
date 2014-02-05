package dropdash.sh;

import java.io.IOException;
import java.util.List;

public class Uptime extends FilteredCommand {

	private static final double MINS = 60*60*100;

	public Uptime() {
		super("/bin/cat /proc/uptime");
	}

	@Override
	public String apply(String input) {
		List<String> times = spaceSplit(input);
		String timeString = times.get(0);
		Double value = Double.valueOf(timeString);
		value = value / MINS;
		try {
			return json(value);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
