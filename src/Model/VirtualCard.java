package Model;

import java.time.LocalDate;

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
}