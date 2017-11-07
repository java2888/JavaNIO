package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import jdk.internal.dynalink.beans.StaticClass;

public class NIOBlockingServer {
	private static final int port=8080;
	public static void main(String[] args) {
		ServerSocketChannel ssChannel;
		try {
			ssChannel = ServerSocketChannel.open();
			ssChannel.bind(new InetSocketAddress(port));
			System.out.println("Server begins to run ...");
			while(true){
				SocketChannel sc=ssChannel.accept();
				System.out.println(sc);
				handle(sc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void handle(SocketChannel sc){
		 final int  capacity=1024;
		 ByteBuffer dst=ByteBuffer.allocate(capacity);
		 try {
			sc.read(dst);
			String result=new String(dst.array(), "UTF-8");
			System.out.println(result);
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}

}
