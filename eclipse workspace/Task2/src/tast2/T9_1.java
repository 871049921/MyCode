package tast2;

public class T9_1 {

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(4, 40);
		Rectangle r2 = new Rectangle(3.5, 35.9);
		System.out.println("r1:    width: " + r1.getWidth() + " height: " + r1.getWidth() + " area:" + (float)r1.getArea() + " Perimeter: " + r1.getPerimeter());
		System.out.println("r2:    width: " + r2.getWidth() + " height: " + r2.getWidth() + " area:" + (float)r2.getArea() + " Perimeter: " + r2.getPerimeter());
	}

}

class Rectangle{
	private double width = 1;
	private double height = 1;
	
	public Rectangle() {
		
	}
	
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getArea() {
		return width * height;
	}
	
	public double getPerimeter() {
		return (width + height) * 2;
	}
}
