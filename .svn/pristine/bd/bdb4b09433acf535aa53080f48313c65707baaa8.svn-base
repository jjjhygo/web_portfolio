package web_portfolio;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalTest {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long a=  1522127904000L;
		System.out.println(sdf.format(a));

		long ts = 1522127904000L;
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(ts));
		String str=sdf.format(c.getTime());
		System.out.println(str);
	}
}
