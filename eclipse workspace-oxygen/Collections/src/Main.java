import java.util.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		System.out.println("arrayList indexOf 2 : " + arrayList.indexOf(2));
		System.out.println("arrayList indexOf 4 : " + arrayList.indexOf(4));
		arrayList.remove(1);
		System.out.println("arrayList: " + arrayList);
		arrayList.set(0, 5);
		System.out.println("arrayList: " + arrayList);
		
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		System.out.println("linkedList indexOf 1 : " + linkedList.indexOf(1));
		System.out.println("linkedList indexOf 4 : " + linkedList.indexOf(4));
		linkedList.remove(1);
		System.out.println("linkedList: " + linkedList);
		linkedList.set(0, 5);
		System.out.println("linkedList: " + linkedList);
		
		Set<String> treeSet = new TreeSet<>();
		treeSet.add("aaa");
		treeSet.add("bbb");
		treeSet.add("ccc");
		System.out.println("treeSet contains aaa : " + treeSet.contains("aaa"));
		System.out.println("treeSet contains bbb : " + treeSet.contains("bbb"));
		treeSet.remove("bbb");
		System.out.println("treeSet: " + treeSet);
		treeSet.add("ddd");
		System.out.println("treeSet: " + treeSet);
		
		Set<String> hashSet = new HashSet<>();
		hashSet.add("aaa");
		hashSet.add("bbb");
		hashSet.add("ccc");
		System.out.println("hashSet contains aaa : " + hashSet.contains("aaa"));
		System.out.println("hashSet contains ddd : " + hashSet.contains("ddd"));
		hashSet.remove("bbb");
		System.out.println("hashSet: " + hashSet);
		hashSet.add("ddd");
		System.out.println("hashSet: " + hashSet);
		
		Set<String> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add("aaa");
		linkedHashSet.add("bbb");
		linkedHashSet.add("ccc");
		System.out.println("linkedHashSet contains aaa : " + linkedHashSet.contains("aaa"));
		System.out.println("linkedHashSet contains ddd : " + linkedHashSet.contains("ddd"));
		linkedHashSet.remove("bbb");
		System.out.println("linkedHashSet: " + linkedHashSet);
		linkedHashSet.add("ddd");
		System.out.println("linkedHashSet: " + linkedHashSet);
		
		Map<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("aaa", 1);
		treeMap.put("bbb", 1);
		treeMap.put("ccc", 2);
		System.out.println("treeMap value aaa : " + treeMap.get("aaa"));
		System.out.println("treeMap value ddd : " + treeMap.get("ccc"));
		treeMap.remove("aaa");
		System.out.println("treeMap: " + treeMap);
		treeMap.put("ddd", 6);
		System.out.println("treeMap: " + treeMap);
		
		Map<String, Integer> hashMap = new HashMap<>();
		hashMap.put("aaa", 1);
		hashMap.put("bbb", 1);
		hashMap.put("ccc", 2);
		System.out.println("hashMap value aaa : " + hashMap.get("aaa"));
		System.out.println("hashMap value ddd : " + hashMap.get("ccc"));
		hashMap.remove("aaa");
		System.out.println("hashMap: " + hashMap);
		hashMap.put("ddd", 6);
		System.out.println("hashMap: " + hashMap);
		
		Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("aaa", 1);
		linkedHashMap.put("bbb", 1);
		linkedHashMap.put("ccc", 2);
		System.out.println("linkedHashMap value aaa : " + linkedHashMap.get("aaa"));
		System.out.println("linkedHashMap value ddd : " + linkedHashMap.get("ccc"));
		linkedHashMap.remove("aaa");
		System.out.println("linkedHashMap: " + linkedHashMap);
		linkedHashMap.put("ddd", 6);
		System.out.println("linkedHashMap: " + linkedHashMap);
	}

}
