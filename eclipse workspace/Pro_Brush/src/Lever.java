
public class Lever extends LeverSuper{
	private int pos;//Œª÷√
	
	public Lever() {
		this.pos = 1;
	}
	
	public Lever(int pos) {
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}
	
	public void upPos() {
		if (this.pos < 4) {
			this.pos ++;
		}
	}
	
	public void downPos() {
		if (this.pos > 1) {
			this.pos --;
		}
	}
}
