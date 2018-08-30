import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;


/**
 * CardLayout 적용 Frame
 * @author 김기정
 *
 */
public class TalkFrame extends Frame {
	
	// TalkFrame의 구성요소
	LoginPanel loginPanel;
	MainPanel mainPanel;
	
	Panel cardPanel;
	CardLayout cardLayout;
	
	
	public TalkFrame() {
		this("이름없음");
	}
	
	public TalkFrame(String title) {
		super(title);
		loginPanel = new LoginPanel(this);
		mainPanel = new MainPanel(this);
		
		// 밑의 패널에 CardLayout을 적용한다.
		cardPanel = new Panel();
		cardLayout = new CardLayout();
		
	}
	
	// 화면 배치
	public void setContents() {
		cardPanel.setLayout(cardLayout);
		// Queue와 같이 순차적으로 나옴.
		cardPanel.add("LOGIN", loginPanel);
		cardPanel.add("MAIN", mainPanel);
		
		add(cardPanel, BorderLayout.CENTER);
		
//		cardLayout.show(cardPanel, "MAIN");
	}
	
	public void setCenter() {
		//Runtime.getRuntime().exec(command);
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (dim.width - getSize().width)/2;
		int y = (dim.height - getSize().height)/2;
		setLocation(x, y);
	}
	
	// 클래스 
	public void changeCard(String name) {
		cardLayout.show(cardPanel, name);
	}
	
	public static void main(String[] args) {
		TalkFrame frame = new TalkFrame("Kotalk");
		frame.setContents();
		frame.setSize(300, 500);
		frame.setCenter();
		frame.setVisible(true);
	}
}


