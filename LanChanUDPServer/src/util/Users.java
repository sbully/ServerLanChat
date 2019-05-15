package util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import static util.GestionSQL.SelectFromConnectTable;

public class Users extends Modules {
	static List<String> listConnect = new ArrayList<String>();

	public Users(Modules _next) {
		this.next = _next;
	}

	public Boolean Update(PackMessage message) {

		System.out.println("Module Users");

		if (message.messageReceive.header == MessageHeader.USERS) {
			ResultSet result = SelectFromConnectTable();
			try {
				listConnect.clear();
				while (result.next()) {
					String pseudo = result.getString("Pseudo");
					
					listConnect.add(pseudo);
				}
				
				message.messageReceive.Msg = new ObjectMapper().writeValueAsString(listConnect);
				System.out.println(message);
				return true;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		return next.Update(message);
	}

}
