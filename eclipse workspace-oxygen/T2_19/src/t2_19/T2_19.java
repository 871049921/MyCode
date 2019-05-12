package t2_19;

import java.util.Scanner;
import java.math.*;

public class T2_19 {
	public static void main(String[] args) {
		String recv;
		double x1,x2,y1,y2,x3,y3,s1,s2,s3,s;
		float area;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter three points for a triangle: ");
		recv = input.nextLine();
		String[] p = recv.trim().split(" ");//·Ö¸î×Ö·û´®
		x1 = Double.parseDouble(p[0]);
		y1 = Double.parseDouble(p[1]);
		x2 = Double.parseDouble(p[2]);
		y2 = Double.parseDouble(p[3]);
		x3 = Double.parseDouble(p[4]);
		y3 = Double.parseDouble(p[5]);
		s1 = Math.pow((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)), 0.5);
		s2 = Math.pow((Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2)), 0.5);
		s3 = Math.pow((Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2)), 0.5);
		s = (s1 + s2 + s3)/2f;
		area = (float)Math.pow((s * (s - s1) * (s - s2) * (s - s3)), 0.5);
		System.out.println("The area of the triangle is " + area);
		input.close();
	}
}
