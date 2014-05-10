package org.mockejb.test;

import java.sql.SQLException;

import javax.ejb.*;
import javax.naming.NamingException;

/**
 * Business interface of the helper bean.
 * HelperBean uses local interface. 
 */
public interface SampleHelper extends EJBLocalObject {
    
    void insertData() throws NamingException, SQLException;

    String dummyMethod( String param );
    
    void throwSystemException();
   
   
}