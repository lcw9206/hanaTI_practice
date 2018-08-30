import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 이벤트 처리를 위한 implements
public class LoginPanel extends Panel implements ActionListener {

	// 카드레이아웃을 위해 LoginPanel에서 TalkFrame에 접근해야 한다.
	// 기존 TalkFrame에서 Main과 Login을 생성하기에 복합 관계이다.
	// 하지만 Login에서 Talk에 접근해야 패널 교체가 가능하기 때문에, TalkFrame을 선언한다.
	TalkFrame frame;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	Label emailL, passwdL;
	TextField emailTF, passwdTF;
	Button loginB, registB;

	public LoginPanel(TalkFrame frame) {
		this.frame = frame;
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		emailL = new Label("EMAIL");
		passwdL = new Label("PASSWD");
		emailTF = new TextField();
		passwdTF = new TextField();
		passwdTF.setEchoChar('*');
		loginB = new Button("LOGIN");
		registB = new Button("REGIST");
		setContents();

		// 자체 처리이므로 this
		loginB.addActionListener(this);
	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(4, 1, 4, 1);

		gridBagLayout.setConstraints(component, gridBagConstraints);
		add(component);
	}

	public void setContents() {
		setLayout(gridBagLayout);
		add(new Label(" "), 0, 0, 1, 1, 0, 0);
		add(emailL, 1, 0, 1, 1, 0, 0);
		add(emailTF, 2, 0, 1, 1, 1, 0);
		add(new Label(" "), 3, 0, 1, 1, 0, 0);

		add(new Label(" "), 0, 1, 1, 1, 0, 0);
		add(passwdL, 1, 1, 1, 1, 0, 0);
		add(passwdTF, 2, 1, 1, 1, 1, 0);
		add(new Label(" "), 3, 1, 1, 1, 0, 0);

		Panel buttonPanel = new Panel();
		buttonPanel.add(loginB);
		buttonPanel.add(registB);
		add(buttonPanel, 0, 2, 3, 1, 0, 0);

	}

	public static void main(String[] args) {
		/*
		 * Frame frame = new Frame("메인화면");
		 * 
		 * LoginPanel loginPanel = new LoginPanel();
		 * 
		 * frame.add(loginPanel); frame.setSize(300, 500); // frame.pack();
		 * frame.setVisible(true);
		 */
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// LoginPanel에서 TalkFrame으로 접근해야한다.
		// frame.cardLayout.show(frame.cardPanel, "MAIN");
		// LoginPanel에서 TalkFrame의 카드 교체를 위해 직접 접근하고 있다.
		// 이는 클래스 캡슐화에 어긋나는 방법으로, 아래와 같이 메서드를 만들어 해결할 수 있다.
		System.out.println(arg0);
		frame.changeCard("MAIN");
	}

}
