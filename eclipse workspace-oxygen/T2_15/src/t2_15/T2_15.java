package t2_15;

import java.util.Scanner;
import java.math.*;

public class T2_15 {
	public static void main(String[] args) {
		String recv;
		double x1,x2,y1,y2,distance;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter x1 and y1: ");
		recv = input.nextLine();
		String[] p = recv.trim().split(" ");//·Ö¸î×Ö·û´®
		x1 = Double.parseDouble(p[0]);
		y1 = Double.parseDouble(p[1]);
		System.out.println("Enter x2 and y2: ");
		recv = input.nextLine();
		p = recv.trim().split(" ");//·Ö¸î×Ö·û´®
		x2 = Double.parseDouble(p[0]);
		y2 = Double.parseDouble(p[1]);
		distance = Math.pow((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)), 0.5);
		System.out.println("The distance between the two points is  " + distance);
		input.close();
	}
}
