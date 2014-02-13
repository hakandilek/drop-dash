package dropdash.sh;

import static dropdash.sh.FilteredCommand.json;
import static dropdash.sh.FilteredCommand.spaceSplit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class Where implements Command {

	private static final Logger log = LoggerFactory.getLogger(Where.class);

	final String binaries = "php node mysql vim python ruby java apache2 nginx openssl vsftpd make";
	
	StringBuffer error;
	String out;

	@Override
	public String execute() {
		out = null;
		error = new StringBuffer();
		List<List<String>> list = Lists.newArrayList();

		List<String> bins = spaceSplit(binaries);
		for (String binary : bins) {
			Command which = new ShellCommand("command -v " + binary);
			String location = which.execute();
			error.append(which.getError());
			if (Strings.isNullOrEmpty(location)) {
				location = "Not Installed";
			}
			List<String> binval = Lists.newArrayList(binary, location);
			list.add(Lists.newArrayList(binval));
		}
		try {
			out = json(list);
		} catch (Exception e) {
			log.error("cannot read nums", e);
		}
		return out;
	}

	@Override
	public int getResult() {
		return 0;
	}

	@Override
	public String getError() {
		return error + "";
	}

	@Override
	public String getOutput() {
		return out;
	}

}
