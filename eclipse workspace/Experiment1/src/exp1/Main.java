/*2018.10.10 
*��Τ��
 */
package exp1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String Name, studentID;
		int[] score = new int[4];
		Student[] stu = new Student[31];
		for (int i = 0; i < 30; i++) {
			stu[i] = new Student();
		}
		System.out.print("������ѧ�������� ");
		Name = input.next();
		System.out.print("������ѧ��ѧ�ţ� ");
		studentID = input.next();
		System.out.print("������ѧ�����ſεĳɼ����Կո�ָ��� ");
		for (int i = 0; i < 4; i++) {
			score[i] = input.nextInt();
			if (score[i] > 100) {
				System.out.println("�γ̳ɼ����ܸ���100�֣���");
				input.close();
				return;
			}
		}
		stu[30] = new Student(Name, studentID, score);
		input.close();
	}
}
