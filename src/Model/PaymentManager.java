package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Exceptions.InsufficientFundsException;

public class PaymentManager {
    private List<VirtualCard>virtualCardsDataBase;
    
    
	public PaymentManager() {
      virtualCardsDataBase=new ArrayList<VirtualCard>();
	}
	
	public void addVirtualCardToList(VirtualCard virtualCard){
		virtualCardsDataBase.add(virtualCard);
	}

	public void verifyCcv() {

	}

	public void verifyCardCode() {

	}

	public void verifyDueDate() {

	}
	
	public VirtualCard createVirtualCard(User user,int ccv,long code,LocalDate dueDate){
		VirtualCard virtualCard=new VirtualCard(user,ccv,code,dueDate);
		int funds=virtualCard.generateFunds();
		virtualCard.setFunds(funds);
		return virtualCard;
	}

	public void paymentOfAppoinments(MedicalAppoinment medicalAppoinment,VirtualCard virtualcard){
		long founds=virtualcard.getFunds();
		int medicalAppoinmentValue=medicalAppoinment.getValue();		
		if(founds>=medicalAppoinmentValue){
			founds-=medicalAppoinmentValue;
			medicalAppoinment.setAppoinmentPaymentStatus(true);
		}
		else{
			throw new InsufficientFundsException();
		}
	}
	
	
	
	
	


}
