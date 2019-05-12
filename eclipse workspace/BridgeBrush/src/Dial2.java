
public class Dial2 extends Dial {
	public Dial2() {
		super();
	}
	
	public Dial2(int pos) {
		super(pos);
	}
	
	public int getPos() {
		return this.pos;
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
