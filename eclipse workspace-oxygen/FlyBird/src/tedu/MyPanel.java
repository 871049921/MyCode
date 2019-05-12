package tedu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//	  3.1����һ����
//	  3.2�̳�JPanel
public class MyPanel  extends JPanel{
//	  3.3��������
//			3.3.1�����ࡢ���ࡢ�ذ���
		Bird bird;
		Pillar p1;
		Pillar p2;
		Floor floor;
//    		3.3.2���أ����ڸı���Ϸ״̬������
//				3.3.2.1 һ���Ƿ�ʼ��Ϸ��һ���Ƿ������Ϸ
		boolean istart;
		boolean isover;
//         	3.3.2.2 ��߷֣����ֵ÷�
		int top;
		int score;
//    		3.3.3����ͼ����ʼͼƬ���ؿ�ͼƬ
		BufferedImage bg;
		BufferedImage start;
		BufferedImage restart;
//	  3.4��ʼ������
//		  3.4.1  ���췽��
		public MyPanel() {
//	      3.4.2 ʵ����  2������(�вι���)���񡢵ذ���� 
			
			bird=new Bird();
			p1 =new Pillar(0);
			p2 = new Pillar(1);
			floor=new Floor();
//	      3.4.3 ���أ�����������ͼ����ʼͼƬ���ؿ�ͼƬ
			istart=false;
			isover=false;
			top=0;
			score=0;
			try {
				bg=ImageIO.read(getClass().getResource("bg.png"));
				start=ImageIO.read(getClass().getResource("start.png"));
				restart=ImageIO.read(getClass().getResource("gameover.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//	  3.5 paint���� 
		@Override
			public void paint(Graphics g) {
				super.paint(g);
//		  3.5.1 ���������ӡ��ذ塢�񡢷���
				g.drawImage(bg, 0, 0, null);
				Graphics2D g2= (Graphics2D)g;
				g2.rotate(bird.r, bird.x+bird.width/2, bird.y+bird.height/2);
				g.drawImage(bird.image, bird.x, bird.y, null);
				g2.rotate(-bird.r, bird.x+bird.width/2, bird.y+bird.height/2);
				g.drawImage(p1.imgup, p1.x, p1.y1, null);
				g.drawImage(p1.imgdown, p1.x, p1.y2, null);
				g.drawImage(p2.imgup, p2.x, p2.y1, null);
				g.drawImage(p2.imgdown, p2.x, p2.y2, null);
				g.drawImage(floor.image, floor.x, floor.y, null);
				g.drawString("Score :"+score, 20, 30);
				g.drawString("Top :"+top, 200, 30);
//	      3.5.2 ������Ϸ״̬����ʼ�������¿�ʼ��ͼƬ
				if(!istart){
					g.drawImage(start, 82, 215, null);
				}
				if(isover){
				g.drawImage(restart, (280-restart.getWidth())/2,
						(500-restart.getHeight())/2, null);
				}
		}
//	  3.6 action����
		public void action(){
			//�Զ���һ������¼��������Լ�������Ҫ��Ӽ���
			MouseAdapter ma = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					istart=true;
					bird.fly();
					isover=false;
				}
//				public void mouseMoved(MouseEvent e) {
//					super.mouseMoved(e);
//					bird.x=e.getX()-bird.width/2;
//					bird.y=e.getY()-bird.height/2;
//				}
			};
			//�󶨼����¼���������
			addMouseListener(ma);
//			addMouseMotionListener(ma);
//		  3.6.1 ��ѭ��
			while(true){
//	      3.6.2 �ı����꣨�����ƶ�������
				if(istart&&!isover){
					bird.move();
					p1.move();
					p2.move();
					floor.move();
					if(bird.x==p1.x+p1.width||bird.x==p2.x+p2.width){
						score++;
					}
					repaint();
					if(floor.hit(bird)||p1.hit(bird)||p2.hit(bird)){
						isover=true;
						if(score>top){
							top=score;
						}
						score=0;
						bird=new Bird();
						p1 =new Pillar(0);
						p2 = new Pillar(1);
						floor=new Floor();
					}
					
				}
//	      3.6.3 �ػ�
//	      3.6.4 �߳�����
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
}
