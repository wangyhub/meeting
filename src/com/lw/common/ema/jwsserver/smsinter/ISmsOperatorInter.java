/**
 * ISmsOperatorInter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lw.common.ema.jwsserver.smsinter;

public interface ISmsOperatorInter extends java.rmi.Remote {
	public com.lw.common.ema.jwsserver.smsinter.MoMessageRes getSms(
			java.lang.String arg0, java.lang.String arg1)
			throws java.rmi.RemoteException;

	public com.lw.common.ema.jwsserver.smsinter.ReportMessageRes getReport(
			java.lang.String arg0, java.lang.String arg1)
			throws java.rmi.RemoteException;

	public com.lw.common.ema.jwsserver.smsinter.MtMessageRes sendSms(
			java.lang.String arg0, java.lang.String arg1,
			java.lang.String arg2, java.lang.String arg3,
			com.lw.common.ema.jwsserver.smsinter.MtMessage arg4)
			throws java.rmi.RemoteException;

	public com.lw.common.ema.jwsserver.smsinter.BalanceRes getBalance(
			java.lang.String arg0, java.lang.String arg1, java.lang.String arg2)
			throws java.rmi.RemoteException;

	public com.lw.common.ema.jwsserver.smsinter.MtMessageRes bathSendSms(
			java.lang.String arg0, java.lang.String arg1,
			java.lang.String arg2, java.lang.String arg3,
			com.lw.common.ema.jwsserver.smsinter.MtMessage[] arg4)
			throws java.rmi.RemoteException;
}
