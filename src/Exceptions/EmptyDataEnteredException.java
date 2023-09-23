package Exceptions;

public class EmptyDataEnteredException extends RuntimeException{

	public EmptyDataEnteredException() {
		super("Error: La entrada no puede estar vacia ");
	}
}
