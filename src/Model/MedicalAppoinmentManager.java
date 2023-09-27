package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
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
	
	public void setMedicalAppoinmentId() {
		AtomicInteger id=new AtomicInteger(0);
		medicalAppoinmentsList.stream().forEach(medicalAppoinment->medicalAppoinment.setId(id.getAndIncrement()));
	}
	
	public List<MedicalAppoinment> getAvaiablesMedicalAppoinment() {
		List<MedicalAppoinment>avaiablesMedicalAppoinments=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getStateOfMedicalAppoinment())
				.collect(Collectors.toList());

		return avaiablesMedicalAppoinments;
	}
	
	public List<MedicalAppoinment>filterByAppoinmentType(int selectedMedicalAppoinment){
		List<MedicalAppoinment>filteredListByAppoinmentType=medicalAppoinmentsList.stream()
				.filter(medicalAppoinment->medicalAppoinment.getAppoinmentType()==selectedMedicalAppoinment)
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
