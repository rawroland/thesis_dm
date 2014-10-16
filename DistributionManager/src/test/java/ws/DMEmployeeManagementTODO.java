package ws;
//package ws;
//
//import static org.junit.Assert.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import javax.xml.namespace.QName;
//import javax.xml.ws.Service;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
//
//import ws.services.EmployeeManagementService;
//
//import com.github.springtestdbunit.DbUnitTestExecutionListener;
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:*/spring-test.xml")
//@TestExecutionListeners({
//    DependencyInjectionTestExecutionListener.class,
//    DirtiesContextTestExecutionListener.class,
//    DbUnitTestExecutionListener.class})
//@DatabaseSetup("classpath:*/employee/data.xml")
//public class DMEmployeeManagementIntegrationTest {
//
//	
//	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/employees?wsdl";
//	public static final String NAMESPACE_URI = "http://ws/";
//	public static final String NAMESPACE_PORT = "DMEmployeeManagementPort";
//	
//	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMEmployeeManagementService");
//	private Service service;
//	private EmployeeManagementService employee;
//	
//	@Before
//	public void setUp() throws Exception{
//		try {
//			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		employee = service.getPort(EmployeeManagementService.class);
//	}
//	
//	@After 
//	public void tearDown() throws Exception {
//		
//	}
//	
//	@Test
//	@DirtiesContext
//	public void testAddEmployeeService() {
//		boolean added = this.employee.addEmployee("Ronald", "Koeverman", "freekicker", "cashier");
//		assertEquals(true, added);
//	}
//}
