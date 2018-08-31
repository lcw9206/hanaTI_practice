import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ExitHandler implements WindowListener {

	// 참조받기 위한 frame 선언 (생성자 or getter/setter로 선언 가능)
	ChatFrame frame;
	
	public ExitHandler(ChatFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// 속성에 직접 접근하는 것이 아닌, 기능을 통해 종료함.
		frame.finish();

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
