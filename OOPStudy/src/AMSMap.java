import java.util.List;

/**
 * 은행계좌관리 애플리케이션
 * @author 이철우
 *
 *
 */
public class AMSMap {

   public static void main(String[] args) throws AccountException {
   
      AccountManagerMap manager = new AccountManagerMap(10);
      manager.add(new Account("1111-2222-3333","lee",1234,1000));
      manager.add(new Account("2222-2222-3333","lee",1234,1000));
      manager.add(new Account("4444-2222-3333","park",1234,1000));
      manager.add(new Account("5555-2222-3333","park",1234,1000));
      manager.add(new Account("6666-2222-3333","choi",1234,1000));
      
      // up casting 
      manager.add(new MinusAccount("9999-1111-2222", "박지성", 1111, 1000, 10000));
      manager.add(new MinusAccount("7777-1111-2222", "손흥민", 1111, 2000, 20000));
      
      manager.add(new Account("8888-2222-3333","kim",1234,1000));
            
      List list = manager.list();
      System.out.println("계좌 전체 리스트");
      for (Object account : list) {
         System.out.println(account);
      }
      
      // test get
      System.out.println("계좌번호로 계좌찾기");
      Account acc = manager.get("4444-2222-3333");
      System.out.println(acc.toString());
      
      // test search
      System.out.println("이름에 따른 계좌찾기");
      List list2 = manager.search("lee");
      for (Object account : list2) {
         System.out.println(account.toString());
      }
      
      // test remove
      manager.remove("1111-2222-3333");
      manager.remove("4444-2222-3333");
      
      System.out.println("삭제 후 리스트");
      List list3 = manager.list();
      for (Object account : list3) {
    	  if(account instanceof MinusAccount) {
    		  System.out.println("마이너스 계좌\t" + account);
    	  } else {
    		  System.out.println("입출금 계좌\t" + account);
    	  }
      }
   }
}