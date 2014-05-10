package org.mockejb.test;

/**
 * This is the example of the "external service" interface. 
 * This service is not part of the unit that we're testing, 
 * so we don't want to depend on it. Therefore, instead of deploying a
 * real "ExternalService" EJB, we want to provide its mock implementation which 
 * always return known test data. This implementation is provided as the nested class
 * <code>MockEjbTest.ExternalServiceMockBean</code>  
 *  
 * @author Alexander Ananiev
 */
public interface ExternalService {

    public final static String JNDI_NAME = "mockejb/ExternalService";
    
    String sampleMethod();
    

}