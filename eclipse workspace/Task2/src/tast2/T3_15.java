package tast2;

import java.util.Scanner;

public class T3_15 {
	public static void main(String[] args) {
		int number;
		int randonNumber = (int)(Math.random() * 900 + 100);
//		randonNumber = 575;
		System.out.println("中奖号码为: " + randonNumber);
		Scanner input = new Scanner(System.in);
		System.out.println("输入一个三位数的数字: ");
		number = input.nextInt();
		input.close();
		prices(number, randonNumber);
	}
	
	public static void prices(int number, int randonNumber) {
		String sRandonNumber = randonNumber + "";
		String sNumber = number + "";
		int flag = 0;
		
		if (number == randonNumber) {
			System.out.println("恭喜您中了10000美元! ");
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
				System.out.println("恭喜您中了3000美元! ");
			}
			else if (flag != 0) {
				System.out.println("恭喜您中了1000美元! ");
			}
			else {
				System.out.println("您没有中奖");
			}
		}
	}
}
