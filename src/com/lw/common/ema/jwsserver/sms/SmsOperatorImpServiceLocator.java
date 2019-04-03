/**
 * SmsOperatorImpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms;

public class SmsOperatorImpServiceLocator extends org.apache.axis.client.Service implements com.lw.common.ema.jwsserver.sms.SmsOperatorImpService {

    public SmsOperatorImpServiceLocator() {
    }


    public SmsOperatorImpServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SmsOperatorImpServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SmsOperatorImpPort
    private java.lang.String SmsOperatorImpPort_address = "http://127.0.0.1:8080/ctc-emassh60/webService/smsOper";

    public java.lang.String getSmsOperatorImpPortAddress() {
        return SmsOperatorImpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SmsOperatorImpPortWSDDServiceName = "SmsOperatorImpPort";

    public java.lang.String getSmsOperatorImpPortWSDDServiceName() {
        return SmsOperatorImpPortWSDDServiceName;
    }

    public void setSmsOperatorImpPortWSDDServiceName(java.lang.String name) {
        SmsOperatorImpPortWSDDServiceName = name;
    }

    public com.lw.common.ema.jwsserver.sms.ISmsOperator getSmsOperatorImpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SmsOperatorImpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSmsOperatorImpPort(endpoint);
    }

    public com.lw.common.ema.jwsserver.sms.ISmsOperator getSmsOperatorImpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lw.common.ema.jwsserver.sms.SmsOperatorImpServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.sms.SmsOperatorImpServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSmsOperatorImpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSmsOperatorImpPortEndpointAddress(java.lang.String address) {
        SmsOperatorImpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lw.common.ema.jwsserver.sms.ISmsOperator.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lw.common.ema.jwsserver.sms.SmsOperatorImpServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.sms.SmsOperatorImpServiceSoapBindingStub(new java.net.URL(SmsOperatorImpPort_address), this);
                _stub.setPortName(getSmsOperatorImpPortWSDDServiceName());
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
        if ("SmsOperatorImpPort".equals(inputPortName)) {
            return getSmsOperatorImpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "SmsOperatorImpService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sms.jwsserver.server.ema.ctc.com/", "SmsOperatorImpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SmsOperatorImpPort".equals(portName)) {
            setSmsOperatorImpPortEndpointAddress(address);
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
