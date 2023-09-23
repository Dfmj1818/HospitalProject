package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
public class MedicalAppoinmentManager  {
	private List<MedicalAppoinment>medicalAppoinments;

	public MedicalAppoinmentManager() {
		medicalAppoinments=new ArrayList<MedicalAppoinment>();
	}

	public void setAvaiablesMedicalAppoinmentsList(List<MedicalAppoinment>avaiablesmedicalAppoinmentList){
		this.medicalAppoinments=avaiablesmedicalAppoinmentList;
	}

	public List<MedicalAppoinment>getAvaiablestMedicalAppoinmentsList(){
		return medicalAppoinments;
	}
	
	public void setMedicalAppoinmentId() {
		AtomicInteger id=new AtomicInteger(0);
		medicalAppoinments.stream().forEach(medicalAppoinment->medicalAppoinment.setId(id.getAndIncrement()));
	}
	
	public List<MedicalAppoinment> getAvaiablesMedicalAppoinment() {
		List<MedicalAppoinment>avaiablesMedicalAppoinments=medicalAppoinments.stream()
				.filter(medicalAppoinment->medicalAppoinment.getStateOfMedicalAppoinment())
				.collect(Collectors.toList());

		return avaiablesMedicalAppoinments;
	}
	
	
	public void modifyStateOfMedicalAppoinment(MedicalAppoinment medicalApppoinment){
		medicalApppoinment.setStateOfMedicalAppoinment(false);
	}
	 



}
