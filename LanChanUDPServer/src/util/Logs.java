package util;

import java.io.*;




public class Logs extends Modules {
	
	public Logs(Modules _next) {
		this.next = _next;
	}
	
	public Boolean Update(PackMessage message)
	{
		try {
		//Path file = Paths.get("src/ressources/Logs.txt");
		
		FileWriter fw = new FileWriter("src/ressources/Logs.txt",true);
		fw.write(message.toString());
		fw.write(System.getProperty( "line.separator" ));
		fw.close();
		if(next != null)
		{
			return next.Update(message);
		}		
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
