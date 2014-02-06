package dropdash.sh;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import static dropdash.sh.FilteredCommand.*;

public class Ip implements Command {

	Command ip, ifconFig, curl;

	public Ip() {
		ip = new ShellCommand("/bin/ip -oneline link show | /usr/bin/awk '{print $2}' | /bin/sed \"s/://\"");
		ifconFig = new ShellCommand("/sbin/ifconfig | /bin/grep -B1 \"inet addr\" | /usr/bin/awk '{ if ( $1 == \"inet\" ) { print $2 } else if ( $2 == \"Link\" ) { printf \"%s:\";$1 } }' | /usr/bin/awk -F: '{ print $1\";\"$3 }'");
		curl = new ShellCommand("curl http://ipecho.net/plain; echo");
	}

	@Override
	public String execute() {
		String result = ip.execute();
		if(Strings.isNullOrEmpty(ip.getError())) {
			List<String> split = lineSplit(result);
			result = spaceJoin(split);
			
			String cmd = String.format("for interface in %s;do for family in inet inet6;do /bin/ip -oneline -family $family addr show $interface | /bin/grep -v fe80 | /usr/bin/awk '{print $2\";\"$4}';done;done", result);
			Command fam = new ShellCommand(cmd);
			result = fam.execute();
		} else {
			result = ifconFig.execute();
		}
		String extIp = curl.execute();
		List<List<String>> out = Lists.newArrayList();
		out.add(Lists.newArrayList("external ip", extIp));
		
		List<String> lines = lineSplit(result);
		for (String line : lines) {
			out.add(Lists.newArrayList(semicolSplit(line)));
		}
		
		result = json(out);
		return result;
	}

	public int getResult() {
		return 0;
	}

	public String getError() {
		return null;
	}

	public String getOutput() {
		return null;
	}

}
