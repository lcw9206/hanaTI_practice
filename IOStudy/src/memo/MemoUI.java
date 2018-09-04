package memo;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

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
	
	public void setComponents() {
		setMenuBar(menubar);
		menubar.add(menu);
		menu.add(createMI);
		menu.add(openMI);
		menu.add(saveMI);
		menu.add(exitMI);
		add(contentTA, BorderLayout.CENTER);
	}
	
	public void setUI() {
		MemoUI mainFrame = new MemoUI();
		mainFrame.setComponents();
		mainFrame.setSize(600, 500);
		mainFrame.setVisible(true);
		mainFrame.eventRegist();
	}
	
	public void create() {
		contentTA.setText("");
	}
	
	public void save() {
		fDialog = new FileDialog(this, "저장", FileDialog.SAVE);
		fDialog.setVisible(true);
		fileDao.save(fDialog.getFile(), contentTA.getText());
		contentTA.setText("");  
	}
	
	public void open() {
		fDialog = new FileDialog(this, "열기", FileDialog.LOAD);
		fDialog.setVisible(true);
		setTitle(fDialog.getFile());
		contentTA.append(String.valueOf(fileDao.open(fDialog.getFile())));
	}
	
	public void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
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

