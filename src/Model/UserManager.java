package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import Exceptions.ExistingUserException;
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
				.filter(user->user.getLicense()==license&&user.getPassword().equals(password))
				.findFirst()
				.orElseThrow(UserNotFoundException::new);

		return filteredUser;
	}

	public boolean verifyIfUserExists(long license,String password){
		boolean userExists=usersDataBase.stream()
		             .anyMatch(user->user.getLicense()==license&&password.equals(password));
		             
		return userExists;

	}

	public long verifyUserLicense(long license){
		return license;
	}

	public long verifyUserIdentityTarjet(long identityTarjet){

		return identityTarjet;
	}



}
