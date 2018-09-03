package graphics;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

public class ImagePanel extends Panel {
	
	Button button;
	private String path = "classes/resource/images.jpeg";
	Image background;
	
	public ImagePanel() {
		button = new Button("Test");
		background = Toolkit.getDefaultToolkit().getImage(path);
		setLayout(new FlowLayout());
		add(button);
	}
	
	@Override
	public void paint(Graphics g) {
		// 현재 패널의 사이즈를 동적으로 getWidth(), getHeight()로 가져옴
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		ImagePanel panel = new ImagePanel();
		frame.add(panel);
		frame.setSize(600, 500);
		frame.setVisible(true);
	}
}
