package Exceptions;

public class InsufficientFundsException extends RuntimeException{

	public InsufficientFundsException() {
		super("No tienes los fondos suficientes para concretar la compra");
	}
}
