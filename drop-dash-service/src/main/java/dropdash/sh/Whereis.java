package dropdash.sh;

import java.util.List;

import com.google.common.collect.Lists;

public class Whereis extends FilteredCommand {

	public Whereis() {
		super(
				"/usr/bin/whereis php node mysql vim python ruby java apache2 nginx openssl vsftpd make | /usr/bin/awk '{ split($1, a, \":\");if (length($2)==0) print a[1]\";Not Installed\"; else print a[1]\";\"$2;}'");
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
