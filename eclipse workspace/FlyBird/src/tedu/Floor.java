package tedu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//�ذ���
public class Floor {
//	���� ��ͼƬ��x,  y , width , height 
	BufferedImage image;
	int x;
	int y;
	int width;
	int height;
// ���� ���ƶ�(����ͼƬ������)�����췽��(��ʼ������)
	public Floor() {
		try {
			image=ImageIO.read(getClass().getResource("ground1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height=image.getHeight();
		x=0;
		y=500-height;
	}
	public void move(){
		x--;
		if(x==280-width){
			x=0;
		}
	}
// ��ײ����(�в���(����))
	public boolean hit(Bird bird){
		if(bird.y+bird.height>=y){
			return true;
		}
		return false;
	}
	
}
