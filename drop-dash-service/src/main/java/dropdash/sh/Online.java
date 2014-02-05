package dropdash.sh;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

public class Online extends FilteredCommand {

	public Online() {
		super("/usr/bin/w -h | /usr/bin/awk \'{print $1\";\"$3\";\"$4\";\"$5}\'");
	}

	@Override
	public String apply(String input) {
		List<List<String>> output = Lists.newArrayList();
		List<String> lines = lineSplit(input);
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			output.add(semicolSplit(line));
		}
		try {
			return json(output);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
