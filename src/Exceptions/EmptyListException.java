package Exceptions;

public class EmptyListException extends RuntimeException{

	public EmptyListException() {
		super("La lista esta vacia\n");
	}
}
