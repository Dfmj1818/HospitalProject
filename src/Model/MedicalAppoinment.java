package Model;

import java.time.LocalDateTime;

public class MedicalAppoinment {

	private int id;
	private User user;
	private String appoinmentType;
	private String nameOfDoctor;
	private LocalDateTime dateOfAppoinment;
	private int value;
	private boolean stateOfMedicalAppoinment;

	public MedicalAppoinment(){
		
	}

	public void setId(int id){
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setUser(User user){
		if(user!=null){
			this.user=user;
		}
		throw new NullPointerException();
	}

	public User getUser(){
		return user;
	}

	public void setAppoinmentType(String appoinmentType){
		if(appoinmentType!=null){
			this.appoinmentType=appoinmentType;
		}
		throw new NullPointerException();
	}

	public String getAppoinmentType() {
		return appoinmentType;
	}

	public void setNameOfDoctor(String nameOfDoctor) {
		if(nameOfDoctor!=null){
			this.nameOfDoctor=nameOfDoctor;
		}
		throw new NullPointerException();
	}

	public String getNameOfDoctor() {
		return nameOfDoctor;
	}

	public void setDateOfAppoinment(LocalDateTime dateOfAppoinment) {
		if(dateOfAppoinment!=null){
			this.dateOfAppoinment=dateOfAppoinment;
		}
		throw new NullPointerException();
		
	}

	public LocalDateTime getDateOfAppoinment() {
		return dateOfAppoinment;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean getStateOfMedicalAppoinment() {
		return stateOfMedicalAppoinment;
	}

	public void setStateOfMedicalAppoinment(boolean stateOfMedicalAppoinment) {
		this.stateOfMedicalAppoinment = stateOfMedicalAppoinment;
	}

	@Override
	public String toString() {
		StringBuilder medicalAppoinmentInformation=new StringBuilder();
		medicalAppoinmentInformation.append("Nombre del Paciente: ")
		.append(user.getName())
		.append(user.getLastName())
		.append("\n")
		.append("Tipo de Cita: ")
		.append(appoinmentType)
		.append("\n")
		.append("Nombre del Doctor: ")
		.append(nameOfDoctor)
		.append("\n")
		.append("Fecha de la cita")
		.append(dateOfAppoinment)
		.append("\n");
		if(!stateOfMedicalAppoinment){
			medicalAppoinmentInformation.append("Estado de la Cita: ").append("[✘]");
		}
		else {
			medicalAppoinmentInformation.append("Estado de la Cita: ").append("[✔]");
		}
		return medicalAppoinmentInformation.toString();
	}
	
}
