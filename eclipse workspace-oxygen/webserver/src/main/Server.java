package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) {
		ServerSocket ss = null;
		ExecutorService pool = null;
		try {
			ss = new ServerSocket(1607);
			pool = Executors.newCachedThreadPool();
			while (true) {//因为是每一个连接有一个 ，所以这里用到了while循环
				System.out.println("正在等待连接..");
				//对每一个连入的连接创建一个  Socket
				//但是他是线程阻塞方法，就像你用Scanner 的时候，如果需要输入啥等输入完以后程序才会往下走
				Socket s = ss.accept();
			
				pool.submit(new ThreadTask(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
			try {
				if (ss != null)
					ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
