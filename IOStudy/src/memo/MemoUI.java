package memo;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MemoUI extends Frame {

	MenuBar menubar;
	Menu menu;
	MenuItem createMI, openMI, saveMI, exitMI;
	TextArea contentTA;

	private FileDialog fDialog;
	private FileDao fileDao;

	public FileDao getFile() {
		return fileDao;
	}

	public void setFile(FileDao file) {
		this.fileDao = file;
	}

	/**
	 * Default 생성자
	 */
	public MemoUI() {
		super("제목 없음 - 메모장");
		menubar = new MenuBar();
		menu = new Menu("메뉴");
		createMI = new MenuItem("새로만들기");
		openMI = new MenuItem("열기");
		saveMI = new MenuItem("저장");
		exitMI = new MenuItem("종료");
		contentTA = new TextArea();
		fileDao = new FileDao();
	}

	/**
	 * 컴포넌트의 위치를 조정하는 메서드
	 */
	public void setComponents() {
		setMenuBar(menubar);
		menubar.add(menu);
		menu.add(createMI);
		menu.add(openMI);
		menu.add(saveMI);
		menu.add(exitMI);
		add(contentTA, BorderLayout.CENTER);
	}

	/**
	 * UI를 세팅하는 메서드
	 */
	public void setUI() {
		MemoUI mainFrame = new MemoUI();
		mainFrame.setComponents();
		mainFrame.setSize(600, 500);
		mainFrame.setVisible(true);
		mainFrame.eventRegist();
	}

	/**
	 * 메모장을 새로 생성하는 create 메서드 
	 * 본디 복잡한 메서드이지만 여기서는 화면 클리어로 대체
	 */
	public void create() {
		contentTA.setText("");
	}

	/**
	 * 메모장의 내용을 저장하는 save 메서드
	 * getFile 메서드와 getText 메서드를 이용 제목과 내용을 저장시킨다.
	 */
	public void save() {
		fDialog = new FileDialog(this, "저장", FileDialog.SAVE);
		fDialog.setVisible(true);
		try {
			fileDao.save(fDialog.getFile(), contentTA.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentTA.setText("");
	}

	/**
	 * txt파일을 여는 open 메서드
	 * getFile 메서드로 제목을 가져와 내용을 오픈한다.
	 */
	public void open() {
		fDialog = new FileDialog(this, "열기", FileDialog.LOAD);
		fDialog.setVisible(true);
		setTitle(fDialog.getFile());
		contentTA.setText("");
		try {
			contentTA.append(fileDao.open(fDialog.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 종료를 위한 exit 메서드
	 */
	public void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}

	/**
	 * 이름없는 지역 내부클래스(익명클래스)로 이벤트 처리를 담당
	 */
	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		createMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});

		openMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});

		saveMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		exitMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}

}
