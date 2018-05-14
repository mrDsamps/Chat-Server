package client;

import java.io.IOException;
import java.net.Socket;

public class Client {
	
	public static void main (String [] args) {
		
		Socket socket = null;
		int portnumber = 4444;
		try {
			
			socket = new Socket("127.0.0.1",portnumber);
		} catch (IOException e) {
			
			System.err.println("Fatal connection error");
			e.printStackTrace();
		}
		
	}

}
