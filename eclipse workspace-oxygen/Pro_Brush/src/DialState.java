
public abstract class DialState {
	protected int pos;
	
	public DialState() {
		pos = 1;
	}
	
	public int getPos(){
		return this.pos;
	}
	
	public void upPos(Dial dial){
		DialState state;
		if(this.pos < 3){
			if (this.pos == 1) {
				state = new DialState2();
				dial.setState(state);
			} else if (this.pos == 2) {
				state = new DialState3();
				dial.setState(state);
			}
		}
	}
	
	public void downPos(Dial dial){
		DialState state;
		if(this.pos > 1){
			if (this.pos == 2) {
				state = new DialState1();
				dial.setState(state);
			} else if (this.pos == 3) {
				state = new DialState2();
				dial.setState(state);
			}
		}
	}
	
	public abstract void state1();
	
	public abstract void state2();
	
	public abstract void state3();
}
