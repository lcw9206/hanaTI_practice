package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.AccountManagerMap;
import kr.or.kosta.entity.MinusAccount;

/**
 * GUI 생성 및 이벤트 처리를 담당하는 MainFrame 클래스
 * 
 * @author 이철우
 */
public class MainFrame extends Frame {
	Label aTypeL, aNumL, memberNameL, passL, inputMoneyL, outputMoneyL, accountsL, unitL, blankL;
	Choice aTypeC;
	TextField aNumTF, memberNameTF, passTF, restMoneyTF, outputMoneyTF;
	Button aNumCheckB, deleteB, memberNameCheckB, registB, viewB;
	TextArea contentTA;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	private AccountManagerMap aManager;

	public AccountManagerMap getaManager() {
		return aManager;
	}

	public void setaManager(AccountManagerMap aManager) {
		this.aManager = aManager;
	}

	/**
	 *  Default 생성자
	 */
	public MainFrame() {
		aTypeL = new Label("계좌종류");
		aNumL = new Label("계좌번호");
		memberNameL = new Label("예금주명");
		passL = new Label("비밀번호");
		inputMoneyL = new Label("입금금액", Label.CENTER);
		outputMoneyL = new Label("대출금액");
		accountsL = new Label("계좌목록", Label.LEFT);
		// )이 생략되어 이스케이프 문자 추가 
		unitL = new Label("(단위 : 원\\)", Label.RIGHT);
		blankL = new Label(" ");

		aTypeC = new Choice();
		aTypeC.add("전체");
		aTypeC.add("입출금계좌");
		aTypeC.add("마이너스계좌");

		aNumTF = new TextField();
		memberNameTF = new TextField();
		passTF = new TextField();
		restMoneyTF = new TextField();
		outputMoneyTF = new TextField();

		aNumCheckB = new Button("조회");
		deleteB = new Button("삭제");
		memberNameCheckB = new Button("검색");
		registB = new Button("신규등록");
		viewB = new Button("전체조회");

		contentTA = new TextArea();

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		// 생성자에서 Manager 객체 생성
		aManager = new AccountManagerMap();
	}

	/**
	 * GUI 세팅 메서드
	 * gridBagLayout을 기준으로 GUI를 세팅합니다.
	 */
	public void bagsetContents() {
		setLayout(gridBagLayout);

		add(aTypeL, 0, 0, 1, 1, 0, 0, false);
		add(aTypeC, 1, 0, 1, 1, 0, 0, false);

		add(aNumL, 0, 1, 1, 1, 0, 0, false);
		add(aNumTF, 1, 1, 2, 1, 1, 0, true);
		add(aNumCheckB, 3, 1, 1, 1, 0, 0, false);
		add(deleteB, 4, 1, 1, 1, 0, 0, false);

		add(memberNameL, 0, 2, 1, 1, 0, 0, false);
		add(memberNameTF, 1, 2, 2, 1, 0, 0, true);
		add(memberNameCheckB, 3, 2, 1, 1, 0, 0, false);

		add(passL, 0, 3, 1, 1, 0, 0, false);
		add(passTF, 1, 3, 2, 1, 0, 0, false);
		add(inputMoneyL, 3, 3, 1, 1, 0, 0, false);
		add(restMoneyTF, 4, 3, 2, 1, 1, 0, true);

		add(outputMoneyL, 0, 4, 1, 1, 0, 0, false);
		add(outputMoneyTF, 1, 4, 2, 1, 0, 0, false);
		add(registB, 3, 4, 1, 1, 0, 0, false);
		add(viewB, 4, 4, 1, 1, 0, 0, false);
		add(blankL, 5, 4, 1, 1, 0, 0, false);

		add(accountsL, 0, 5, 1, 1, 0, 0, false);
		add(unitL, 3, 5, 3, 1, 1, 0, false);

		add(contentTA, 0, 6, 6, 0, 1, 0, true);
	}

	/**
	 * 컴포넌트 설정 메서드
	 * 컴포넌트별 위치, 길이 등의 설정을 세팅합니다.
	 * 
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param gridheight
	 * @param weightx
	 * @param weighty
	 * @param fill
	 */
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty, boolean fill) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		// TextField별 HORIZONTAL 적용 유무를 구별하기 위한 분기문
		if (fill == true) {
			gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		}

		gridBagLayout.setConstraints(component, gridBagConstraints);

		add(component);
	}

	/**
	 * 테스트 생성 메서드
	 * 테스트 값을 생성합니다.
	 */
	public void setExample() {
		aManager.add(new Account("1111-2222-3333", "이철우", 1234, 1000));
		aManager.add(new Account("1111-2222-4444", "김철우", 1234, 0));
		aManager.add(new Account("1111-2222-5555", "박철우", 1234, 50000));
		aManager.add(new MinusAccount("1111-2222-6666", "최철우", 1234, 0, 50000));
		aManager.add(new MinusAccount("1111-2222-7777", "최철우", 1234, 50000, 50000));
		aManager.add(new MinusAccount("1111-2222-8888", "최철우", 1234, 0, 100000));
	}

	/**
	 * GUI 생성 위치 조정 메서드
	 * 동적으로 GUI의 생성 위치를 중앙으로 조정합니다.
	 */
	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
	}

	/**
	 * 메뉴바 출력 메서드
	 * 출력이 필요한 이벤트에 선언되어 TextArea의 최상단에 메뉴를 출력합니다.
	 */
	public void topMenu() {
		contentTA.setText("");
		contentTA.append("------------------------------------------------------------------\n");
		contentTA.append("계좌종류\t      계좌번호\t\t예금주명\t현재잔액\t대출금액\n");
		contentTA.append("=====================================================\n");
	}

	/**
	 * 계좌종류 구분 메서드
	 * 계좌의 종류에 따라 대출금액 필드를 활성/비활성화 처리합니다.
	 * getSelectedIndex를 이용해 분류합니다. 
	 */
	public void selectType() {
		// TextArea 초기화
		contentTA.setText("");
		if (aTypeC.getSelectedIndex() == 1) {
			outputMoneyTF.setEnabled(false);
		} else {
			outputMoneyTF.setEnabled(true);
		}
	}

	/**
	 * 계좌조회 메서드 
	 * 입력된 계좌번호가 없거나, 입력된 계좌번호로 조회한 결과가 없을 경우, 예외처리로 넘어갑니다.
	 */
	public void getAccount() {
		Account account = aManager.get(aNumTF.getText());
		if (!(aNumTF.getText().trim().equals("") || account == null)) {
			topMenu();
			contentTA.append(account.toString());
		} else {
			try {
				throw new AccountException("해당 번호로 조회되는 계좌가 없습니다.", -200);
			} catch (AccountException e1) {
				e1.printStackTrace();
				contentTA.setText("");
				JOptionPane.showMessageDialog(null, "해당 번호로 조회되는 계좌가 없습니다.", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * 계좌삭제 메서드
	 * 입력된 계좌번호가 없거나, 입력된 계좌번호로 조회한 결과가 없을 경우, 삭제를 진행하지 않고 예외처리로 넘어갑니다.
	 */
	public void removeAccount() {
		Account account = aManager.get(aNumTF.getText());
		if (!(aNumTF.getText().trim().equals("") || account == null)) {
			aManager.remove(aNumTF.getText());
			JOptionPane.showMessageDialog(null, "해당계좌가 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			listAll();
		} else {
			try {
				throw new AccountException("삭제하려는 계좌가 존재하지 않습니다.", -500);
			} catch (AccountException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "삭제하려는 계좌가 존재하지 않습니다.", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	/**
	 * 계좌검색 메서드 
	 * 입력된 예금주명이 없거나, 입력된 예금주명으로 조회한 결과가 없을경우 예외처리로 넘어갑니다.
	 * 검색 값이 있을 경우, 반복문을 통해 값을 출력합니다.
	 */
	public void searchAccount() {
		List<Account> list = aManager.search(memberNameTF.getText());
		if (!(memberNameTF.getText().trim().equals("") || list == null)) {
			topMenu();
			for (int i = 0; i < list.size(); i++) {
				contentTA.append(list.get(i).toString());
			}
		} else {
			try {
				throw new AccountException("해당 이름으로 조회되는 계좌가 없습니다.", -300);
			} catch (AccountException e1) {
				e1.printStackTrace();
				contentTA.setText("");
				JOptionPane.showMessageDialog(null, "해당 이름으로 조회되는 계좌가 없습니다.", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * 계좌생성 메서드
	 * 계좌생성에 필요한 값이 하나라도 비어있을 경우 예외처리로 넘어갑니다.
	 * 값이 모두 입력되어있을 경우, 계좌종류에 따라 객체를 생성하며, 입력된 금액이 없으면 0으로 초기화됩니다.
	 */
	public void addAccount() {
		if (!(aNumTF.getText().trim().equals("") || memberNameTF.getText().trim().equals("")
				|| passTF.getText().trim().equals(""))) {
			if (aTypeC.getSelectedIndex() == 1) {
				Account addAccount = new Account(aNumTF.getText(), memberNameTF.getText(),
						Integer.parseInt(passTF.getText()),
						(!restMoneyTF.getText().trim().equals("")) ? Long.parseLong(restMoneyTF.getText()) : 0);
				aManager.add(addAccount);
				listAll();
				return;
			} else if (aTypeC.getSelectedIndex() == 2) {
				MinusAccount addMAccount = new MinusAccount(aNumTF.getText(), memberNameTF.getText(),
						Integer.parseInt(passTF.getText()),
						(!restMoneyTF.getText().trim().equals("")) ? Long.parseLong(restMoneyTF.getText()) : 0,
						(!outputMoneyTF.getText().trim().equals("")) ? Long.parseLong(outputMoneyTF.getText()) : 0);
				aManager.add(addMAccount);
				listAll();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "계좌 종류를 선택 후, 생성해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
				contentTA.setText("");
			}
		} else {
			try {
				throw new AccountException("비어있는 항목이 있습니다. 다시 확인해주세요.", -400);
			} catch (AccountException e1) {
				e1.printStackTrace();
				contentTA.setText("");
				JOptionPane.showMessageDialog(null, "비어있는 항목이 있습니다. 다시 확인해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	
	/**
	 *  전체 계좌 조회 메서드
	 *  계좌 정보가 담겨있는 aManager의 size를 측정해 0일 경우, 예외처리를 진행합니다.
	 */
	public void listAll() {
		if (aManager.list().size() != 0) {
			topMenu();
			for (int i = 0; i < aManager.list().size(); i++) {
				contentTA.append(aManager.list().get(i).toString());
			}
		} else {
			try {
				throw new AccountException("현재 등록된 계좌가 없습니다.", -100);
			} catch (AccountException e1) {
				e1.printStackTrace();
				contentTA.setText("");
				JOptionPane.showMessageDialog(null, "현재 등록된 계좌가 없습니다.", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * 종료 메서드
	 * GUI 상단의 X를 눌렀을 때 종료되도록 합니다.
	 */
	public void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	/**
	 * 이름없는 지역 클래스를 이용한 이벤트 처리 메서드
	 * 기존 로직을 메서드로 분리했습니다.
	 */
	public void eventRegist() {

		aTypeC.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				selectType();
			}
		});

		aNumCheckB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getAccount();
			}
		});

		deleteB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeAccount();
			}
		});

		memberNameCheckB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchAccount();
			}
		});

		registB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addAccount();
			}
		});

		viewB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listAll();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		// 비밀번호 입력 시, '*'로 입력되게 합니다.
		passTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				passTF.setEchoChar('*');
			}
		});
	}

	/**
	 * 최종 세팅 메서드
	 * 세팅에 필요한 메서드들을 모아 일괄적으로 처리합니다. 
	 */
	public void setUI() {
		MainFrame practice = new MainFrame();
		practice.bagsetContents();
		practice.setSize(600, 500);
		practice.setCenter();
		practice.setExample();
		practice.eventRegist();
		practice.setVisible(true);
	}
}
