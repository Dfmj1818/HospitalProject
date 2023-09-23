package Exceptions;

public class NonExistentOptionException extends RuntimeException{

	public NonExistentOptionException() {
		super("La opcion digitada No Existe");
	}
}
