/**
 * 2018.10.10 test HelloWorld
 */
package test;

import java.util.*;

public class HelloWorld {
	final static double PI = 3.14159265358;
	public static void main(String[] args) {
		for (int i = 0;i < 10;++i) {
			action(i);
		}
		System.out.println("------------END------------");
	}
	
	public static double getArea(double r) {
		return r * r * PI;
	}
	
	public static void action(int i) {
		double r,area;
		System.out.println("Please enter the radius : ");
		Scanner input = new Scanner(System.in);
		r = input.nextDouble();
		area = getArea(r);
		System.out.println(i+1 +" The area of this circle is : "+area);
	}
}
