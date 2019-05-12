package exp1;

public class Student{
	String[] randonName = {"arlene","alice","becky","bella","cerina","carlo","dannie","doris",
							"emi","emme","flora","florence","gia","grace","geidi","helen","ivy",
							"isabel","jill","joanna","kiki","kate","lilian","losa","michelle",
							"margie","nadia","niki","olivia","ova","penny","pauline","racheal",
							"renee","sally","stephanie","tiffanny","tori","violet","vivian",
							"wing","whitney","yoyo","yuki","zoe","zolar"};
	private String studentID;
	private String studentName;
	private double averageScore;//该学生的平均分
	private int maxScore;//该学生的最高分
	private int []scores = new int[4];//该同学的四门课程的分数
	private static String[] allName = new String[31];
	private static double[] averageScores = new double[31];//全班每个学生的平均分
	private static double averageScoreInClass;//全班平均分
	private static double highestAverageScore;//全班平均分最高的同学的成绩
	private static double highestScore = 0;//全班平均分最高的同学的成绩最高成绩
	private static int numberInClass = 0;//全班总学生数
	private static String index;//用于记录产生完30个学生后最高平均分的同学
	//无参随机创造学生类方法
	public Student() {
		numberInClass++;
		int totleScore = 0;
		studentName = randonName[(int) (Math.random() * 46)];
		allName[numberInClass - 1] = studentName;
		if ((numberInClass + 1) < 10) {
			studentID = "1720130" + (numberInClass + 1);
		} else {
			studentID = "172013" + (numberInClass + 1);
		}
		for (int i = 0; i < 4; i++) {
			scores[i] = (int) (Math.random() * 100);
			totleScore += scores[i];
		}
		averageScore = averageScore(totleScore);
		averageScores[numberInClass - 1] = averageScore;
		maxScore = max(scores);
		if (maxScore > highestScore) {
			highestScore = maxScore;
		}
		printStudentInformation(numberInClass - 1);///
		if (numberInClass == 30) {
			averageScoreInClass = getAverageScoreInClass();// 获取全班平均分
			getHighestAverageScoreInClass();
		}
	}// 构造方法(随机产生一名学生信息)
	//获得名字
	public String getName() {
		return studentName;
	}//获得学生姓名
	
	public String getID() {
		return studentID;
	}//获得学生学号(ID)
	//取得一个学生中的最高成绩
	public int max(int []scores) {
		int max = scores[0];
		for (int i = 1;i < 4;i++) {
			if (scores[i] > max) {
				max = scores[i];
			}
		}
		return max;
	}
	//取得平均成绩
	public double averageScore(int totleScore){
		return totleScore/4.0;
	}
	//取得全班平均成绩
	public double getAverageScoreInClass(){
		double totle = 0.0;
		for (int i = 0;i < numberInClass - 1;i++) {
			totle += averageScores[i];
		}
		return (totle / numberInClass);
	}
	//取得全班最高平均分的同学
	public double getHighestAverageScoreInClass() {
		int i;
		int maxi = 0;
		double max = averageScores[0];
		for (i = 1;i < numberInClass;i++) {
			if (averageScores[i] > max) {
				max = averageScores[i];
				maxi = i;
			}
		}
		highestAverageScore = max;
		if (numberInClass == 30) {
			index = printStudentInformation(maxi);//写一个无参数的学生信息表，应用于随机产生30个学生之后
		}
		return max;
	}
	//输入新学生信息
	public Student(String studentName, String studentID, int []score) {
		numberInClass++;
		int maxScore = max(score);//该同学的最高分
		int totle = 0;
		for (int i = 0; i < 4; i++) {
			totle += score[i];
		}
		averageScore = averageScore(totle);
		averageScores[numberInClass-1] = averageScore;//全班每个同学的平均分
		averageScoreInClass = getAverageScoreInClass();//全班平均分
		highestAverageScore = getHighestAverageScoreInClass();//全班最高平均分
		printStudentInformation(studentName, studentID, averageScore, maxScore);//打印
	}
	//打印学生信息
	public void printStudentInformation(String studentName, String studentID, double averageScore, int maxScore) {
		System.out.println(studentName + "(ID" + studentID + ") 平均分:  " + averageScore + "最高分: " + maxScore);
		if (averageScore >= highestAverageScore) {
			System.out.println(studentName + "现在是班上成绩最好的学生");
			highestAverageScore = averageScore;//设置最高分
		}
		else {
			System.out.println(index);
		}
		averageScoreInClass = getAverageScoreInClass();
		if (averageScore > averageScoreInClass) {
			System.out.println(studentName + "的平均成绩高于全班的平均分");
		}
		else {
			System.out.println(studentName + "的平均成绩低于全班的平均分");
		}
	}
	//产生三十个学生后打印的学生数据
	public String printStudentInformation(int num) {
		String s;
		if ((num+1) < 10) {
//			System.out.println(allName[num] + "(ID" + 1720130 + (num + 1) + ") 平均分:  " + averageScore + " 最高分: " + maxScore);
			s = allName[num] + "(ID" + 1720130 + (num + 1) + ") 平均分:  " + averageScore + " 最高分: " + maxScore; 
		}
		else {
//			System.out.println(allName[num] + "(ID" + 172013 + (num + 1) + ") 平均分:  " + averageScore + " 最高分: " + maxScore);
			s = allName[num] + "(ID" + 172013 + (num + 1) + ") 平均分:  " + averageScore +  "最高分: " + maxScore;
		}
		if (numberInClass == 30) {
//			System.out.println(allName[num] + "(ID" + 172013 + (num + 1) + ") 平均分:  " + highestAverageScore + " 最高分: " + highestScore);
			s = allName[num] + "(ID" + 172013 + (num + 1) + ") 平均分:  " + highestAverageScore +  "最高分: " + highestScore;
//			System.out.println(highestAverageScore + "!!!!!");
		}
		return s;
	}
}
