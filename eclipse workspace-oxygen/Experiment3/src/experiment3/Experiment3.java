package experiment3;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

public class Experiment3 {//美人鱼还没写
	private static int currentMinute = 480;//八点开始
	private static ArrayList<FishShow> listOfFish = new ArrayList<FishShow>();//鱼类表演列表
	private static ArrayList<MammalShow> listOfMammal = new ArrayList<MammalShow>();//海洋哺乳类表演列表
	private static ArrayList<ReptileShow> listOfReptile = new ArrayList<ReptileShow>();//爬行动物类表演列表
	
	public static void main(String[] args) {
		File_IO file = new File_IO();
		FormList list = new FormList();
		
		ArrayList<String> showList = new ArrayList<>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();//获取序号
		ArrayList<AnimalShow> objectList = new ArrayList<AnimalShow>();//实例化对象
		
		
		System.out.println("8:00   各部门开始准备");
		formList(showList);//生成节目表
		numbers = getAllNumber(showList);//获取所有序号
		objectList = formObject(numbers);//实例化所有的对象
		System.out.println(objectList);
		dealWithFeed(numbers, objectList);//喂食
		enter();//观众入场
		objectList = sortShow();
		show(objectList);//开始表演
	}
		
		
	
	//生成节目表
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
	
	//生成对象
	public static ArrayList<AnimalShow> formObject(ArrayList<Integer> numbers) {
		ArrayList<AnimalShow> objectList = new ArrayList<AnimalShow>();
		for (int i = 0; i < numbers.size(); i++) {
			int num = numbers.get(i);
			if (num >= 10000 && num < 20000) {//鱼类表演
				FishShow fishShow = FormList.setRandomFishShow(num);
				listOfFish.add(fishShow);
				objectList.add(fishShow);
			} else if (num >= 20000 && num < 30000) {//哺乳类动物表演
				MammalShow mammalShow = FormList.setRandomMammalShow(num);
				listOfMammal.add(mammalShow);
				objectList.add(mammalShow);
			} else if (num >= 30000 && num < 90000) {//爬行类动物表演
				ReptileShow reptileShow = FormList.setRandomReptileShow(num);
				listOfReptile.add(reptileShow);
				objectList.add(reptileShow);
			} else {
				//美人鱼
			}
		}
		return objectList;
	}
	
	//处理喂食动物 @TODO
	public static void dealWithFeed(ArrayList<Integer> numbers, ArrayList<AnimalShow> objectList) {
		Scanner input = new Scanner(System.in);
		while (!numbers.isEmpty()) {
			String feed = input.nextLine();
			String[] feeds = feed.split(" ");
			int number = Integer.parseInt(feeds[0]);
			if (numbers.contains(number)) {
				int index = getFeededType(objectList, number);
				if (index == objectList.size()) {//美人鱼
					break;
				}
				currentMinute += objectList.get(index).getFeedTime();
				String stdTime = transformMinuteIntoHour(currentMinute);//转化时间
				System.out.println(stdTime + "   " + objectList.get(index).getType() + "喂食成功");
				numbers.remove(numbers.indexOf(number));
			}
		}
		int feedTime = 1;
		currentMinute += feedTime;
		String stdTime = transformMinuteIntoHour(currentMinute);//转化时间
		System.out.println(stdTime + "   美人鱼准备完毕");
		System.out.println("所有的动物喂食完毕，美人鱼也准备完毕，可以入场");
		input.close();
	}
	
	//获得所有序号
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
	
	//将分钟转化为小时（从八点开始）
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
	
	//获取被喂食的动物的下标
	public static int getFeededType(ArrayList<AnimalShow> objectList, int number) {
		int i = 0;
		for (; i <  objectList.size(); i++) {
			if (objectList.get(i).getNumber() == number) {
				break;
			}
		}
		return i;
	}
	
	//观众入场
	public static void enter() {
		currentMinute += 3;
		String time = transformMinuteIntoHour(currentMinute);
		System.out.println(time + "   观众开始入场");
		currentMinute += (int)(Math.random() * 5 +5);
		time = transformMinuteIntoHour(currentMinute);
		System.out.println(time + "   观众入场完毕，表演即将开始，请大家赶快坐好");
		currentMinute += 5;
		time = transformMinuteIntoHour(currentMinute);
		System.out.println(time + "   各位观众，欢迎大家的到来，表演即将开始");
		currentMinute += 1;
	}
	
	//演出排序
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
	
	//正式演出 @TODO
	public static void show(ArrayList<AnimalShow> objectList) {
		for (int i = 0; i < objectList.size(); i++) {
			String stdTime = transformMinuteIntoHour(currentMinute);
			if (i == 0) {
				System.out.print(stdTime + "   第一个出场的是" + objectList.get(i).getType());
			} else {
				System.out.print(stdTime + "   下一个出场的是" + objectList.get(i).getType());
			}
			System.out.println("," + objectList.get(i).toString());//出场
			ShowItem showItem[] = objectList.get(i).getShowItem();
			Interaction interaction[] = objectList.get(i).getInteraction();
			for (int j = 0; j < 2; j++){
				stdTime = transformMinuteIntoHour(currentMinute);
				int num = selectRandomShowOrInteraction();
				System.out.println(stdTime + "   " + objectList.get(i).getName() + "表演" + showItem[num].getName());
				currentMinute += showItem[num].getTimeOfShow();
			}
			int num = selectRandomShowOrInteraction();
			stdTime = transformMinuteIntoHour(currentMinute);
			System.out.println(stdTime + "   现在我们征选" + (int)(Math.random() * 10 + 1) + "位幸运观众跟" + objectList.get(i).getName() + interaction[num].getName());
			currentMinute += interaction[num].getTime();
		}
		System.out.println("各位观众，今天的表演至此结束，请各位有序退场，欢迎大家下次光临。");
	}
	
	//选择随机节目的下标
	public static int selectRandomShowOrInteraction() {
		int show = 0;
		show = (int)(Math.random() * 3);
		return show;
	}
}
