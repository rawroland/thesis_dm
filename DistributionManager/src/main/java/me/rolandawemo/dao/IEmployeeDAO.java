package me.rolandawemo.dao;

import java.util.ArrayList;

import me.rolandawemo.dao.model.Employee;

/**
 * Employee DAO Interface to be implemented by a concrete implementation
 * @author Roland Awemo
 *
 */
public interface IEmployeeDAO {
	/**
	 * Find an Employee by the username and password
	 * @param String username The employee's username.
	 * @param String password The employee's password.
	 * @return Employee An Employee object.
	 */
	Employee findByUsernameAndPassword(String username, String password);
	
	/**
	 * Create a new Employee.
	 * @param String givenname The employee's givenname.
	 * @param String surname The employee's surname.
	 * @param String username The employee's username.
	 * @param String role The employee's role.
	 * @param String password The employee's password.
	 * @return boolean true if the employee was successfully created.
	 */
	int create(String givenname, String surname, String username, String role);
	
	/**
	 * Update an employee with the given id.
	 * @param int id The employee's id.
	 * @param String givenname The employee's givenname.
	 * @param String surname The employee's surname.
	 * @param String username The employee's username.
	 * @param String role The employee's role.
	 * @return boolean true if the employee was successfully updated.
	 */
	int update(int id, String givenname, String surname, String username, String role);
	
	/**
	 * Delete the employee with the given id.
	 * @param int id The employee's id.
	 * @return true if the employee was successfully deleted.
	 */
	int delete(int id);
	
	/**
	 * Generate a secure password.
	 * @param length Length of the password.
	 * @return String Generated password.
	 */
	String generatePassword(int length);
	
	ArrayList<Employee> getAll();
	
	Employee getById(int id);
}
