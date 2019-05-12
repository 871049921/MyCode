package experiment3;

public abstract class ReptileShow extends AnimalShow {
	public ReptileShow() {
		super();
		setType("Reptile");
	}
	
	public abstract String toString();
	
	public abstract boolean equals(int number);
}
