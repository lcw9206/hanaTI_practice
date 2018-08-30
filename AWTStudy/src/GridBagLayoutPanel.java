import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;

// 재사용성을 위해 Panel을 상속받는다.
public class GridBackLayoutPanel extends Panel {

	Button button1, button2, button3;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	public GridBackLayoutPanel() {
		button1 = new Button("버튼1");
		button2 = new Button("버튼2");
		button3 = new Button("버튼3");
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

	}

	public void setContents() {
		// 레이아웃 변경
		setLayout(gridBagLayout);

//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 0;
//		gridBagConstraints.gridwidth = 1;
//		gridBagConstraints.gridheight = 1;
//		gridBagConstraints.weightx = 0;
//		gridBagConstraints.weighty = 0;
//		gridBagLayout.setConstraints(button1, gridBagConstraints);
//		add(button1);
//
//
//		gridBagConstraints.gridx = 1;
//		gridBagConstraints.gridy = 0;
//		gridBagConstraints.gridwidth = 1;
//		gridBagConstraints.gridheight = 1;
//		gridBagConstraints.weightx = 1;
//		gridBagConstraints.weighty = 1;
//		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
//		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
//		gridBagLayout.setConstraints(button2, gridBagConstraints);
//		add(button2);

		add(button1, 0, 0, 1, 1, 0, 0);
		add(button2, 1, 0, 1, 1, 1, 0);
		add(button3, 0, 1, 2, 1, 1, 0);
	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty) {
		// 버튼 정보 설정 => 밑의 정보들은 요소 붙일 때 꼭 필요한 것
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		
		// 요소의 크기를 조정, default = NONE
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		
		// 격자에 margin을 준다.
		// gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		// 위에서 설정한 정보를 set
		gridBagLayout.setConstraints(component, gridBagConstraints);

		// 버튼 붙이기
		add(component);
	}

	public static void main(String[] args) {
		Frame frame = new Frame("GridBag 레이아웃");
		// Panel 모듈
		GridBackLayoutPanel panel = new GridBackLayoutPanel();
		panel.setContents();

		// Frame에 Panel 붙이기
		frame.add(panel);
		frame.setSize(400, 400);
		/*알아서 구성요소의 크기에 맞게 size를 줄여줌 (pack)
		frame.pack();*/
		frame.setVisible(true);
	}
}
