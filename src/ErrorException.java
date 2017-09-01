/*
 * Custom error exception class.
 */
public class ErrorException extends RuntimeException {
	public ErrorException() {
		// empty constructor.
	}
	
	// Constructor that takes in a message.
	public ErrorException(String message) {
		super(message);
	}
}
