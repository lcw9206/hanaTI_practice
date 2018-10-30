package test;
import org.junit.Test;

import kr.or.kosta.shoppingmall.log4j.SomeService;

/**
 * log4J
 */
public class Log4jTest {
	SomeService service = new SomeService();
	
	@Test
	public void testLog4J() {
		service.someMethod();
	}
	
}
