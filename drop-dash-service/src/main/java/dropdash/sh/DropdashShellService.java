package dropdash.sh;


public class DropdashShellService {

	Command df = new Df(),

	bandwidth = new Bandwidth(),

	hostname = new Hostname(),

	ip = new Ip(),

	issue = new Issue(),

	loadavg = new Loadavg(),

	mem = new Mem(),

	numberofcores = new Numberofcores(),

	online = new Online(),

	ps = new Ps(),

	speed = new Speed(),

	top = new Top(),

	uptime = new Uptime(),

	users = new Users(),

	where = new Where(),
	
	netstat = new Netstat();

	public String df() {
		return df.execute();
	}

	public String hostname() {
		return hostname.execute();
	}

	public String ip() {
		return ip.execute();
	}

	public String issue() {
		return issue.execute();
	}

	public String loadavg() {
		return loadavg.execute();
	}

	public String mem() {
		return mem.execute();
	}

	public String numberofcores() {
		return numberofcores.execute();
	}

	public String online() {
		return online.execute();
	}

	public String ps() {
		return ps.execute();
	}

	public String speed() {
		return speed.execute();
	}

	public String top() {
		return top.execute();
	}

	public String uptime() {
		return uptime.execute();
	}

	public String users() {
		return users.execute();
	}

	public String where() {
		return where.execute();
	}

	public String netstat() {
		return netstat.execute();
	}

	public String bandwidth() {
		return bandwidth.execute();
	}

}
