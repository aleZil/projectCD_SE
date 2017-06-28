package exception;

public class InsertFailedException extends RuntimeException {

	public InsertFailedException() {}
	
	public InsertFailedException(String message) {
		super(message);
	}
}
