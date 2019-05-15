package util;

abstract public class Modules {

	protected Modules next;
	
	/*public Boolean Update(Message message)
	{
		
		
		if(next != null)
		{
			return next.Update(message);
		}
		
		return true;
	}*/
	abstract Boolean Update(PackMessage _mess);
}
