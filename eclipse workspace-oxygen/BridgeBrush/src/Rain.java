
public abstract class Rain {
	protected int heavy;
	protected Lever lever;
	
	public Rain() {
		heavy = 0;
	}
	
	public Rain(int heavy) {
		this.heavy = heavy;
	}
	
	public abstract int getHeavy();
	
	public abstract void setHeavy(int heavy);
	
	public abstract void heavyUp();
	
	public abstract void heavyDown();
	
	public abstract void dealWithSpeed();
}
