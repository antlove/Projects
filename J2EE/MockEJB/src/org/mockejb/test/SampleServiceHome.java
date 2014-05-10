package org.mockejb.test;

import javax.ejb.CreateException;
import java.rmi.RemoteException;

/**
 * Home interface for SampleSessionBean. 
 */
public interface SampleServiceHome extends javax.ejb.EJBHome {

   public SampleService create() throws CreateException, RemoteException;
   

}