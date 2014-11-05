package ws;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DMAccountManagementTest.class, DMAuthenticationTest.class,
		DMClientManagementTest.class, DMEmployeeManagementTest.class,
		DMProductManagementTest.class, DMTransactionManagementTest.class,DMReportingManagementTest.class })
public class AllWSTests {

}
