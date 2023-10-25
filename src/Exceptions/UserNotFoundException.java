package Exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
	   super("Usuario No Encontrado,Contraseña o Documento Incorrecto");	
	}
}
