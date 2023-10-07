package Presenter;

import java.util.List;
import Exceptions.ExistingUserException;
import Exceptions.UserNotFoundException;
import Model.MedicalAppoinment;
import Model.MedicalAppoinmentManager;
import Model.User;
import Model.UserManager;
import View.View;

public class Presenter {

	private View view;
	private UserManager userManager;
	private MedicalAppoinmentManager medicalAppoinmentMananger;

	public Presenter(){
		view=new View();
		userManager=new UserManager();
		medicalAppoinmentMananger=new MedicalAppoinmentManager();
	}

	public static void main(String[]args){
		Presenter presenter=new Presenter();
		presenter.medicalAppoinmentMananger.setMedicalAppoinmentsPrice();
		presenter.medicalAppoinmentMananger.medicalAppoinmentsCreationAndSetHour();
		presenter.medicalAppoinmentMananger.assignDoctors();
		presenter.entryMenu();


	}

	public void entryMenu() {
		int digitedOption=0;
		int documentType=0;
		long digitedDocument=0;
		boolean exit=false;
		String password="";

		while(!exit){
			view.showMessage("Bienvenido a Colsubsidio,¿Que deseas Hacer Hoy?\n1.Crear Cuenta\n2.Iniciar sesion\n3.Salir");
			digitedOption=view.readInt();
			switch(digitedOption){
			case 1:  
				User createdUser=registerUser();
				if(createdUser!=null){
					runHospitalServices(createdUser);
				}
				break;
			case 2:
				User foundUser=loginUser();
				if(foundUser!=null){
					runHospitalServices(foundUser);
				}
				break;
			case 3:
				view.showMessage("Hasta luego,Saliendo de la App.....");
				exit=true;
				break;

			default:
				view.showMessage("La opcion digitada No Existe,Vuelve a intentarlo");
				break;
			}

		}

	}

	public void runHospitalServices(User user) {
		int digitedOption=0;
		boolean exit=false;

		while(!exit){
			view.showMessage("Bienvenido "+user.getName());
			view.showMessage("Te damos la bienvenida al portal salud\nAqui encuentras todas las opciones de tu plan Salud\n1.Agendar Cita\n2.Ver Citas Agendadas\n3.Ver historia Clinica\n4.Pagar Cita\n5.Salir");
			digitedOption=view.readInt();
			switch(digitedOption){
			case 1:	 
				scheduleAppoinment(user);
				break;
			case 2:
				List<MedicalAppoinment>medicalAppoinmentsHistory=user.getMedicalAppoinmentsHistory();
				view.showMedicalAppoinmentsList(medicalAppoinmentsHistory);
				break;
			case 3:

				break;

			default:




			}

		}

	}


	public User loginUser(){
		int selectedOption;
		boolean exit=false;
		long license;
		String digitedPassword="";
		User foundUser=null;

		while(!exit) {
			view.showMessage("¿Selecciona el tipo de documento que tienes\n1.Tarjeta de identidad\n2.Cedula de Ciudadania\n3.Selecciona 3 Si deseas Volver al menu Principal");
			selectedOption=view.readInt();
			switch(selectedOption){
			case 1:
				view.showMessage("Digita el Numero del documento");
				license=view.readLong();
				view.showMessage("Digita tu contraseña");
				digitedPassword=view.readString();
				try {
					foundUser=userManager.filterUser(license, digitedPassword);
					exit=true;
				}catch(UserNotFoundException e){
					view.showMessage(e.getMessage());
				}

				break;

			case 2:
				view.showMessage("Digita el numero del Documento");
				license=view.readLong();
				view.showMessage("Digita tu Contraseña");
				digitedPassword=view.readString();
				try {
					foundUser=userManager.filterUser(license, digitedPassword);
					exit=true;
				}catch(UserNotFoundException e){
					view.showMessage(e.getMessage());
				}

				break;

			case 3:
				exit=true;
				break;

			default:
				view.showMessage("La opcion digitada no existe,Vuelve a intentarlo");
				break;

			}
		}
		return foundUser;
	}

	public User registerUser() {
		String digitedMail;
		String password;
		String digitedName;
		String digitedLastName;
		int digitedOption;
		boolean exit=false;
		long license;
		boolean userExists;
		User createdUser = null;
		while(!exit) {
			view.showMessage("Bienvenido a nuestro sistema de registro\nEscoge el tipo de documento que tienes");
			view.showMessage("1.Tarjeta de Identidad\n2.Cedula de Ciudadania\n3.Selecciona 3 Si deseas Salir");
			digitedOption=view.readInt();

			switch(digitedOption){
			case 1:
				view.showMessage("Digita el numero del Documento");
				license=view.readLong();
				view.showMessage("Digita Una Contraseña");
				password=view.readString();
				view.showMessage("Digita un Correo Electronico");
				digitedMail=view.readString();
				view.showMessage("Digita Tu nombre");
				digitedName=view.readString();
				view.showMessage("Digita tu Appelido");
				digitedLastName=view.readString();
				userExists=userManager.verifyIfUserExists(license, password);
				if(!userExists){
					createdUser=userManager.createUser(digitedMail, password, digitedName, digitedLastName, license);
					userManager.registerUserInDataBase(createdUser);
					view.showMessage("Registro Exitoso :-)");
					exit=true;
				}
				else {
					throw new ExistingUserException();
				}
				break;

			case 2:
				view.showMessage("Digita el numero del documento");
				license=view.readLong();
				view.showMessage("Digita Tu contraseña");
				password=view.readString();
				view.showMessage("Digita Un correo Electronico");
				digitedMail=view.readString();
				view.showMessage("Digita Tu Nombre ");
				digitedName=view.readString();
				view.showMessage("Digita tu apellido");
				digitedLastName=view.readString();
				userExists=userManager.verifyIfUserExists(license, password);
				if(!userExists){
					createdUser=userManager.createUser(digitedMail, password, digitedName, digitedLastName, license);
					userManager.registerUserInDataBase(createdUser);
					view.showMessage("Registro Exitoso :-)");
					exit=true;
				}
				else {
					throw new ExistingUserException();
				}
				break;

			case 3:
				exit=true;
				break;

			default:
				view.showMessage("La Opcion digitada No Existe,Vuelve a intentarlo");
				break;

			}
		}
		return createdUser;
	}

	public void scheduleAppoinment(User user){
		int digitedDay;
		view.showMessage("A continuacion te mostraremos las citas disponibles");
		List<MedicalAppoinment>avaiablesMedicalAppoinments=medicalAppoinmentMananger.getAvaiablestMedicalAppoinmentsList();
		view.showMedicalAppoinmentsList(avaiablesMedicalAppoinments);
		view.showMessage("Digita el dia de la cita que deseas agendar");
		digitedDay=view.readInt();
		MedicalAppoinment selectedMedicalAppoinment=user.selectMedicalAppoinment(avaiablesMedicalAppoinments, digitedDay);
		view.showMessage(selectedMedicalAppoinment.toString());

	}


}


