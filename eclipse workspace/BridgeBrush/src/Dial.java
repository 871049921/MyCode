
public abstract class Dial {
	protected int pos;//λ��
	
	public Dial() {
		
	}
	
	public Dial(int pos) {
		this.pos = pos;
	}
	
	public abstract int getPos();
	
	public abstract void upPos();
	
	public abstract void downPos();
}
