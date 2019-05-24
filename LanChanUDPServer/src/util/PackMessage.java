package util;

import java.net.*;
import java.nio.charset.Charset;
import java.text.DateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;


public class PackMessage {

	Message messageReceive;
	byte[] Databyte;
	String IpSender;

	public PackMessage(byte[] _dataByte, InetAddress _ipSender) {
		this.Databyte = _dataByte;
		this.messageReceive = JsonDeserie(_dataByte);
		this.IpSender = _ipSender.toString().substring(1,_ipSender.toString().length());
	}

	private Message JsonDeserie(byte[] _databyte) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			
			String str = new String(_databyte,"UTF-8");			
			byte[] data = str.getBytes(Charset.forName("UTF-8"));
			Message messRecep = mapper.readValue(data, Message.class);
			
			
			//Message messRecep = mapper.readValue(_databyte, Message.class);
			/*if(messRecep.Msg !=null || messRecep.Msg.length()!= 0) {
			byte[] data = messRecep.Msg.getBytes(Charset.forName("UTF-8"));
			messRecep.Msg = new String(data,"UTF-8");
			}*/
			return messRecep;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {

		DateFormat shortDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		StringBuffer StrBuff = new StringBuffer();
		StrBuff.append("Ip: ").append(this.IpSender).append(" date: ")
				.append(shortDate.format(this.messageReceive.date)).append(" header: ").append(this.messageReceive.header).append(" token: ").append(this.messageReceive.token)
				.append(" > From: ").append(this.messageReceive.From);

		if (this.messageReceive.To != null && !this.messageReceive.To.isEmpty()) {
			StrBuff.append(" (").append(this.messageReceive.To).append(")");
		}

		StrBuff.append(": ").append(this.messageReceive.Msg);

		return StrBuff.toString();

	}
	
	public String JsonSerie() {
		try {
		String JsonString = new ObjectMapper().writeValueAsString(this.messageReceive);
		return JsonString;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
