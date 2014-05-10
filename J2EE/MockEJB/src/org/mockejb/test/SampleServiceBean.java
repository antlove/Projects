package org.mockejb.test;

import javax.ejb.*;
import javax.naming.*;
import java.util.*;

import java.rmi.RemoteException;
import java.security.Principal;
import java.sql.*;

import javax.sql.DataSource;

import javax.jms.*;

import org.apache.commons.logging.*;


/**
 * Sample EJB with methods to access other beans and JDBC.
 * 
 * @ejb.bean type="Stateless" view-type="remote" name="SampleService" 
 *      jndi-name="mockejb/SampleService"
 * 
 * @ejb.ejb-ref ejb-name="SampleHelper" view-type="local" ref-name="ejb/sampleHelper"
 * @ejb.resource-ref  res-ref-name="jdbc/SampleDataSource"  res-type="javax.sql.DataSource"  res-auth="Container" jndi-name="jdbc/SampleDataSource"
 * 
 */
public class SampleServiceBean extends  BaseSessionBean  {

    /**
     * JNDI name used to access HelperBean. We assume that ejb-ref with 
     * this name is configured in the deployment descriptor if this bean
     * runs in the app server. 
     */
    public final static String HELPER_BEAN_JNDI_NAME = "java:comp/env/ejb/sampleHelper";
    
    // logger for this class
    private static Log logger = LogFactory.getLog( SampleServiceBean.class.getName() );
    

   /**
    * Simple method which returns an input parameter back to the caller.
    * 
     * @ejb.interface-method
     * @ejb:transaction type="Supports" 
    */
    public String echoString( String input ) {
        
        return input;
        
    }

    /**
     * Calls a method of {@link SampleHelper}.
     * Demonstrates the use of mock JNDI to lookup the beans.
     *   
     * @return a string returned by SampleHelper
     * @throws NamingException
     * @throws CreateException
     *
     * @ejb.interface-method
     * @ejb:transaction type="Supports" 
     */
    public String invokeOtherBean() throws NamingException, CreateException {

        Context ctx = new InitialContext();

        SampleHelperHome helperHome = 
            (SampleHelperHome)ctx.lookup( HELPER_BEAN_JNDI_NAME );
        
        SampleHelper  helperBean = helperHome.create();
        
        return helperBean.dummyMethod( "some value");
    }

    /**
     * 
     * @ejb.interface-method
     * @ejb:transaction type="Supports" 
     */
    public void invokeExternalService() throws NamingException, CreateException, RemoteException {

        Context ctx = new InitialContext();

        ExternalServiceHome externalServiceHome = 
            (ExternalServiceHome)ctx.lookup( ExternalService.JNDI_NAME );
        
        ExternalService  externalService = externalServiceHome.create();
        
        /*
         * In this example we don't do anything with the the return value,  
         * in reality we should have some code that uses it.
         */        
        externalService.sampleMethod();
           
    }

    
    /** 
     * Sends the message with the provided message text to the test topic 
     * @param message message text to send
     * @throws NamingException
     * @throws JMSException
     *
     * @ejb.interface-method
     * @ejb:transaction type="Supports" 
     */
    public void sendMessage( String message ) throws NamingException, JMSException{

        InitialContext ctx = new InitialContext();

        TopicConnectionFactory topicConnectionFactory = 
            (TopicConnectionFactory) ctx.lookup("SampleConnectionFactory");
        TopicConnection topicConnection =  topicConnectionFactory.createTopicConnection();
        TopicSession topicSession = topicConnection.createTopicSession(false, 
            Session.AUTO_ACKNOWLEDGE);

        Topic sampleTopic =
            (Topic) ctx.lookup("SampleTopic");
        TopicPublisher publisher = topicSession.createPublisher( sampleTopic );
        // create the message
        TextMessage textMessage = topicSession.createTextMessage();
        textMessage.setText( message );

        // post the message to the topic
        publisher.publish( textMessage );
        
    }


    
    /**
     * Retrieves all values for the column from the provided table
     * @param columnName column name to select from
     * @param tableName table name to select from
     * @return collection of retrieved values
     * 
     * @ejb.interface-method
     * @ejb:transaction type="Required"
     */
    public Collection selectFromTable( String tableName, String columnName ) 
        throws NamingException, SQLException {

        Context ctx = new InitialContext();
        
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/SampleDataSource");
        
        java.sql.Connection con = ds.getConnection();
        
        log("Connected.");
                             
        Statement stmt =con.createStatement();
        ResultSet rs = stmt.executeQuery("select "+columnName+" from "+tableName);
        
        Collection collection = new ArrayList();
        while (rs.next()) {
            String s = rs.getString(1);
            collection.add(s);
            //log(s);
        }
        
        con.close();
        
        return collection;
    }
    
    /**
     * Example of using SessionContext to rollback 
     * a database transaction
     * @ejb.interface-method 
     * @ejb.transaction type="Required"
     *
     */
    public void rollbackSampleTransaction() throws NamingException, 
        SQLException, CreateException {

        
        Context ctx = new InitialContext();
        
        SampleHelperHome helperBeanHome = 
            (SampleHelperHome)ctx.lookup( HELPER_BEAN_JNDI_NAME );
        SampleHelper  helperBean = helperBeanHome.create();
        
        helperBean.insertData();
        
        sessionCtx.setRollbackOnly();
        
    }
    
    /**
     * 
     * @ejb.interface-method
     * @ejb:transaction type="Supports" 
     */
    public void throwSystemException() throws NamingException, CreateException, RuntimeException {

        Context ctx = new InitialContext();
        SampleHelperHome helperHome = 
            (SampleHelperHome)ctx.lookup( HELPER_BEAN_JNDI_NAME );
        SampleHelper helperBean = helperHome.create();
        helperBean.throwSystemException();        
    } 
    /**
     * 
     * @ejb.interface-method
     * @ejb:transaction type="Supports" 
     */
    public void throwAppException() throws Exception {
        throw new Exception("Example of application exception");
    }

    
    /**
     * Returns the principal provided by the "getCallerPrincipal" method of 
     * the EJBContext for this bean.
     * 
     * @return current principal
     * 
     * @ejb.interface-method
     */
    public Principal getPrincipal(){
        
        return sessionCtx.getCallerPrincipal();
        
    }
    
    /**
     * Returns the result of the EJBContext.isCallerInRole check.
     * @param name role name
     * @return true if the current user has the given role
     * 
     * @ejb.interface-method
     */
    public boolean hasRole( String role ){

        return sessionCtx.isCallerInRole( role );
        
    }
    
    
}