import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

// Thread 사용이 불가능하므로 Runnable 인터페이스를 이용
public class GameFrame extends Frame implements Runnable {
	// 원을 옮기기 위한 변수 선언
	int x = 10, distance = 5;

	Image unit;
	Toolkit toolkit;
	
	GameFrame(String title) {
		super(title);
		toolkit = Toolkit.getDefaultToolkit();
		unit = toolkit.getImage("/Users/mcbookpro/Downloads/images.jpeg");
	}

	public void setContents() {

	}

	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				gameStart();
			}
		});
	}

	public void gameStart() {
		// Runnable에는 start 메서드가 없으므로 밑과 같이
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(unit, x, 50, this);
		
	}

	// 스레드의 엔트리포인트
	// 복잡한 로직은 메서드를 만들어 사용만 해라. 엔트리포인트로 메인 메서드와 동일한 컨트롤타워 역할이기 때문이다.
	@Override
	public void run() {
		Random random = new Random();
		while(true) {
			x += distance;
			repaint();
			if(x > 400) {
				x = 5;
			}
			try {
				Thread.sleep(random.nextInt(200));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
