
public abstract class AgentSuper {
	private LeverSuper l;
	private DialSuper d;
	private Brush b;
	
	public AgentSuper() {
		
	}
	
	public AgentSuper(LeverSuper l, DialSuper d, Brush b) {
		this.l = l;
		this.d = d;
		this.b = b;
	}
	
	public abstract void dealSpeed();
}
