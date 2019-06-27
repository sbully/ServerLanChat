package util;

import java.text.DateFormat;
import java.util.Date;

public class Message {

	public MessageHeader header;
	public Date date;
	public String token;
	public String From;
	public String To;
	public String msg;
	
	public Message() {
		header = MessageHeader.AUTH;
        date = new Date();
	}
	

	public Message(String token, String from) {
		this.date = new Date();
		this.token = token;
		From = from;
	}

	@Override
	public String toString() {
		DateFormat shortDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		StringBuffer StrBuff = new StringBuffer();
		StrBuff.append(shortDate.format(date)).append(" > ").append(From);

		if(To !=null && !To.isEmpty()) {
			StrBuff.append(" (").append(To).append("]");
			
		}
		
		StrBuff.append(": ").append(msg);
		
		return StrBuff.toString();
	}
	

	public String toLogs() {

		DateFormat shortDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		StringBuffer StrBuff = new StringBuffer();
		StrBuff.append(shortDate.format(date)).append("header: ").append(header).append(" token: ").append(token).append(" > ").append(From);

		if(To !=null && !To.isEmpty()) {
			StrBuff.append(" (").append(To).append("]");
		}
		
		StrBuff.append(": ").append(msg);
		
		return StrBuff.toString();
		
	}
	
	
	
}
