/**
 * ISmsOperator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.sms;

public interface ISmsOperator extends java.rmi.Remote {
    public com.lw.common.ema.jwsserver.sms.MoMessageRes getSms(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public com.lw.common.ema.jwsserver.sms.ReportMessageRes getReport(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public com.lw.common.ema.jwsserver.sms.MtMessageRes sendSms(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, com.lw.common.ema.jwsserver.sms.MtMessage arg3) throws java.rmi.RemoteException;
    public com.lw.common.ema.jwsserver.sms.BalanceRes getBalance(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
