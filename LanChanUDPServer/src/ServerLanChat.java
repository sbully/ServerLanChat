import java.net.*;

import static util.GestionSQL.GetConnexion;
import static util.GestionSQL.LoadDriver;
import static util.Mediator.Traitement;



public class ServerLanChat {

	static String url = "jdbc:mysql://localhost:3306/users";
	
	public static void main(String[] args) {
		
		try {
			
			LoadDriver();
			GetConnexion(url,"Users","Connect");
			DatagramSocket server = new DatagramSocket(2345);
			while(true) {
				
				byte[] buffer = new byte[2048];
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				System.out.println("--Attente d'un message--");
				server.receive(packet);
				System.out.println("--Message reçu--");
				
				//String receivePack = new String(packet.getData()).trim();
				byte[] data = packet.getData();
				InetAddress adressebroad = packet.getAddress();
				
				
				Traitement(data,adressebroad);
				
				/*
				String adresse = packet.getAddress().toString();
				
				String str = adresse  +" "+ receivePack;
				
				System.out.println(str);
				
				System.out.println("--Renvoi du message--");
				DatagramSocket client = new DatagramSocket();
				InetAddress adressebroad = InetAddress.getByName("192.168.221.255");
				int port = 1000;
				byte[] data = str.getBytes();
				DatagramPacket dataPack = new DatagramPacket(data, data.length, adressebroad, port);
				dataPack.setData(data);
				client.send(dataPack);*/
				Thread.sleep(10);
			}
		}catch(Exception e) {
			
			e.printStackTrace();	
			
		}
	}

}
