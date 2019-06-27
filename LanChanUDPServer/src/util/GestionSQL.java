package util;

import java.sql.*;

public final class GestionSQL {

	static Connection connectSQL;

	public static void LoadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {

			System.out.println("ERROR");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	public static Connection GetConnexion(String _url, String _util, String _pass) {

		try {
			connectSQL = DriverManager.getConnection(_url, _util, _pass);
			System.out.println("la connexion est établie");
			return connectSQL;

		} catch (Exception e) {
			System.out.println("la connexion a échoué");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}
	}

	/*public void UpdateTable() {
		try {
			Statement stm = con.createStatement();
			stm.executeUpdate("INSERT INTO adherent VALUES(8,'BULLY','Sebastien', '6 rue Des paum�s')");
			stm.close();
		} catch (Exception e) {
			System.out.println("ECHEC de l'ajout");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}*/

	/*public ResultSet SelectTable(String req) {

		// String req = "SELECT "+_champSelect+" FROM "+ _table;

		try {
			Statement stm = con.createStatement();
			ResultSet resultat = stm.executeQuery(req);
			// stm.close();
			System.out.println("SELECTION Réussi");
			return resultat;

		} catch (Exception e) {
			System.out.println("ECHEC de la SELECTION");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}
	}*/
/*
	public static ResultSet PrepareSelectConnectTable(String _pseudo) {

		try {
			PreparedStatement stm = connectSQL.prepareStatement("SELECT Pseudo, Token, Ip FROM userconnect WHERE Pseudo=?");
			stm.setString(1, _pseudo);
			ResultSet rs = stm.executeQuery();
			return rs;

		} catch (Exception e) {
			System.out.println("ECHEC de la mise a jour");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}

	}
*/
	public static ResultSet PrepareSelectUtilTable(String _pseudo, String _pass) {

		try {
			PreparedStatement stm = connectSQL.prepareStatement("SELECT Pseudo, Passwrd FROM utilisateurs WHERE Pseudo=? AND Passwrd=? ");
			stm.setString(1, _pseudo);
			stm.setString(2, _pass);
			ResultSet rs = stm.executeQuery();
			return rs;

		} catch (Exception e) {
			System.out.println("ECHEC de la mise a jour");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}

	}

	public static void insertToConnectTable(String _pseudo, String _token, String _Ip) {
		try {
			PreparedStatement stm = connectSQL.prepareStatement("INSERT INTO userconnect (Pseudo,Token,Ip) Value(?,?,?) ON DUPLICATE KEY UPDATE Ip=?");
			stm.setString(1, _pseudo);
			stm.setString(2, _token);
			stm.setString(3, _Ip);
			stm.setString(4, _Ip);
			stm.executeUpdate();

		} catch (Exception e) {
			System.out.println("ECHEC de l'ajout");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

	}

	public static void deleteFromConnectTable(String _pseudo, String _token, String _Ip) {
		try {
			PreparedStatement stm = connectSQL.prepareStatement("DELETE FROM userconnect WHERE Pseudo=? AND Token=? AND Ip=?");
			stm.setString(1, _pseudo);
			stm.setString(2, _token);
			stm.setString(3,_Ip);
			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static ResultSet SelectFromConnectTable() {
		try {
			PreparedStatement stm = connectSQL.prepareStatement("SELECT Pseudo FROM userconnect");
			return stm.executeQuery();	
		}catch(Exception e) {
			e.printStackTrace();
			return null;	
		}
	
	}

}
