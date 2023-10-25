package Exceptions;

public class MedicalAppoinmentsNotAvaiablesException extends RuntimeException{

	public MedicalAppoinmentsNotAvaiablesException() {
		super("No hay Citas Disponibles En este momento");
	}
}
