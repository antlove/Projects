package glassfish;

import javax.naming.Context;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Context ctx = GlassFishJndiProvider.newInstance().getContext();
		
		ctx.lookup("defaultDatasource");

	}

}
