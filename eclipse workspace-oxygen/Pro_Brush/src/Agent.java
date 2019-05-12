
public class Agent {
	
	public Agent(){
	}
	
	public void dealSpeed(Lever l,
			Dial d,Brush b){
		switch(l.getState().getPos()){
		case 1:
			b.setSpeed(0);
			break;
		case 2:
			switch(d.getState().getPos()){
			case 1:
				b.setSpeed(4);
				break;
			case 2:
				b.setSpeed(6);
				break;
			case 3:
				b.setSpeed(12);
				break;			
			}
			break;
		case 3:
			b.setSpeed(30);
			break;
		case 4:
			b.setSpeed(60);
			break;		
		}
	}

}
