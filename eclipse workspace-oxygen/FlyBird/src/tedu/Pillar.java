package tedu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//柱子类
public class Pillar {
//	属性 ：图片，x,  y1, y2 , width , height , distance , gap 
	BufferedImage imgup;
	BufferedImage imgdown;
	int x;
	int y1;
	int y2;
	int width;
	int height;
	int distance;
	int gap;
//    方法 ：移动(更改图片的坐标)，构造方法(初始化属性)
	public Pillar(int i) {
		try {
			imgup=ImageIO.read(getClass().getResource("zzshang.png"));
			imgdown=ImageIO.read(getClass().getResource("zzxia.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width=imgup.getWidth();
		height=imgup.getHeight();
		distance=280-width;
		x=300+distance*i;
		gap=100;
		y1=(int)(Math.random()*(500-height*2-gap)/20)*20;
		y2=y1+height+gap;
	}
	public void move(){
		x--;
		if(x==-width){
			x=distance*2-width;
			y1=(int)(Math.random()*(500-height*2-gap)/20)*20;
			y2=y1+height+gap;
		}
	}
//    碰撞方法(有参数(鸟类))
	public boolean hit(Bird bird){
		int maxx=x+width;
		int minx=x-bird.width;
		int miny=y2-gap-15;
		int maxy=y2-bird.height+15;
		if(bird.x>minx&&bird.x<maxx){
			if(bird.y>miny&&bird.y<maxy){
				return false;
			}
			return true;
		}
		return false;
	}
	
	
}
