package kr.or.kosta.entity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * File 입출력을 이용한 은행계좌 관리 클래스
 * 
 * @author 이철우
 */
public class AccountDao {
	private static final String FILE_PATH = "accounts.dbf";

	/** 계좌 저장을 위한 파일 컬럼 사이즈 고정 */
	private static final int RECORD_COUNT_LENGTH = 5;

	/** 레코드(친구 이름,나이,몸무게,전화번호) 저장을 위한 컬럼별 사이즈 고정 */
	private static final int A_NUM_LENGTH = 30; // 이름(10바이트)
	private static final int PASS_LENGTH = 4; // 몸무게(8바이트)
	private static final int RESTMONEY_LENGTH = 8; // 전화번호(26바이트)
	private static final int A_OWNER_LENGTH = 10; // 나이(4바이트)
	private static final int OUTPUTMONEY_LENGTH = 8; // 전화번호(26바이트)
	/** 친구정보 저장을 위한 레코드 사이즈 : 60바이트 */
	public static final int RECORD_LENGTH = A_NUM_LENGTH + A_OWNER_LENGTH + PASS_LENGTH + RESTMONEY_LENGTH
			+ OUTPUTMONEY_LENGTH;

	private RandomAccessFile file;

	/** 저장된 계좌 수 */
	private int recordCount = 0;

	/** Default 생성자 */
	public AccountDao() throws IOException {
		file = new RandomAccessFile(FILE_PATH, "rw");

		// 저장된 파일이 있는 경우
		if (file.length() != 0) {
			recordCount = file.readInt();
		} else {
			System.out.println("친구를 등록해주세요!");
		}
	}

	/** Getter */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * 계좌 생성 메서드 
	 * 입력받은 객체의 속성들을 받아 파일에 입력합니다.
	 * 
	 * @param account
	 * @throws AccountException 
	 */
	public void add(Account account) throws IOException, AccountException {
		// 계좌번호로 계좌검색을 진행하는 aSearch 메서드를 이용해 유효성 검사를 진행합니다.
		if(aSearch(account.getAccountNum())!=null) {
			JOptionPane.showMessageDialog(null, "계좌번호가 중복되었습니다.", "알림", JOptionPane.ERROR_MESSAGE);
			throw new AccountException("존재하는 계좌입니다. 계좌번호를 확인하세요", -700);
		}
		
		String aNum = account.getAccountNum();
		String aOwner = account.getAccountOwner();
		int passwd = account.getPasswd();
		long restMoney = account.getRestMoney();
		long outputMoney;

		// 입력받은 객체의 형에 따라 대출금액을 설정합니다. 
		if (account instanceof MinusAccount) {
			outputMoney = (long)((MinusAccount) account).getBorrowMoney();
		} else {
			outputMoney = 0;
		}
		
		// 파일 레코드의 맨 끝으로 포인터 이동
		file.seek((recordCount * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
		for (int i = 0; i < A_NUM_LENGTH / 2; i++) {
			file.writeChar((i < aNum.length() ? aNum.charAt(i) : ' '));
		}

		file.writeInt(passwd);
		file.writeLong(restMoney);

		for (int i = 0; i < A_OWNER_LENGTH / 2; i++) {
			file.writeChar((i < aOwner.length() ? aOwner.charAt(i) : ' '));
		}

		file.writeLong(outputMoney);
		file.seek(0);
		file.writeInt(++recordCount);
	}

	/**
	 * 전체 계좌 출력 메서드
	 * recordCount만큼 반복문을 돌며 list에 계좌를 담습니다.
	 * 
	 * @return list
	 * @throws IOException
	 */
	public List<Account> listAll() throws IOException {
		List<Account> list = new ArrayList<Account>();
		for (int i = 0; i < recordCount; i++) {
			Account account = read(i);
			list.add(account);
		}
		return list;
	}
	

	/**
	 * 계좌 읽기 메서드
	 * 기본적인 계좌 읽기 메서드입니다.
	 * 
	 * @param index
	 * @return Account or MinusAccount
	 * @throws IOException
	 */
	public Account read(int index) throws IOException {
		Account account = null;
		MinusAccount mAccount = null;
		String aOwner = "";
		String aNum = "";
		int passwd = 0;
		long restMoney = 0;
		long outputMoney;

		file.seek(index * RECORD_LENGTH + RECORD_COUNT_LENGTH);
		
		for (int j = 0; j < A_NUM_LENGTH / 2; j++) {
			aNum += file.readChar();
		}
		aNum.trim();
		passwd = file.readInt();
		restMoney = file.readLong();

		for (int j = 0; j < A_OWNER_LENGTH / 2; j++) {
			aOwner += file.readChar();
		}
		aOwner.trim();
		outputMoney = file.readLong();
		
		// 대출금액이 두번 빠지는 현상 방지
		restMoney += outputMoney;
		
		// 대출금액(outputMoney) 값에 따른 객체 생성
		// outputMoney = 0 : 입출금계좌 생성시 초기값
		if (outputMoney == 0) {
			account = new Account(aNum, aOwner, passwd, restMoney);
			return account;
			// outputMoney = -1 : 마이너스계좌를 생성하며 대출금액을 적지 않았을 시 초기값
		} else if (outputMoney == -1) {
			mAccount = new MinusAccount(aNum, aOwner, passwd, restMoney - 1, 0);
			return mAccount;
			// 위의 두 경우를 제외한 값이 들어왔을 경우
		} else {
			mAccount = new MinusAccount(aNum, aOwner, passwd, restMoney, outputMoney);
			return mAccount;
		}
	}


	/**
	 * 예금주명을 이용한 계좌 검색 메서드
	 * 전체계좌를 출력하는 listAll 메서드를 이용해 전체 계좌 목록을 갖고온 후, 값을 찾습니다.  
	 * 
	 * @param accountOwner
	 * @return list
	 * @throws IOException
	 */
	public List<Account> search(String accountOwner) throws IOException {
		List<Account> list = new ArrayList<Account>();
		List<Account> lists = listAll();
		for (Account account : lists) {
			if (accountOwner.equals(account.getAccountOwner().trim())) {
				list.add(account);
			}
		}
		return list;
	}

	/**
	 * 계좌번호를 이용한 계좌 검색 메서드
	 * 입력받은 계좌번호와 listAll 메서드를 이용해 계좌를 찾습니다. 
	 * 
	 * @param accountNum
	 * @return list or null
	 * @throws IOException
	 */
	public Account aSearch(String accountNum) throws IOException {
		List<Account> lists = listAll();
		for (int i = 0; i < lists.size(); i++) {
			if (accountNum.trim().equals(lists.get(i).getAccountNum().trim())) {
				return lists.get(i);
			}
		}
		return null;
	}
	

	/**
	 * 계좌 삭제 메서드 (데이터 처리)
	 * 삭제할 계좌의 인덱스에 다음 계좌를 덮습니다.
	 * 
	 * @param removeAccount
	 * @throws IOException
	 */
	public void removeAccount(Account removeAccount) throws IOException {
		String aNum = removeAccount.getAccountNum();
		String aOwner = removeAccount.getAccountOwner();
		int passwd = removeAccount.getPasswd();
		long restMoney = removeAccount.getRestMoney();
		long outputMoney;

		// 입력받은 객체의 형에 따라 대출금액을 설정합니다. 
		if (removeAccount instanceof MinusAccount) {
			outputMoney = ((MinusAccount) removeAccount).getBorrowMoney();
		} else {
			outputMoney = 0;
		}

		for (int i = 0; i < A_NUM_LENGTH / 2; i++) {
			file.writeChar((i < aNum.length() ? aNum.charAt(i) : ' '));
		}

		file.writeInt(passwd);
		file.writeLong(restMoney);

		for (int i = 0; i < A_OWNER_LENGTH / 2; i++) {
			file.writeChar((i < aOwner.length() ? aOwner.charAt(i) : ' '));
		}

		file.writeLong(outputMoney);
	}
	
	/**
	 * 계좌 삭제 메서드 (인덱스 처리)
	 * 계좌와 관련된 인덱스를 이용해 현재 위치를 seek 메서드로 잡고, 다음 데이터를 가져와 removeAccount 메서드에 넘깁니다.
	 * 
	 * @param accountNum
	 * @return boolean
	 * @throws IOException
	 */
	public boolean remove(String accountNum) throws IOException {
		Account account = null;
		List<Account> lists = listAll();
		int count = 0;
		for (int i = 0; i < recordCount; i++) {
			// 입력된 계좌번호와 파일에서 읽어온 계좌 lists를 비교해 삭제할 값을 찾습니다.
			if (accountNum.trim().equals(lists.get(i).getAccountNum().trim())) {
				// 삭제할 index 값을 count 변수에 저장하고, recordCount를 줄이고 반복문을 빠져나갑니다.
				count = i;
				recordCount--;
				break;
			}
		}
		// 삭제할 값을 찾지 못했을 경우 파일 포인터를 처음으로 돌리고 false를 반환합니다.
		if (count == 0) {
			file.seek(0);
			return false;
		}
		
		// 삭제할 값을 찾았을 경우
		for (int i = count; i < recordCount; i++) {
			// account 객체에 다음 데이터를 담습니다.
			account = read(i+1);
			// 이 후, 파일 포인터를 삭제할 데이터의 처음 인덱스에 넣습니다.
			file.seek(count * RECORD_LENGTH + RECORD_COUNT_LENGTH);
			// removeAccount 메서드를 이용해 삭제할 인덱스에 위치한 포인터에 다음 데이터의 값을 덮어씌웁니다. 
			removeAccount(account);
		}
		
		// 삭제 후, true를 반환합니다.
		file.seek(0);
		file.writeInt(recordCount);
		return true;
	}

	
	/**
	 * File 클래스 객체를 종료하는 메서드
	 */
	public void close() {
		try {
			if (file != null)
				file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
