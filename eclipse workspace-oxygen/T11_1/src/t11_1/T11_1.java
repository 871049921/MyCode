package t11_1;

import java.util.Scanner;

public class T11_1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("���������ε������ߣ���ɫ���Լ�һ��Booleanֵ�������������Ƿ����");
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
