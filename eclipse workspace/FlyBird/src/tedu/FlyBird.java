package tedu;

import javax.swing.JFrame;

public class FlyBird {
	public static void main(String[] args) {
//		1.�½�����ʵ������
		JFrame jf = new JFrame();
//		2.���ô��ڵĸ�������
		//2.1��С��
		jf.setSize(280, 500);
		//2.2λ�á�
		jf.setLocationRelativeTo(null);
		//2.3�رշ�ʽ��
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//2.4���ɷŴ���С
		jf.setResizable(false);
		jf.setTitle("FlyBird v1.0.0 by hwd");
//		3.ʵ����һ���������
		MyPanel mp = new MyPanel();
//		4.�����������ӵ����ڶ�����
		jf.add(mp);
//		5.���ڿɼ�
		jf.setVisible(true);
//		6.�����ƶ�����
		mp.action();
	}
}
