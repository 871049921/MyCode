/*2018.10.10 
*黄韦德
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
		System.out.print("请输入学生姓名： ");
		Name = input.next();
		System.out.print("请输入学生学号： ");
		studentID = input.next();
		System.out.print("请输入学生四门课的成绩，以空格分隔： ");
		for (int i = 0; i < 4; i++) {
			score[i] = input.nextInt();
			if (score[i] > 100) {
				System.out.println("课程成绩不能高于100分！！");
				input.close();
				return;
			}
		}
		stu[30] = new Student(Name, studentID, score);
		input.close();
	}
}
