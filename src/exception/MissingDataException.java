package exception;

public class MissingDataException extends RuntimeException {

	public MissingDataException() {}
	
	public MissingDataException(String message) {
		super(message);
	}
	
	public MissingDataException(String message, String s) {
		super(message+s);
	}
}
