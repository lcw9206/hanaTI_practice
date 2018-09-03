import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Window;

public class ContainerExample {

	public static void main(String[] args) {
		Frame owner = new Frame();
		owner.setSize(400, 200);
		owner.setVisible(true);
		// dialog의 true 옵션은 모달 활성화(뒤의 창 선택 불가능)
		Dialog dialog = new Dialog(owner, "안녕", false);
		dialog.setSize(100, 100);
		dialog.setVisible(true);
	
		// 최상위 객체로 Frame, Dialog 등 다양한 것들이 Window를 상속받아 사용한다.
		Window window = new Window(owner);
		window.setSize(400, 200);
		window.setVisible(true);
		
		FileDialog fd = new FileDialog(owner, "파일열기", FileDialog.LOAD);
		fd.setVisible(true);
	}

}
