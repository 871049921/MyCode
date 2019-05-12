package tast2;

public class T9_8 {
	public static void main(String[] args) { 
		Fan f1 = new Fan(3, true, 10.0, "yellow");
		Fan f2 = new Fan(2, false, 5.0, "blue");
		System.out.println(f1.toString());
		System.out.println(f2.toString());
	}
}

class Fan{
	public static final int SLOW = 1;
	public static final int MEDIUM = 2;
	public static final int FAST = 3;
	private int speed = SLOW;
	private boolean on = false;
	private double radius = 5.0;
	private String color = "blue";
	
	public Fan() {
		
	}
	public Fan(int speed, boolean on, double radius, String color) {
		this.speed = speed;
		this.on = on;
		this.radius = radius;
		this.color = color;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public boolean getOn() {
		return on;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setOn(boolean on) {
		this.on = on;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String index;
		if (on == true) {
			index = "speed:" + speed + " color:" + color + " radius:" + radius;
		}
		else {
			index = "The fan is off   color:" + color + " radius:" + radius;
		}
		
		return index;
	}
}
