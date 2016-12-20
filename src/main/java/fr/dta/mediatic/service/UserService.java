package fr.dta.mediatic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.mediatic.model.User;
import fr.dta.mediatic.repository.UserRepository;

@Service
public class UserService {
	
	//@Autowired private UserRepository userRepository;

//	/**
//	 * Ask for all users to Repository
//	 * 
//	 * @return
//	 */
//	public List<User> getAllUsers() {
//
//		return userRepository.selectAllUsers();
//	}
//
//	/**
//	 * Ask for user by id
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public User getUserById(String id) {
//
//		return userRepository.findUsersById(id);
//	}
//
//	/**
//	 * Ask for user by name
//	 * 
//	 * @param firstname
//	 * @param lastname
//	 * @return
//	 */
//	public List<User> getUserByName(String firstname, String lastname) {
//
//		return userRepository.findUserByName(lastname, firstname);
//	}
//
//	/**
//	 * Ask for user by id or name
//	 * 
//	 * @param id
//	 * @param firstname
//	 * @param lastname
//	 * @return
//	 */
//	public List<User> getUserByIdOrName(String id, String firstname, String lastname) {
//
//		if ((id == null || "".equals(id)) && (firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {
//			return userRepository.selectAllUsers();
//		} else if (id == null || "".equals(id)) {
//			return userRepository.findUserByName(lastname, firstname);
//		} else if ((firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {
//			return userRepository.findUsersByIdPartial(id);
//		} else {
//			return userRepository.findUserByIdOrNames(id, lastname, firstname);
//		}
//	}
	
	
}
