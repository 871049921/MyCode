package tedu;

import javax.swing.JFrame;

public class FlyBird {
	public static void main(String[] args) {
//		1.新建窗口实例对象
		JFrame jf = new JFrame();
//		2.设置窗口的各种属性
		//2.1大小、
		jf.setSize(280, 500);
		//2.2位置、
		jf.setLocationRelativeTo(null);
		//2.3关闭方式、
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//2.4不可放大缩小
		jf.setResizable(false);
		jf.setTitle("FlyBird v1.0.0 by hwd");
//		3.实例化一个画板对象
		MyPanel mp = new MyPanel();
//		4.将画板对象添加到窗口对象中
		jf.add(mp);
//		5.窗口可见
		jf.setVisible(true);
//		6.开启移动方法
		mp.action();
	}
}
