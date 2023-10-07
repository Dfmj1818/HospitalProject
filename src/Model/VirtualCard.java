package Model;

import java.time.LocalDate;
import java.util.Random;

public class VirtualCard {
    
	private User user;
	private long founds;
	private int ccv;
	private long code;
	private LocalDate dueDate;
	
	public VirtualCard(User user,long founds,int ccv,long code,LocalDate dueDate){
		this.user=user;
		this.founds=founds;
		this.ccv=ccv;
		this.code=code;
		this.dueDate=dueDate;
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setFounds(long founds){
		this.founds=founds;
	}
	
	public long getFounds() {
		return founds;
	}
	
	public void setCcv(int ccv){
		this.ccv=ccv;
	}
	
	public int getCcv() {
		return ccv;
	}
	
	public void setCode(long code){
		this.code=code;
	}
	
	public long getCode() {
		return code;
	}
	
	public void setDueDate(LocalDate dueDate){
		this.dueDate=dueDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public int generateFunds() {
		Random random=new Random();
		int funds = random.nextInt(500000) + 500001;
		return funds;
	}
	public String toString() {
		StringBuilder virtualCardInformation=new StringBuilder();
		virtualCardInformation.append("Propietario de la tarjeta")
		.append(user.getName()).append(user.getName())
		.append("Numero de la tarjeta: ").append(getCode())
		.append("CCV: ").append(getCcv())
		.append("Fecha de Vencimiento: ").append(getDueDate());
		
		return virtualCardInformation.toString();
		
	}
}