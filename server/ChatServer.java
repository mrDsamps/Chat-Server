package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
	static ServerSocket serverSocket;
	static int portnumber = 4444;
	static ArrayList<ClientThread> clients;
	public static void main(String [] args) {
	
		
		
		serverSocket = null;
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(portnumber);
			acceptClients();
			
		} catch (IOException e) {
			
			System.err.println("Could not listen on port number: "+portnumber);
			System.exit(1);
		}
	}
	
	public static void acceptClients() {
		
		clients = new ArrayList<ClientThread>();
		while (true) {
			
			try {
				
				Socket socket = serverSocket.accept();
				ClientThread client = new ClientThread(socket);
				Thread thread = new Thread(client);
				thread.start();
				clients.add(client);
			} catch (IOException e) {
				
				System.out.println("Accept failed on: "+portnumber);
			}
		}
	}

}
