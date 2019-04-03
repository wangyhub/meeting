package com.lw.common.ema.jwsserver.sms.contacts;

public class IContactsService4JSONProxy implements com.lw.common.ema.jwsserver.sms.contacts.IContactsService4JSON {
  private String _endpoint = null;
  private com.lw.common.ema.jwsserver.sms.contacts.IContactsService4JSON iContactsService4JSON = null;
  
  public IContactsService4JSONProxy() {
    _initIContactsService4JSONProxy();
  }
  
  public IContactsService4JSONProxy(String endpoint) {
    _endpoint = endpoint;
    _initIContactsService4JSONProxy();
  }
  
  private void _initIContactsService4JSONProxy() {
    try {
      iContactsService4JSON = (new com.lw.common.ema.jwsserver.sms.contacts.ContactsService4JSONImplServiceLocator()).getContactsService4JSONImplPort();
      if (iContactsService4JSON != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iContactsService4JSON)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iContactsService4JSON)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iContactsService4JSON != null)
      ((javax.xml.rpc.Stub)iContactsService4JSON)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lw.common.ema.jwsserver.sms.contacts.IContactsService4JSON getIContactsService4JSON() {
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON;
  }
  
  public java.lang.String modifyPerson(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.modifyPerson(arg0);
  }
  
  public java.lang.String addPerson(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.addPerson(arg0);
  }
  
  public java.lang.String modifyGroup(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.modifyGroup(arg0);
  }
  
  public java.lang.String deletePersonByGroupId(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.deletePersonByGroupId(arg0);
  }
  
  public java.lang.String deletePerson(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.deletePerson(arg0);
  }
  
  public java.lang.String addGroup(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.addGroup(arg0);
  }
  
  public java.lang.String deleteGroup(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.deleteGroup(arg0);
  }
  
  public java.lang.String loadPerson(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iContactsService4JSON == null)
      _initIContactsService4JSONProxy();
    return iContactsService4JSON.loadPerson(arg0);
  }
  
  
}