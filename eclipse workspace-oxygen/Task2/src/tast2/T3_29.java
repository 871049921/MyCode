package tast2;

import java.util.Scanner;

public class T3_29 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double x1,x2,y1,y2,r1,r2,distance;
		System.out.println("Enter circle1's center x-, y-coordinates, and radius: ");
		x1 = input.nextDouble();
		y1 = input.nextDouble();
		r1 = input.nextDouble();
		System.out.println("Enter circle2's center x-, y-coordinates, and radius: ");
		x2 = input.nextDouble();
		y2 = input.nextDouble();
		r2 = input.nextDouble();
		input.close();
		distance = Math.pow(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)), 0.5);
		if (distance <= Math.abs(r1 - r2)) {
			System.out.println("Circle2 is inside circle1: ");
		}
		else if ((distance > Math.abs(r1 - r2)) && distance <= Math.abs(r1 + r2)) {
			System.out.println("Circle2 is overlpas circle1: ");
		}
		else {
			System.out.println("Circle2 does not overlpas circle1: ");
		}
	}

}
