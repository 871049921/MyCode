import java.util.Scanner;

public class BridgeBrush {

	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
		
		Rain r;
		r = (Rain)XMLUtil.getBean("Rain");
		
		
		int choice = 0;
		
		while (true) {
			menu();
			choice = input.nextInt();
			switch(choice) {
			case 1:
				r.lever.upPos();
				break;
			case 2:
				r.lever.downPos();
				break;
			case 3:
				r.lever.dial.upPos();
				break;
			case 4:
				r.lever.dial.downPos();
				break;
			case 5:
				r.heavyUp();
				break;
			case 6:
				r.heavyDown();
				break;
			default:
				input.close();
				System.exit(0);
				break;
			}
			r.dealWithSpeed();
			System.out.println("Lever's pos:" + r.lever.getPos());
			System.out.println("Dial's pos:" + r.lever.dial.getPos());
			System.out.println("Rain's heavy:" + r.getHeavy());
		}
	}
	
	private static void menu() {
		System.out.println("Please input your choice");
		System.out.println("1:Lever up");
		System.out.println("2:Lever down");
		System.out.println("3:Dial up");
		System.out.println("4:Dial down");
		System.out.println("5:Rain up");
		System.out.println("6:Rain down");
		System.out.println("0:exit");
		System.out.println("");
	}

}
