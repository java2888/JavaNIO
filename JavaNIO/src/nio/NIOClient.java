package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient implements Runnable {
	private String host;
	private int port;

	public NIOClient(int port, int numThreads) {
		this.port = port;

		for (int i = 0; i < numThreads; i++) {
			new Thread(this).start();
		}

	}

	public NIOClient(String host, int port, int numThreads) {
		this.host = host;
		this.port = port;

		for (int i = 0; i < numThreads; i++) {
			new Thread(this).start();
		}

	}

	@Override
	public void run() {
		InetSocketAddress address = new InetSocketAddress("localHost", port);
		try {
			SocketChannel sc = SocketChannel.open(address);
			String result = "hello  " + this.getClass().getName().toString() + System.currentTimeMillis();
			sc.write(ByteBuffer.wrap(result.getBytes()));
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
/*		String host = args[0];
		int port = Integer.parseInt(args[1]);
		int numThreads = Integer.parseInt(args[2]);*/
		
		new NIOClient(2222, 5);
	}

}
