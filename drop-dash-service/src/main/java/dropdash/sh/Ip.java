package dropdash.sh;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import static dropdash.sh.FilteredCommand.*;

public class Ip implements Command {

	Command ip, ifconFig, curl;

	String output;
	StringBuffer error;

	public Ip() {
		ip = new ShellCommand("/bin/ip -oneline link show | /usr/bin/awk '{print $2}' | /bin/sed \"s/://\"");
		ifconFig = new ShellCommand(
				"/sbin/ifconfig | /bin/grep -B1 \"inet addr\" | /usr/bin/awk '{ if ( $1 == \"inet\" ) { print $2 } else if ( $2 == \"Link\" ) { printf \"%s:\";$1 } }' | /usr/bin/awk -F: '{ print $1\";\"$3 }'");
		curl = new ShellCommand("curl http://ipecho.net/plain; echo");
	}

	@Override
	public String execute() {
		error = new StringBuffer();
		output = ip.execute();
		if (Strings.isNullOrEmpty(ip.getError())) {
			error.append(ip.getError());

			List<String> split = lineSplit(output);
			output = spaceJoin(split);

			String cmd = String
					.format("for interface in %s;do for family in inet inet6;do /bin/ip -oneline -family $family addr show $interface | /bin/grep -v fe80 | /usr/bin/awk '{print $2\";\"$4}';done;done",
							output);
			Command fam = new ShellCommand(cmd);
			output = fam.execute();
			error.append(fam.getError());
		} else {
			output = ifconFig.execute();
			error.append(ifconFig.getError());
		}
		String extIp = curl.execute();
		error.append(curl.getError());

		List<List<String>> out = Lists.newArrayList();
		out.add(Lists.newArrayList("external ip", extIp));

		List<String> lines = lineSplit(output);
		for (String line : lines) {
			out.add(Lists.newArrayList(semicolSplit(line)));
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
