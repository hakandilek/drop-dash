package dropdash.sh;

import static dropdash.sh.FilteredCommand.json;
import static dropdash.sh.FilteredCommand.semicolSplit;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class Loadavg implements Command {

	private static final Logger log = LoggerFactory
			.getLogger(Loadavg.class);

	Command loadavg, numberofcores;
	StringBuffer error;
	String out;

	public Loadavg() {
		loadavg = new ShellCommand("/bin/cat /proc/loadavg | /usr/bin/awk '{print $1\";\"$2\";\"$3}'");
		numberofcores = new Numberofcores();
	}

	@Override
	public String execute() {
		out = null;
		error = new StringBuffer();
		List<List<Serializable>> list= Lists.newArrayList();

		String numCoresStr = numberofcores.execute();
		error.append(numberofcores.getError());
		try {
			int numCores = Integer.parseInt(numCoresStr.trim());

			String las = loadavg.execute();
			error.append(loadavg.getError());
			List<String> loads = semicolSplit(las);
			for (String load : loads) {
				double value = Double.parseDouble(load);
				Serializable[] l = new Serializable[] { "" + (100 * value), (int) (100 * value / numCores) };
				list.add(Lists.newArrayList(l));
			}
			
			out = json(list);
		} catch (NumberFormatException e) {
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
