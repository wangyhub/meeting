/**
 * MmsOperatorImpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.mms;

public class MmsOperatorImpServiceLocator extends org.apache.axis.client.Service implements com.lw.common.ema.jwsserver.mms.MmsOperatorImpService {

    public MmsOperatorImpServiceLocator() {
    }


    public MmsOperatorImpServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MmsOperatorImpServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MmsOperatorImpPort
    private java.lang.String MmsOperatorImpPort_address = "http://127.0.0.1:8080/ctc-emassh60/webService/mmsOper";

    public java.lang.String getMmsOperatorImpPortAddress() {
        return MmsOperatorImpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MmsOperatorImpPortWSDDServiceName = "MmsOperatorImpPort";

    public java.lang.String getMmsOperatorImpPortWSDDServiceName() {
        return MmsOperatorImpPortWSDDServiceName;
    }

    public void setMmsOperatorImpPortWSDDServiceName(java.lang.String name) {
        MmsOperatorImpPortWSDDServiceName = name;
    }

    public com.lw.common.ema.jwsserver.mms.IMmsOperator getMmsOperatorImpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MmsOperatorImpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMmsOperatorImpPort(endpoint);
    }

    public com.lw.common.ema.jwsserver.mms.IMmsOperator getMmsOperatorImpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lw.common.ema.jwsserver.mms.MmsOperatorImpServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.mms.MmsOperatorImpServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getMmsOperatorImpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMmsOperatorImpPortEndpointAddress(java.lang.String address) {
        MmsOperatorImpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lw.common.ema.jwsserver.mms.IMmsOperator.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lw.common.ema.jwsserver.mms.MmsOperatorImpServiceSoapBindingStub _stub = new com.lw.common.ema.jwsserver.mms.MmsOperatorImpServiceSoapBindingStub(new java.net.URL(MmsOperatorImpPort_address), this);
                _stub.setPortName(getMmsOperatorImpPortWSDDServiceName());
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
        if ("MmsOperatorImpPort".equals(inputPortName)) {
            return getMmsOperatorImpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mms.jwsserver.server.ema.ctc.com/", "MmsOperatorImpService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mms.jwsserver.server.ema.ctc.com/", "MmsOperatorImpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MmsOperatorImpPort".equals(portName)) {
            setMmsOperatorImpPortEndpointAddress(address);
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
