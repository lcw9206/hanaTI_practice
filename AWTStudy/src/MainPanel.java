import java.awt.BorderLayout;
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

public class MainPanel extends Panel implements ActionListener {
	TalkFrame frame;

	Label tempL;
	Button logoutB;

	public MainPanel(TalkFrame frame) {
		this.frame = frame;
		tempL = new Label("This is Test Panel", Label.CENTER);
		logoutB = new Button("LOGOUT");
		setContents();

		logoutB.addActionListener(this);
	}

	public void setContents() {
		setLayout(new BorderLayout());
		Panel panel = new Panel();
		panel.add(logoutB);

		add(tempL, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		/*
		 * Frame frame = new Frame("메인화면"); MainPanel mainPanel = new MainPanel();
		 * 
		 * frame.add(mainPanel); frame.setSize(300, 500); // frame.pack();
		 * frame.setVisible(true)
		 */;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.changeCard("LOGIN");

	}

}
