package nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleBlockingServer {
	private static final int port = 8080;

	private static void handle(Socket socket) {

		try (
				InputStream inputStream = socket.getInputStream(); 
				OutputStream outputStream = socket.getOutputStream();
		) 
		{
			int data;
			while( (data=inputStream.read())!=-1 ){
				outputStream.write(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UncheckedIOException(e);
		}

	}

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();
			handle(socket);
		}
	}

}
