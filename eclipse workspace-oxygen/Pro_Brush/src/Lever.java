
public class Lever {
	private LeverState state;
	
	public Lever(){
		state = new LeverState1();
	}
	
	public LeverState getState() {
		return this.state;
	}
	
	public void setState(LeverState state) {
		this.state = state;
	}

}
