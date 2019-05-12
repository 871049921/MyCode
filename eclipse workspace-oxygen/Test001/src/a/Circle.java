package a;

public class Circle {
	private double radius = 0;
	
	public Circle() {
		radius = 1;
	}
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		double area = 0;
		area = Math.PI * radius * radius;
		return area;
	}
}
