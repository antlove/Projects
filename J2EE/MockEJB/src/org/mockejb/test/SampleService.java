package org.mockejb.test;

import javax.ejb.CreateException;
import javax.ejb.EJBObject;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.util.Collection;
import java.security.Principal;
import java.sql.SQLException;
import java.rmi.RemoteException;

/**
 * Business interface of the sample bean.
 * You can also use local interface with mockejb. 
 */
public interface SampleService extends EJBObject {
    
    public final static String JNDI_NAME = "mockejb/SampleService";
    
    
    String echoString( String input )  throws RemoteException;

    String invokeOtherBean() throws NamingException, CreateException, RemoteException;   

    void invokeExternalService() throws NamingException, CreateException, RemoteException;   


    /** 
     * Sends the message with the provided message text to the test topic 
     * @param message message text to send
     * @throws NamingException
     * @throws JMSException
     */
    void sendMessage( String message ) throws NamingException, JMSException, RemoteException;


    /**
     * Retrieves all values for the column from the provided table
     * @param columnName column name to select from
     * @param tableName table name to select from
     * @return collection of retrieved values 
     */
    Collection selectFromTable( String tableName, String columnName) 
        throws NamingException, SQLException, RemoteException;
   
    /**
     * Add a record to the table and rolls back the transaction
     */
    void rollbackSampleTransaction() throws NamingException, SQLException, 
        CreateException, RemoteException; 

    void throwSystemException() throws NamingException, CreateException, RemoteException;

    void throwAppException() throws RemoteException, Exception;

    // Methods for security testing
    
    /**
     * Returns the result of the EJBContext.isCallerInRole check.
     * @param name role name
     * @return true if the current user has the given role
     */
    boolean hasRole( String role );
    
    /**
     * Returns the principal provided by the "getCallerPrincipal" method of 
     * the EJBContext for this bean.
     * 
     * @return current principal
     */
    Principal getPrincipal();
}