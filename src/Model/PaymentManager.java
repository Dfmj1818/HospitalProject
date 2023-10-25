package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Exceptions.IncorrectFormatException;
import Exceptions.InsufficientFundsException;

public class PaymentManager {
    private List<VirtualCard>virtualCardsDataBase;
    
    
	public PaymentManager() {
      virtualCardsDataBase=new ArrayList<VirtualCard>();
	}
	
	public void addVirtualCardToList(VirtualCard virtualCard){
		virtualCardsDataBase.add(virtualCard);
	}

	public int verifyCcv(int ccv) {
       String ccvAsString=String.valueOf(ccv);
       if(ccvAsString.matches("[0-9]{3}")){
    	   return ccv;
       }
       else {
    	   throw new IncorrectFormatException();
       }
	}

	public long verifyCardCode(long cardCode){
	  String cardCodeAsString=String.valueOf(cardCode);	
      if(cardCodeAsString.matches("^[0-9]{10}$")){
    	  return cardCode;
      }
      else {
    	  throw new IncorrectFormatException();
      }
	}

	public LocalDate verifyDueDate(LocalDate dueDate) {
	   LocalDate todaysDate=LocalDate.now();
       if(dueDate.isAfter(todaysDate)){
    	   return todaysDate;
       }
       else {
    	   throw new IncorrectFormatException();
       }
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
