import java.util.ArrayList;

public class a {

	public static void main(String[] args) {
		ArrayList<Integer> aa = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++) {
			aa.add(i);
		}
		aa.add(4, 123);
		System.out.println(aa);
		aa.remove(1);
		System.out.println(aa);
	}

}
