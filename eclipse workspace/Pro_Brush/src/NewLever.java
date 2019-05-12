
public class NewLever extends Lever {
	private int pos;
	
	public NewLever() {
		
	}
	
	public NewLever(int pos) {
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}
	
	public void upPos() {
		if (this.pos < 5) {
			this.pos ++;
		}
	}
	
	public void downPos() {
		if (this.pos > 1) {
			this.pos --;
		}
	}
	
}
