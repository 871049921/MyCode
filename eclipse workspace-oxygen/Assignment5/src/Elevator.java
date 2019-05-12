
public class Elevator implements ElevatorRun {
	private int elevatorFloor;
	private String direction;
	private String lastDirection;

	public Elevator() {
		elevatorFloor = 1;
		direction = "STILL";
	}

	// getElevatorFloor
	public int getElevatorFloor() {
		return elevatorFloor;
	}

	// setElevatorFloor
	public void setElevatorFloor(int elevatorFloor) {
		this.elevatorFloor = elevatorFloor;
	}

	// getDirection
	public String getDirection() {
		return direction;
	}

	// setDirction
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	// getLastDirection
	public String getLastDirection() {
		return lastDirection;
	}

	// setLastDirction
	public void setLastDirection(String lastDirection) {
		this.lastDirection = lastDirection;
	}

	@Override
	public String toString() {
		return direction;
	}

	public void run(int floor) {
		elevatorFloor = floor;
	}
}
