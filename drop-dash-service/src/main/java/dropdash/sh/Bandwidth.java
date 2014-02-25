package dropdash.sh;

import static dropdash.sh.FilteredCommand.json;

import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class Bandwidth implements Command {

	Command tx, rx;

	String output;
	StringBuffer error;

	public Bandwidth() {
		tx = new ShellCommand("cat /sys/class/net/eth0/statistics/tx_bytes");
		rx = new ShellCommand("cat /sys/class/net/eth0/statistics/rx_bytes");
	}

	@Override
	public String execute() {
		error = new StringBuffer();

		String tx0Str = tx.execute();
		String rx0Str = rx.execute();
		if (Strings.isNullOrEmpty(tx.getError()))
			error.append(tx.getError());
		if (Strings.isNullOrEmpty(rx.getError()))
			error.append(rx.getError());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			error.append(e + "");
		}

		String tx1Str = tx.execute();
		String rx1Str = rx.execute();
		if (Strings.isNullOrEmpty(tx.getError()))
			error.append(tx.getError());
		if (Strings.isNullOrEmpty(rx.getError()))
			error.append(rx.getError());

		int tx0 = Integer.parseInt(tx0Str.trim());
		int tx1 = Integer.parseInt(tx1Str.trim());
		int rx0 = Integer.parseInt(rx0Str.trim());
		int rx1 = Integer.parseInt(rx1Str.trim());
		int tx = (tx1 - tx0) / 2;
		int rx = (rx1 - rx0) / 2;
		
		Map<String, Integer> out = Maps.newTreeMap();
		out.put("tx", tx);
		out.put("rx", rx);

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
