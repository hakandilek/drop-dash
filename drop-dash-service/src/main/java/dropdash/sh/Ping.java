package dropdash.sh;

import static dropdash.sh.FilteredCommand.json;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Ping implements Command {

	int pingCount = 2;
	List<String> hosts = Lists.newArrayList("google.com", "gnu.org", "github.com", "wikipedia.org");
	Map<String, Command> commands;

	String output;
	StringBuffer error;

	public Ping() {
		commands = Maps.newTreeMap();
		for (String host : hosts) {
			String cmd = String
					.format("/bin/ping -qc %d %s |  /usr/bin/awk -F/ '/^rtt/ { print $5 }'", pingCount, host);
			commands.put(host, new ShellCommand(cmd));
		}
	}

	@Override
	public String execute() {
		error = new StringBuffer();

		List<List<String>> out = Lists.newArrayList();

		for (String host : hosts) {
			Command cmd = commands.get(host);
			String o = cmd.execute();

			if (!Strings.isNullOrEmpty(cmd.getError())) {
				error.append(cmd.getError());
				o = "N/A";
			}

			out.add(Lists.newArrayList(host, o.trim()));
		}

		output = json(out);
		return output;
	}

	public int getResult() {
		return 0;
	}

	public String getError() {
		return error + "";
	}

	public String getOutput() {
		return output;
	}

}
