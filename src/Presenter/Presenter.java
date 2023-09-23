package Presenter;

import Exceptions.NonExistentOptionException;
import Model.User;
import Model.UserManager;
import View.View;

public class Presenter {

	private View view;
	private UserManager userManager;

	public Presenter(){
		view=new View();
		userManager=new UserManager();
	}

	public static void main(String[]args){
		Presenter presenter=new Presenter();
		presenter.firstMenu();
	}

	public void firstMenu() {
		int digitedOption=0;
		int documentType=0;
		long digitedDocument=0;
		boolean exit=false;
		String password="";
		
		while(!exit){
			view.showMessage("Bienvenido A Colsubsidio Online \nEscoge tu tipo de Documento\n1.Tarjeta de Identidad\n2.Cedula de Ciudadania\n3.Salir");
			digitedOption=view.readInt();
			switch(digitedOption){
			case 1:  
				view.showMessage("Digita tu Tarjeta de Identidad");
				digitedDocument=view.readLong();
				userManager.verifyUserIdentityTarjet(digitedDocument);
				view.showMessage("Digita Tu contraseña");
				password=view.readString();
				User foundAdultUser=userManager.filterUser(digitedDocument, password);
		    		
				break;
			case 2:
			    view.showMessage("Digita Tu Cedula De Ciudadania");
			    digitedDocument=view.readLong();
			    userManager.verifyUserLicense(digitedDocument);
			    view.showMessage("Digita tu contraseña");
			    password=view.readString();
			    User foundUnderAgeUser=userManager.filterUser(digitedDocument, password);
			    
				break;
			case 3:
				view.showMessage("Hasta luego");
				exit=true;
				break;

			default:
				view.showMessage("La opcion digita No Existe,Vuelve a intentarlo");
                break;
			}

		}

	}

	public void runHospitalServices(User user) {
		int digitedOption=0;
		view.showMessage("Bienvenido "+user.getName());
		view.showMessage("Te damos la bienvenida al portal salud\nAqui encuentras todas las opciones de tu plan Salud\n1.Agendar Cita\n2.Ver Citas Agendadas\n3.Ver historia Clinica");
		digitedOption=view.readInt();
		
	}
	
	



}
