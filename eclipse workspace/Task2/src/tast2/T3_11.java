package tast2;

import java.util.Scanner;

public class T3_11 {

	public static void main(String[] args) {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		int year,month;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a month and a year: ");
		month = input.nextInt();
		year = input.nextInt();
		input.close();
		if (month == 2) {
			if (isLeapYear(year) == true) {
				System.out.println(months[month - 1] + " " + year + " has 29 days");
			}
			else {
				System.out.println(months[month - 1] + " " + year + " has 28 days");
			}
		}
		else {
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				System.out.println(months[month - 1] + " " + year + " has 31 days" );
			}
			else {
				System.out.println(months[month - 1] + " " + year + " has 30 days" );
			}
		}
	}
	
	public static boolean isLeapYear(int year){
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		else {
			return false;
		}
	}

}
