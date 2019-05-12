package t2_6;

import java.util.Scanner;

public class T2_6 {
	public static void main(String[] args) {
		int number,sum = 0;
		
		System.out.println("Enter a number between 0 and 1000: ");
		Scanner input = new Scanner(System.in);
		number = input.nextInt();
		while (true) {
			sum += number % 10;
			if (number / 10 == 0) {
				break;
			}
			number /= 10;
		}
		System.out.println("The sum of the digits is " + sum);
		input.close();
	}
}
