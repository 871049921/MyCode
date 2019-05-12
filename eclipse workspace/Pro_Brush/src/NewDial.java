
public class NewDial extends DialSuper {
	private int pos;
	
	public NewDial() {
		super();
	}
	
	public NewDial(int pos) {
		this.pos = pos;
	}
	
	public int getPos() {
		return this.pos;
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
