package dropdash;

import dropdash.sh.DropdashShellService;

public class DropdashController {

	DropdashShellService shell;

	public DropdashController(DropdashShellService shell) {
		this.shell = shell;
	}

	public String df() {
		return shell.df();
	}

	public String hostname() {
		return shell.hostname();
	}

	public String ip() {
		return shell.ip();
	}

	public String issue() {
		return shell.issue();
	}

	public String loadavg() {
		return shell.loadavg();
	}

	public String mem() {
		return shell.mem();
	}

	public String numberofcores() {
		return shell.numberofcores();
	}

	public String online() {
		return shell.online();
	}

	public String ps() {
		return shell.ps();
	}

	public String speed() {
		return shell.speed();
	}

	public String top() {
		return shell.top();
	}

	public String uptime() {
		return shell.uptime();
	}

	public String users() {
		return shell.users();
	}

	public String where() {
		return shell.where();
	}

	public String netstat() {
		return shell.netstat();
	}

	public String bandwidth() {
		return shell.bandwidth();
	}

	public String ping() {
		return shell.ping();
	}

	public String time() {
		return shell.time();
	}

	public String lastlog() {
		return shell.lastlog();
	}

}
