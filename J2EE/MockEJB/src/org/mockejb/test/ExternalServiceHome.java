package org.mockejb.test;

import javax.ejb.*;

/**
 * Home interface of the ExternalService bean.  
 * 
 * @author Alexander Ananiev
 */
public interface ExternalServiceHome extends EJBHome {

    public ExternalService create() throws CreateException;

}