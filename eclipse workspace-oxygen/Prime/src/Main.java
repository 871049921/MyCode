import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	public static void main(String[] args){
		ArrayList<Integer> primes1 = findPrime(100000);
		ArrayList<Integer> primes2 = (ArrayList<Integer>) primes1.clone();
		mul(primes1, primes2);
//		Iterator<Integer> it = primes1.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}

	}
	
	public static ArrayList<Integer> findPrime(int n) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i <= n; i ++) {
			int flag = 1;
			for(int j = 2; j < i; j ++) {
				if(i % j == 0) {// 不是质数
					flag = 0;
					break;
				}
			}
			if(flag == 1) {
				primes.add(i);
			}
		}
		return primes;
	}
	
	public static void mul(ArrayList<Integer> a, ArrayList<Integer> b) {
		
		BigInteger p3 = new BigInteger("3227207173");
		for(int i = 0; i < a.size(); i ++) {
			for(int j = 0; j < b.size(); j ++) {
				BigInteger p1 = BigInteger.valueOf(a.get(i));
				BigInteger p2 = BigInteger.valueOf(b.get(j));
				if(p1.multiply(p2).equals(p3)) {
					System.out.println(p1 + "," + p2);
					return;
				}
			}
		}
		System.out.println("NO!");
	}
}
