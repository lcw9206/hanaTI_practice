package kr.or.kosta.bin;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.AccountManagerMap;

/**
 * 은행계좌관리 애플리케이션 클래스
 * 
 * @author 이철우
 */
public class AMSMap {

	public static void main(String[] args) throws AccountException {
		AccountManagerMap aManager = new AccountManagerMap();
		MainFrame accountGUI = new MainFrame();
		accountGUI.setaManager(aManager);
		accountGUI.setUI();
	}
}