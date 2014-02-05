package dropdash.sh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Function;

public class ShellCommand {

	private String[] command;
	protected Function<String, String> filter;

	public ShellCommand(String shell, String command, Function<String, String> filter) {
		this.filter = filter;
		this.command = new String[] { shell, "-c", command };
	}

	public ShellCommand(String command, Function<String, String> filter) {
		this("bash", command, filter);
	}

	public ShellCommand(String shell, String command) {
		this(shell, command, null);
	}

	public ShellCommand(String command) {
		this("bash", command, null);
	}

	public String execute() {
		StringBuffer sb = new StringBuffer();

		Process process;
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(command);
			process.waitFor();
			InputStream is = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String result = sb.toString();
		if (filter != null) {
			result = filter.apply(result);
		}
		return result;
	}

}
