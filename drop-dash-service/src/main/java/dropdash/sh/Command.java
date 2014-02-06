package dropdash.sh;

public interface Command {

	String execute();

	public abstract int getResult();

	public abstract String getError();

	public abstract String getOutput();

}