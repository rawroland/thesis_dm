package unit;

import me.rolandawemo.dao.AllDAOTests;
import me.rolandawemo.dao.model.AllModelTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ws.AllWSTests;

@RunWith(Suite.class)
@SuiteClasses({AllDAOTests.class,AllModelTests.class,AllWSTests.class})
public class AllTests {

}
