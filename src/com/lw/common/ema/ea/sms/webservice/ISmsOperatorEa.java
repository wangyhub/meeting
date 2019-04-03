/**
 * ISmsOperatorEa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.ea.sms.webservice;

public interface ISmsOperatorEa extends java.rmi.Remote {
    public java.lang.String getSms(int arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String getKey(int arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String getReport(int arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String sendSms(int arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
}
