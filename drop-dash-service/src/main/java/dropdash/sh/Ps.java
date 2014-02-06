package dropdash.sh;

import java.util.List;

import com.google.common.collect.Lists;

public class Ps extends FilteredCommand {

	public Ps() {
		super("/bin/ps aux | /usr/bin/awk \'NR>1{print $1\";\"$2\";\"$3\";\"$4\";\"$5\";\"$6\";\"$7\";\"$8\";\"$9\";\"$10\";\"$11}\'");
	}

	@Override
	public String apply(String input) {
		List<List<String>> output = Lists.newArrayList();
		List<String> lines = lineSplit(input);
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			output.add(semicolSplit(line));
		}
		return json(output);
	}

}
