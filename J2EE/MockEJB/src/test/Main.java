package test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		properties.put(Context.PROVIDER_URL, "t3://localhost:7001");

		Context context = new InitialContext(properties);
		Object ref = context.lookup("UserServiceResource"); // Í¨¹ýejbµÄJNDI
		
		
		
		System.out.println(ref);
															
	}

}
