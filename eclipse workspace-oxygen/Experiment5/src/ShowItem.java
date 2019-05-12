

public class ShowItem {
	private String name;
	private int timeOfShow;
	
	public ShowItem() {
		name = null;
		timeOfShow = 0;
	}
	
	public ShowItem(String name, int timeOfShow) {
		this.name = name;
		this.timeOfShow = timeOfShow;
	}
	
	//getName
	public String getName() {
		return this.name;
	}
	
	//setName
	public void setName(String name) {
		this.name = name;
	}
	
	//getTimeOfShow
	public double getTimeOfShow() {
		return timeOfShow;
	}
	
	//setTimeOfShow
	public void setTimeOfShow(int timeOfShow) {
		this.timeOfShow = timeOfShow;
	}
}
