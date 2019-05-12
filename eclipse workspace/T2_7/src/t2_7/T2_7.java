package t2_7;

import java.util.Scanner;

public class T2_7 {
	public static void main(String[] args) {
		int year,recv,dayInt;
		double dayFloat;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of minutes: ");
		recv = input.nextInt();
		year = recv / 60 / 24 / 365;
		if (year != 0) {
			dayInt = recv / 60 / 24 % 365;
			System.out.println(recv + " minutes is approximately " + year + " years and " + dayInt + " days");
		}
		else {
			dayFloat = recv / 60 / 24.0f;
			System.out.println(recv + " minutes is approximately " + year + " years and " + dayFloat + " days");
		}
		input.close();
	}
}
