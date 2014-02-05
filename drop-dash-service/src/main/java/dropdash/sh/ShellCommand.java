package dropdash.sh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellCommand {

	private String command;

	public ShellCommand(String command) {
		this.command = command;
	}

	public String execute() {
		StringBuffer output = new StringBuffer();

		Process process;
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(command);
			process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}

}
