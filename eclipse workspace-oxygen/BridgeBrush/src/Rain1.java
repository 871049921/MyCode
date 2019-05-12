
public class Rain1 extends Rain {
	
	public Rain1() {
		super();
		lever = new Lever2(1);
		lever.dial = new Dial2(1);
	}
	
	public Rain1(int heavy) {
		super(heavy);
		lever = new Lever2(1);
		lever.dial = new Dial2(1);
	}
	
	@Override
	public int getHeavy() {
		return heavy;
	}

	@Override
	public void setHeavy(int heavy) {
		this.heavy = heavy;

	}
	
	@Override
	public void heavyUp() {
		if (heavy < 5) {
			heavy ++;
		}
	}
	
	@Override
	public void heavyDown() {
		if (heavy > 0) {
			heavy --;
		}
	}
	
	public void dealWithSpeed() {
		Brush brush = new Brush();
		if (getHeavy() == 0) {
			brush.setSpeed(0);
			System.out.println("Brush's speed:" + brush.getSpeed());
			return;
		} else if (getHeavy() == 5) {
			brush.setSpeed(120);
			System.out.println("Brush's speed:" + brush.getSpeed());
			return;
		}
		switch(lever.getPos()) {
		case 1:
			brush.setSpeed(0);
			break;
		case 2:
			switch(lever.dial.getPos()) {
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
