package experiment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Experiment4 {
	private static int currentMinute = 600;//ʮ�㿪ʼ
	private static ArrayList<FishShow> listOfFish = new ArrayList<FishShow>();//��������б�
	private static ArrayList<MammalShow> listOfMammal = new ArrayList<MammalShow>();//������������б�
	private static ArrayList<ReptileShow> listOfReptile = new ArrayList<ReptileShow>();//���ж���������б�
	private static ArrayList<OrnamentalAnimalShow> listOfOrnamentalAnimal = new ArrayList<OrnamentalAnimalShow>();//���ж���������б�
	private static ArrayList<Visitor> visitorList = new ArrayList<Visitor>();
	private static LinkedList<FlyShow> flyCruiseList = new LinkedList<FlyShow>();
	private static LinkedList<SwimShow> SwimCruiseList = new LinkedList<SwimShow>();
	private static LinkedList<Cruise> allCruiseList = new LinkedList<Cruise>();
	private static ArrayList<String> showList = new ArrayList<>();
	private static ArrayList<AnimalShow> trueShow = new ArrayList<AnimalShow>();
	
	public static void main(String[] args) throws Exception {
		FormList list = new FormList();
		File_IO file = new File_IO();
		ArrayList<Integer> numbers = new ArrayList<Integer>();//��ȡ���
		ArrayList<AnimalShow> objectList = new ArrayList<AnimalShow>();//ʵ��������
		
		creatVisitors();//�����ο�
		creatAVisitor();//����һ���ο�
		formList(showList, file);//���ɽ�Ŀ��
		ArrayList<String> show = file.outputInformation();
		numbers = getAllNumber(show);//��ȡ�������
		objectList = formObject(numbers);//ʵ�������еĶ���
		createCruise(objectList);// ����Ѳ���б�
		sortCruise();// Ѳ���б�����
		CruiseShow();// Ѳ�α���
		System.out.println("10:00   �����ſ�ʼ׼��");
		dealWithFeed(numbers, objectList);//ιʳ
		enter();//�����볡
		sortShow();
		objectList = trueShow;
		System.out.println("Size of objectList is:" + objectList.size());
		show(objectList);//��ʼ����
	}
		
	public static void creatVisitors() {
		ArrayList<String> names = FormList.getNames();
		String gender = "";
		for (int i = 0; i < 70; i ++) {
			Collections.shuffle(names);
			gender = selectRandomGender();
			Visitor visitor = new Visitor(names.get(0), i, false, gender);
			visitorList.add(visitor);
		}
		for (int i = 0; i < 29; i ++) {
			Collections.shuffle(names);
			gender = selectRandomGender();
			Visitor visitor = new Visitor(names.get(0), i, true, gender);
			visitor.setVipNumber(i);
			visitorList.add(visitor);
		}
	}
	
	public static void creatAVisitor() {
		String gender = selectRandomGender();
		Visitor visitor = new Visitor("Jack", 1234, true, gender);
		visitor.setVipNumber(30);
		visitorList.add(visitor);
		
		System.out.println("���ã�jack�������������ǵĵ�" + visitor.getNumber() + "�Ż�Ա��������"
				+ "��Ա�۸�ԭƱ��150Ԫ����Ա��120Ԫ����������Ҫ�����������");
		Scanner input = new Scanner(System.in);
		int amount = input.nextInt();
		while ((amount * 120 > visitor.getMoney())) {
			System.out.println("������㣬���ֵ��");
			int money = input.nextInt();
			visitor.setMoney(visitor.getMoney() + money);
			System.out.println("��ֵ��ɣ������ڵ������" + visitor.getMoney() + "Ԫ��");
		}
		visitor.setMoney(visitor.getMoney() - (amount * 120));
		System.out.println("�������ƱΪ" + amount + "�ţ�һ��" + (amount * 120) + "Ԫ����������Ԥ����" + visitor.getMoney() + "Ԫ��");
		System.out.println("���ڳ�Ʊ�������Դ��·��ĳ�Ʊ������Ʊ���ټ���");
	}
	
	//���ɽ�Ŀ��
	public static void formList(ArrayList<String> showList, File_IO file) throws Exception {
		showList.add("show prepare");
		showList.add("entrance start");
		showList.add("entrance end"); 
		showList.add("10000 start");
		showList.add("20000 start");
		showList.add("30000 start");
		showList.add("10001 start");
		showList.add("20001 start");
		showList.add("30001 start");
		showList.add("10002 start");
		showList.add("20002 start");
		showList.add("30002 start");
		showList.add("40000 start");
		showList.add("40001 start");
		showList.add("40002 start");
		showList.add("90000 start");
		showList.add("show end");
		file.inputInformation(showList);
	}
	
	//���ɶ���
	public static ArrayList<AnimalShow> formObject(ArrayList<Integer> numbers) {
		ArrayList<AnimalShow> objectList = new ArrayList<AnimalShow>();
		for (int i = 0; i < numbers.size(); i++) {
			int num = numbers.get(i);
			if (num >= 10000 && num < 20000) {//�������
				FishShow fishShow = FormList.setRandomFishShow(num);
				listOfFish.add(fishShow);
				objectList.add(fishShow);
			} else if (num >= 20000 && num < 30000) {//�����ද�����
				MammalShow mammalShow = FormList.setRandomMammalShow(num);
				listOfMammal.add(mammalShow);
				objectList.add(mammalShow);
			} else if (num >= 30000 && num < 40000) {//�����ද�����
				ReptileShow reptileShow = FormList.setRandomReptileShow(num);
				listOfReptile.add(reptileShow);
				objectList.add(reptileShow);
			} else if (num >= 40000 && num < 50000) {// �������ද�����
				OrnamentalAnimalShow ornamentalAnimalShow = FormList.setRandomOrnamentalAnimalShow(num);
				listOfOrnamentalAnimal.add(ornamentalAnimalShow);
				objectList.add(ornamentalAnimalShow);
			}
		}
		return objectList;
	}
	
	//����ιʳ���� @TODO
	public static void dealWithFeed(ArrayList<Integer> numbers, ArrayList<AnimalShow> objectList) {
		Scanner input = new Scanner(System.in);
		while (!numbers.isEmpty()) {
			String feed = input.nextLine();
			String[] feeds = feed.split(" ");
			int number = Integer.parseInt(feeds[0]);
			if (numbers.contains(number)) {
				int index = getFeededType(objectList, number);
				if (index == objectList.size()) {//������
					int feedTime = 1;
					currentMinute += feedTime;
					String stdTime = transformMinuteIntoHour(currentMinute);//ת��ʱ��
					System.out.println(stdTime + "   ������׼�����");
					numbers.remove(numbers.indexOf(number));
					continue;
				}
				currentMinute += objectList.get(index).getFeedTime();
				String stdTime = transformMinuteIntoHour(currentMinute);//ת��ʱ��
				System.out.println(stdTime + "   " + objectList.get(index).getType() + "ιʳ�ɹ�");
				numbers.remove(numbers.indexOf(number));
			} else {
				System.out.println("ιʳ����");
			}
		}
		System.out.println("���еĶ���ιʳ��ϣ�������Ҳ׼����ϣ������볡");
		//input.close();
	}
	
	//����������
	public static ArrayList<Integer> getAllNumber(ArrayList<String> showList) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int i = 3;
		while (!showList.get(i).equals("show end")) {
			String feed = showList.get(i);
			String[] feeds = feed.split(" ");
			int number = Integer.parseInt(feeds[0]);
			numbers.add(number);
			i++;
		}
		return numbers;
	}
	
	//������ת��ΪСʱ���Ӱ˵㿪ʼ��
	public static String transformMinuteIntoHour(int minute) {
		String stdTime = "";
		int hour = minute / 60;
		int min = minute % 60;
		
		if (min >= 10) {
			stdTime = hour + ":" + min;
		} else {
			stdTime = hour + ":0" + min;
		}
		
		return stdTime;
	}
	
	//��ȡ��ιʳ�Ķ�����±�
	public static int getFeededType(ArrayList<AnimalShow> objectList, int number) {
		int i = 0;
		for (; i <  objectList.size(); i++) {
			if (objectList.get(i).getNumber() == number) {
				break;
			}
		}
		return i;
	}
	
	//�����볡
	public static void enter() {
		currentMinute += 3;
		String time = transformMinuteIntoHour(currentMinute);
		System.out.println(time + "   ���ڿ�ʼ�볡");
		currentMinute += (int)(Math.random() * 5 +5);
		time = transformMinuteIntoHour(currentMinute);
		System.out.println(time + "   �����볡��ϣ����ݼ�����ʼ�����ҸϿ�����");
		currentMinute += 5;
		time = transformMinuteIntoHour(currentMinute);
		System.out.println(time + "   ��λ���ڣ���ӭ��ҵĵ��������ݼ�����ʼ");
		currentMinute += 1;
	}
	
	//�ݳ�����
	public static ArrayList<AnimalShow> sortShow() {
		int i = 0;
		while (!listOfFish.isEmpty() || !listOfMammal.isEmpty() || !listOfReptile.isEmpty() || !listOfOrnamentalAnimal.isEmpty()) {
			sortCase(i);
			i ++;
		}
		
		return trueShow;
	}
	
	public static void sortCase(int i) {
		switch(i % 4 + 1){
		case 1:
			sortFishShow();
			break;
		case 2:
			sortMammalShow();
			break;
		case 3:
			sortReptileShow();
			break;
		case 4:
			sortOrnamentalAnimalShow();
			break;
		}
	}
	
	public static void sortFishShow() {
		if (!listOfFish.isEmpty()) {
			Collections.shuffle(listOfFish);
			trueShow.add(listOfFish.get(0));
			listOfFish.remove(0);
		}
	}
	
	public static void sortMammalShow() {
		if (!listOfMammal.isEmpty()) {
			Collections.shuffle(listOfMammal);
			trueShow.add(listOfMammal.get(0));
			listOfMammal.remove(0);
		}
	}
	
	public static void sortReptileShow() {
		if (!listOfReptile.isEmpty()) {
			Collections.shuffle(listOfReptile);
			trueShow.add(listOfReptile.get(0));
			listOfReptile.remove(0);
		}
	}
	
	public static void sortOrnamentalAnimalShow() {
		if (!listOfOrnamentalAnimal.isEmpty()) {
			Collections.shuffle(listOfOrnamentalAnimal);
			trueShow.add(listOfOrnamentalAnimal.get(0));
			listOfOrnamentalAnimal.remove(0);
		}
	}
	
	//��ʽ�ݳ� @TODO
	public static void show(ArrayList<AnimalShow> objectList) {
		Scanner input1 = new Scanner(System.in);
		for (int i = 0; i < objectList.size(); i++) {
			String stdTime = transformMinuteIntoHour(currentMinute);
			if (i == 0) {
				System.out.print(stdTime + "   ��һ����������" + objectList.get(i).getType());
			} else {
				System.out.print(stdTime + "   ��һ����������" + objectList.get(i).getType());
			}
			System.out.println("," + objectList.get(i).toString());//����
			ShowItem showItem[] = objectList.get(i).getShowItem();
			Interaction interaction[] = objectList.get(i).getInteraction();
			for (int j = 0; j < 2; j++){
				stdTime = transformMinuteIntoHour(currentMinute);
				int num = selectRandomShowOrInteraction();
				System.out.println(stdTime + "   " + objectList.get(i).getName() + "����" + showItem[num].getName());
				currentMinute += showItem[num].getTimeOfShow();
			}
			int num = selectRandomShowOrInteraction();
			stdTime = transformMinuteIntoHour(currentMinute);
			Visitor visitor = selectVisitor();
			System.out.println(stdTime + "   ����������ѡ" + (int)(Math.random() * 10 + 1) + 
					"λ���˹��ڸ�" + objectList.get(i).getName() + interaction[num].getName() + "," + visitor.toString());
			if (visitor.isVip) {
				System.out.println("���������λ��Ա��" +  objectList.get(i).getName() + "��֣�����10�֣����0�֣�10");
				int score;
				score = input1.nextInt();
				objectList.get(i).setScore(score);
			}
			currentMinute += interaction[num].getTime();
		}
		staffShow();// Ա������
		System.out.println("��λ���ڣ�����ı������˽��������λ�����˳�����ӭ����´ι��١�");
		input1.close();
	}
	
	// Ա������
	public static void staffShow() {
		Scanner input1 = new Scanner(System.in);
		for (int i = 0; i < showList.size(); i ++) {
			String stdTime = transformMinuteIntoHour(currentMinute);
			if (showList.get(i).contains("90000 start")) {
				System.out.println(stdTime + "   ��һ����������" + "�����㣬��������������");
				Staff staffShow = new Mermaid("pw");
				whatStaffShow(staffShow, stdTime);
			} else if (showList.get(i).contains("90001 start")) {
				System.out.println(stdTime + "   ��һ����������" + "���з��ˣ���������������");
				Staff staffShow = new Flyer("qwet");
				whatStaffShow(staffShow, stdTime);
			} else if (showList.get(i).contains("90002 start")) {
				System.out.println(stdTime + "   ��һ����������" + "��ͧ����������������");
				Staff staffShow = new Airship("vdsfg");
				whatStaffShow(staffShow, stdTime);
			}
		}
	}
	
	//ʲô���͵�Ա������
	public static void whatStaffShow(Staff staffShow, String stdTime) {
		Scanner input1 = new Scanner(System.in);
		ShowItem showItem[] = staffShow.getShowItem();
		Interaction interaction[] = staffShow.getInteraction();
		for (int j = 0; j < 2; j++){
			stdTime = transformMinuteIntoHour(currentMinute);
			int num = selectRandomShowOrInteraction();
			System.out.println(stdTime + "   " + staffShow.getName() + "����" + showItem[num].getName());
			currentMinute += showItem[num].getTimeOfShow();
		}
		int num = selectRandomShowOrInteraction();
		stdTime = transformMinuteIntoHour(currentMinute);
		System.out.println(stdTime + "   ����������ѡ" + (int)(Math.random() * 10 + 1) + "λ���˹��ڸ�" + staffShow.getName() + interaction[num].getName());
		Visitor visitor = selectVisitor();
		if (visitor.isVip) {
			System.out.println("���������λ��Ա��" +  staffShow.getName() + "��֣�����10�֣����0�֣�10");
			int score;
			score = input1.nextInt();
			staffShow.setScore(score);
		}
		currentMinute += interaction[num].getTime();
	}
	
	//ѡ�������Ŀ���±�
	public static int selectRandomShowOrInteraction() {
		int show = 0;
		show = (int)(Math.random() * 3);
		return show;
	}
	
	//���ѡ���Ա�
	public static String selectRandomGender() {
		String gender = "";
		int number = (int)(Math.random() * 2);
		if (number == 1) {
			gender = "��";
		} else {
			gender = "Ů";
		}
		return gender;
	}
	
	//ѡ���ο�
	public static Visitor selectVisitor() {
		Visitor visitor;
		Collections.shuffle(visitorList);
		int a = 0;
		a = (int)(Math.random() * 3);
		if (a == 0 || a == 1) {// ѡ��Ա
			visitor = visitorList.get(0);
			if (!visitor.isVip) {
				visitor = visitorList.get(1);
			}
		} else {
			visitor = visitorList.get(2);
		}
		return visitor;
	}
	
	// ����Ѳ���б�
	public static void createCruise(ArrayList<AnimalShow> showList) {
		
		AnimalShow canary = new Canary();
		AnimalShow clownFishShow = new ClownFishShow();
		AnimalShow crocodileShow = new CrocodileShow();
		AnimalShow dolphinShow = new DolphinShow();
		AnimalShow flyingFishShow = new FlyingFishShow();
		AnimalShow lizardShow = new LizardShow();
		AnimalShow parrot = new Parrot();
		AnimalShow seaLoinShow = new SeaLoinShow();
		AnimalShow sealShow = new SealShow();
		AnimalShow sharkShow = new SharkShow();
		
		Staff airship = new Airship();
		Staff flyer = new Flyer();
		Staff mermaid = new Mermaid();
		Staff submarine = new Submarine();
		
		FlyShow flyShow1 = new FlyShow(canary.getType(), canary.getScore());
		FlyShow flyShow2 = new FlyShow(flyingFishShow.getType(), flyingFishShow.getScore());
		FlyShow flyShow3 = new FlyShow(parrot.getType(), parrot.getScore());
		FlyShow flyShow4 = new FlyShow(airship.getType(), airship.getScore());
		FlyShow flyShow5 = new FlyShow(flyer.getType(), flyer.getScore());
		flyCruiseList.add(flyShow1);
		flyCruiseList.add(flyShow2);
		flyCruiseList.add(flyShow3);
		flyCruiseList.add(flyShow4);
		flyCruiseList.add(flyShow5);
		
		SwimShow swimShow1 = new SwimShow(clownFishShow.getType(), clownFishShow.getScore());
		SwimShow swimShow2 = new SwimShow(crocodileShow.getType(), crocodileShow.getScore());
		SwimShow swimShow3 = new SwimShow(dolphinShow.getType(), dolphinShow.getScore());
		SwimShow swimShow4 = new SwimShow(lizardShow.getType(), lizardShow.getScore());
		SwimShow swimShow5 = new SwimShow(seaLoinShow.getType(), seaLoinShow.getScore());
		SwimShow swimShow6 = new SwimShow(sealShow.getType(), sealShow.getScore());
		SwimShow swimShow7 = new SwimShow(sharkShow.getType(), sharkShow.getScore());
		SwimShow swimShow8 = new SwimShow(mermaid.getType(), mermaid.getScore());
		SwimShow swimShow9 = new SwimShow(submarine.getType(), submarine.getScore());
		SwimCruiseList.add(swimShow1);
		SwimCruiseList.add(swimShow2);
		SwimCruiseList.add(swimShow3);
		SwimCruiseList.add(swimShow4);
		SwimCruiseList.add(swimShow5);
		SwimCruiseList.add(swimShow6);
		SwimCruiseList.add(swimShow7);
		SwimCruiseList.add(swimShow8);
		SwimCruiseList.add(swimShow9);
		
		allCruiseList.add(flyShow1);
		allCruiseList.add(flyShow2);
		allCruiseList.add(flyShow3);
		allCruiseList.add(flyShow4);
		allCruiseList.add(flyShow5);
		
		allCruiseList.add(swimShow1);
		allCruiseList.add(swimShow2);
		allCruiseList.add(swimShow3);
		allCruiseList.add(swimShow4);
		allCruiseList.add(swimShow5);
		allCruiseList.add(swimShow6);
		allCruiseList.add(swimShow7);
		allCruiseList.add(swimShow8);
		allCruiseList.add(swimShow9);
		
	}
	
	// Ѳ���б�����
	public static void sortCruise() {
		Collections.sort(flyCruiseList);
		Collections.sort(SwimCruiseList);
		Collections.sort(allCruiseList);
	}
	
	// ����Ѳ�α���
	public static void flyCruiseShow() {
		System.out.println("�����ܷ��ж��������ɣ�");
		Iterator<FlyShow> it = flyCruiseList.iterator();
		while (it.hasNext()) {
			it.next().show();
		}
	}
	
	// ��ӾѲ�α���
	public static void swimCruiseShow() {
		Iterator<SwimShow> it = SwimCruiseList.iterator();
		while (it.hasNext()) {
			it.next().show();
		}
	}
	
	// ����Ѳ�α��ݣ�ǰʮ��
	public static void allCruiseShow() {
		for (int i = 0; i < 10; i ++) {
			if (i == 0) {
				System.out.println("��һ�����������ӭ��" + allCruiseList.get(i).getType() + " �÷֣�" + allCruiseList.get(i).getScore());
			}
			System.out.println("��������������" + allCruiseList.get(i).getType() + " �÷֣�" + allCruiseList.get(i).getScore());
		}
	}
	
	//Ѳ�α���
	public static void CruiseShow() {
		System.out.println("9��00  Ѳ�α�������Ҫ��ʼ�ˣ����λ�ο;���");
		System.out.println("9��10  Ѳ�α������Ͽ�ʼ�����Ƚ��еĿ��б���");
		flyCruiseShow();
		System.out.println("��������Ӿ����");
		swimCruiseShow();
		System.out.println("�����Ƿ�������ʱ�䣬�����ܻ�ӭ��ʮ�����ǳ���");
		allCruiseShow();
	}
}
