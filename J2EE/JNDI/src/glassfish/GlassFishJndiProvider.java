package glassfish;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class GlassFishJndiProvider {
	private static GlassFishJndiProvider glassFishJndiProvider = new GlassFishJndiProvider();

	private GlassFishJndiProvider() {

	}

	public static GlassFishJndiProvider newInstance() {
		return glassFishJndiProvider;
	}

	public Context getContext() {
		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial",
				"com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost"); // host
		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700"); // EJB Port
		Context ctx;
		try {
			ctx = new InitialContext(props);
			return ctx;
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
