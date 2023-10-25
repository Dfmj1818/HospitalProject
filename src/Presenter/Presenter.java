package Presenter;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import Exceptions.EmptyEntryException;
import Exceptions.EmptyListException;
import Exceptions.ExistingUserException;
import Exceptions.IncorrectFormatException;
import Exceptions.InsufficientFundsException;
import Exceptions.NonExistentOptionException;
import Exceptions.UserNotFoundException;
import Model.MedicalAppoinment;
import Model.MedicalAppoinmentManager;
import Model.PaymentManager;
import Model.User;
import Model.UserManager;
import Model.VirtualCard;
import View.View;

public class Presenter {

	private View view;
	private UserManager userManager;
	private MedicalAppoinmentManager medicalAppoinmentMananger;
	private PaymentManager paymentManager;

	public Presenter(){
		view=new View();
		userManager=new UserManager();
		medicalAppoinmentMananger=new MedicalAppoinmentManager();
		paymentManager=new PaymentManager();
	}

	public static void main(String[]args){
		Presenter presenter=new Presenter();
		presenter.medicalAppoinmentMananger.medicalAppoinmentsCreationAndSetHour();
		presenter.medicalAppoinmentMananger.setMedicalAppoinmentsPrice();
		presenter.medicalAppoinmentMananger.setMedicalAppoinmentsId();
		presenter.medicalAppoinmentMananger.assignDoctors();
		presenter.medicalAppoinmentMananger.setMedicalAppoinmentState();
		presenter.medicalAppoinmentMananger.setStateOfPayementForAllMedicalAppoinments();
		presenter.medicalAppoinmentMananger.setStateOfMedicalAppoinment();
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
		String yesOrNotAnswer="";
		while(!exit){
			view.showMessage("Bienvenido "+user.getName());
			view.showMessage("Te damos la bienvenida al portal salud\nAqui encuentras todas las opciones de tu plan Salud\n1.Agendar Cita\n2.Ver Citas Agendadas\n3.Ver historia Clinica\n4.Pagar Cita\n5.Salir");
			digitedOption=view.readInt();
			switch(digitedOption){
			case 1:	 
				MedicalAppoinment scheduledAppoinment=scheduleAppoinment(user);
				if(scheduledAppoinment!=null){
					addMedicalAppoinmentToDataBases(user, medicalAppoinmentMananger, scheduledAppoinment);
				}

				break;
			case 2:
				List<MedicalAppoinment>medicalAppoinmentsHistory=user.getMedicalAppoinmentsHistory();
				try {
					view.showMessage("Estas Son Tus citas Agendadadas");
					view.showMedicalAppoinmentsList(medicalAppoinmentsHistory);

				}catch(EmptyListException e){
					view.showMessage(e.getMessage());
				}
				break;
			case 3:


				break;
			case 4:
				try {
					List<MedicalAppoinment>unpaidsMedicalAppoinments=getUnpaidAppoinmets(user);
					viewPendingMedicalAppoinments(unpaidsMedicalAppoinments);
					view.showMessage("¿Deseas Pagar Alguna De tus Citas?\n(Escribe si para pagar alguna cita y no para salir)");
					yesOrNotAnswer=view.readString();
					if(yesOrNotAnswer.toLowerCase().equals("si")){
						VirtualCard virtualCard=enterPaymentData(user);
						MedicalAppoinment selectedMedicalAppoinment=selectMedicalAppoinmentToPay(user, unpaidsMedicalAppoinments);			
						paymentManager.paymentOfAppoinments(selectedMedicalAppoinment, virtualCard);
						view.showMessage("Pago Concretado de manera exitosa :-)");
					}
				}catch(NonExistentOptionException e){
					view.showMessage(e.getMessage());
				}catch(InsufficientFundsException e){
					view.showMessage(e.getMessage());
				}catch(EmptyListException e){
					view.showMessage(e.getMessage());
				}


				break; 

			default:
				throw new NonExistentOptionException();

			}

		}

	}


	public User loginUser(){
		int selectedOption;
		boolean exit=false;
		String digitedPassword="";
		User foundUser=null;

		while(!exit) {
			view.showMessage("¿Selecciona el tipo de documento que tienes\n1.Tarjeta de identidad\n2.Cedula de Ciudadania\n3.Selecciona 3 Si deseas Volver al menu Principal");
			selectedOption=view.readInt();
			switch(selectedOption){
			case 1:
				view.showMessage("Digita el Numero del documento");
				long identityTarjet=view.readLong();
				view.showMessage("Digita tu contraseña");
				digitedPassword=view.readString();

				try {
					userManager.verifyUserIdentityTarjet(identityTarjet);
					foundUser=userManager.filterUser(identityTarjet, digitedPassword);
					exit=true;
				}catch(UserNotFoundException e){
					view.showMessage(e.getMessage());
				}catch(IncorrectFormatException e){
					view.showMessage(e.getMessage());
				}

				break;

			case 2:
				view.showMessage("Digita el numero del Documento");
				long license=view.readLong();
				view.showMessage("Digita tu Contraseña");
				digitedPassword=view.readString();
				try {
					userManager.verifyUserLicense(license);
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
		boolean userExists;
		User createdUser = null;
		List<MedicalAppoinment>avaiablesMedicalAppoinments;

		try {		
			while(!exit) {
				view.showMessage("Bienvenido a nuestro sistema de registro\nEscoge el tipo de documento que tienes");
				view.showMessage("1.Tarjeta de Identidad\n2.Cedula de Ciudadania\n3.Selecciona 3 Si deseas Salir");
				digitedOption=view.readInt();

				switch(digitedOption){
				case 1:
					view.showMessage("Digita el numero del Documento");
					long identityTarjet=view.readLong();
					view.showMessage("Digita Una Contraseña");
					password=view.readString();
					view.showMessage("Digita un Correo Electronico");
					digitedMail=view.readString();
					view.showMessage("Digita Tu nombre");
					digitedName=view.readString();
					view.showMessage("Digita tu Appelido");
					digitedLastName=view.readString();
					try {
						userManager.verifyUserIdentityTarjet(identityTarjet);
						userManager.verifyUserMail(digitedMail);
					}catch(IncorrectFormatException e){
						view.showMessage(e.getMessage());
					}
					userExists=userManager.verifyIfUserExists(identityTarjet, password);
					if(!userExists){
						createdUser=userManager.createUser(digitedMail, password, digitedName, digitedLastName, identityTarjet);
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
					long license=view.readLong();
					view.showMessage("Digita Tu contraseña");
					password=view.readString();
					view.showMessage("Digita Un correo Electronico");
					digitedMail=view.readString();
					view.showMessage("Digita Tu Nombre ");
					digitedName=view.readString();
					view.showMessage("Digita tu apellido");
					digitedLastName=view.readString();
					try {
						userManager.verifyUserMail(digitedMail);
						userManager.verifyUserLicense(license);
					}catch(IncorrectFormatException e){
						view.showMessage(e.getMessage());
					}
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

		}catch(EmptyEntryException e){
			view.showMessage(e.getMessage());
		}catch(IncorrectFormatException e){
			view.showMessage(e.getMessage());
		}
		return createdUser;
	}	

	public MedicalAppoinment scheduleAppoinment(User user){
		int digitedId;
		int journeyChosen;

		MedicalAppoinment selectedMedicalAppoinment=null;
		List<MedicalAppoinment>avaiablesMedicalAppoinments;
		view.showMessage("Selecciona la jornada en la que deseas agendar tu cita\n1.Mañana\n2.Tarde");
		journeyChosen=view.readInt();

		switch(journeyChosen){
		case 1:		
			try {
				avaiablesMedicalAppoinments=medicalAppoinmentMananger.filterMedicalAppoinmentsByMorningHour();
				view.showMedicalAppoinmentsList(avaiablesMedicalAppoinments);
				view.showMessage("Digita el id de la cita que deseas agendar");
				digitedId=view.readInt();
				selectedMedicalAppoinment=user.selectMedicalAppoinment(avaiablesMedicalAppoinments, digitedId);
				medicalAppoinmentMananger.removeMedicalAppoinment(avaiablesMedicalAppoinments, digitedId);
				medicalAppoinmentMananger.setAvaiablesMedicalAppoinmentsList(avaiablesMedicalAppoinments);
				view.showMessage("Cita Agendada Correctamente :-)");
			}catch(NonExistentOptionException e){
				view.showMessage(e.getMessage());
			}

			break;
		case 2:

			try {
				avaiablesMedicalAppoinments=medicalAppoinmentMananger.filterMedicalAppoinmentsByAfternoonHour();
				view.showMedicalAppoinmentsList(avaiablesMedicalAppoinments);
				view.showMessage("Digita el id de la cita que deseas agendar");
				digitedId=view.readInt();
				selectedMedicalAppoinment=user.selectMedicalAppoinment(avaiablesMedicalAppoinments, digitedId);
				medicalAppoinmentMananger.removeMedicalAppoinment(avaiablesMedicalAppoinments, journeyChosen);
				medicalAppoinmentMananger.setAvaiablesMedicalAppoinmentsList(avaiablesMedicalAppoinments);
				view.showMessage("Cita Agendada Correctamente :-)");
			}catch(NonExistentOptionException e){
				view.showMessage(e.getMessage());
			}

			break;

		default:
			view.showMessage("La Opcion digitada No existe,Vuelve a intentarlo");
			break;

		}

		return selectedMedicalAppoinment;
	}


	public VirtualCard enterPaymentData(User user){
		long code;
		int ccv;
		String dueDateAsString;
		LocalDate dueDate;
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		boolean correctFormat=false;
		VirtualCard virtualCard=null;

		while(!correctFormat) {
			try {
				view.showMessage("Digita el codigo de la tarjeta");
				code=view.readLong();
				view.showMessage("Digita el ccv de tu tarjeta");
				ccv=view.readInt();
				view.showMessage("Digita la fecha de vencimiento de la tarjeta");
				dueDateAsString=view.readString();
				dueDate=LocalDate.parse(dueDateAsString, formatter);
				correctFormat=true;
				virtualCard=paymentManager.createVirtualCard(user, ccv, code, dueDate);
				paymentManager.verifyCcv(ccv);
				paymentManager.verifyCardCode(code);
			}catch(IncorrectFormatException e){
				view.showMessage(e.getMessage());
				break;
			}catch(DateTimeException e){
				view.showMessage("Error:Digita la Fecha en el Formato Indicado");
				break;
			}

		}
		return virtualCard;
	}

	public void addMedicalAppoinmentToDataBases(User user,MedicalAppoinmentManager medicalAppoinmentManager,MedicalAppoinment medicalAppoinment){
		user.addMedicalAppoinmenToList(medicalAppoinment);
	}

	public List<MedicalAppoinment>getUnpaidAppoinmets(User user){
		List<MedicalAppoinment>unpaidMedicalAppoinments=medicalAppoinmentMananger.filterUnpaidAppoinments(user);    
		if(!unpaidMedicalAppoinments.isEmpty()){
			return unpaidMedicalAppoinments;
		}
		else {
			throw new EmptyListException();
		}

	}

	public MedicalAppoinment selectMedicalAppoinmentToPay(User user,List<MedicalAppoinment>unpaidMedicalAppoinments) {
		int choosedAppoinment;
		MedicalAppoinment selectedMedicalAppoinment = null;
		view.showMessage("Digita el Id de la cita que deseas Pagar");
		choosedAppoinment=view.readInt();
		selectedMedicalAppoinment=user.selectMedicalAppoinment(unpaidMedicalAppoinments, choosedAppoinment);

		return selectedMedicalAppoinment;
	}

	public void paymentOfMedicalAppoinment(MedicalAppoinment medicalAppoinment,VirtualCard virtualCard){
		paymentManager.paymentOfAppoinments(medicalAppoinment, virtualCard);
	}

	public void viewPendingMedicalAppoinments(List<MedicalAppoinment>unpaidQuotas) {
		view.showMessage("Estas Son tus Citas Pendientes por Pagar\n");
		view.showMedicalAppoinmentsList(unpaidQuotas);
	}



}


