package me.rolandawemo.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountDAOTest.class, ClientDAOTest.class,
		EmployeeDAOTest.class, ProductDAOTest.class, TransactionDAOTest.class })
public class AllDAOTests {

}
