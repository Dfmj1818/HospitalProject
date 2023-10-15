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
	private boolean appoinmentPaymentStatus;
	private int price; 

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
		else {
			throw new NullPointerException();
		}

	}

	public User getUser(){
		return user;
	}

	public void setAppoinmentType(String appoinmentType){
		if(appoinmentType!=null){
			this.appoinmentType=appoinmentType;
		}
		else {
			throw new NullPointerException();
		}

	}

	public String getAppoinmentType() {
		return appoinmentType;
	}

	public void setNameOfDoctor(String nameOfDoctor) {
		if(nameOfDoctor!=null){
			this.nameOfDoctor=nameOfDoctor;
		}
		else {
			throw new NullPointerException();
		}

	}

	public String getNameOfDoctor() {
		return nameOfDoctor;
	}

	public void setDateOfAppoinment(LocalDateTime dateOfAppoinment) {
		if(dateOfAppoinment!=null){
			this.dateOfAppoinment=dateOfAppoinment;
		}
		else {
			throw new NullPointerException();
		}	
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

	public void setPriceOfMedicalAppoinment(int priceOfMedicalAppoinment){
		this.price=priceOfMedicalAppoinment;
	}

	public int getPriceOfMedicalAppoinment() {
		return price;
	}

	public void setAppoinmentPaymentStatus(boolean appoinmentPaymentStatus) {
		this.appoinmentPaymentStatus=appoinmentPaymentStatus;
	}

	public boolean getStateOfAppoinmentPaymentStatus() {
		return appoinmentPaymentStatus;
	}

	public String showNotice(){
		StringBuilder information=new StringBuilder();
		information.append("NOTA: ").append("Si el estado estado esta marcado con [✘],la cita no esta disponible")
		.append("\n")
		.append("Si el estado de la Cita es [✔] la cita esta disponible");

		return information.toString();
	}


	@Override
	public String toString() {
		StringBuilder medicalAppoinmentInformation=new StringBuilder();

		medicalAppoinmentInformation.append("ID: ")
		.append(getId()).append("\n")
		.append("Nombre del Doctor: ")
		.append(getNameOfDoctor())
		.append("\n")
		.append("Tipo de Cita: ")
		.append(getAppoinmentType())
		.append("\n")
		.append("Fecha de la cita: ")
		.append(getDateOfAppoinment()).append("\n");
		if(getStateOfAppoinmentPaymentStatus()){
			medicalAppoinmentInformation.append("Pago de la Cita: ").append("Pagada").append("\n");
		}
		else {
			medicalAppoinmentInformation.append("Pago de La Cita: ").append("No Pagada").append("\n");
		}
		return medicalAppoinmentInformation.toString();
	}

}
