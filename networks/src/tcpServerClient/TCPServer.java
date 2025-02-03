package tcpServerClient;

import java.io.*;
import java.net.*;

public class TCPServer {

	public static void main(String[] args) throws IOException {
		
		int port = 6000; 
		ServerSocket server = new ServerSocket(port);
		Socket client = null;
		System.out.println("Server: Waiting for Client");
		client = server.accept();
		
		InputStream input = null;
		input = client.getInputStream();
		DataInputStream inputStream = new DataInputStream(input);
		
		System.out.println("Server: Client Side Handshake");
		System.out.println("Server: receiving " + inputStream.readUTF());
		
		OutputStream output = null;
		output = client.getOutputStream();
		DataOutputStream outputStream = new DataOutputStream(output);
		outputStream.writeUTF("Server Side Handshake");

		inputStream.close();
		outputStream.close();
		server.close();
	}

}