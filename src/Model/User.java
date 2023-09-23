package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exceptions.NonExistentOptionException;

public class User {

	private String mail;
	private String password;
	private long license;
	private String name;
	private String lastName;
	private List<MedicalAppoinment>medicalAppoinmentsList;

	public User(){
		
		medicalAppoinmentsList=new ArrayList<MedicalAppoinment>();
	}

	public void setMail(String mail){
		this.mail=mail;
	}

	public String getMail() {
		return mail;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword() {
		return password;
	}

	public void setLastName(String lastName){
		this.lastName=lastName;
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

	public void setMedicalAppoinmentList(List<MedicalAppoinment>medicalAppoinment){
		this.medicalAppoinmentsList=medicalAppoinment;
	}

	public List<MedicalAppoinment>getMedicalAppoinment(){
		return medicalAppoinmentsList;
	}

	public void addMedicalAppoinmenToList(MedicalAppoinment medicalAppoinment) {
		medicalAppoinmentsList.add(medicalAppoinment);
	}

	public MedicalAppoinment selectMedicalAppoinment(List<MedicalAppoinment>avaiablesMedicalAppoinments,int selectedAppoinment){
		MedicalAppoinment selectedMedicalAppoinment=avaiablesMedicalAppoinments.stream()
				.filter(medicalAppoinment->medicalAppoinment.getId()==selectedAppoinment)
				.findFirst()
				.orElseThrow();

		return selectedMedicalAppoinment;
	}
	
	




}
