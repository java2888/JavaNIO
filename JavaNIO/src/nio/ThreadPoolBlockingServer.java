package nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jdk.internal.org.objectweb.asm.Handle;

public class ThreadPoolBlockingServer {

	private static final int port=8080;
    // 
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(port);
			ExecutorService pool=Executors.newFixedThreadPool(5);
			while(true){
				Socket socket=serverSocket.accept();
				pool.submit(new Runnable() {					
					@Override
					public void run() {
						handle(socket);						
					}
				});			
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void handle(Socket socket) {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String strInput;
			while( (strInput=br.readLine())!=null ){
				System.out.println(strInput);
				bw.write(strInput+"\r\n");
				bw.flush();				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
