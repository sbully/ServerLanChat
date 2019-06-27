package util;

import static util.GestionSQL.PrepareSelectUtilTable;
import static util.GestionSQL.deleteFromConnectTable;
import static util.GestionSQL.insertToConnectTable;

import java.sql.ResultSet;

public class Auth extends Modules {

	public Auth(Modules _next) {
		this.next = _next;
	}

	public Boolean Update(PackMessage message) {
		System.out.println("--Module Auth--");

		
		switch (message.messageReceive.header) {
		case AUTH:
			try {
				ResultSet result = PrepareSelectUtilTable(message.messageReceive.From, message.messageReceive.token);
				if (!result.next()) {
					System.out.println("ERROR : Authentification");
					message.messageReceive.msg = "FALSE";
					message.messageReceive.To = message.messageReceive.From;
					message.messageReceive.From="Server";
					System.out.println(message);
					return true;

				} else {
					message.messageReceive.msg = "TRUE";
					//message.messageReceive.To = message.messageReceive.From;
					//message.messageReceive.From="Server";
					System.out.println(message);
					insertToConnectTable(message.messageReceive.From, message.messageReceive.token, message.IpSender);
					message.messageReceive.To = message.messageReceive.From;
					message.messageReceive.From="Server";
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case QUIT:
			System.out.println("-- User Disconnected");
			deleteFromConnectTable(message.messageReceive.From, message.messageReceive.token, message.IpSender);
			message.messageReceive.msg = "Disconnect";
			message.messageReceive.To = message.messageReceive.From;
			message.messageReceive.From="Server";
			return true;

		default:
			if (next != null) {
				return next.Update(message);
			}
		}
		return false;
	}
}
