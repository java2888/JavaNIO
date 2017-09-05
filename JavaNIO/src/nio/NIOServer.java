package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.omg.CORBA.PRIVATE_MEMBER;


public class NIOServer {
    private static final int port=2222;
	public static void main(String[] args) {
		
		try {
			Selector selector=Selector.open();
			ServerSocketChannel ssc=ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ServerSocket ss=ssc.socket();
			InetSocketAddress address=new InetSocketAddress(port);
			ss.bind(address);
			ssc.register(selector, SelectionKey.OP_ACCEPT);
			while(true){
				int num=selector.select();
				if(0==num){
					continue;
				}
				Set keys=selector.selectedKeys();
				Iterator iterator=keys.iterator();
				while(iterator.hasNext()){
					
					SelectionKey key=(SelectionKey)iterator.next();
					iterator.remove();
					if( (key.readyOps()&SelectionKey.OP_ACCEPT)==
						SelectionKey.OP_ACCEPT){
						System.out.println("acc");
						Socket s=ss.accept();
						System.out.println("Got connection from "+s);
						SocketChannel sc=s.getChannel();
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);						
					}else if( (key.readyOps()&SelectionKey.OP_READ)==
							SelectionKey.OP_READ){
						System.out.println("read");
						SocketChannel sc=null;						
						sc=(SocketChannel) key.channel();
						ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
						int count=sc.read(byteBuffer);
						if(count>0){
							String result=new String(byteBuffer.array());
							System.out.println(result);
						}
						
						
					}
					
				}
			}
			
		} catch (IOException e) {			 
			e.printStackTrace();
		}
	  
		

	}

}
