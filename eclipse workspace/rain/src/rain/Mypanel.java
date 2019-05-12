package rain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Mypanel extends JPanel{
	int locx[] = new int[500];
	int locy[] = new int[500];
	int x[] = new int [500];
	int y[] = new int [500];
	int z[] = new int [500];
	{
	for (int i=0; i<500; i++) {
		locx[i] = (int)(Math.random()*1920);
		locy[i] = (int)(Math.random()*1080);
		x[i] = (int)(Math.random()*255);
		y[i] = (int)(Math.random()*255);
		z[i] = (int)(Math.random()*255);
	}
	}
	public void paint(Graphics g) {
		super.paint(g);
		for (int i=0; i<500; i++) {
			g.drawString("ÁõÓîÉµ±Æ",locx[i] ,locy[i]);
			g.setColor(new Color(x[i], y[i], z[i]));
			g.setFont(new Font("ËÎÌå", 0, 30));
	}

	}
	public void action() {
		while (true) {
			for (int i=0; i<500; i++){
				locy[i]++;
				if (locy[i] == 1080) {
					locy[i] = 0;
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			repaint();
			}
		}
}
	
