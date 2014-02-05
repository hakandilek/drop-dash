package dropdash.sh;

public class DropdashShellService {
	
	ShellCommand df = new ShellCommand("/bin/df -h|awk '{print $1\",\"$2\",\"$3\",\"$4\",\"$5\",\"$6}'");

	public String df() {
		return df.execute();
	}

	public String hostname() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ip() {
		// TODO Auto-generated method stub
		return null;
	}

	public String issue() {
		// TODO Auto-generated method stub
		return null;
	}

	public String loadavg() {
		// TODO Auto-generated method stub
		return null;
	}

	public String mem() {
		// TODO Auto-generated method stub
		return null;
	}

	public String numberofcores() {
		// TODO Auto-generated method stub
		return null;
	}

	public String online() {
		// TODO Auto-generated method stub
		return null;
	}

	public String ps() {
		// TODO Auto-generated method stub
		return null;
	}

	public String speed() {
		// TODO Auto-generated method stub
		return null;
	}

	public String test() {
		// TODO Auto-generated method stub
		return null;
	}

	public String top() {
		// TODO Auto-generated method stub
		return null;
	}

	public String uptime() {
		// TODO Auto-generated method stub
		return null;
	}

	public String users() {
		// TODO Auto-generated method stub
		return null;
	}

	public String whereis() {
		// TODO Auto-generated method stub
		return null;
	}

}
