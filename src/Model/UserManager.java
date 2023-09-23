package Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.UserNotFoundException;

public class UserManager {

	public List<User>usersDataBase;


	public UserManager() {
		usersDataBase=new ArrayList<User>();
	}

	public User createUser(String mail,String password,String name,String lastName,long license){
		User user=new User();
		user.setMail(mail);
		user.setPassword(password);
		user.setName(name);
		user.setLastName(lastName);
		user.setLicense(license);
		return user;
	}

	public void registerUserInDataBase(User user){
		usersDataBase.add(user);
	}

	public User filterUser(long license,String password){
		User filteredUser=usersDataBase.stream()
				.filter(user->user.getLicense()==license&&user.getName().equals(password))
				.findFirst()
				.orElseThrow(UserNotFoundException::new);

		return filteredUser;
	}

	public void verifyUserLicense(long license){

	}

	public void verifyUserIdentityTarjet(long identityTarjet){

	}



}
