package fr.dta.mediatic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.dta.mediatic.model.Usr;
import fr.dta.mediatic.service.UsrService;

@RestController
@SessionAttributes
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	private UsrService userService;
	
	/**
	 * Select all users
	 * 
	 * @return List<User>
	 */
	@RequestMapping(value="/allUser", method=RequestMethod.GET)
	public List<Usr> getAllUsers() {
		
		return userService.getAllUsers();
	}
	
	/**
	 * Select one user by his id
	 * 
	 * @param id
	 * 
	 * @return User
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody
	public Usr getUserById(@PathVariable int id) {
		
		return userService.getUserById(id);
	}
	
	/**
	 * Select user by name
	 * 
	 * @param firstname
	 * @param lastname
	 * 
	 * @return List<User>
	 */
	@RequestMapping(value="/userByName", method=RequestMethod.GET)
	public List<Usr> getUserByName(@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname) {
		
		return userService.getUserByName(firstname, lastname);
	}
	
	/**
	 * Select User by id or name
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname 
	 * 
	 * @return List<User>
	 */
	@RequestMapping(value="/userByIdOrName", method=RequestMethod.GET)
	public List<Usr> getUserByIdOrName(@RequestParam(required = false) String login, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname){
		
		return userService.getUserByIdOrName(login, firstname, lastname);
	}

	/**
	 * Create a User
	 * 
	 * @param user
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public void addUser(@RequestBody Usr user) {
		
		userService.addUser(user);
	}
	
	/**
	 * Delete a user
	 * 
	 * @param user
	 */
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public void delete(@RequestBody Usr user) {
		
		userService.deleteUser(user);
	}
	
	/**
	 * Update a user
	 * 
	 * @param user
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void updateUser(@RequestBody Usr user) {
		
		userService.updateUser(user);
	}
	
	/**
	 * Authentication of user
	 * 
	 * @param login
	 * @param pwd
	 */
	@RequestMapping(value="/authenticate", method=RequestMethod.GET)
	public boolean authenticate(String login, String pwd) {
		
		return userService.authenticate(login, pwd);
	}
}
