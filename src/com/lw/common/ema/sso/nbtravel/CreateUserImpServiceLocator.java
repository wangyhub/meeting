/**
 * CreateUserImpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.sso.nbtravel;

public class CreateUserImpServiceLocator extends org.apache.axis.client.Service implements com.lw.common.ema.sso.nbtravel.CreateUserImpService {

    public CreateUserImpServiceLocator() {
    }


    public CreateUserImpServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CreateUserImpServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CreateUserImpPort
    private java.lang.String CreateUserImpPort_address = "http://180.168.192.126:13080/ctc-emassh60/webService/nbtravelCreateUser";

    public java.lang.String getCreateUserImpPortAddress() {
        return CreateUserImpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CreateUserImpPortWSDDServiceName = "CreateUserImpPort";

    public java.lang.String getCreateUserImpPortWSDDServiceName() {
        return CreateUserImpPortWSDDServiceName;
    }

    public void setCreateUserImpPortWSDDServiceName(java.lang.String name) {
        CreateUserImpPortWSDDServiceName = name;
    }

    public com.lw.common.ema.sso.nbtravel.ICreateUser getCreateUserImpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CreateUserImpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCreateUserImpPort(endpoint);
    }

    public com.lw.common.ema.sso.nbtravel.ICreateUser getCreateUserImpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lw.common.ema.sso.nbtravel.CreateUserImpServiceSoapBindingStub _stub = new com.lw.common.ema.sso.nbtravel.CreateUserImpServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCreateUserImpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCreateUserImpPortEndpointAddress(java.lang.String address) {
        CreateUserImpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lw.common.ema.sso.nbtravel.ICreateUser.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lw.common.ema.sso.nbtravel.CreateUserImpServiceSoapBindingStub _stub = new com.lw.common.ema.sso.nbtravel.CreateUserImpServiceSoapBindingStub(new java.net.URL(CreateUserImpPort_address), this);
                _stub.setPortName(getCreateUserImpPortWSDDServiceName());
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
        if ("CreateUserImpPort".equals(inputPortName)) {
            return getCreateUserImpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://nbtravel.sso.ema.ctc.com/", "CreateUserImpService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://nbtravel.sso.ema.ctc.com/", "CreateUserImpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CreateUserImpPort".equals(portName)) {
            setCreateUserImpPortEndpointAddress(address);
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
