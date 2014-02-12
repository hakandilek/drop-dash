package dropdash.auth;

public class Subject {

	public String username;

	public Subject(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subject [");
		if (username != null)
			builder.append("username=").append(username);
		builder.append("]");
		return builder.toString();
	}

}
