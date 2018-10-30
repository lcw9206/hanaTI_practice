package kr.or.kosta.shoppingmall.common.dao;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class JdbcDaoFactory extends DaoFactory {
	
	private Hashtable<String, Object> daos;
	
	public JdbcDaoFactory(String Location) {
		daos = new Hashtable<String, Object>();
		
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(Location);
			prop.load(fis);
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("--- DAO 생성 목록 ---");
			while (keyIter.hasNext()) {
				String daoName = (String) keyIter.next();
				String daoClassName = prop.getProperty(daoName);
				// 컨트롤러 생성
				Object daoObject =  Class.forName(daoClassName).newInstance();
				addDataSource(daoObject);
				daos.put(daoClassName, daoObject);
				System.out.println(daoClassName + "=" + daoObject);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addDataSource(Object dao) {
		Class cls = dao.getClass();
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, getDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getDao(String daoName) {
		return daos.get(daoName);
	}
	
	@Override
	public Object getDao(Class cls) {
		return daos.get(cls.getName());
	}
	
	public static void main(String[] args) throws Exception {
		String location = "/Users/mcbookpro/Java/workspace/Model2Study/WebContent/WEB-INF/dao-mapper.properties";
		DaoFactory factory = new JdbcDaoFactory(location);
		System.out.println(JdbcUserDao.class);
		UserDao dao = (UserDao) factory.getDao(JdbcUserDao.class);
//		UserDao dao = (UserDao) factory.getDao("kr.or.kosta.shoppingmall.user.dao.JdbcUserDao");
		List<User> list = dao.listAll();
		for (User user : list) {
			System.out.println(user.toString());
		}
	}
}
