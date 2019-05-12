package experiment3;

public abstract class FishShow extends AnimalShow {
	public FishShow() {
		setType("Fish");
	}
	
	public abstract String toString();
	
	public abstract boolean equals(int number);
}
