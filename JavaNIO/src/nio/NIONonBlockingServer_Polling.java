package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.omg.CORBA.PRIVATE_MEMBER;

import jdk.internal.dynalink.beans.StaticClass;

public class NIONonBlockingServer_Polling {

	private static final int port = 8080;

	public static void main(String[] args) throws InterruptedException {
		ServerSocketChannel ssc = null;
		try {
			System.out.println("NonBlockingServer polling begins to wait ...");
			ssc = ServerSocketChannel.open();
			ssc.bind(new InetSocketAddress(port));
			ssc.configureBlocking(false);
			while (true) {
				SocketChannel sc = ssc.accept();
				if (sc != null) {
					System.out.println(sc);
					sc.configureBlocking(false);
					handle(sc);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// handle(SocketChannel sc)
	private static void handle(SocketChannel sc) throws InterruptedException {
		final int capacity = 1024;
		ByteBuffer dst = ByteBuffer.allocate(capacity);
		try {
			while (true) {
				if (sc.finishConnect() && sc.isConnected()) {
					dst.clear();
					if (sc.isConnected()&&sc.isOpen()) {
						sc.read(dst);
					} else {
						continue;
					}
					String result = new String(dst.array(), "UTF-8");
					System.out.println("[result]=" + result);
					Thread.sleep(1000);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
