import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.charset.Charset;

import util.Mediator;

import static util.GestionSQL.GetConnexion;
import static util.GestionSQL.LoadDriver;

public class ServerLanChat {

	static String url = "jdbc:mysql://localhost:3306/users";
	private static String nameBdd= "Users";
	private static String mdpBdd = "Connect";
	private static DatagramPacket packet;
	private static DatagramSocket server;
	private static int Listenport =2345;
	private static Mediator mediator = new Mediator();

	public static void main(String[] args) {

		
		try {

			System.setProperty("sun.jnu.encoding", "UTF-8");
			System.setProperty( "file.encoding", "UTF-8" );
			/*System.out.println(System.getProperty("sun.jnu.encoding"));
			System.out.println(System.getProperty("file.encoding"));

			
			OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
	        String enc = writer.getEncoding();
	        System.out.println("Default Charset=" + Charset.defaultCharset());
	        //System.setProperty("file.encoding", "Latin-1");
	        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
	        System.out.println("Default Charset=" + Charset.defaultCharset());
	        System.out.println("Default Charset in Use=" + enc);*/
			
			
			LoadDriver();
			GetConnexion(url, nameBdd, mdpBdd);
			server = new DatagramSocket(Listenport);
			while (true) {

				byte[] buffer = new byte[2048];
				packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("--Attente d'un message--");
				server.receive(packet);
				System.out.println("--Message reçu--");


				byte[] data = packet.getData();

				InetAddress adressebroad = packet.getAddress();

				mediator.Traitement(data, adressebroad);

				Thread.sleep(10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

}
