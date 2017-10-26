package nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jdk.nashorn.internal.ir.WhileNode;

class Handle implements Runnable {
	private Socket socket;

	public Handle(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String strInput;
			while ((strInput = br.readLine()) != null) {
				System.out.println(strInput);
				bw.write(strInput + "\r\n");
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class HandleThread extends Thread {
	private Socket socket;

	public HandleThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String strInput;
			while ((strInput = br.readLine()) != null) {
				System.out.println(strInput);
				bw.write(strInput + "\r\n");
				bw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class ThreadPoolBlockingServer_2 {
	private final static int port = 8080;

	public static void main(String[] args) {
		System.out.println("wait for some thread...");
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			ExecutorService pool = Executors.newFixedThreadPool(5);
			while (true) {
				Socket socket = serverSocket.accept();
//				pool.submit(new HandleThread(socket));
				pool.submit(new Handle(socket));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
