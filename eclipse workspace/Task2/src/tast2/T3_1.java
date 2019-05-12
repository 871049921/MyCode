package tast2;

import java.util.Scanner;

public class T3_1 {
	public static void main(String[] args) {
		double a,b,c,k;
		float p,q;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a, b, c: ");
		a = input.nextDouble();
		b = input.nextDouble();
		c = input.nextDouble();
		input.close();
		k = Math.pow((Math.pow(b, 2) - 4 * a * c), 0.5);
		if (k < 0) {
			System.out.println("The equation has no real roots");
		}
		else if (k == 0) {
			p = (float)((-b + k)/(2 * a));
			System.out.println("The equation has one root " + p);
		}
		else {
			p = (float)((-b + k)/(2 * a));
			q = (float)((-b - k)/(2 * a));
			System.out.println("The equation has two roots " + p + " and " + q);
		}
	}
}

