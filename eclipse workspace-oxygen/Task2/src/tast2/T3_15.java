package tast2;

import java.util.Scanner;

public class T3_15 {
	public static void main(String[] args) {
		int number;
		int randonNumber = (int)(Math.random() * 900 + 100);
//		randonNumber = 575;
		System.out.println("�н�����Ϊ: " + randonNumber);
		Scanner input = new Scanner(System.in);
		System.out.println("����һ����λ��������: ");
		number = input.nextInt();
		input.close();
		prices(number, randonNumber);
	}
	
	public static void prices(int number, int randonNumber) {
		String sRandonNumber = randonNumber + "";
		String sNumber = number + "";
		int flag = 0;
		
		if (number == randonNumber) {
			System.out.println("��ϲ������10000��Ԫ! ");
			return;
		}
		else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0, a = 10; j < 3; j++) {
					if (a == j) {
						continue;
					}
					if (sNumber.charAt(i) == sRandonNumber.charAt(j)) {
						a = j;
						flag += 1;
						break;
					}
				}
			}
			if (flag == 3) {
				System.out.println("��ϲ������3000��Ԫ! ");
			}
			else if (flag != 0) {
				System.out.println("��ϲ������1000��Ԫ! ");
			}
			else {
				System.out.println("��û���н�");
			}
		}
	}
}
