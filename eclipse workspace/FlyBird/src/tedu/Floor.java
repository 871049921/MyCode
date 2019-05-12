package tedu;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//地板类
public class Floor {
//	属性 ：图片，x,  y , width , height 
	BufferedImage image;
	int x;
	int y;
	int width;
	int height;
// 方法 ：移动(更改图片的坐标)，构造方法(初始化属性)
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
// 碰撞方法(有参数(鸟类))
	public boolean hit(Bird bird){
		if(bird.y+bird.height>=y){
			return true;
		}
		return false;
	}
	
}
