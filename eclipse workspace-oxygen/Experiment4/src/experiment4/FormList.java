package experiment4;

import java.util.ArrayList;
import java.util.Collections;

public class FormList {
	private static ArrayList<String> names = new ArrayList<String>();
	private static int numberOfFish = 10000;
	private static int numberOfMammal = 20000;
	private static int numberOfReptile = 30000;
	private static int numberOfOrnamentalAnimal = 40000;
	private ArrayList<String> list = new ArrayList<String>();//�ܽ�Ŀ��
	
	public FormList() {
		names.add("aqw");
		names.add("basd");
		names.add("asdasd");
		names.add("jwhgkj");
		names.add("asduahwui");
		names.add("vuhfv");
		names.add("jhgrfu");
		names.add("skgdhuiarhg");
		names.add("vnueiarvuieve");
	}
	
	//��ý�Ŀ�ܱ�
	public ArrayList<String> getFormList() {
		return list;
	}
	
	//�������
	public static ArrayList<String> getNames() {
		return names;
	}
	
//	// ��������Ľ�Ŀ��
//	public static AnimalShow setRandomShowList() {
//		ArrayList<FishShow> listOfFish = new ArrayList<FishShow>();//��������б�
//		ArrayList<MammalShow> listOfMammal = new ArrayList<MammalShow>();//������������б�
//		ArrayList<ReptileShow> listOfReptile = new ArrayList<ReptileShow>();//���ж���������б�
//		int i = 0;
//		while (i < 20) {
//			switch (i % 3 + 1) {
//			case 1:
//				return setRandomFishShow();
//			case 2:
//				return setRandomMammalShow();
//			case 3:
//				return setRandomReptileShow();
//			}
//			i++;
//		}
//		return null;
//	}

	// ������������ݳ�
	public static FishShow setRandomFishShow(int number) {
		int i = (int) (Math.random() * 3) + 1;
		Collections.shuffle(names);
		switch (i) {
		case 1:
			FishShow flyingFishShow = new FlyingFishShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfFish ++;
			return flyingFishShow;
		case 2:
			FishShow shark = new SharkShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfFish ++;
			return shark;
		case 3:
			FishShow clownFish = new ClownFishShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfFish ++;
			return clownFish;
		}
		return null;
	}

	// ������������鶯���ݳ�
	public static MammalShow setRandomMammalShow(int number) {
		int i = (int) (Math.random() * 3) + 1;
		Collections.shuffle(names);
		switch (i) {
		case 1:
			MammalShow seaLoin = new SeaLoinShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfMammal ++;
			return seaLoin;
		case 2:
			MammalShow seal = new SealShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfMammal ++;
			return seal;
		case 3:
			MammalShow dolphoin = new DolphinShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfMammal ++;
			return dolphoin;
		}
		return null;
	}

	// ����������ж����ݳ�
	public static ReptileShow setRandomReptileShow(int number) {
		int i = (int) (Math.random() * 2) + 1;//��Ҫ��
		Collections.shuffle(names);
		switch (i) {
		case 1:
			ReptileShow crocodileShow = new CrocodileShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfReptile ++;
			return crocodileShow;
		case 2:
			ReptileShow lizard = new LizardShow(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfReptile ++;
			return lizard;
		}
		return null;
	}
	
	// ������������ද���ݳ�
	public static OrnamentalAnimalShow setRandomOrnamentalAnimalShow(int number) {
		int i = (int) (Math.random() * 2) + 1;//��Ҫ��
		Collections.shuffle(names);
		switch (i) {
		case 1:
			OrnamentalAnimalShow canary = new Canary(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfOrnamentalAnimal ++;
			return canary;
		case 2:
			OrnamentalAnimalShow parrot = new Parrot(number, names.get(1), (int)(Math.random() * 100), "����", (int)(Math.random() * 5 + 1));
			numberOfOrnamentalAnimal ++;
			return parrot;
		}
		return null;
	}

	// �����������ݳ�
	public static Mermaid setMermaidShow(int number) {
		Collections.shuffle(names);
		Mermaid mermaid = new Mermaid(names.get(1));
		return mermaid;
	}
	
	//�����������õı���
	public static ArrayList<String> sortShows(ArrayList<FishShow> listOfFish, ArrayList<MammalShow> listOfMammal, ArrayList<ReptileShow> listOfReptile, Mermaid mermaid) {
		ArrayList<String> list = new ArrayList<String>();//�ܽ�Ŀ��
		int i = 0;
		while (!listOfFish.isEmpty() && !listOfMammal.isEmpty() && !listOfReptile.isEmpty()) {
			switch(i++ % 3 + 1){
			case 1:
				if (!listOfFish.isEmpty()) {
					Collections.shuffle(listOfFish);
					list.add(listOfFish.get(0).toString());
					listOfFish.remove(0);
				}
				break;
			case 2:
				if (!listOfMammal.isEmpty()) {
					Collections.shuffle(listOfMammal);
					list.add(listOfMammal.get(0).toString());
					listOfMammal.remove(0);
				}
				break;
			case 3:
				if (!listOfReptile.isEmpty()) {
					Collections.shuffle(listOfReptile);
					list.add(listOfReptile.get(0).toString());
					listOfReptile.remove(0);
				}
				break;
			}
		}
		list.add(mermaid.toString());
		return list;
	}
	
}