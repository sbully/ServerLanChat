import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.charset.Charset;

import static util.GestionSQL.GetConnexion;
import static util.GestionSQL.LoadDriver;
import static util.Mediator.Traitement;

public class ServerLanChat {

	static String url = "jdbc:mysql://localhost:3306/users";

	public static void main(String[] args) {

		try {
/*
			System.setProperty("sun.jnu.encoding", "UTF-8");
			System.setProperty( "file.encoding", "UTF-8" );
			System.out.println(System.getProperty("sun.jnu.encoding"));
			System.out.println(System.getProperty("file.encoding"));

			
			OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
	        String enc = writer.getEncoding();
	        System.out.println("Default Charset=" + Charset.defaultCharset());
	        //System.setProperty("file.encoding", "Latin-1");
	        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
	        System.out.println("Default Charset=" + Charset.defaultCharset());
	        System.out.println("Default Charset in Use=" + enc);
			*/
			
			LoadDriver();
			GetConnexion(url, "Users", "Connect");
			DatagramSocket server = new DatagramSocket(2345);
			while (true) {

				byte[] buffer = new byte[2048];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("--Attente d'un message--");
				server.receive(packet);
				System.out.println("--Message reçu--");

				// String receivePack = new String(packet.getData()).trim();
				byte[] data = packet.getData();
				/*for(byte b : data) {
					System.out.println(b+"-");
				}*/

			/*	String str = new String(datas, "UTF-8");
				byte[] data = str.getBytes(Charset.forName("UTF-8"));*/
				InetAddress adressebroad = packet.getAddress();

				Traitement(data, adressebroad);

				/*
				 * String adresse = packet.getAddress().toString();
				 * 
				 * String str = adresse +" "+ receivePack;
				 * 
				 * System.out.println(str);
				 * 
				 * System.out.println("--Renvoi du message--"); DatagramSocket client = new
				 * DatagramSocket(); InetAddress adressebroad =
				 * InetAddress.getByName("192.168.221.255"); int port = 1000; byte[] data =
				 * str.getBytes(); DatagramPacket dataPack = new DatagramPacket(data,
				 * data.length, adressebroad, port); dataPack.setData(data);
				 * client.send(dataPack);
				 */
				Thread.sleep(10);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
