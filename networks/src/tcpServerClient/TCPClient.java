package tcpServerClient;

import java.io.*;
import java.net.*;

public class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		int port = 6000;
		
		System.out.println("Client: Client started");
		Socket client = new Socket(host, port);
		
		DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
		outputStream.writeUTF("Client Side Handshake");
		
		InputStream input = null;
		input = client.getInputStream();
		DataInputStream inputStream = new DataInputStream(input);
		
		System.out.println("Client: Server Side Handshake");
		System.out.println("Client: receiving " + inputStream.readUTF());
		
		inputStream.close();
		outputStream.close();
		client.close();
	}

}
