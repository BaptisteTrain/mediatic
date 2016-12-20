package fr.dta.mediatic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.dta.mediatic.model.User;
import fr.dta.mediatic.service.UserService;

@RestController
@SessionAttributes
@RequestMapping(value="/api/user")
public class UserController {
	
//	@Autowired
//	private UserService userService;
//	
//	/**
//	 * Select all users
//	 * 
//	 * @return List<User>
//	 */
//	@RequestMapping(value="/allUser", method=RequestMethod.GET)
//	@ResponseBody
//	public List<User> getAllUsers() {
//		
//		return userService.getAllUsers();
//	}
//	
//	/**
//	 * Select one user by his id
//	 * 
//	 * @param id
//	 * 
//	 * @return User
//	 */
//	@RequestMapping(value="{id}", method=RequestMethod.GET)
//	@ResponseBody
//	public User getUserById(@PathVariable String id) {
//		
//		return userService.getUserById(id);
//	}
//	
//	/**
//	 * Select user by name
//	 * 
//	 * @param firstname
//	 * @param lastname
//	 * 
//	 * @return List<User>
//	 */
//	@RequestMapping(value="/userByName", method=RequestMethod.GET)
//	public List<User> getUserByName(@RequestBody String firstname, @RequestBody String lastname) {
//		
//		return userService.getUserByName(firstname, lastname);
//	}
//	
//	/**
//	 * Select User by id or name
//	 * 
//	 * @param id
//	 * @param firstname
//	 * @param lastname 
//	 * 
//	 * @return List<User>
//	 */
//	@RequestMapping(value="/userByIdOrName", method=RequestMethod.GET)
//	public List<User> getUserByIdOrName(@RequestBody String id, @RequestBody String firstname, @RequestBody String lastname){
//		
//		return userService.getUserByIdOrName(id, firstname, lastname);
//	}

}
