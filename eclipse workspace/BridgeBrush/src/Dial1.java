
public class Dial1 extends Dial {
	public Dial1() {
		super();
	}
	
	public Dial1(int pos) {
		super(pos);
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public void upPos() {
		if (this.pos < 3) {
			this.pos ++;
		}
	}
	
	public void downPos() {
		if (this.pos > 1) {
			this.pos --;
		}
	}
}
