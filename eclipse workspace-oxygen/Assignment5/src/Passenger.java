
public class Passenger {
	
	private float waitTime;
	private float leaveTime;
	
	public Passenger() {

	}

	public float getWaitTime() {
		return this.waitTime;
	}
	
	public void setType(float waitTime) {
		this.waitTime = waitTime;
	}
	
	public void setLeaveTime(float leaveTime) {
		this.leaveTime = leaveTime;
	}
	
	@Override
	public String toString() {
		return "乘客的离开电梯的时间是" + leaveTime;
	}
}
