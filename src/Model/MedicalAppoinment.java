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
	
	public MedicalAppoinment(int id,User user,String appoinmentType,String nameOfDoctor,LocalDateTime dateOfAppoinment,int value,boolean stateOfMedicalAppoinment){
        this.id=id;
		this.user=user;
		this.appoinmentType=appoinmentType;
        this.nameOfDoctor=nameOfDoctor;
        this.dateOfAppoinment=dateOfAppoinment;
        this.setValue(value);
        this.setStateOfMedicalAppoinment(stateOfMedicalAppoinment);
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setAppoinmentType(String appoinmentType){
		this.appoinmentType=appoinmentType;
	}
   
	public String getAppoinmentType() {
		return appoinmentType;
	}
	
	public void setNameOfDoctor(String nameOfDoctor) {
		this.nameOfDoctor=nameOfDoctor;
	}
	
	public String getNameOfDoctor() {
		return nameOfDoctor;
	}
	
	public void setDateOfAppoinment(LocalDateTime dateOfAppoinment) {
		this.dateOfAppoinment=dateOfAppoinment;
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
