
public class Dial extends DialSuper {
	private int pos;//λ��
	
	public Dial() {
		
	}
	
	public Dial(int pos) {
		this.pos = pos;
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
