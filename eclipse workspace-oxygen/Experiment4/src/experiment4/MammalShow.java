package experiment4;

public abstract class MammalShow extends AnimalShow {
	public MammalShow() {
		super();
		setType("MammalShow");
	}
	
	public abstract String toString();
	
	public abstract boolean equals(int number);
}