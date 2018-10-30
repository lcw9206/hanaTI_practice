package kr.or.kosta.shoppingmall.common.service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.xml.internal.ws.util.StringUtils;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

public class XMLObjectFactory {

	// serviceList, daoList를 하나로 통합
	private Hashtable<String, Object> objectList;
	private Document document;
	private DocumentBuilder parser;

	public XMLObjectFactory(String objectMapperLocation) throws ParserConfigurationException, SAXException, IOException {
		objectList = new Hashtable<String, Object>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		factory.setIgnoringElementContentWhitespace(true);
		document = parser.parse(objectMapperLocation);
		try {
			// bean 이름의 객체들 얻기
			NodeList beanList = document.getElementsByTagName("bean");

			// bean 개수만큼 순회
			for (int i = 0; i < beanList.getLength(); i++) { 
				Element bean = (Element)beanList.item(i);
				
				String beanType = bean.getAttribute("type");
				String beanName = bean.getAttribute("name");
				String beanClassName = bean.getAttribute("class");
				// reflection API를 이용해 bean에 선언된 객체 생성 
				Object beanObject = Class.forName(beanClassName).newInstance();
				objectList.put(beanName, beanObject);
				System.out.println("[debug] : ["+beanType+"] "+beanName+" = " + beanClassName);
				if(beanType.equals("component") && beanName.equals("dataSource")) {
					NodeList propertyList = bean.getChildNodes();
					String driverClassName = null;
					String url = null;
					String username = null;
					String password = null;
					String initialSize = null;
					String maxTotal = null;
					String maxIdle = null;
					
					if(propertyList.getLength() > 0) {
						for(int j=0; j<propertyList.getLength(); j++) {
							Node node = propertyList.item(j);
							if(node.getNodeType() == Node.ELEMENT_NODE) {
								Element propertyElement = (Element)node;
								String propertyName = propertyElement.getAttribute("name");
								if(propertyName.equals("driverClassName")) {
									driverClassName = propertyElement.getAttribute("value"); 
								}else if(propertyName.equals("url")) {
									url = propertyElement.getAttribute("value"); 
								}else if(propertyName.equals("username")) {
									username = propertyElement.getAttribute("value"); 
								}else if(propertyName.equals("password")) {
									password = propertyElement.getAttribute("value"); 
								}else if(propertyName.equals("initialSize")) {
									initialSize = propertyElement.getAttribute("value"); 
								}else if(propertyName.equals("maxTotal")) {
									maxTotal = propertyElement.getAttribute("value"); 
								}else if(propertyName.equals("maxIdle")) {
									maxIdle = propertyElement.getAttribute("value"); 
								}
							}
						}
					}
					BasicDataSource dataSource = (BasicDataSource)getBean("dataSource");
					dataSource.setDriverClassName(driverClassName);
					dataSource.setUrl(url);
					dataSource.setUsername(username);
					dataSource.setPassword(password);
					dataSource.setInitialSize(Integer.parseInt(initialSize));
					dataSource.setMaxTotal(Integer.parseInt(maxTotal));
					dataSource.setMaxIdle(Integer.parseInt(maxIdle));
				}else if(beanType.equals("dao")) {
					addDataSource(beanObject);
				}else if(beanType.equals("service")) {
					NodeList propertyList = bean.getChildNodes();
					if(propertyList.getLength() > 0) {
						for(int j=0; j<propertyList.getLength(); j++) {
							Node node = propertyList.item(j);
							if(node.getNodeType() == Node.ELEMENT_NODE) {
								Element propertyElement = (Element)node;
								String propertyName = propertyElement.getAttribute("name");
								String ref = propertyElement.getAttribute("ref");
								String methodName = "set" + StringUtils.capitalize(propertyName);
								Object serviceObject = objectList.get(beanName);
								Object daoObject = objectList.get(ref);
								Class cls = serviceObject.getClass();
								Method method = null;
								try {
									String interfaceName = daoObject.getClass().getInterfaces()[0].getName();
									method = cls.getMethod(methodName,Class.forName(interfaceName));
									method.invoke(serviceObject, daoObject);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getBean(String serviceName) {
		return objectList.get(serviceName);
	}

	public Object getBean(Class cls) {
		String className = cls.getName();
		Enumeration<Object> e = objectList.elements();
		while (e.hasMoreElements()) {
			Object daoObject = e.nextElement();
			if(className.equals(daoObject.getClass().getName())) {
				return daoObject;
			}
		}
		return null;
	}

	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getBean("dataSource"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String mapperLocation = "/Users/mcbookpro/Java/workspace/Model2Study/WebContent/WEB-INF/object-mapper.xml";
		XMLObjectFactory factory = new XMLObjectFactory(mapperLocation);
		UserService userService = (UserService) factory.getBean(UserServiceImpl.class);
		System.out.println(userService.list());
		Class c1 = userService.getClass();
		System.out.println(c1.getSimpleName());
		System.out.println(c1.getTypeName());
		Method[] me = c1.getMethods();
		for (int i = 0; i < me.length; i++) {
			System.out.println(me[i].getName());
		}

	}

}
