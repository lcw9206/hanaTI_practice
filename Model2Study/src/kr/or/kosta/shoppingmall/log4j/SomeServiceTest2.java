package kr.or.kosta.shoppingmall.log4j;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SomeServiceTest2 {
	
	SomeService service = new SomeService();
	
	@Before
	public void setUp(){}
	
	@After
	public void tearDown(){}
	
	@Test
	public void testSomeService(){
		service.someMethod();
	}
}
