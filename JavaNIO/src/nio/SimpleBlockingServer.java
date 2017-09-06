package nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleBlockingServer {
	private static final int port=8080;
	
	private static void handle(Socket socket){
		
	}
	
	public static void main(String[] args) throws IOException {
		 ServerSocket serverSocket=new ServerSocket(port);
		 while(true){
			 Socket socket=serverSocket.accept();
			 handle(socket);
		 }
	}

}
