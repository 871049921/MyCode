package t11_1;

import java.util.Scanner;

public class T11_1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("输入三角形的三条边，颜色，以及一个Boolean值表明该三角形是否填充");
		double side1 = input.nextDouble();
		double side2 = input.nextDouble();
		double side3 = input.nextDouble();
		String color = input.next();
		boolean filled = input.nextBoolean();
		input.close();
		Triangle triangle = new Triangle(side1, side2, side3);
		triangle.setColor(color);
		triangle.setFilled(filled);
		System.out.println(triangle.toString() + " Area: " + triangle.getArea() + " Color: " + triangle.getColor() + " Filled: " + triangle.isFilled());
	}
}
