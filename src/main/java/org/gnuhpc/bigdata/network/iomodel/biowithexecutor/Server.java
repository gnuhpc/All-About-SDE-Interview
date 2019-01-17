package org.gnuhpc.bigdata.network.iomodel.biowithexecutor;

import java.net.ServerSocket;

public class Server {

	final static int PORT = 8765;

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
			System.out.println("server start");
			//内部封装了 一个ThreadPoolExecutor对象
			HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
			//使用线程池节省线程的创建与销毁带来的资源浪费。
			while(true){
				executorPool.execute(new ServerHandler(server.accept()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(server != null){
				try {
					server.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
	}
}
