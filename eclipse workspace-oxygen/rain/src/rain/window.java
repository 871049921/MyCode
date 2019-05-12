package rain;

import javax.swing.JFrame;

public class window {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(1920, 1080);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		Mypanel mp = new Mypanel();
		jf.add(mp);
		jf.setVisible(true);
		mp.action();
	}

}
