package exception;

public class MissingDataException extends RuntimeException {

	public MissingDataException() {}
	
	public MissingDataException(String message) {
		super(message);
	}
}
