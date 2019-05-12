package experiment3;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

public class Experiment3 {//�����㻹ûд
	private static int currentMinute = 480;//�˵㿪ʼ
	private static ArrayList<FishShow> listOfFish = new ArrayList<FishShow>();//��������б�
	private static ArrayList<MammalShow> listOfMammal = new ArrayList<MammalShow>();//������������б�
	private static ArrayList<ReptileShow> listOfReptile = new ArrayList<ReptileShow>();//���ж���������б�
	
	public static void main(String[] args) {
		File_IO file = new File_IO();
		FormList list = new FormList();
		
		ArrayList<String> showList = new ArrayList<>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();//��ȡ���
		ArrayList<AnimalShow> objectList = new ArrayList<AnimalShow>();//ʵ��������
		
		
		System.out.println("8:00   �����ſ�ʼ׼��");
		formList(showList);//���ɽ�Ŀ��
		numbers = getAllNumber(showList);//��ȡ�������
		objectList = formObject(numbers);//ʵ�������еĶ���
		System.out.println(objectList);
		dealWithFeed(numbers, objectList);//ιʳ
		enter();//�����볡
		objectList = sortShow();
		show(objectList);//��ʼ����
	}
		
		
	
	//���ɽ�Ŀ��
	public static void formList(ArrayList<String> showList) {
		showList.add("show prepare");
		showList.add("entrance start");
		showList.add("entrance end"); 
		showList.add("10000 start");
		showList.add("10001 start");
		showList.add("10002 start");
		showList.add("20000 start");
		showList.add("20001 start");
		showList.add("20002 start");
		showList.add("30000 start");
		showList.add("30001 start");
		showList.add("30002 start");
		showList.add("90000 start");
		showList.add("show end");
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
			} else if (num >= 30000 && num < 90000) {//�����ද�����
				ReptileShow reptileShow = FormList.setRandomReptileShow(num);
				listOfReptile.add(reptileShow);
				objectList.add(reptileShow);
			} else {
				//������
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
					break;
				}
				currentMinute += objectList.get(index).getFeedTime();
				String stdTime = transformMinuteIntoHour(currentMinute);//ת��ʱ��
				System.out.println(stdTime + "   " + objectList.get(index).getType() + "ιʳ�ɹ�");
				numbers.remove(numbers.indexOf(number));
			}
		}
		int feedTime = 1;
		currentMinute += feedTime;
		String stdTime = transformMinuteIntoHour(currentMinute);//ת��ʱ��
		System.out.println(stdTime + "   ������׼�����");
		System.out.println("���еĶ���ιʳ��ϣ�������Ҳ׼����ϣ������볡");
		input.close();
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
		ArrayList<AnimalShow> trueShow = new ArrayList<AnimalShow>();
		int i = 0;
		while (!listOfFish.isEmpty() && !listOfMammal.isEmpty() && !listOfReptile.isEmpty()) {
			switch(i++ % 3 + 1){
			case 1:
				if (!listOfFish.isEmpty()) {
					Collections.shuffle(listOfFish);
					trueShow.add(listOfFish.get(0));
					listOfFish.remove(0);
				}
				break;
			case 2:
				if (!listOfMammal.isEmpty()) {
					Collections.shuffle(listOfMammal);
					trueShow.add(listOfMammal.get(0));
					listOfMammal.remove(0);
				}
				break;
			case 3:
				if (!listOfReptile.isEmpty()) {
					Collections.shuffle(listOfReptile);
					trueShow.add(listOfReptile.get(0));
					listOfReptile.remove(0);
				}
				break;
			}
		}
		
		return trueShow;
	}
	
	//��ʽ�ݳ� @TODO
	public static void show(ArrayList<AnimalShow> objectList) {
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
			System.out.println(stdTime + "   ����������ѡ" + (int)(Math.random() * 10 + 1) + "λ���˹��ڸ�" + objectList.get(i).getName() + interaction[num].getName());
			currentMinute += interaction[num].getTime();
		}
		System.out.println("��λ���ڣ�����ı������˽��������λ�����˳�����ӭ����´ι��١�");
	}
	
	//ѡ�������Ŀ���±�
	public static int selectRandomShowOrInteraction() {
		int show = 0;
		show = (int)(Math.random() * 3);
		return show;
	}
}