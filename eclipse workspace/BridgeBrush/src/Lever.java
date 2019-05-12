
public abstract class Lever {
	protected int pos;
	protected Dial dial;
	
	public Lever() {
		this.pos = 1;
	}
	
	public Lever(int pos) {
		this.pos = pos;
	}
	
	public abstract int getPos();
	
	public abstract void upPos();
	
	public abstract void downPos();
	
	public abstract void dealWithSpeed();
}
