/**
 * ContactsService4JSONImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms.contacts;

public class ContactsService4JSONImplServiceLocator extends org.apache.axis.client.Service implements com.lw.common.ema.jwsserver.sms.contacts.ContactsService4JSONImplService {

    public ContactsService4JSONImplServiceLocator() {
    }


    public ContactsService4JSONImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ContactsService4JSONImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ContactsService4JSONImplPort
    private java.lang.String ContactsService4JSONImplPort_address = "http://127.0.0.1:8080/ctc-emassh60/webService/contactsOper";

    public java.lang.String getContactsService4JSONImplPortAddress() {
        return ContactsService4JSONImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ContactsService4JSONImplPortWSDDServiceName = "ContactsService4JSONImplPort";

    public java.lang.String getContactsService4JSONImplPortWSDDServiceName() {
        return ContactsService4JSONImplPortWSDDServiceName;
    }

    public void setContactsService4JSONImplPortWSDDServiceName(java.lang.String name) {
        ContactsService4JSONImplPortWSDDServiceName = name;
    }

    public com.lw.common.ema.jwsserver.sms.contacts.IContactsService4JSON getContactsService4JSONImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ContactsService4JSONImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getContactsService4JSONImplPort(endpoint);
    }

    public com.lw.common.ema.jwsserver.sms.contacts.IContactsService4JSON getContactsService4JSONImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lw.common.ema.jwsserver.sms.contacts.ContactsService4JSONImplServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.sms.contacts.ContactsService4JSONImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getContactsService4JSONImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setContactsService4JSONImplPortEndpointAddress(java.lang.String address) {
        ContactsService4JSONImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lw.common.ema.jwsserver.sms.contacts.IContactsService4JSON.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lw.common.ema.jwsserver.sms.contacts.ContactsService4JSONImplServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.sms.contacts.ContactsService4JSONImplServiceSoapBindingStub(new java.net.URL(ContactsService4JSONImplPort_address), this);
                _stub.setPortName(getContactsService4JSONImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ContactsService4JSONImplPort".equals(inputPortName)) {
            return getContactsService4JSONImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://contacts.jwsserver.server.ema.ctc.com/", "ContactsService4JSONImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://contacts.jwsserver.server.ema.ctc.com/", "ContactsService4JSONImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ContactsService4JSONImplPort".equals(portName)) {
            setContactsService4JSONImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
