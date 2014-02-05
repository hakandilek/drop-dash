package dropdash.sh;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.*;

public class Uptime extends FilteredCommand {

	public Uptime() {
		super("/usr/bin/cut -d. -f1 /proc/uptime");
	}

	@Override
	public String apply(String input) {
		List<String> times = spaceSplit(input);
		String timeString = times.get(0);
		double totalSecs = Double.valueOf(timeString);
		double totalMins = totalSecs / 60;
		double totalHours = totalMins / 60;

		int days = (int) floor(totalHours / 24);
		int hours = (int) floor(totalHours - (days * 24));
		int mins = (int) floor(totalMins - (days * 60 * 24) - (hours * 60));

		StringBuffer sb = new StringBuffer();
		if (days != 0) {
			sb.append(days).append(" days ");
		}

		if (hours != 0) {
			sb.append(hours).append(" hours ");
		}

		if (mins != 0) {
			sb.append(mins).append(" minutes");
		}

		try {
			String value = sb.toString();
			return json(value);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
