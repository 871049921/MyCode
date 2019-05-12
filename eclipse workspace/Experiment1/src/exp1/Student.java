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
	private double averageScore;//��ѧ����ƽ����
	private int maxScore;//��ѧ������߷�
	private int []scores = new int[4];//��ͬѧ�����ſγ̵ķ���
	private static String[] allName = new String[31];
	private static double[] averageScores = new double[31];//ȫ��ÿ��ѧ����ƽ����
	private static double averageScoreInClass;//ȫ��ƽ����
	private static double highestAverageScore;//ȫ��ƽ������ߵ�ͬѧ�ĳɼ�
	private static double highestScore = 0;//ȫ��ƽ������ߵ�ͬѧ�ĳɼ���߳ɼ�
	private static int numberInClass = 0;//ȫ����ѧ����
	private static String index;//���ڼ�¼������30��ѧ�������ƽ���ֵ�ͬѧ
	//�޲��������ѧ���෽��
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
			averageScoreInClass = getAverageScoreInClass();// ��ȡȫ��ƽ����
			getHighestAverageScoreInClass();
		}
	}// ���췽��(�������һ��ѧ����Ϣ)
	//�������
	public String getName() {
		return studentName;
	}//���ѧ������
	
	public String getID() {
		return studentID;
	}//���ѧ��ѧ��(ID)
	//ȡ��һ��ѧ���е���߳ɼ�
	public int max(int []scores) {
		int max = scores[0];
		for (int i = 1;i < 4;i++) {
			if (scores[i] > max) {
				max = scores[i];
			}
		}
		return max;
	}
	//ȡ��ƽ���ɼ�
	public double averageScore(int totleScore){
		return totleScore/4.0;
	}
	//ȡ��ȫ��ƽ���ɼ�
	public double getAverageScoreInClass(){
		double totle = 0.0;
		for (int i = 0;i < numberInClass - 1;i++) {
			totle += averageScores[i];
		}
		return (totle / numberInClass);
	}
	//ȡ��ȫ�����ƽ���ֵ�ͬѧ
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
			index = printStudentInformation(maxi);//дһ���޲�����ѧ����Ϣ��Ӧ�����������30��ѧ��֮��
		}
		return max;
	}
	//������ѧ����Ϣ
	public Student(String studentName, String studentID, int []score) {
		numberInClass++;
		int maxScore = max(score);//��ͬѧ����߷�
		int totle = 0;
		for (int i = 0; i < 4; i++) {
			totle += score[i];
		}
		averageScore = averageScore(totle);
		averageScores[numberInClass-1] = averageScore;//ȫ��ÿ��ͬѧ��ƽ����
		averageScoreInClass = getAverageScoreInClass();//ȫ��ƽ����
		highestAverageScore = getHighestAverageScoreInClass();//ȫ�����ƽ����
		printStudentInformation(studentName, studentID, averageScore, maxScore);//��ӡ
	}
	//��ӡѧ����Ϣ
	public void printStudentInformation(String studentName, String studentID, double averageScore, int maxScore) {
		System.out.println(studentName + "(ID" + studentID + ") ƽ����:  " + averageScore + "��߷�: " + maxScore);
		if (averageScore >= highestAverageScore) {
			System.out.println(studentName + "�����ǰ��ϳɼ���õ�ѧ��");
			highestAverageScore = averageScore;//������߷�
		}
		else {
			System.out.println(index);
		}
		averageScoreInClass = getAverageScoreInClass();
		if (averageScore > averageScoreInClass) {
			System.out.println(studentName + "��ƽ���ɼ�����ȫ���ƽ����");
		}
		else {
			System.out.println(studentName + "��ƽ���ɼ�����ȫ���ƽ����");
		}
	}
	//������ʮ��ѧ�����ӡ��ѧ������
	public String printStudentInformation(int num) {
		String s;
		if ((num+1) < 10) {
//			System.out.println(allName[num] + "(ID" + 1720130 + (num + 1) + ") ƽ����:  " + averageScore + " ��߷�: " + maxScore);
			s = allName[num] + "(ID" + 1720130 + (num + 1) + ") ƽ����:  " + averageScore + " ��߷�: " + maxScore; 
		}
		else {
//			System.out.println(allName[num] + "(ID" + 172013 + (num + 1) + ") ƽ����:  " + averageScore + " ��߷�: " + maxScore);
			s = allName[num] + "(ID" + 172013 + (num + 1) + ") ƽ����:  " + averageScore +  "��߷�: " + maxScore;
		}
		if (numberInClass == 30) {
//			System.out.println(allName[num] + "(ID" + 172013 + (num + 1) + ") ƽ����:  " + highestAverageScore + " ��߷�: " + highestScore);
			s = allName[num] + "(ID" + 172013 + (num + 1) + ") ƽ����:  " + highestAverageScore +  "��߷�: " + highestScore;
//			System.out.println(highestAverageScore + "!!!!!");
		}
		return s;
	}
}
