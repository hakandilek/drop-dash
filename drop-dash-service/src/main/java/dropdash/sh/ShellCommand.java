package dropdash.sh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

public class ShellCommand implements Command {

	private static final Logger log = LoggerFactory.getLogger(ShellCommand.class);

	private String[] command;
	protected Function<String, String> filter;
	private String output;
	private String error;
	private int result;

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

	@Override
	public String execute() {
		result = -1;
		try {
			ProcessRunner pr = new ProcessRunner(command);
			result = pr.waitFor();
			output = pr.getOutput();
			error = pr.getError();
			if (filter != null) {
				output = filter.apply(output);
			}
			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getOutput() {
		return output;
	}

	@Override
	public String getError() {
		return error;
	}

	@Override
	public int getResult() {
		return result;
	}

	class ProcessRunner {

		private Process process;
		private InputStream inStream;
		private InputStream erStream;
		private String err;
		private String out;

		public ProcessRunner(String[] command) throws IOException {
			process = new ProcessBuilder(command).start();
			inStream = process.getInputStream();
			erStream = process.getErrorStream();
		}

		public String getError() {
			return err;
		}

		public String getOutput() {
			return out;
		}

		public int waitFor() throws InterruptedException {
			int res = process.waitFor();

			out = read(inStream);
			err = read(erStream);

			return res;
		}

		private String read(InputStream in) {
			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				log.error("cannot read InputStream", e);
			}
			return sb.toString();
		}

	}
}
