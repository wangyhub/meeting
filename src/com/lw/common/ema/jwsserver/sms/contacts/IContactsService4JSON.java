/**
 * IContactsService4JSON.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms.contacts;

public interface IContactsService4JSON extends java.rmi.Remote {
    public java.lang.String modifyPerson(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String addPerson(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String modifyGroup(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String deletePersonByGroupId(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String deletePerson(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String addGroup(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String deleteGroup(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String loadPerson(java.lang.String arg0) throws java.rmi.RemoteException;
}
