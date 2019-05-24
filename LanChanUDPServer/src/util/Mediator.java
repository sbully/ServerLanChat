package util;

import java.net.*;
import java.nio.charset.Charset;


public class Mediator {

	private static Modules module = new Logs(new Auth(new Rom(null)));



	
	public static void Traitement(byte[] _receipMess, InetAddress IpSender) {
		try {
			PackMessage receivPack = new PackMessage(_receipMess, IpSender);
			System.out.println(receivPack);
			
			Boolean test= module.Update(receivPack);
			//receivPack.messageReceive.From= "192.168.221.220";
			if(test) {
				
				System.out.println("--Renvoi du message--");
				
				DatagramSocket client = new DatagramSocket();
				InetAddress adresse = InetAddress.getByName("192.168.221.255");
				int port = 1000;
				
				//byte[] data = receivPack.JsonSerie().getBytes(Charset.forName("UTF-8"));
				byte[] data = receivPack.JsonSerie().getBytes();
				DatagramPacket dataPack = new DatagramPacket(data, data.length, adresse, port);
				dataPack.setData(data);
				client.send(dataPack);
			}else {
				
				System.out.println("Pas de renvoi");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
