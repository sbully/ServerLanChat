package util;

import static util.GestionSQL.SelectFromConnectTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Rom extends Modules {

	static List<String> GeneralUser = new ArrayList<String>();
	static List<String> RoomAlphaUser = new ArrayList<String>();
	static List<String> RoomBetaUser = new ArrayList<String>();
	static List<String> RoomGammaUser = new ArrayList<String>();

	static Map<String, List<String>> ListRoomUser = new HashMap<String, List<String>>();

	public Rom(Modules _next) {
		this.next = null;
		RoomAlphaUser.add("Tete");
		RoomAlphaUser.add("Tutu");
		RoomAlphaUser.add("Titi");
		RoomAlphaUser.add("Tata");
		RoomAlphaUser.add("Toto");
		ListRoomUser.put("General", GeneralUser);
		ListRoomUser.put("RoomAlpha", RoomAlphaUser);
		ListRoomUser.put("RoomBeta", RoomBetaUser);
		ListRoomUser.put("RoomGamma", RoomGammaUser);

	}

	public Boolean Update(PackMessage message) {

		switch (message.messageReceive.header) {
		case ROOM_LIST:
			try {
				ListRoomUser.get("General").clear();
				ListRoomUser.get("General").addAll(SqlRequeteUser());
				message.messageReceive.Msg = new ObjectMapper().writerWithDefaultPrettyPrinter()
						.writeValueAsString(ListRoomUser);
				message.messageReceive.To = message.messageReceive.From;
				message.messageReceive.From="Server";
				System.out.println(message);

				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		case ROOM_QUIT:

			return true;
		case ROOM_POST:

			return true;
		case ROOM_JOIN:

			return true;

		default:

			break;

		}

		return false;
	}

	public List<String> SqlRequeteUser() {

		ResultSet result = SelectFromConnectTable();
		List<String> listconnected = new ArrayList<String>();
		try {
			while (result.next()) {
				// String pseudo = result.getString("Pseudo");
				listconnected.add(result.getString("Pseudo"));
			}
			return listconnected;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public Boolean Update(PackMessage message) {
	 * 
	 * switch (message.messageReceive.header) { case ROOM_LIST: try {
	 * message.messageReceive.Msg = new ObjectMapper().writeValueAsString(listRoom);
	 * System.out.println(message); return true; } catch(Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * case ROOM_QUIT:
	 * SelectList(message.messageReceive.To).remove(message.messageReceive.From);
	 * 
	 * return true; case ROOM_POST:
	 * 
	 * return true; case ROOM_JOIN:
	 * 
	 * SelectList(message.messageReceive.To).add(message.messageReceive.From);
	 * 
	 * return true;
	 * 
	 * 
	 * default:
	 * 
	 * break;
	 * 
	 * }
	 * 
	 * return false; }
	 * 
	 * private List<String> SelectList(String _to) { switch (_to) { case "Alpha":
	 * return RoomAlpha;
	 * 
	 * case "Beta": return RoomBeta;
	 * 
	 * case "Gamma": return RoomGamma;
	 * 
	 * default: return null;
	 * 
	 * }
	 * 
	 * }
	 */
}
