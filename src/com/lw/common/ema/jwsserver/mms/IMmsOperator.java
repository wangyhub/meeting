/**
 * IMmsOperator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.mms;

public interface IMmsOperator extends java.rmi.Remote {
    public void upMmsZipfile(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, javax.xml.rpc.holders.StringHolder arg4, javax.xml.rpc.holders.StringHolder arg5) throws java.rmi.RemoteException;
    public void upMms(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, com.lw.common.ema.jwsserver.mms.MmsFileGroup[] arg3, javax.xml.rpc.holders.StringHolder arg4, javax.xml.rpc.holders.StringHolder arg5) throws java.rmi.RemoteException;
    public void sendMms(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.util.Calendar arg4, java.lang.String arg5, javax.xml.rpc.holders.StringHolder arg6, javax.xml.rpc.holders.StringHolder arg7) throws java.rmi.RemoteException;
    public void getMmsReport(java.lang.String arg0, java.lang.String arg1, javax.xml.rpc.holders.StringHolder arg2, javax.xml.rpc.holders.StringHolder arg3) throws java.rmi.RemoteException;
}
