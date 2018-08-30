import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

// 재사용성을 위해 Panel을 상속받는다.
public class OnlyGridBag extends Panel {
	TextField personTF, fileTF, titleTF;
	TextArea contentTA;
	Label personL, fileL, titleL, blankL;
	Button findB, sendB, cancelB;
	
	Panel bottomP;
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	public OnlyGridBag() {
		personTF = new TextField();
		fileTF = new TextField();
		titleTF = new TextField();
		contentTA = new TextArea();
		personL = new Label("받는사람");
		fileL = new Label("첨부파일");
		titleL = new Label("제목");
		blankL = new Label();
		findB = new Button("찾기");
		sendB = new Button("보내기");
		cancelB = new Button("취소");
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		bottomP = new Panel();
		
	}

	public void bagsetContents() {
		setLayout(gridBagLayout);
		add(personL, 0, 0, 1, 1, 0, 0, 0);
		add(personTF, 1, 0, 2, 1, 1, 0, 1);
		
		add(fileL, 0, 1, 1, 1, 0, 0, 0);
		add(fileTF, 1, 1, 2, 1, 1, 0, 1);
		add(findB, 3, 1, 1, 1, 0, 0, 0);
		add(blankL, 3, 1, 4, 1, 1, 0, 0);
		
		add(titleL, 0, 2, 1, 1, 0, 0, 0);
		add(titleTF, 1, 2, 6, 1, 0, 0, 0);
		
		add(contentTA, 0, 3, 8, 4, 0, 0, 0);
		
		add(sendB, 1, 8, 1, 1, 0, 0, 0);
		add(cancelB, 3, 8, 1, 1, 0, 0, 0);
	}

	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty, int fill) {
		// 버튼 정보 설정 => 밑의 정보들은 요소 붙일 때 꼭 필요한 것
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		
		// 요소의 크기를 조정, default = NONE
		if(fill == 1) {
			gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		}
		// 격자에 margin을 준다.
		gridBagConstraints.insets = new Insets(20, 20, 20, 20);

		// 위에서 설정한 정보를 set
		gridBagLayout.setConstraints(component, gridBagConstraints);

		// 버튼 붙이기
		add(component);
	}
	
	
	public static void main(String[] args) {
		OnlyGridBag practice = new OnlyGridBag(); 
		Frame frame = new Frame();
		
		practice.bagsetContents();
		frame.add(practice);
		
		frame.setSize(600, 600);
		frame.setVisible(true);
	}
}
