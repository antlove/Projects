package org.mockejb.test;

import javax.ejb.*;

/**
 * Home interface for SampleHelper. 
 */
public interface SampleHelperHome extends EJBLocalHome {

   public SampleHelper create() throws CreateException;

}