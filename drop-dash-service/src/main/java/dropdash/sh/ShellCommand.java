package dropdash.sh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Function;

public class ShellCommand implements Command {

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
		private StreamReader outReader;
		private StreamReader errReader;

		public ProcessRunner(String[] command) throws IOException {
			process = new ProcessBuilder(command).start();
			outReader = new StreamReader(process.getInputStream());
			errReader = new StreamReader(process.getInputStream());
			outReader.start();
			errReader.start();
		}

		public String getError() {
			return errReader.getOutput();
		}

		public String getOutput() {
			return outReader.getOutput();
		}

		public int waitFor() throws InterruptedException {
			return process.waitFor();
		}

	}

	class StreamReader extends Thread {

		private InputStream in;
		private StringBuffer sb;

		public StreamReader(InputStream inputStream) {
			this.in = inputStream;
			this.sb = new StringBuffer();
		}

		@Override
		public void run() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public String getOutput() {
			return sb.toString();
		}

	}
}
