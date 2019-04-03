/**
 * SmsOperatorImpInterServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.smsinter;

public class SmsOperatorImpInterServiceLocator extends org.apache.axis.client.Service implements com.lw.common.ema.jwsserver.smsinter.SmsOperatorImpInterService {

    public SmsOperatorImpInterServiceLocator() {
    }


    public SmsOperatorImpInterServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SmsOperatorImpInterServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SmsOperatorImpInterPort
    private java.lang.String SmsOperatorImpInterPort_address = "http://127.0.0.1:8080/ctc-emassh60/webService/smsOperInter";

    public java.lang.String getSmsOperatorImpInterPortAddress() {
        return SmsOperatorImpInterPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SmsOperatorImpInterPortWSDDServiceName = "SmsOperatorImpInterPort";

    public java.lang.String getSmsOperatorImpInterPortWSDDServiceName() {
        return SmsOperatorImpInterPortWSDDServiceName;
    }

    public void setSmsOperatorImpInterPortWSDDServiceName(java.lang.String name) {
        SmsOperatorImpInterPortWSDDServiceName = name;
    }

    public com.lw.common.ema.jwsserver.smsinter.ISmsOperatorInter getSmsOperatorImpInterPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SmsOperatorImpInterPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSmsOperatorImpInterPort(endpoint);
    }

    public com.lw.common.ema.jwsserver.smsinter.ISmsOperatorInter getSmsOperatorImpInterPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lw.common.ema.jwsserver.smsinter.SmsOperatorImpInterServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.smsinter.SmsOperatorImpInterServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSmsOperatorImpInterPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSmsOperatorImpInterPortEndpointAddress(java.lang.String address) {
        SmsOperatorImpInterPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lw.common.ema.jwsserver.smsinter.ISmsOperatorInter.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lw.common.ema.jwsserver.smsinter.SmsOperatorImpInterServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.smsinter.SmsOperatorImpInterServiceSoapBindingStub(new java.net.URL(SmsOperatorImpInterPort_address), this);
                _stub.setPortName(getSmsOperatorImpInterPortWSDDServiceName());
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
        if ("SmsOperatorImpInterPort".equals(inputPortName)) {
            return getSmsOperatorImpInterPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "SmsOperatorImpInterService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "SmsOperatorImpInterPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SmsOperatorImpInterPort".equals(portName)) {
            setSmsOperatorImpInterPortEndpointAddress(address);
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
