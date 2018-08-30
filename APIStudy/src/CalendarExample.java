import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarExample {

	// 근무일수 계산하는 메소드
	// Calendar클래스 필요
	public static int getWorks(String year, String month, String date) {
		Calendar cal;
		cal = Calendar.getInstance();
		long currentSec = cal.getTimeInMillis();

		cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(date));
		long secGap = currentSec - cal.getTimeInMillis();
		// 현재시간에서 밀리세컨받아서 빼용 그담에 1000*60*60*24

		return (int) (secGap / (1000 * 60 * 60 * 24));
	}

	public static void main(String[] args) {
		// 시간 관련 객체는 객체 하나에 계속 저장하면 된다.
		// 객체를 생성할 필요가 없다.
		// Calendar today = new GregorianCalendar();
		// 따라서 getInstance 메서드(static, 팩토리 메서드)를 이용해 하나의 객체를 계속 불러온다.
		// StringPool과 같은 역할
		Calendar today = Calendar.getInstance();
		today.set(Calendar.YEAR, 1987);
		System.out.println(today);

		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		int date = today.get(Calendar.DAY_OF_WEEK);
		// 편하나 출력문이 없어 직접 조합해 출력해줘야한다.
		// today.get(field)
		System.out.println(year);
		System.out.println(month + 1);
		System.out.println(date);

		switch (date) {
		case Calendar.SUNDAY:
			System.out.println("일요일");
			break;

		default:
			break;
		}

		double num = 23343.78;
		NumberFormat nf = new DecimalFormat("#,###.##");
		System.out.println(nf.format(num));

		nf = NumberFormat.getInstance();
		System.out.println(nf.format(4545.45));

		nf = NumberFormat.getCurrencyInstance();
		System.out.println(nf.format(4545.45));

		nf = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println(nf.format(4545.45));

		DateFormat df = DateFormat.getInstance();
		System.out.println(df.format(new Date())); // 18. 8. 27 오후 3:31

		df = new SimpleDateFormat("yyyy-MM-dd(E) hh:mm:ss");
		System.out.println(df.format(new Date()));
		
		
		System.out.println(CalendarExample.getWorks("2011", "3", "20"));

	}

}
