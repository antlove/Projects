package test;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import service.IUserService;

public class Main {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws RemoteException 
	 * @throws CreateException 
	 */
	public static void main(String[] args) throws NamingException, RemoteException, CreateException {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		properties.put(Context.PROVIDER_URL, "t3://localhost:7001");

		Context context = new InitialContext(properties);
		Object UserServiceResource = context.lookup("UserServiceBean#service/IUserService"); // Í¨¹ýejbµÄJNDI
		
		IUserService userService = (IUserService) UserServiceResource;
		
		
		String str = userService.getUserName();
		
		System.out.println(str);
//		IUserService userService = UserServiceHome.create();
		
//		System.out.println(userService.getUserName());
															
	}

}
