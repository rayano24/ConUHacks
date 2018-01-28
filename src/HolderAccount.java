import java.util.Hashtable;

public class HolderAccount {
	
	public static Hashtable<String, Integer> hm = new Hashtable<String, Integer>();
	
	public static void AccountGenerator(String emailAddress, int pinStore) {				
		hm.put(emailAddress,pinStore);  	
	}


}
