package experiment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Experiment4 {
	private static int currentMinute = 600;//十点开始
	private static ArrayList<FishShow> listOfFish = new ArrayList<FishShow>();//鱼类表演列表
	private static ArrayList<MammalShow> listOfMammal = new ArrayList<MammalShow>();//海洋哺乳类表演列表
	private static ArrayList<ReptileShow> listOfReptile = new ArrayList<ReptileShow>();//爬行动物类表演列表
	private static ArrayList<OrnamentalAnimalShow> listOfOrnamentalAnimal = new ArrayList<OrnamentalAnimalShow>();//爬行动物类表演列表
	private static ArrayList<Visitor> visitorList = new ArrayList<Visitor>();
	private static LinkedList<FlyShow> flyCruiseList = new LinkedList<FlyShow>();
	private static LinkedList<SwimShow> SwimCruiseList = new LinkedList<SwimShow>();
	private static LinkedList<Cruise> allCruiseList = new LinkedList<Cruise>();
	private static ArrayList<String> showList = new ArrayList<>();
	private static ArrayList<AnimalShow> trueShow = new ArrayList<AnimalShow>();
	
	public static void main(String[] args) throws Exception {
		FormList list = new FormList();
		File_IO file = new File_IO();
		ArrayList<Integer> numbers = new ArrayList<Integer>();//获取序号
		ArrayList<AnimalShow> objectList = new ArrayList<AnimalShow>();//实例化对象
		
		creatVisitors();//创建游客
		creatAVisitor();//创建一个游客
		formList(showList, file);//生成节目表
		ArrayList<String> show = file.outputInformation();
		numbers = getAllNumber(show);//获取所有序号
		objectList = formObject(numbers);//实例化所有的对象
		createCruise(objectList);// 创建巡游列表
		sortCruise();// 巡游列表排序
		CruiseShow();// 巡游表演
		System.out.println("10:00   各部门开始准备");
		dealWithFeed(numbers, objectList);//喂食
		enter();//观众入场
		sortShow();
		objectList = trueShow;
		System.out.println("Size of objectList is:" + objectList.size());
		show(objectList);//开始表演
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
		
		System.out.println("您好，jack先生，您是我们的第" + visitor.getNumber() + "号会员，可享受"
				+ "会员价格，原票价150元，会员价120元，请输入您要购买的张数：");
		Scanner input = new Scanner(System.in);
		int amount = input.nextInt();
		while ((amount * 120 > visitor.getMoney())) {
			System.out.println("你的余额不足，请充值：");
			int money = input.nextInt();
			visitor.setMoney(visitor.getMoney() + money);
			System.out.println("充值完成，你现在的余额是" + visitor.getMoney() + "元。");
		}
		visitor.setMoney(visitor.getMoney() - (amount * 120));
		System.out.println("您购买的票为" + amount + "张，一共" + (amount * 120) + "元，现在您的预存款还有" + visitor.getMoney() + "元。");
		System.out.println("正在出票，您可以从下方的出票口拿走票，再见。");
	}
	
	//生成节目表
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
			} else if (num >= 30000 && num < 40000) {//爬行类动物表演
				ReptileShow reptileShow = FormList.setRandomReptileShow(num);
				listOfReptile.add(reptileShow);
				objectList.add(reptileShow);
			} else if (num >= 40000 && num < 50000) {// 观赏鸟类动物表演
				OrnamentalAnimalShow ornamentalAnimalShow = FormList.setRandomOrnamentalAnimalShow(num);
				listOfOrnamentalAnimal.add(ornamentalAnimalShow);
				objectList.add(ornamentalAnimalShow);
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
					int feedTime = 1;
					currentMinute += feedTime;
					String stdTime = transformMinuteIntoHour(currentMinute);//转化时间
					System.out.println(stdTime + "   美人鱼准备完毕");
					numbers.remove(numbers.indexOf(number));
					continue;
				}
				currentMinute += objectList.get(index).getFeedTime();
				String stdTime = transformMinuteIntoHour(currentMinute);//转化时间
				System.out.println(stdTime + "   " + objectList.get(index).getType() + "喂食成功");
				numbers.remove(numbers.indexOf(number));
			} else {
				System.out.println("喂食错误");
			}
		}
		System.out.println("所有的动物喂食完毕，美人鱼也准备完毕，可以入场");
		//input.close();
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
	
	//正式演出 @TODO
	public static void show(ArrayList<AnimalShow> objectList) {
		Scanner input1 = new Scanner(System.in);
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
			Visitor visitor = selectVisitor();
			System.out.println(stdTime + "   现在我们征选" + (int)(Math.random() * 10 + 1) + 
					"位幸运观众跟" + objectList.get(i).getName() + interaction[num].getName() + "," + visitor.toString());
			if (visitor.isVip) {
				System.out.println("现在有请各位会员给" +  objectList.get(i).getName() + "打分，满分10分，最低0分：10");
				int score;
				score = input1.nextInt();
				objectList.get(i).setScore(score);
			}
			currentMinute += interaction[num].getTime();
		}
		staffShow();// 员工表演
		System.out.println("各位观众，今天的表演至此结束，请各位有序退场，欢迎大家下次光临。");
		input1.close();
	}
	
	// 员工表演
	public static void staffShow() {
		Scanner input1 = new Scanner(System.in);
		for (int i = 0; i < showList.size(); i ++) {
			String stdTime = transformMinuteIntoHour(currentMinute);
			if (showList.get(i).contains("90000 start")) {
				System.out.println(stdTime + "   下一个出场的是" + "美人鱼，现在有请她出场");
				Staff staffShow = new Mermaid("pw");
				whatStaffShow(staffShow, stdTime);
			} else if (showList.get(i).contains("90001 start")) {
				System.out.println(stdTime + "   下一个出场的是" + "空中飞人，现在有请他出场");
				Staff staffShow = new Flyer("qwet");
				whatStaffShow(staffShow, stdTime);
			} else if (showList.get(i).contains("90002 start")) {
				System.out.println(stdTime + "   下一个出场的是" + "飞艇，现在有请它出场");
				Staff staffShow = new Airship("vdsfg");
				whatStaffShow(staffShow, stdTime);
			}
		}
	}
	
	//什么类型的员工表演
	public static void whatStaffShow(Staff staffShow, String stdTime) {
		Scanner input1 = new Scanner(System.in);
		ShowItem showItem[] = staffShow.getShowItem();
		Interaction interaction[] = staffShow.getInteraction();
		for (int j = 0; j < 2; j++){
			stdTime = transformMinuteIntoHour(currentMinute);
			int num = selectRandomShowOrInteraction();
			System.out.println(stdTime + "   " + staffShow.getName() + "表演" + showItem[num].getName());
			currentMinute += showItem[num].getTimeOfShow();
		}
		int num = selectRandomShowOrInteraction();
		stdTime = transformMinuteIntoHour(currentMinute);
		System.out.println(stdTime + "   现在我们征选" + (int)(Math.random() * 10 + 1) + "位幸运观众跟" + staffShow.getName() + interaction[num].getName());
		Visitor visitor = selectVisitor();
		if (visitor.isVip) {
			System.out.println("现在有请各位会员给" +  staffShow.getName() + "打分，满分10分，最低0分：10");
			int score;
			score = input1.nextInt();
			staffShow.setScore(score);
		}
		currentMinute += interaction[num].getTime();
	}
	
	//选择随机节目的下标
	public static int selectRandomShowOrInteraction() {
		int show = 0;
		show = (int)(Math.random() * 3);
		return show;
	}
	
	//随机选择性别
	public static String selectRandomGender() {
		String gender = "";
		int number = (int)(Math.random() * 2);
		if (number == 1) {
			gender = "男";
		} else {
			gender = "女";
		}
		return gender;
	}
	
	//选择游客
	public static Visitor selectVisitor() {
		Visitor visitor;
		Collections.shuffle(visitorList);
		int a = 0;
		a = (int)(Math.random() * 3);
		if (a == 0 || a == 1) {// 选会员
			visitor = visitorList.get(0);
			if (!visitor.isVip) {
				visitor = visitorList.get(1);
			}
		} else {
			visitor = visitorList.get(2);
		}
		return visitor;
	}
	
	// 创建巡游列表
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
	
	// 巡游列表排序
	public static void sortCruise() {
		Collections.sort(flyCruiseList);
		Collections.sort(SwimCruiseList);
		Collections.sort(allCruiseList);
	}
	
	// 飞行巡游表演
	public static void flyCruiseShow() {
		System.out.println("所有能飞行都飞起来吧！");
		Iterator<FlyShow> it = flyCruiseList.iterator();
		while (it.hasNext()) {
			it.next().show();
		}
	}
	
	// 游泳巡游表演
	public static void swimCruiseShow() {
		Iterator<SwimShow> it = SwimCruiseList.iterator();
		while (it.hasNext()) {
			it.next().show();
		}
	}
	
	// 所有巡游表演（前十）
	public static void allCruiseShow() {
		for (int i = 0; i < 10; i ++) {
			if (i == 0) {
				System.out.println("第一个出场的是最欢迎的" + allCruiseList.get(i).getType() + " 得分：" + allCruiseList.get(i).getScore());
			}
			System.out.println("接下来出场的是" + allCruiseList.get(i).getType() + " 得分：" + allCruiseList.get(i).getScore());
		}
	}
	
	//巡游表演
	public static void CruiseShow() {
		System.out.println("9：00  巡游表演马上要开始了，请各位游客就座");
		System.out.println("9：10  巡游表演马上开始，首先进行的空中表演");
		flyCruiseShow();
		System.out.println("下面是游泳表演");
		swimCruiseShow();
		System.out.println("下面是返场表演时间，由最受欢迎的十大明星出场");
		allCruiseShow();
	}
}
