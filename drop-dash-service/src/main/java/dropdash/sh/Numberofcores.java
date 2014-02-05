package dropdash.sh;


public class Numberofcores extends ShellCommand {

	public Numberofcores() {
		super("/bin/grep -c ^processor /proc/cpuinfo");
	}

}
