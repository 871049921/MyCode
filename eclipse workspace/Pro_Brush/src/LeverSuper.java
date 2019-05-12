
public abstract class LeverSuper {
	private int pos;
	
	public LeverSuper() {
		this.pos = 1;
	}
	
	public LeverSuper(int pos) {
		this.pos = pos;
	}
	
	public abstract int getPos();
	
	public abstract void upPos();
	
	public abstract void downPos();
}
