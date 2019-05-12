import java.util.*;

public class Main {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		Lever lever = new Lever();
		Dial dial = new Dial();
		Brush b = new Brush(0);
		Agent a = new Agent();
		
		int choice = 0;
		
		while(true){
			menu();
			choice = input.nextInt();
			switch(choice){
			case 1:
				lever.getState().upPos(lever);
				break;
			case 2:
				lever.getState().downPos(lever);
				break;
			case 3:
				dial.getState().upPos(dial);
				break;
			case 4:
				dial.getState().downPos(dial);
				break;
			default:
				System.exit(0);
				input.close();
				break;
			}
			a.dealSpeed(lever, dial, b);
			
			System.out.println("Lever's pos:" + lever.getState().getPos());
			System.out.println("Dial's pos:" + dial.getState().getPos());
			System.out.println("Brush's speed:" + b.getSpeed());
				
		}
	}
	
	private static void menu()
	{
		System.out.println("please input your choice:");
		System.out.println("1:Lever up");
		System.out.println("2:Lever down");
		System.out.println("3:Dial up");
		System.out.println("4:Dial down");
		System.out.println("0:exit");
	}

}
