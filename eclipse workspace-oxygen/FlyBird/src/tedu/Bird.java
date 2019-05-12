package tedu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
//	���� ��ͼƬ���飬ͼƬ��x,  y , width , height ,  
//    �������ٶȣ�ʱ�䣬���룬�����ٶ�
	BufferedImage[] images;
	BufferedImage image;
	int x;
	int y;
	int width;
	int height;
	double g;
	double t;
	double s;
	double v;
	int index;
	double r;
//    ���� ��������(����ͼƬ������)�����췽��(��ʼ������)
	public Bird() {
		images=new BufferedImage[8];
			try {
				for(int i=0;i<images.length;i++){
				images[i]=ImageIO.read(getClass().getResource(i+".png"));
				}
			} catch (IOException e) {
				e.printStackTrace();
		}
			image=images[0];
			width=image.getWidth();
			height= image.getHeight();
			x=(280-width)/2;
			y=(500-height)/2;
			g=4.5;
			t=0.25;
			s=0;
			v=20;
			index=0;
			r=0;
	}
	int count;
	public void move(){
		s=g*t*t/2-v*t;
		v=v-g*t;
		y+=s;
		if(y<=0){
			y=0;
		}
		r=Math.atan(s/2);
		index++;
		if(index==80){
			index=0;
		}
		image=images[index/10];
	}
	public void fly(){
		v=20;
	}
	
	
//    С��ͼƬ(�������е�ͼƬ��ͣ���л���ֵ��ͼƬ����)
}
