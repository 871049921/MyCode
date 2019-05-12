
public class Lever2 extends Lever {

	public Lever2() {
		super();
		dial = new Dial2();
	}
	
	public Lever2(int pos) {
		super(pos);
		dial = new Dial2();
	}

	public int getPos() {
		return pos;
	}
	
	public void upPos() {
		if (this.pos < 5) {
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
			case 4:
				brush.setSpeed(24);
				break;
			}
			break;
		case 3:
			brush.setSpeed(30);
			break;
		case 4:
			brush.setSpeed(60);
			break;
		case 5:
			brush.setSpeed(90);
		}
		System.out.println("Brush's speed:" + brush.getSpeed());
	}

}