package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.NonExistentOptionException;
import View.View;

public class User {

	private String mail;
	private String password;
	private long license;
	private String name;
	private String lastName;
	private List<MedicalAppoinment>medicalAppoinmentsHistory;
	private List<MedicalAppoinment>scheduledAppoinments;
	private View view;
	private VirtualCard virtualCard;
    
	public User(){
		medicalAppoinmentsHistory=new ArrayList<MedicalAppoinment>();
		view=new View();
		scheduledAppoinments=new ArrayList<MedicalAppoinment>();
	}

	public void setMail(String mail){
		if(mail!=null){
			this.mail=mail;
		}
		else {
			throw new NullPointerException();
		}
	}

	public String getMail() {
		return mail;
	}

	public void setName(String name){
		if(name!=null){
			this.name=name;
		}
		else {
			throw new NullPointerException();
		}
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password){
		if(password!=null){
			this.password=password;
		}
		else {
			throw new NullPointerException();
		}	
	}

	public String getPassword() {
		return password;
	}

	public void setLastName(String lastName){
		if(lastName!=null){
			this.lastName=lastName;
		}
		else {
			throw new NullPointerException();
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLicense(long license){
		this.license=license;
	}

	public long getLicense() {
		return license;
	}

	public void setVirtualCard(VirtualCard virtualCard){
		this.virtualCard=virtualCard;
	}

	public VirtualCard getVirtualCard() {
		return virtualCard;
	}

	public void setMedicalAppoinmentList(List<MedicalAppoinment>medicalAppoinmentHistory){
		this.medicalAppoinmentsHistory=medicalAppoinmentHistory;
	}

	public List<MedicalAppoinment>getMedicalAppoinmentsHistory(){
		return medicalAppoinmentsHistory;
	}

	public void addMedicalAppoinmenToList(MedicalAppoinment medicalAppoinment) {
		medicalAppoinmentsHistory.add(medicalAppoinment);
	}
	

	public MedicalAppoinment selectMedicalAppoinment(List<MedicalAppoinment>list,int selectedAppoinment){
		MedicalAppoinment selectedMedicalAppoinment=list.stream()			
				.filter(medicalAppoinment->medicalAppoinment.getId()==selectedAppoinment)
				.findFirst()
				.orElseThrow(NonExistentOptionException::new);

		return selectedMedicalAppoinment;
	}
  
	public void checkAppoinmentsInProgress(){
		
	}
	


	@Override
	public String toString() {
		StringBuilder userInformation=new StringBuilder();
		userInformation.append("Nombre del usuario: ").append(getName()).append(" ").append(getLastName()).append("\n")
		.append("Documento: ").append(getLicense()).append("\n")
		.append("Correo Electronico: ").append(getMail());

		return userInformation.toString();
	}





}
