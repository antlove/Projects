package org.mockejb.test;

import javax.ejb.*;
import org.apache.commons.logging.*;


/**
 * Base class for our session beans.
 * Provides implementation of the standard EJb methods.
 * Most methods simply logs the entry point. 
 * 
 * @author Alexander Ananiev
 */
public class BaseSessionBean implements SessionBean {
    
    // logger for this class
    private static Log logger = LogFactory.getLog( BaseSessionBean.class.getName() );
    
    protected SessionContext sessionCtx;
    
    
    protected void log(String message) {
        logger.debug(message);
    }
    
    /**
     * Logs the activation of the bean
     */
    public void ejbActivate() {
        log("ejbActivate called");
    }
    
    /**
     * Logs the removal of the bean
     */
    public void ejbRemove() {
        log("ejbRemove called");
    }
    
    /**
     * Logs the passivation of the bean
     */
    public void ejbPassivate() {
        log("ejbPassivate called");
    }
    
    /**
     * Sets the session context.
     *
     * @param sessionCtx SessionContext Context for session
     */
    public void setSessionContext(SessionContext sessionCtx) {
        log("setSessionContext called");
        this.sessionCtx = sessionCtx;
    }
    
    /**
     * Logs the creation of the bean
     */
    public void ejbCreate() throws CreateException {
        log("ejbCreate called");
    }

}