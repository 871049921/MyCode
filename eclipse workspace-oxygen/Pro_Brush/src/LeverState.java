
public abstract class LeverState {
	protected int pos;
	
	public LeverState() {
		pos = 1;
	}
	
	public int getPos(){
		return pos;
	}
	
	public void upPos(Lever lever){
		LeverState state;
		if(this.pos < 4){
			if (this.pos == 1) {
				state = new LeverState2();
				lever.setState(state);
			} else if (this.pos == 2) {
				state = new LeverState3();
				lever.setState(state);
			} else if (this.pos == 3) {
				state = new LeverState4();
				lever.setState(state);
			}
		}
	}
	
	public void downPos(Lever lever){
		LeverState state;
		if(this.pos > 1){
			if (this.pos == 2) {
				state = new LeverState1();
				lever.setState(state);
			} else if (this.pos == 3) {
				state = new LeverState2();
				lever.setState(state);
			} else if (this.pos == 4) {
				state = new LeverState3();
				lever.setState(state);
			}
		}
	}
	
	public abstract void state1();
	
	public abstract void state2();
	
	public abstract void state3();
	
	public abstract void state4();
	
}
