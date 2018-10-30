package kr.or.kosta.shoppingmall.common.service;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.domain.User;
import kr.or.kosta.shoppingmall.user.service.UserService;
import kr.or.kosta.shoppingmall.user.service.UserServiceImpl;

public class ServiceFactory {
	
	private Hashtable<String, Object> serviceList;
	
	public ServiceFactory(String serviceMapperLocation) {
		serviceList = new Hashtable<String, Object>();
		
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(serviceMapperLocation);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("--- 서비스 생성 목록 ---");
			while (keyIter.hasNext()) {
				String serviceName = (String) keyIter.next();
				String serviceClassName = prop.getProperty(serviceName);
				// 컨트롤러 생성
				Object serviceObject =  Class.forName(serviceClassName).newInstance();
				serviceList.put(serviceClassName, serviceObject);
				System.out.println(serviceClassName + "=" + serviceObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object getService(String serviceName) {
		return serviceList.get(serviceName);
	}
	
	public Object getService(Class cls) {
		return getService(cls.getName());
	}
	
	public static void main(String[] args) throws Exception {
		String location = "/Users/mcbookpro/Java/workspace/Model2Study/WebContent/WEB-INF/service-mapper.properties";
		ServiceFactory factory = new ServiceFactory(location);
		System.out.println(JdbcUserDao.class);
		UserService userService = (UserService) factory.getService(UserServiceImpl.class);
		System.out.println(userService);
		userService.list();
		}
}

