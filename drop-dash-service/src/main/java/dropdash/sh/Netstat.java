package dropdash.sh;

import java.util.List;

import com.google.common.collect.Lists;

public class Netstat extends FilteredCommand {

	public Netstat() {
		super("/bin/netstat -tunp|/usr/bin/awk '{print $1\";\"$2\";\"$3\";\"$4\";\"$5\";\"$6\";\"$7}'");
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
