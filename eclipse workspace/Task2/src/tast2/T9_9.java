package tast2;

public class T9_9 {

	public static void main(String[] args) {
		RegularPolygon r1 = new RegularPolygon();
		RegularPolygon r2 = new RegularPolygon(6, 4);
		RegularPolygon r3 = new RegularPolygon(10, 4, 5.6, 7.8);
		System.out.println("r1  Perimeter:" + r1.getPerimeter() + " Area:" + r1.getArea());
		System.out.println("r2  Perimeter:" + r2.getPerimeter() + " Area:" + r2.getArea());
		System.out.println("r3  Perimeter:" + r3.getPerimeter() + " Area:" + r3.getArea());
	}
}

class RegularPolygon{
	private int n;
	private double side;
	private double x;
	private double y;
	
	public RegularPolygon() {
		n = 3;
		side = 1;
		x = 0.0;
		y = 0.0;
	}
	
	public RegularPolygon(int n, double side) {
		this.n = n;
		this.side = side;
		x = 0.0;
		y = 0.0;
	}
	
	public RegularPolygon(int n, double side, double x, double y) {
		this.n = n;
		this.side = side;
		this.x = x;
		this.y = y;
	}
	
	public double getPerimeter() {
		return n * side;
	}
	
	public double getArea() {
		double area = 0.0;
		area = (n * side *side) / (4 * Math.tan(Math.toRadians(Math.PI / n)));
		return area;
	}
	
	public int getN() {
		return n;
	}
	
	public double getSide() {
		return side;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public void setSide(double side) {
		this.side = side;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
