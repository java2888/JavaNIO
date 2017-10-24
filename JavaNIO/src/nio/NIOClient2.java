package nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NIOClient2 {
	private static final int port = 8080;

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", port);
			OutputStream out = socket.getOutputStream();
			for (int i = 0; i < 10; i++) {
				out.write(i);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
