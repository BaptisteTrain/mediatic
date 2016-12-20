package fr.dta.mediatic.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.dta.mediatic.user.dao.UserDAO;
import fr.dta.mediatic.user.model.User;

@Service
public class UserService {
	
	private UserDAO userDao = UserDAO.instance();

	/**
	 * Ask for all users to DAO
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {

		return userDao.selectAllUsers();
	}

	/**
	 * Ask for user by id
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(String id) {

		return userDao.findUsersById(id);
	}

	/**
	 * Ask for user by name
	 * 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public List<User> getUserByName(String firstname, String lastname) {

		return userDao.findUserByName(lastname, firstname);
	}

	/**
	 * Ask for user by id or name
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public List<User> getUserByIdOrName(String id, String firstname, String lastname) {

		if ((id == null || "".equals(id)) && (firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {
			return userDao.selectAllUsers();
		} else if (id == null || "".equals(id)) {
			return userDao.findUserByName(lastname, firstname);
		} else if ((firstname == null || "".equals(firstname)) && (lastname == null || "".equals(lastname))) {
			return userDao.findUsersByIdPartial(id);
		} else {
			return userDao.findUserByIdOrNames(id, lastname, firstname);
		}
	}
	
	
}
