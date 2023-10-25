package View;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import Exceptions.EmptyEntryException;
import Exceptions.EmptyListException;
import Model.MedicalAppoinment;


public class View {
	private Scanner scanner;

	public View() {
		scanner=new Scanner(System.in);
	}

	public void showMessage(String message){
		System.out.println(message);
	}

	public void printList(List<?>list){
		list.forEach(System.out::println);
	}

	public void showMedicalAppoinmentsList(List<MedicalAppoinment>medicalAppoinmentsList){
		if(!medicalAppoinmentsList.isEmpty()){
			medicalAppoinmentsList.stream().forEach(medicalAppoinment->System.out.println(medicalAppoinment));
		}
		else {
			throw new EmptyListException();
		}
	}

	public Long readLong(){
		String longAsString=scanner.nextLine().trim();
		long number=0;
		if(!longAsString.isEmpty()){		
			try {
				number=Long.parseLong(longAsString);
			}catch(NumberFormatException e){
				showMessage("Ingresa un numero Valido");
			}
		}
		return number;
	}


	public String readString(){	
		String digitedString=scanner.nextLine().trim();
		if(!digitedString.isEmpty()) {
			return digitedString;
		}
		else {
			throw new EmptyEntryException();
		}
	}

	public int readInt(){
		String digitedNumberAsString=scanner.nextLine().trim();
		int number=0;

		if(!digitedNumberAsString.isEmpty()&&digitedNumberAsString.matches("^[0-9]+$")){
			try {
				number=Integer.parseInt(digitedNumberAsString);

			}catch(NumberFormatException e){
				showMessage("Ingresa un Numero entero Valido");
			}
		}
		else {
			throw new EmptyEntryException();
		}
	
	return number;

}



}
