
public class Lever1 extends Lever {
	
	public Lever1() {
		super();
		dial = new Dial1();
	}
	
	public Lever1(int pos) {
		super(pos);
		dial = new Dial1();
	}

	public int getPos() {
		return pos;
	}
	
	public void upPos() {
		if (this.pos < 4) {
			this.pos ++;
		}
	}
	
	public void downPos() {
		if (this.pos > 1) {
			this.pos --;
		}
	}
	
	public void dealWithSpeed() {
		Brush brush = new Brush();
		switch(getPos()) {
		case 1:
			brush.setSpeed(0);
			break;
		case 2:
			switch(dial.getPos()) {
			case 1:
				brush.setSpeed(4);
				break;
			case 2:
				brush.setSpeed(6);
				break;
			case 3:
				brush.setSpeed(12);
				break;
			}
			break;
		case 3:
			brush.setSpeed(30);
			break;
		case 4:
			brush.setSpeed(60);
			break;
		}
		System.out.println("Brush's speed:" + brush.getSpeed());
	}
}