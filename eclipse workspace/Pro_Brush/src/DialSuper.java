
public abstract class DialSuper {
	private int pos;//λ��
	
	public DialSuper() {
		
	}
	
	public DialSuper(int pos) {
		this.pos = pos;
	}
	
	public abstract int getPos();
	
	public abstract void upPos();
	
	public abstract void downPos();
}