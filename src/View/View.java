package View;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Exceptions.EmptyDataEnteredException;
import Model.MedicalAppoinment;


public class View {
	private Scanner scanner;

	public View() {
		scanner=new Scanner(System.in);
	}

	public void showMessage(String message){
		System.out.println(message);
	}

	public void showMedicalAppoinmentsList(List<MedicalAppoinment>medicalAppoinmentsList){
		medicalAppoinmentsList.stream().forEach(medicalAppoinment->System.out.println());
	}

	public Long readLong(){
		String longAsString=scanner.nextLine().trim();
		long number=0;
		if(!longAsString.isEmpty()){		
			try {
				number=Long.parseLong(longAsString);
			}catch(NumberFormatException e){
				showMessage("Ingresa un numero,Valido");
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
			throw new EmptyDataEnteredException();
		}
	}

	public int readInt(){
		String digitedNumberAsString=scanner.nextLine().trim();
		boolean validInput=false;
		int number=0;

		if(!digitedNumberAsString.isEmpty()){
			while(!validInput){
				try {
					number=Integer.parseInt(digitedNumberAsString);
					validInput=true;

				}catch(NumberFormatException e){
					showMessage("Ingresa un Numero entero Valido");
				}
			}

		}
		else {
			throw new EmptyDataEnteredException();
		}

		return number;

	}




}
