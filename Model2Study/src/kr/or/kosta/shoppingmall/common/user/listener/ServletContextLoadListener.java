package kr.or.kosta.shoppingmall.common.user.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import kr.or.kosta.shoppingmall.common.service.XMLObjectFactory;


/**
 * 모델2
 * @author 김기정
 */
public class ServletContextLoadListener implements ServletContextListener {
	
	/**
	 * ServletContext생성 이벤트 처리
     * ServletContext가 생성되면(서블릿컨테이너 초기화) 웹 애플리케이션내의
     * 모든 Servlet, JSP, Filter 등이 공유할 수 있는 객체 또는 리소스 생성 및 등록(초기화)
	 */
	public void contextInitialized(ServletContextEvent event)  {
		ServletContext servletContext = event.getServletContext();
//		String serviceMapperLocation = servletContext.getInitParameter("serviceMapperLocation");
//		String daoMapperLocation = servletContext.getInitParameter("daoMapperLocation");
		String objectMapperLocation = servletContext.getInitParameter("objectMapperLocation");
		System.out.println(objectMapperLocation);
//		DaoFactory daoFactory = new JdbcDaoFactory(daoMapperLocation);
//		ServiceFactory serviceFactory = new ServiceFactory(serviceMapperLocation);
		
		XMLObjectFactory objectFactory = null;
		try {
			objectFactory = new XMLObjectFactory(objectMapperLocation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		서비스에서 사용하기 위해 등록
//		servletContext.setAttribute("daoFactory", daoFactory);
//		servletContext.setAttribute("serviceFactory", serviceFactory);
		servletContext.setAttribute("objectFactory", objectFactory);
		System.out.println("[debug] : ObjectFactory 생성 완료!");
	}
	
	
	public void contextDestroyed(ServletContextEvent event)  {
		System.out.println("[Debug] : ServletContext(서블릿컨테이너) 종료됨 >>>");
    }
}
