import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class AWTExample {

	public static void main(String[] args) {	
		Frame frame = new Frame("처음 만든 프레임");
		frame.setSize(1000, 800);
		frame.setVisible(true);
		
		Button button1 = new Button("AWT 버튼");
		Button button2 = new Button("AWT 버튼2");
		
		frame.setLayout(new FlowLayout());
		frame.add(button1);
		frame.add(button2);
		
		Label label = new Label("AWT Label");
		frame.add(label);
		
		TextField textField = new TextField("아이디", 10);
		frame.add(textField);
		
		TextArea textArea = new TextArea(5, 20);
		frame.add(textArea);
		
		Checkbox checkBox = new Checkbox("남자", true);
		frame.add(checkBox);
		CheckboxGroup c3 = new CheckboxGroup();
		Checkbox cb1 = new Checkbox("Male", true, c3);
		Checkbox cb2 = new Checkbox("Female", false, c3);
		frame.add(cb1);
		frame.add(cb2);
		
		Choice selectBox = new Choice();
		selectBox.add("축구");
		selectBox.add("농구");
		selectBox.add("배구");
		
//		frame.setResizable(false);
		
	}

}
