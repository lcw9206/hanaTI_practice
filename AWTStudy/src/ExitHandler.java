import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// 어댑터 클래스를 이용하면 이벤트 처리에 필요없는 메서드를 상속받지 않아도 된다.
public class ExitHandler extends WindowAdapter {

	// 참조받기 위한 frame 선언 (생성자 or getter/setter로 선언 가능)
	ChatFrame frame;
	
	public ExitHandler(ChatFrame frame) {
		this.frame = frame;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// 속성에 직접 접근하는 것이 아닌, 기능을 통해 종료함.
		frame.finish();

	}


}
