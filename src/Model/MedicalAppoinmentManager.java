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

	public void setAvaiablesMedicalAppoinmentsList(List<MedicalAppoinment>avaiablesmedicalAppoinmentList){
		this.medicalAppoinmentsList=avaiablesmedicalAppoinmentList;
	}

	public List<MedicalAppoinment>getAvaiablestMedicalAppoinmentsList(){
		return medicalAppoinmentsList;
	}

	public void setMedicalAppoinmentsId() {
		int id=0;
		for(MedicalAppoinment medicalAppoinment:medicalAppoinmentsList){
			id++;
			medicalAppoinment.setId(id);
		}
	}

	public List<MedicalAppoinment> getAvaiablesMedicalAppoinment() {
		List<MedicalAppoinment>avaiablesMedicalAppoinments=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getStateOfMedicalAppoinment())
				.collect(Collectors.toList());

		return avaiablesMedicalAppoinments;
	}

	public void setMedicalAppoinmentsPrice() {
		medicalAppoinmentsList.forEach(medicalAppoinment->medicalAppoinment.setPriceOfMedicalAppoinment(16400));
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


	public List<MedicalAppoinment>filterByAppoinmentType(int selectedMedicalAppoinment){
		List<MedicalAppoinment>filteredListByAppoinmentType=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getAppoinmentType().equals(selectedMedicalAppoinment))
				.collect(Collectors.toList());

		return filteredListByAppoinmentType;
	}

	public List<MedicalAppoinment>filterMedicalAppoinmentsByMorningHour(){
		List<MedicalAppoinment>listFilteredByMorningHour= medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->{
					int medicalAppoinmentHour=medicalAppoinment.getDateOfAppoinment().getHour();
					return medicalAppoinmentHour>=8&&medicalAppoinmentHour<=12;
				})
				.collect(Collectors.toList());

		return listFilteredByMorningHour;           
	}

	public List<MedicalAppoinment>filterMedicalAppoinmentsByAfternoonHour(){
		List<MedicalAppoinment>listFilteredByAfternoonHour=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->{
					int medicalAppoinmentHour=medicalAppoinment.getDateOfAppoinment().getHour();
					return medicalAppoinmentHour>=13;
				})
				.collect(Collectors.toList());

		return listFilteredByAfternoonHour;
	}

	public void modifyStateOfMedicalAppoinment(MedicalAppoinment medicalApppoinment){
		medicalApppoinment.setStateOfMedicalAppoinment(false);
	}


}
