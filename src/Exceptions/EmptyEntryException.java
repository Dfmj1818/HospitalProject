package Exceptions;

public class EmptyEntryException extends RuntimeException{

	public EmptyEntryException() {
		super("La entrada no puede estar vacia");
	}
}
