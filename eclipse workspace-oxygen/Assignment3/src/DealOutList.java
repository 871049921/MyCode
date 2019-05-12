
public class DealOutList {
	private float needTime;
	private String directionOfElevator;
	private int elevatorFloor;
	private float currentTime;
	private Elevator elevator;
	private String request1;

	public DealOutList() {

	}

	public float getNeedTime() {
		return needTime;
	}

	public String getDirectionOfElevator() {
		return directionOfElevator;
	}

	public int getElevatorFloor() {
		return elevatorFloor;
	}

	public float getCurrentTime() {
		return currentTime;
	}

	public Elevator getElevator() {
		return elevator;
	}

	public String getRequest1() {
		return request1;
	}

	public void setNeedTime(float needTime) {
		this.needTime = needTime;
	}

	public void setDirectionOfElevator(String directionOfElevator) {
		this.directionOfElevator = directionOfElevator;
	}

	public void setElevatorFloor(int elevatorFloor) {
		this.elevatorFloor = elevatorFloor;
	}

	public void setCurrentTime(float currentTime) {
		this.currentTime = currentTime;
	}

	public void setElevator(Elevator elevator) {
		this.elevator = elevator;
	}

	public void setRequest1(String request1) {
		this.request1 = request1;
	}
}
