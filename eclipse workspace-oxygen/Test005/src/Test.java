
class Test <E> {
	
	private E e;
	
	public Test() {
		e = null;
	}
	
	public Test(E e) {
		
	}
	
	public void start(E e) {
		System.out.println(e);
	}
	
	public E ss() {
		return e;
	}
	
}
