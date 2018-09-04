package memo;

public class Memojang {

	public static void main(String[] args) {
		FileDao fileDao = new FileDao();
		MemoUI memoUI = new MemoUI();
		memoUI.setFile(fileDao);
		memoUI.setUI();
	}
}
