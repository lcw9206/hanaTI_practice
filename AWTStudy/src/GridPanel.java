import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;

// 재사용성을 위해 Panel을 상속받는다.
public class GridPanel extends Panel {
	Button[] buttons;

	public GridPanel() {
		// 순서는 좌에서 우로 순차적으로 채워진다.
		setLayout(new GridLayout(10, 10));
		buttons = new Button[100];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button(i + "버튼");
			add(buttons[i]);
		}
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame("그리드 레이아웃");
		// Panel 모듈
		GridPanel panel = new GridPanel();
		frame.add(panel);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
}
