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
	 * 기본 생성자
	 */
	public MainFrame() {
		aTypeL = new Label("계좌종류");
		aNumL = new Label("계좌번호");
		memberNameL = new Label("예금주명");
		passL = new Label("비밀번호");
		inputMoneyL = new Label("입금금액", Label.CENTER);
		outputMoneyL = new Label("대출금액");
		accountsL = new Label("계좌목록", Label.LEFT);
		// )이 짤려서 이스케이프 문자를 추가했습니다.
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
	 * gridBagLayout을 기반으로한 GUI 세팅 메서드
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
	 * 컴포넌트 별 위치 지정 및 설정을 위한 add 메서드
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

		// TextField의 HORIZONTAL 적용 유무를 구별하기 위한 분기문
		if (fill == true) {
			gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		}

		gridBagLayout.setConstraints(component, gridBagConstraints);

		add(component);
	}

	/**
	 * 테스트를 위한 데이터 세팅 메서드
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
	 * 동적으로 GUI의 생성 위치를 조정하기 위한 메서드
	 */
	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
	}

	/**
	 * TextArea의 최상단 고정 메뉴 출력을 위한 메서드 출력 메서드의 최상단에서 호출
	 */
	public void topMenu() {
		contentTA.setText("");
		contentTA.append("------------------------------------------------------------------\n");
		contentTA.append("계좌종류\t      계좌번호\t\t예금주명\t현재잔액\t대출금액\n");
		contentTA.append("=====================================================\n");
	}

	/**
	 * 종료버튼 활성화를 위한 메서드
	 */
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	/**
	 * 이름없는 지역 클래스를 이용한 이벤트 처리 메서드 핵심 부분
	 */
	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			/**
			 * 종료버튼 활성화를 위한 이벤트 처리
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		passTF.addKeyListener(new KeyAdapter() {
			/**
			 * 비밀번호 입력 시, '*'로 입력되도록 하는 이벤트 처리
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				passTF.setEchoChar('*');
			}
		});

		aTypeC.addItemListener(new ItemListener() {
			/**
			 * 계좌 종류 선택에 따라 대출금액 TextField를 활성화 혹은 비활성화 한다. 
			 */
			@Override
			public void itemStateChanged(ItemEvent e) {
				contentTA.setText("");
				if (aTypeC.getSelectedIndex() == 1) {
					outputMoneyTF.setEnabled(false);
				} else {
					outputMoneyTF.setEnabled(true);
				}
			}
		});

		viewB.addMouseListener(new MouseAdapter() {
			/**
			 * 전체조회 버튼을 눌렀을 때의 이벤트 처리 계좌정보가 담겨있는 list의 길이가 0일 경우 예외처리 진행 아닐 경우,
			 * TextArea에 list를 출력
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
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
		});

		aNumCheckB.addMouseListener(new MouseAdapter() {
			/**
			 * 조회 버튼을 눌렀을 때의 이벤트 처리 계좌번호에 입력값이 없거나, 입력된 계좌번호로 조회한 결과가 없을 경우 예외처리 진행
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
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
		});

		memberNameCheckB.addMouseListener(new MouseAdapter() {
			/**
			 * 검색 버튼을 눌렀을 때의 이벤트 처리 예금주명에 입력값이 없거나, 입력된 예금주명으로 조회한 결과가 없을 경우 예외처리 진행
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
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
		});

		registB.addMouseListener(new MouseAdapter() {
			/**
			 * 신규등록 버튼을 눌렀을 때의 이벤트 처리 등록에 필요한 계좌번호, 예금주명, 비밀번호, 그리고 동일 계좌번호의 유무로 유효성 검사를 진행해
			 * 예외처리 이 후, 계좌타입에 따라 분기 후, 객체 생성 및 종료
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!(aNumTF.getText().trim().equals("") || memberNameTF.getText().trim().equals("")
						|| passTF.getText().trim().equals(""))) {
					topMenu();
					if (aTypeC.getSelectedIndex() == 1) {
						Account addAccount = new Account(aNumTF.getText(), memberNameTF.getText(),
								Integer.parseInt(passTF.getText()),
								(!restMoneyTF.getText().trim().equals("")) ? Long.parseLong(restMoneyTF.getText()) : 0);
						aManager.add(addAccount);
						contentTA.append(addAccount.toString());
						return;
					} else if (aTypeC.getSelectedIndex() == 2) {
						MinusAccount addMAccount = new MinusAccount(aNumTF.getText(), memberNameTF.getText(),
								Integer.parseInt(passTF.getText()),
								(!restMoneyTF.getText().trim().equals("")) ? Long.parseLong(restMoneyTF.getText()) : 0,
								(!outputMoneyTF.getText().trim().equals("")) ? Long.parseLong(outputMoneyTF.getText())
										: 0);
						aManager.add(addMAccount);
						contentTA.append(addMAccount.toString());
						return;
					} else {
						JOptionPane.showMessageDialog(null, "계좌 종류를 선택 후, 생성해주세요.", "알림", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					try {
						throw new AccountException("비어있는 항목이 있습니다. 다시 확인해주세요.", -400);
					} catch (AccountException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "비어있는 항목이 있습니다. 다시 확인해주세요.", "알림",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		deleteB.addMouseListener(new MouseAdapter() {
			/**
			 * 삭제 버튼을 눌렀을 때의 이벤트 처리 계좌번호에 입력값이 없거나, 조회되는 계좌가 없을 경우 예외처리 진행
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				Account account = aManager.get(aNumTF.getText());
				if (!(aNumTF.getText().trim().equals("") || account == null)) {
					aManager.remove(aNumTF.getText());
					JOptionPane.showMessageDialog(null, "해당계좌가 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						throw new AccountException("삭제하려는 계좌가 존재하지 않습니다.", -500);
					} catch (AccountException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "삭제하려는 계좌가 존재하지 않습니다.", "알림", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	/**
	 * 세팅 관련 메서드들을 모아놓은 메서드
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
