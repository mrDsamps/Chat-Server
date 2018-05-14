package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
	
	private Socket socket;
	private String name;
	private BufferedReader serverIn;
	private BufferedReader userIn;
	private PrintWriter out;
	
	
	public ServerThread(Socket socket , String name) {
		
		this.socket = socket;
		this.name =name;
		run();
	}
public ServerThread(Socket socket ) {
		
		this.socket = socket;
		run();
	}
	
	public static void main (String [] args) {
		
		Socket socket = null;
		System.out.println("Input username");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		scan.close();
		int portnumber =4444;
		try {
			
			socket = new Socket("127.0.0.1",portnumber);
			Thread.sleep(1000);
			Thread server = new Thread(new ServerThread(socket));
			server.start();
		}catch (IOException e) {
			
			System.err.println("Fatal connection error");
			e.printStackTrace();
		}catch (InterruptedException e) {
			
			System.err.println("Fatal connection error");
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			userIn = new BufferedReader(new InputStreamReader(System.in));
			
			while(!socket.isClosed()) {
				
				if (serverIn.ready()) {
					
					String input = serverIn.readLine();
					if (input != null ) {
						
						System.out.println(input);
					}
				}
				
				if(userIn.ready()) {
					
					out.println(name+">"+userIn.readLine());
				}
			}
		
		}catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
