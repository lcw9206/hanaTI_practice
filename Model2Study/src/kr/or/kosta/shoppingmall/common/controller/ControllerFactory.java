package kr.or.kosta.shoppingmall.common.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

/**
 * Factory 패턴 적용 ControllerFactory
 * 세부 컨트롤러들을 생성해 프로퍼티 파일에 저장
 * @author 김기정
 */
public class ControllerFactory{

	// 요청에 대한 세부 컨트롤러 클래스 생성 및 관리
	private HashMap<String, Controller> controllerMap;
	// controllerMapperLocation => 프로퍼티 파일의 위치
	public ControllerFactory(String controllerMapperLocation) {
		controllerMap = new HashMap<String, Controller>();

		// 매핑정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(controllerMapperLocation);
			prop.load(fis);
			// 프로퍼티 파일에서 리스트를 읽어와 순회하며 HashMap에 컨트롤러 저장 
			Iterator keyIter = prop.keySet().iterator();
			System.out.println("--- 컨트롤러 생성 목록 ---");
			while (keyIter.hasNext()) {
				String uri = (String) keyIter.next();
				String controllerClass = prop.getProperty(uri);
				System.out.println("1234 : " + controllerClass);
				// 컨트롤러 생성
				Controller controllerObject = (Controller) Class.forName(controllerClass).newInstance();
				controllerMap.put(uri, controllerObject);
				System.out.println(uri + "=" + controllerObject);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Controller getController(String uri) {
		return controllerMap.get(uri);
	}
}
