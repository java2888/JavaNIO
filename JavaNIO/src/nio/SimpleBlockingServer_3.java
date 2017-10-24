package nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.zip.InflaterInputStream;

import jdk.internal.dynalink.beans.StaticClass;
import jdk.internal.org.objectweb.asm.Handle;

class myHandle implements Runnable {
	@Override
	public void run() {
		
	}
}

class myThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 
	}
}

public class SimpleBlockingServer_3 {

	private static final int port = 8080;
    private static int count=0;
    private static Object obj=new Object();
	public static void main(String[] args) {
		ServerSocket serverSocker = null;
		try {
			System.out.println("begin to main...");
			serverSocker = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocker.accept();
				Thread myThread=new Thread(new Runnable() {
					
					@Override
					public void run() {
						addCount();
						handle(socket);
						
					}
				});
				myThread.start();
				//handle(socket);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new UncheckedIOException(e);
		} finally {
			if ((serverSocker != null) && (!serverSocker.isClosed())) {
				try {
					serverSocker.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("end to main.");
		}
	}
   
	private   static void addCount(){
		synchronized(obj){
			count++;
			System.out.println("当前是第:[" + count+"]个线程");
		}		
	}
	
	private static void handle(Socket socket) {
		System.out.println("begin to handle ...");
		try {
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			InputStreamReader ipsr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(ipsr);
			String s = "";
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				handle_out_2(socket, s);
				if (s.equalsIgnoreCase("bye")) {
					System.exit(0);
				}
				/*
				 * if (s.equals("ok")) { handle_out(socket); }
				 */

			}
			socket.close();
			/*
			 * int data; while ((data = in.read()) != -1) { out.write(data);
			 * out.flush(); }
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UncheckedIOException(e);
		} finally {
			System.out.println("end to handle.");
		}

	}

	private static void handle_out_2(Socket socket, String str) {
		OutputStream ops = null;
		try {
			ops = socket.getOutputStream();
			OutputStreamWriter opsw = new OutputStreamWriter(ops);
			BufferedWriter bw = new BufferedWriter(opsw);
			StringBuilder sb = new StringBuilder();
			sb.append("From server: " + str + "\r\n");
			bw.write(sb.toString());
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void handle_out(Socket socket) {
		OutputStream ops = null;
		try {
			ops = socket.getOutputStream();
			OutputStreamWriter opsw = new OutputStreamWriter(ops);
			BufferedWriter bw = new BufferedWriter(opsw);
			StringBuilder sb = new StringBuilder();
			sb.append("\r\n");
			bw.write(sb.toString());
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
