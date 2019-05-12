
public class Dial {
	private DialState state;
	
	public Dial(){
		state = new DialState1();
	}
	
	public DialState getState() {
		return this.state;
	}
	
	public void setState(DialState state) {
		this.state = state;
	}

}
