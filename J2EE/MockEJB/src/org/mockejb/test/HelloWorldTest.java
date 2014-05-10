package org.mockejb.test;

import javax.rmi.PortableRemoteObject;
import javax.naming.*;

import junit.framework.TestCase;

import org.mockejb.*;
import org.mockejb.jndi.*;



/**
 * Simple MockEJB test case, the place to start exploring MockEJB.
 * Deploys a session bean and calls its method.
 * This class uses only the most basic features of MockEJB, please 
 * see other test classes in this package to learn about more advanced MockEJB capabilities.
 *
 * @author Alexander Ananiev
 */
public class HelloWorldTest extends TestCase {
    
        
    // State of this test case. These variables are initialized by setUp method
    private Context context;

        
    public HelloWorldTest(String name) {
        super(name);
    }
        
    /**
     * Deploys and creates EJBs needed for our tests.
     */    
    public void setUp() throws Exception {
        

        /* We need to set MockContextFactory as our JNDI provider.
         * This method sets the necessary system properties. 
         */
        MockContextFactory.setAsInitial();
        
        // create the initial context that will be used for binding EJBs
        context = new InitialContext( );
        
        // Create an instance of the MockContainer
        MockContainer mockContainer = new MockContainer( context );

        /* Create deployment descriptor of our sample bean.
         * MockEjb uses it instead of XML deployment descriptors
         */
        SessionBeanDescriptor sampleServiceDescriptor = 
            new SessionBeanDescriptor( SampleService.JNDI_NAME, 
            SampleServiceHome.class, SampleService.class, new SampleServiceBean() );
        // Deploy operation creates Home and binds it to JNDI
        mockContainer.deploy( sampleServiceDescriptor );

    }
    
    /**
     * Looks up sample EJB and calls its method
     */
    public void testHelloWorld() throws Exception {

        // Lookup the home
        Object sampleServiceHomeObj = context.lookup( SampleService.JNDI_NAME );
        
        // PortableRemoteObject does not do anything in MockEJB but it does no harm to call it
        SampleServiceHome sampleServiceHome = 
            (SampleServiceHome) PortableRemoteObject.narrow( sampleServiceHomeObj, 
                SampleServiceHome.class );
        
        // create the bean
        SampleService sampleService = sampleServiceHome.create();
        // call the method
        String result = sampleService.echoString("HelloWorld");
        // is the return value correct?
        assertEquals( "HelloWorld", result );
        
    }
        
}
