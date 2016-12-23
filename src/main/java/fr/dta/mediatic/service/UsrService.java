package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.mediatic.model.Usr;
import fr.dta.mediatic.repository.UsrRepository;

@Service
public class UsrService {
	
	@Autowired
	private UsrRepository userRepository;

	/**
	 * Ask for all users to Repository
	 * 
	 * @return
	 */
	public List<Usr> getAllUsers() {

		return userRepository.selectAllUsers();
	}

	/**
	 * Ask for user by id
	 * 
	 * @param id
	 * @return
	 */
	public Usr getUserById(int id) {

		return userRepository.getById(id);
	}

	/**
	 * Ask for user by name
	 * 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public List<Usr> getUserByName(String firstname, String lastname) {

		if ((firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {
			return userRepository.getAll();
		} else {
			return userRepository.findUserByName(lastname, firstname);
		}
	}

	/**
	 * Ask for user by id or name
	 * 
	 * If all parameters are empty it will return the complete list of Users.
	 * Else
	 * If id is empty, the search will only be based on the Lastname and Firstname.
	 * Else
	 * If lastname AND firstname are empty, the search will only be based on the ID.
	 * Else
	 * The search will use all three parameters to find the Users.
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public List<Usr> getUserByIdOrName(String login, String firstname, String lastname) {

		if ((login == null || "".equals(login)) && (firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {System.out.println("hre");
			return userRepository.selectAllUsers();
		} else if (login == null || "".equals(login)) {
			return userRepository.findUserByName(lastname, firstname);
		} else if ((firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {
			return userRepository.findUsersByIdPartial(login);
		} else {
			return userRepository.findUserByIdOrNames(login, lastname, firstname);
		}
	}
	
	/**
	 * Create a user
	 * 
	 * @param entity
	 */
	public void addUser(Usr user) {
		
		userRepository.add(user);
	}
	
	/**
	 * Delete a user
	 * 
	 * @param user
	 */
	public void deleteUser(Usr user) {
		
		userRepository.delete(user);
	}
	
	/**
	 * Update a user
	 * 
	 * @param user
	 */
	public void updateUser(Usr user) {
		
		userRepository.update(user);
	}
	
	/**
	 * Ask for authenticate
	 * 
	 * @param login
	 * @param pwd
	 */
	public boolean authenticate(String login, String pwd) {
		
		return userRepository.authenticate(login, pwd);
	}

	/**
	 * Ask for user by login
	 * 
	 * @return
	 */
	public Usr getUserByLogin(String login) {

		return userRepository.findByLogin(login);
	}
}
