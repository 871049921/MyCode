package t11_2;

import java.util.*;

public class Mydate {
	private int year;
	private int month;
	private int day;
	Calendar cal = Calendar.getInstance();
	
	public Mydate() {
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
	}
	
	public Mydate(long ms) {
		Date date = new Date();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
	}
	
	public Mydate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
}
