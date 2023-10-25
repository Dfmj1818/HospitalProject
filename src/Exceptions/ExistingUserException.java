package Exceptions;

public class ExistingUserException extends RuntimeException{

	public ExistingUserException() {
		super("Error:Ya tienes una cuenta creada afiliada a este Documento");
	}
}
