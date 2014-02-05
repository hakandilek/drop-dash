package dropdash.sh;

import java.io.IOException;
import java.util.List;

public class Mem extends FilteredCommand {

	public Mem() {
		super("/usr/bin/free -tmo | /usr/bin/awk \'{print $1\";\"$2\";\"$3-$6-$7\";\"$4+$6+$7}\'");
	}

	@Override
	public String apply(String input) {
		List<String> lines = lineSplit(input);
		String line = lines.get(1);
		List<String> out = semicolSplit(line);
		try {
			return json(out);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
