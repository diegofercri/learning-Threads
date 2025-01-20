package urlClasses;

import java.net.*;
import java.io.*;

public class Ejemplo2URL {
  public static void main(String[] args) {
	URL url=null;
	//URL url2=null;
	try {
		url = new URL("http://www.elaltozano.es"); //www.example.com
		//url2= new URI("http://www.eldaltozano.es").toURL();
		
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} /*
		 * catch (URISyntaxException e) { e.printStackTrace();
		 
	}*/
		
	BufferedReader in;
	try {
		InputStream inputStream = url.openStream();
		in = new BufferedReader(new 
                               InputStreamReader(inputStream));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();

	} 
	catch (UnknownHostException e) {System.out.println("Probablemente no existe la página");}
	catch (IOException e) {e.printStackTrace();}
  }//
}//Ejemplo2URL
