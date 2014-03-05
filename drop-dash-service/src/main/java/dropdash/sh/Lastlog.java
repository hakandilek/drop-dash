package dropdash.sh;

import java.util.List;

import com.google.common.collect.Lists;

public class Lastlog extends FilteredCommand {

	public Lastlog() {
		super("/usr/bin/lastlog --time 365 | /usr/bin/awk '{print $1\";\"$3\";\"$4\" \"$5\" \"$6\" \"$7\" \"$8}'");
	}

	@Override
	public String apply(String input) {
		List<List<String>> output = Lists.newArrayList();
		List<String> lines = lineSplit(input);
		for (int i = 0; i < lines.size(); i++) {
			if (i > 0) {
				String line = lines.get(i);
				output.add(semicolSplit(line));
			}
		}
		return json(output);
	}

}
