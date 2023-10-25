package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Exceptions.EmptyListException;

public class MedicalAppoinmentManager  {
	private List<MedicalAppoinment>medicalAppoinmentsList;

	public MedicalAppoinmentManager() {
		medicalAppoinmentsList=new ArrayList<MedicalAppoinment>();
	}

	public void setAvaiablesMedicalAppoinmentsList(List<MedicalAppoinment>medicalAppoinmentsList){
		this.medicalAppoinmentsList=medicalAppoinmentsList;
	}

	public List<MedicalAppoinment>getMedicalAppoinmentsList(){
		return medicalAppoinmentsList;
	}

	public void addMedicalAppoinmentToList(MedicalAppoinment medicalAppoinment) {
		medicalAppoinmentsList.add(medicalAppoinment);
	}

	public void setMedicalAppoinmentsId() {
		int id=0;
		for(MedicalAppoinment medicalAppoinment:medicalAppoinmentsList){
			id++;
			medicalAppoinment.setId(id);
		}
	}

	public void setStateOfPayementForAllMedicalAppoinments() {
		medicalAppoinmentsList.forEach(medicalAppoinment->medicalAppoinment.setAppoinmentPaymentStatus(false));
	}

	public void setStateOfMedicalAppoinment() {
		medicalAppoinmentsList.forEach(medicalAppoinment->medicalAppoinment.setStateOfMedicalAppoinment(true));
	}


	public List<MedicalAppoinment> filterAvaiablesMedicalAppoinments() {
		List<MedicalAppoinment>avaiablesMedicalAppoinments=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getStateOfMedicalAppoinment())
				.collect(Collectors.toList());

		return avaiablesMedicalAppoinments;
	}

	public void setMedicalAppoinmentsPrice() {
		medicalAppoinmentsList.forEach(medicalAppoinment->medicalAppoinment.setPriceOfMedicalAppoinment(16400));
	}

	public void setMedicalAppoinmentState() {
		medicalAppoinmentsList.forEach(medicalAppoinment->medicalAppoinment.setStateOfMedicalAppoinment(true));
	}

	public void medicalAppoinmentsCreationAndSetHour(){
		LocalDateTime startTime=LocalDateTime.parse("2023-10-07T08:00:00");	
		LocalDateTime lastDayOfTheYear=LocalDateTime.parse("2023-10-08T18:00");
		int hoursPassedInTheDay=0;

		while(startTime.isBefore(lastDayOfTheYear)){
			MedicalAppoinment medicalAppoinment=new MedicalAppoinment();
			medicalAppoinment.setDateOfAppoinment(startTime.plusHours(hoursPassedInTheDay));
			medicalAppoinmentsList.add(medicalAppoinment);
			hoursPassedInTheDay++;

			if(hoursPassedInTheDay<=7) {
				medicalAppoinment.setAppoinmentType("Medicina General");
			}
			else {
				medicalAppoinment.setAppoinmentType("Odontologia");
			}
			if(hoursPassedInTheDay>=10){
				hoursPassedInTheDay=0;
				startTime=startTime.plusDays(1);
			}			
		}

	}

	public void checkIfTheListIsEmpty() {
		if(medicalAppoinmentsList.isEmpty()){
			throw new EmptyListException();
		}
	}


	public void assignDoctors(){
		if(medicalAppoinmentsList.isEmpty()){
			throw new EmptyListException();
		}
		else {
			for(MedicalAppoinment medicalAppoinment:medicalAppoinmentsList){
				if(medicalAppoinment.getAppoinmentType().equalsIgnoreCase("Medicina General")){
					medicalAppoinment.setNameOfDoctor("Dr.Daniel");
				}
				else {
					medicalAppoinment.setNameOfDoctor("Dr Johnson.");
				}
			}
		}

	}


	public List<MedicalAppoinment>filterMedicalAppoinmentsByMorningHour(){
		List<MedicalAppoinment>listFilteredByMorningHour= medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getDateOfAppoinment().getHour()<=13&&medicalAppoinment.getStateOfMedicalAppoinment())
				.collect(Collectors.toList());

		return listFilteredByMorningHour;           
	}

	public List<MedicalAppoinment>filterMedicalAppoinmentsByAfternoonHour(){
		List<MedicalAppoinment>listFilteredByAfternoonHour=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getDateOfAppoinment().getHour()>13&&medicalAppoinment.getStateOfMedicalAppoinment())
				.collect(Collectors.toList());

		return listFilteredByAfternoonHour;
	}

	public List<MedicalAppoinment>filterUnpaidAppoinments(User user){
		return user.getMedicalAppoinmentsHistory().stream()
				.filter(medicalAppoinment->!medicalAppoinment.getStateOfAppoinmentPaymentStatus())
				.collect(Collectors.toList());

	}

	public void verifyIfListIsEmpty(List<MedicalAppoinment>list){
		if(list.isEmpty()){
			throw new EmptyListException();
		}
	}
	
	public void removeMedicalAppoinment(List<MedicalAppoinment>medicalAppoinments,int selectedAppoinment) {
        medicalAppoinments.removeIf(medicalAppoinment->medicalAppoinment.getId()==selectedAppoinment);
	}
	

}
