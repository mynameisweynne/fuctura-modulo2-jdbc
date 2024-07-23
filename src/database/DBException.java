package database;

public class DBException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	public DBException(String message) {
		super(message);
	}
}
