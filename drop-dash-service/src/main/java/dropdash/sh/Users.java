package dropdash.sh;

import java.util.List;

import com.google.common.collect.Lists;

public class Users extends FilteredCommand {

	public Users() {
		super(
				"/usr/bin/awk -F: '{ if ($3<=499) print \"system,\"$1\";\"$6; else print \"user,\"$1\";\"$6; }' < /etc/passwd");
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
