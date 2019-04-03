/**
 * CodeCheckServiceServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package com.lw.common.orgcode.axis2;

/*
 * CodeCheckServiceServiceStub java implementation
 */

public class CodeCheckServiceServiceStub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("CodeCheckServiceService" + this.hashCode());

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[3];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "searchKEY"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "searchDMInfo"));
		_service.addOperation(__operation);

		_operations[1] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "postOrgInfo"));
		_service.addOperation(__operation);

		_operations[2] = __operation;

	}

	// populates the faults
	private void populateFaults() {

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public CodeCheckServiceServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public CodeCheckServiceServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener) throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

		configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public CodeCheckServiceServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://codeplat.cnca.cn:80/codecheckws/CodeCheckServicePort");

	}

	/**
	 * Default Constructor
	 */
	public CodeCheckServiceServiceStub() throws org.apache.axis2.AxisFault {

		this("http://codeplat.cnca.cn:80/codecheckws/CodeCheckServicePort");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public CodeCheckServiceServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see client.CodeCheckServiceService#searchKEY
	 * @param searchKEY
	 * 
	 */

	public com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4 searchKEY(

			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0 searchKEY)

	throws java.rmi.RemoteException

	{

		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0]
					.getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), searchKEY,
					optimizeContent(new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "searchKEY")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4.class,
					getEnvelopeNamespaces(_returnEnv));
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);

			return (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see client.CodeCheckServiceService#searchDMInfo
	 * @param searchDMInfo
	 * 
	 */

	public com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1 searchDMInfo(

	com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2 searchDMInfo)

	throws java.rmi.RemoteException

	{

		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1]
					.getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(
					getFactory(_operationClient.getOptions().getSoapVersionURI()),
					searchDMInfo,
					optimizeContent(new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "searchDMInfo")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1.class,
					getEnvelopeNamespaces(_returnEnv));
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);

			return (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see client.CodeCheckServiceService#postOrgInfo
	 * @param postOrgInfo
	 * 
	 */

	public com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3 postOrgInfo(

	com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5 postOrgInfo)

	throws java.rmi.RemoteException

	{

		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2]
					.getName());
			_operationClient.getOptions().setAction("\"\"");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), postOrgInfo,
					optimizeContent(new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "postOrgInfo")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3.class,
					getEnvelopeNamespaces(_returnEnv));
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);

			return (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		}
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://codeplat.cnca.cn:80/codecheckws/CodeCheckServicePort

	public static class PostOrgInfoResponse implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = postOrgInfoResponse Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected java.lang.String local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				local_returnTracker = true;
			} else {
				local_returnTracker = false;

			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					PostOrgInfoResponse.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (local_returnTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "return", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "return");
					}

				} else {
					xmlWriter.writeStartElement("return");
				}

				if (local_return == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");

				} else {

					xmlWriter.writeCharacters(local_return);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName("", "return"));

				if (local_return != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_return));
				} else {
					throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static PostOrgInfoResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				PostOrgInfoResponse object = new PostOrgInfoResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"postOrgInfoResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (PostOrgInfoResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.set_return(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchKEYResponse implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = searchKEYResponse Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected java.lang.String local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				local_returnTracker = true;
			} else {
				local_returnTracker = false;

			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchKEYResponse.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (local_returnTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "return", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "return");
					}

				} else {
					xmlWriter.writeStartElement("return");
				}

				if (local_return == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");

				} else {

					xmlWriter.writeCharacters(local_return);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName("", "return"));

				if (local_return != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_return));
				} else {
					throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchKEYResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SearchKEYResponse object = new SearchKEYResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"searchKEYResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SearchKEYResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.set_return(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class QueryResult implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = queryResult Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Message
		 */

		protected java.lang.String localMessage;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localMessageTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMessage() {
			return localMessage;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Message
		 */
		public void setMessage(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMessageTracker = true;
			} else {
				localMessageTracker = false;

			}

			this.localMessage = param;

		}

		/**
		 * field for OrgInfos This was an Array!
		 */

		protected OrgInfo[] localOrgInfos;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgInfosTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return OrgInfo[]
		 */
		public OrgInfo[] getOrgInfos() {
			return localOrgInfos;
		}

		/**
		 * validate the array for OrgInfos
		 */
		protected void validateOrgInfos(OrgInfo[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgInfos
		 */
		public void setOrgInfos(OrgInfo[] param) {

			validateOrgInfos(param);

			if (param != null) {
				// update the setting tracker
				localOrgInfosTracker = true;
			} else {
				localOrgInfosTracker = true;

			}

			this.localOrgInfos = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            OrgInfo
		 */
		public void addOrgInfos(OrgInfo param) {
			if (localOrgInfos == null) {
				localOrgInfos = new OrgInfo[] {};
			}

			// update the setting tracker
			localOrgInfosTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localOrgInfos);
			list.add(param);
			this.localOrgInfos = (OrgInfo[]) list.toArray(new OrgInfo[list.size()]);

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					QueryResult.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localMessageTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "message", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "message");
					}

				} else {
					xmlWriter.writeStartElement("message");
				}

				if (localMessage == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("message cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localMessage);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgInfosTracker) {
				if (localOrgInfos != null) {
					for (int i = 0; i < localOrgInfos.length; i++) {
						if (localOrgInfos[i] != null) {
							localOrgInfos[i].serialize(new javax.xml.namespace.QName("", "orgInfos"), factory,
									xmlWriter);
						} else {

							// write null attribute
							java.lang.String namespace2 = "";
							if (!namespace2.equals("")) {
								java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

								if (prefix2 == null) {
									prefix2 = generatePrefix(namespace2);

									xmlWriter.writeStartElement(prefix2, "orgInfos", namespace2);
									xmlWriter.writeNamespace(prefix2, namespace2);
									xmlWriter.setPrefix(prefix2, namespace2);

								} else {
									xmlWriter.writeStartElement(namespace2, "orgInfos");
								}

							} else {
								xmlWriter.writeStartElement("orgInfos");
							}

							// write the nil attribute
							writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
							xmlWriter.writeEndElement();

						}

					}
				} else {

					// write null attribute
					java.lang.String namespace2 = "";
					if (!namespace2.equals("")) {
						java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

						if (prefix2 == null) {
							prefix2 = generatePrefix(namespace2);

							xmlWriter.writeStartElement(prefix2, "orgInfos", namespace2);
							xmlWriter.writeNamespace(prefix2, namespace2);
							xmlWriter.setPrefix(prefix2, namespace2);

						} else {
							xmlWriter.writeStartElement(namespace2, "orgInfos");
						}

					} else {
						xmlWriter.writeStartElement("orgInfos");
					}

					// write the nil attribute
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
					xmlWriter.writeEndElement();

				}
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localMessageTracker) {
				elementList.add(new javax.xml.namespace.QName("", "message"));

				if (localMessage != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMessage));
				} else {
					throw new org.apache.axis2.databinding.ADBException("message cannot be null!!");
				}
			}
			if (localOrgInfosTracker) {
				if (localOrgInfos != null) {
					for (int i = 0; i < localOrgInfos.length; i++) {

						if (localOrgInfos[i] != null) {
							elementList.add(new javax.xml.namespace.QName("", "orgInfos"));
							elementList.add(localOrgInfos[i]);
						} else {

							elementList.add(new javax.xml.namespace.QName("", "orgInfos"));
							elementList.add(null);

						}

					}
				} else {

					elementList.add(new javax.xml.namespace.QName("", "orgInfos"));
					elementList.add(localOrgInfos);

				}

			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static QueryResult parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				QueryResult object = new QueryResult();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"queryResult".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (QueryResult) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					java.util.ArrayList list2 = new java.util.ArrayList();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "message").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMessage(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgInfos").equals(reader.getName())) {

						// Process the array and step past its final element's end.

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
							list2.add(null);
							reader.next();
						} else {
							list2.add(OrgInfo.Factory.parse(reader));
						}
						// loop until we find a start element that is not part of this array
						boolean loopDone2 = false;
						while (!loopDone2) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are exiting the xml structure
								loopDone2 = true;
							} else {
								if (new javax.xml.namespace.QName("", "orgInfos").equals(reader.getName())) {

									nillableValue = reader.getAttributeValue(
											"http://www.w3.org/2001/XMLSchema-instance", "nil");
									if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
										list2.add(null);
										reader.next();
									} else {
										list2.add(OrgInfo.Factory.parse(reader));
									}
								} else {
									loopDone2 = true;
								}
							}
						}
						// call the converter utility to convert and set the array

						object.setOrgInfos((OrgInfo[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
								OrgInfo.class, list2));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class OrgInfo implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = orgInfo Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ApproveOrgCode
		 */

		protected java.lang.String localApproveOrgCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localApproveOrgCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getApproveOrgCode() {
			return localApproveOrgCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ApproveOrgCode
		 */
		public void setApproveOrgCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localApproveOrgCodeTracker = true;
			} else {
				localApproveOrgCodeTracker = false;

			}

			this.localApproveOrgCode = param;

		}

		/**
		 * field for ApproveOrgName
		 */

		protected java.lang.String localApproveOrgName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localApproveOrgNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getApproveOrgName() {
			return localApproveOrgName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ApproveOrgName
		 */
		public void setApproveOrgName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localApproveOrgNameTracker = true;
			} else {
				localApproveOrgNameTracker = false;

			}

			this.localApproveOrgName = param;

		}

		/**
		 * field for ApproveTs
		 */

		protected java.util.Calendar localApproveTs;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localApproveTsTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getApproveTs() {
			return localApproveTs;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ApproveTs
		 */
		public void setApproveTs(java.util.Calendar param) {

			if (param != null) {
				// update the setting tracker
				localApproveTsTracker = true;
			} else {
				localApproveTsTracker = false;

			}

			this.localApproveTs = param;

		}

		/**
		 * field for AreaCode
		 */

		protected java.lang.String localAreaCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localAreaCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAreaCode() {
			return localAreaCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AreaCode
		 */
		public void setAreaCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAreaCodeTracker = true;
			} else {
				localAreaCodeTracker = false;

			}

			this.localAreaCode = param;

		}

		/**
		 * field for AreaName
		 */

		protected java.lang.String localAreaName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localAreaNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAreaName() {
			return localAreaName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AreaName
		 */
		public void setAreaName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAreaNameTracker = true;
			} else {
				localAreaNameTracker = false;

			}

			this.localAreaName = param;

		}

		/**
		 * field for BusinessScope
		 */

		protected java.lang.String localBusinessScope;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localBusinessScopeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBusinessScope() {
			return localBusinessScope;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BusinessScope
		 */
		public void setBusinessScope(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBusinessScopeTracker = true;
			} else {
				localBusinessScopeTracker = false;

			}

			this.localBusinessScope = param;

		}

		/**
		 * field for BusinessType
		 */

		protected java.lang.String localBusinessType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localBusinessTypeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBusinessType() {
			return localBusinessType;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BusinessType
		 */
		public void setBusinessType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBusinessTypeTracker = true;
			} else {
				localBusinessTypeTracker = false;

			}

			this.localBusinessType = param;

		}

		/**
		 * field for BusinessTypeName
		 */

		protected java.lang.String localBusinessTypeName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localBusinessTypeNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBusinessTypeName() {
			return localBusinessTypeName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BusinessTypeName
		 */
		public void setBusinessTypeName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBusinessTypeNameTracker = true;
			} else {
				localBusinessTypeNameTracker = false;

			}

			this.localBusinessTypeName = param;

		}

		/**
		 * field for ChangeTs
		 */

		protected java.util.Calendar localChangeTs;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localChangeTsTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getChangeTs() {
			return localChangeTs;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ChangeTs
		 */
		public void setChangeTs(java.util.Calendar param) {

			if (param != null) {
				// update the setting tracker
				localChangeTsTracker = true;
			} else {
				localChangeTsTracker = false;

			}

			this.localChangeTs = param;

		}

		/**
		 * field for CountryOrArea
		 */

		protected java.lang.String localCountryOrArea;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localCountryOrAreaTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCountryOrArea() {
			return localCountryOrArea;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CountryOrArea
		 */
		public void setCountryOrArea(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCountryOrAreaTracker = true;
			} else {
				localCountryOrAreaTracker = false;

			}

			this.localCountryOrArea = param;

		}

		/**
		 * field for CountryOrgAreaName
		 */

		protected java.lang.String localCountryOrgAreaName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localCountryOrgAreaNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCountryOrgAreaName() {
			return localCountryOrgAreaName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CountryOrgAreaName
		 */
		public void setCountryOrgAreaName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCountryOrgAreaNameTracker = true;
			} else {
				localCountryOrgAreaNameTracker = false;

			}

			this.localCountryOrgAreaName = param;

		}

		/**
		 * field for CredenNum
		 */

		protected java.lang.String localCredenNum;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localCredenNumTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCredenNum() {
			return localCredenNum;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CredenNum
		 */
		public void setCredenNum(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCredenNumTracker = true;
			} else {
				localCredenNumTracker = false;

			}

			this.localCredenNum = param;

		}

		/**
		 * field for IndustryCode
		 */

		protected java.lang.String localIndustryCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localIndustryCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIndustryCode() {
			return localIndustryCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IndustryCode
		 */
		public void setIndustryCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIndustryCodeTracker = true;
			} else {
				localIndustryCodeTracker = false;

			}

			this.localIndustryCode = param;

		}

		/**
		 * field for IndustryName
		 */

		protected java.lang.String localIndustryName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localIndustryNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIndustryName() {
			return localIndustryName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IndustryName
		 */
		public void setIndustryName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIndustryNameTracker = true;
			} else {
				localIndustryNameTracker = false;

			}

			this.localIndustryName = param;

		}

		/**
		 * field for LegalName
		 */

		protected java.lang.String localLegalName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localLegalNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLegalName() {
			return localLegalName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LegalName
		 */
		public void setLegalName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLegalNameTracker = true;
			} else {
				localLegalNameTracker = false;

			}

			this.localLegalName = param;

		}

		/**
		 * field for MoneyType
		 */

		protected java.lang.String localMoneyType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localMoneyTypeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMoneyType() {
			return localMoneyType;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            MoneyType
		 */
		public void setMoneyType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMoneyTypeTracker = true;
			} else {
				localMoneyTypeTracker = false;

			}

			this.localMoneyType = param;

		}

		/**
		 * field for MoneyTypeName
		 */

		protected java.lang.String localMoneyTypeName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localMoneyTypeNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMoneyTypeName() {
			return localMoneyTypeName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            MoneyTypeName
		 */
		public void setMoneyTypeName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMoneyTypeNameTracker = true;
			} else {
				localMoneyTypeNameTracker = false;

			}

			this.localMoneyTypeName = param;

		}

		/**
		 * field for OrgAddress
		 */

		protected java.lang.String localOrgAddress;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgAddressTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgAddress() {
			return localOrgAddress;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgAddress
		 */
		public void setOrgAddress(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgAddressTracker = true;
			} else {
				localOrgAddressTracker = false;

			}

			this.localOrgAddress = param;

		}

		/**
		 * field for OrgCode
		 */

		protected java.lang.String localOrgCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgCode() {
			return localOrgCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgCode
		 */
		public void setOrgCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgCodeTracker = true;
			} else {
				localOrgCodeTracker = false;

			}

			this.localOrgCode = param;

		}

		/**
		 * field for OrgId
		 */

		protected java.lang.String localOrgId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgId() {
			return localOrgId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgId
		 */
		public void setOrgId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgIdTracker = true;
			} else {
				localOrgIdTracker = false;

			}

			this.localOrgId = param;

		}

		/**
		 * field for OrgName
		 */

		protected java.lang.String localOrgName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgName() {
			return localOrgName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgName
		 */
		public void setOrgName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgNameTracker = true;
			} else {
				localOrgNameTracker = false;

			}

			this.localOrgName = param;

		}

		/**
		 * field for OrgType
		 */

		protected java.lang.String localOrgType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgTypeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgType() {
			return localOrgType;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgType
		 */
		public void setOrgType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgTypeTracker = true;
			} else {
				localOrgTypeTracker = false;

			}

			this.localOrgType = param;

		}

		/**
		 * field for OrgTypeName
		 */

		protected java.lang.String localOrgTypeName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgTypeNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgTypeName() {
			return localOrgTypeName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgTypeName
		 */
		public void setOrgTypeName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgTypeNameTracker = true;
			} else {
				localOrgTypeNameTracker = false;

			}

			this.localOrgTypeName = param;

		}

		/**
		 * field for PhoneNum
		 */

		protected java.lang.String localPhoneNum;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localPhoneNumTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPhoneNum() {
			return localPhoneNum;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PhoneNum
		 */
		public void setPhoneNum(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPhoneNumTracker = true;
			} else {
				localPhoneNumTracker = false;

			}

			this.localPhoneNum = param;

		}

		/**
		 * field for QuestionMarks
		 */

		protected java.lang.String localQuestionMarks;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localQuestionMarksTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getQuestionMarks() {
			return localQuestionMarks;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            QuestionMarks
		 */
		public void setQuestionMarks(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localQuestionMarksTracker = true;
			} else {
				localQuestionMarksTracker = false;

			}

			this.localQuestionMarks = param;

		}

		/**
		 * field for RegisterTs
		 */

		protected java.util.Calendar localRegisterTs;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localRegisterTsTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.util.Calendar
		 */
		public java.util.Calendar getRegisterTs() {
			return localRegisterTs;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RegisterTs
		 */
		public void setRegisterTs(java.util.Calendar param) {

			if (param != null) {
				// update the setting tracker
				localRegisterTsTracker = true;
			} else {
				localRegisterTsTracker = false;

			}

			this.localRegisterTs = param;

		}

		/**
		 * field for RegisteredCapital
		 */

		protected double localRegisteredCapital;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localRegisteredCapitalTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return double
		 */
		public double getRegisteredCapital() {
			return localRegisteredCapital;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RegisteredCapital
		 */
		public void setRegisteredCapital(double param) {

			// setting primitive attribute tracker to true

			if (java.lang.Double.isNaN(param)) {
				localRegisteredCapitalTracker = false;

			} else {
				localRegisteredCapitalTracker = true;
			}

			this.localRegisteredCapital = param;

		}

		/**
		 * field for SuperOrgCode
		 */

		protected java.lang.String localSuperOrgCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localSuperOrgCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSuperOrgCode() {
			return localSuperOrgCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SuperOrgCode
		 */
		public void setSuperOrgCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSuperOrgCodeTracker = true;
			} else {
				localSuperOrgCodeTracker = false;

			}

			this.localSuperOrgCode = param;

		}

		/**
		 * field for SuperOrgName
		 */

		protected java.lang.String localSuperOrgName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localSuperOrgNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSuperOrgName() {
			return localSuperOrgName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SuperOrgName
		 */
		public void setSuperOrgName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSuperOrgNameTracker = true;
			} else {
				localSuperOrgNameTracker = false;

			}

			this.localSuperOrgName = param;

		}

		/**
		 * field for WorkerNumber
		 */

		protected int localWorkerNumber;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localWorkerNumberTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return int
		 */
		public int getWorkerNumber() {
			return localWorkerNumber;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            WorkerNumber
		 */
		public void setWorkerNumber(int param) {

			// setting primitive attribute tracker to true

			if (param == java.lang.Integer.MIN_VALUE) {
				localWorkerNumberTracker = false;

			} else {
				localWorkerNumberTracker = true;
			}

			this.localWorkerNumber = param;

		}

		/**
		 * field for ZipCode
		 */

		protected java.lang.String localZipCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localZipCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getZipCode() {
			return localZipCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ZipCode
		 */
		public void setZipCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localZipCodeTracker = true;
			} else {
				localZipCodeTracker = false;

			}

			this.localZipCode = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					OrgInfo.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localApproveOrgCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "approveOrgCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "approveOrgCode");
					}

				} else {
					xmlWriter.writeStartElement("approveOrgCode");
				}

				if (localApproveOrgCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("approveOrgCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localApproveOrgCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localApproveOrgNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "approveOrgName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "approveOrgName");
					}

				} else {
					xmlWriter.writeStartElement("approveOrgName");
				}

				if (localApproveOrgName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("approveOrgName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localApproveOrgName);

				}

				xmlWriter.writeEndElement();
			}
			if (localApproveTsTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "approveTs", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "approveTs");
					}

				} else {
					xmlWriter.writeStartElement("approveTs");
				}

				if (localApproveTs == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("approveTs cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localApproveTs));

				}

				xmlWriter.writeEndElement();
			}
			if (localAreaCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "areaCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "areaCode");
					}

				} else {
					xmlWriter.writeStartElement("areaCode");
				}

				if (localAreaCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("areaCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAreaCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localAreaNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "areaName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "areaName");
					}

				} else {
					xmlWriter.writeStartElement("areaName");
				}

				if (localAreaName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("areaName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localAreaName);

				}

				xmlWriter.writeEndElement();
			}
			if (localBusinessScopeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "businessScope", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "businessScope");
					}

				} else {
					xmlWriter.writeStartElement("businessScope");
				}

				if (localBusinessScope == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("businessScope cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBusinessScope);

				}

				xmlWriter.writeEndElement();
			}
			if (localBusinessTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "businessType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "businessType");
					}

				} else {
					xmlWriter.writeStartElement("businessType");
				}

				if (localBusinessType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("businessType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBusinessType);

				}

				xmlWriter.writeEndElement();
			}
			if (localBusinessTypeNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "businessTypeName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "businessTypeName");
					}

				} else {
					xmlWriter.writeStartElement("businessTypeName");
				}

				if (localBusinessTypeName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("businessTypeName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localBusinessTypeName);

				}

				xmlWriter.writeEndElement();
			}
			if (localChangeTsTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "changeTs", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "changeTs");
					}

				} else {
					xmlWriter.writeStartElement("changeTs");
				}

				if (localChangeTs == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("changeTs cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localChangeTs));

				}

				xmlWriter.writeEndElement();
			}
			if (localCountryOrAreaTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "countryOrArea", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "countryOrArea");
					}

				} else {
					xmlWriter.writeStartElement("countryOrArea");
				}

				if (localCountryOrArea == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("countryOrArea cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCountryOrArea);

				}

				xmlWriter.writeEndElement();
			}
			if (localCountryOrgAreaNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "countryOrgAreaName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "countryOrgAreaName");
					}

				} else {
					xmlWriter.writeStartElement("countryOrgAreaName");
				}

				if (localCountryOrgAreaName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("countryOrgAreaName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCountryOrgAreaName);

				}

				xmlWriter.writeEndElement();
			}
			if (localCredenNumTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "credenNum", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "credenNum");
					}

				} else {
					xmlWriter.writeStartElement("credenNum");
				}

				if (localCredenNum == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("credenNum cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localCredenNum);

				}

				xmlWriter.writeEndElement();
			}
			if (localIndustryCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "industryCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "industryCode");
					}

				} else {
					xmlWriter.writeStartElement("industryCode");
				}

				if (localIndustryCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("industryCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIndustryCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localIndustryNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "industryName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "industryName");
					}

				} else {
					xmlWriter.writeStartElement("industryName");
				}

				if (localIndustryName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("industryName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localIndustryName);

				}

				xmlWriter.writeEndElement();
			}
			if (localLegalNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "legalName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "legalName");
					}

				} else {
					xmlWriter.writeStartElement("legalName");
				}

				if (localLegalName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("legalName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localLegalName);

				}

				xmlWriter.writeEndElement();
			}
			if (localMoneyTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "moneyType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "moneyType");
					}

				} else {
					xmlWriter.writeStartElement("moneyType");
				}

				if (localMoneyType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("moneyType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localMoneyType);

				}

				xmlWriter.writeEndElement();
			}
			if (localMoneyTypeNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "moneyTypeName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "moneyTypeName");
					}

				} else {
					xmlWriter.writeStartElement("moneyTypeName");
				}

				if (localMoneyTypeName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("moneyTypeName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localMoneyTypeName);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgAddressTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgAddress", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgAddress");
					}

				} else {
					xmlWriter.writeStartElement("orgAddress");
				}

				if (localOrgAddress == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgAddress cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgAddress);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgCode");
					}

				} else {
					xmlWriter.writeStartElement("orgCode");
				}

				if (localOrgCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgIdTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgId");
					}

				} else {
					xmlWriter.writeStartElement("orgId");
				}

				if (localOrgId == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgId cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgId);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgName");
					}

				} else {
					xmlWriter.writeStartElement("orgName");
				}

				if (localOrgName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgName);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgTypeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgType");
					}

				} else {
					xmlWriter.writeStartElement("orgType");
				}

				if (localOrgType == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgType cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgType);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgTypeNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgTypeName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgTypeName");
					}

				} else {
					xmlWriter.writeStartElement("orgTypeName");
				}

				if (localOrgTypeName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgTypeName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgTypeName);

				}

				xmlWriter.writeEndElement();
			}
			if (localPhoneNumTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "phoneNum", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "phoneNum");
					}

				} else {
					xmlWriter.writeStartElement("phoneNum");
				}

				if (localPhoneNum == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("phoneNum cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPhoneNum);

				}

				xmlWriter.writeEndElement();
			}
			if (localQuestionMarksTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "questionMarks", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "questionMarks");
					}

				} else {
					xmlWriter.writeStartElement("questionMarks");
				}

				if (localQuestionMarks == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("questionMarks cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localQuestionMarks);

				}

				xmlWriter.writeEndElement();
			}
			if (localRegisterTsTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "registerTs", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "registerTs");
					}

				} else {
					xmlWriter.writeStartElement("registerTs");
				}

				if (localRegisterTs == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("registerTs cannot be null!!");

				} else {

					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localRegisterTs));

				}

				xmlWriter.writeEndElement();
			}
			if (localRegisteredCapitalTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "registeredCapital", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "registeredCapital");
					}

				} else {
					xmlWriter.writeStartElement("registeredCapital");
				}

				if (java.lang.Double.isNaN(localRegisteredCapital)) {

					throw new org.apache.axis2.databinding.ADBException("registeredCapital cannot be null!!");

				} else {
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localRegisteredCapital));
				}

				xmlWriter.writeEndElement();
			}
			if (localSuperOrgCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "superOrgCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "superOrgCode");
					}

				} else {
					xmlWriter.writeStartElement("superOrgCode");
				}

				if (localSuperOrgCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("superOrgCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSuperOrgCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localSuperOrgNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "superOrgName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "superOrgName");
					}

				} else {
					xmlWriter.writeStartElement("superOrgName");
				}

				if (localSuperOrgName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("superOrgName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSuperOrgName);

				}

				xmlWriter.writeEndElement();
			}
			if (localWorkerNumberTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "workerNumber", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "workerNumber");
					}

				} else {
					xmlWriter.writeStartElement("workerNumber");
				}

				if (localWorkerNumber == java.lang.Integer.MIN_VALUE) {

					throw new org.apache.axis2.databinding.ADBException("workerNumber cannot be null!!");

				} else {
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localWorkerNumber));
				}

				xmlWriter.writeEndElement();
			}
			if (localZipCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "zipCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "zipCode");
					}

				} else {
					xmlWriter.writeStartElement("zipCode");
				}

				if (localZipCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("zipCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localZipCode);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localApproveOrgCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "approveOrgCode"));

				if (localApproveOrgCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localApproveOrgCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("approveOrgCode cannot be null!!");
				}
			}
			if (localApproveOrgNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "approveOrgName"));

				if (localApproveOrgName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localApproveOrgName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("approveOrgName cannot be null!!");
				}
			}
			if (localApproveTsTracker) {
				elementList.add(new javax.xml.namespace.QName("", "approveTs"));

				if (localApproveTs != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localApproveTs));
				} else {
					throw new org.apache.axis2.databinding.ADBException("approveTs cannot be null!!");
				}
			}
			if (localAreaCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "areaCode"));

				if (localAreaCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAreaCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("areaCode cannot be null!!");
				}
			}
			if (localAreaNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "areaName"));

				if (localAreaName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAreaName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("areaName cannot be null!!");
				}
			}
			if (localBusinessScopeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "businessScope"));

				if (localBusinessScope != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localBusinessScope));
				} else {
					throw new org.apache.axis2.databinding.ADBException("businessScope cannot be null!!");
				}
			}
			if (localBusinessTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "businessType"));

				if (localBusinessType != null) {
					elementList
							.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBusinessType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("businessType cannot be null!!");
				}
			}
			if (localBusinessTypeNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "businessTypeName"));

				if (localBusinessTypeName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localBusinessTypeName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("businessTypeName cannot be null!!");
				}
			}
			if (localChangeTsTracker) {
				elementList.add(new javax.xml.namespace.QName("", "changeTs"));

				if (localChangeTs != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChangeTs));
				} else {
					throw new org.apache.axis2.databinding.ADBException("changeTs cannot be null!!");
				}
			}
			if (localCountryOrAreaTracker) {
				elementList.add(new javax.xml.namespace.QName("", "countryOrArea"));

				if (localCountryOrArea != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localCountryOrArea));
				} else {
					throw new org.apache.axis2.databinding.ADBException("countryOrArea cannot be null!!");
				}
			}
			if (localCountryOrgAreaNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "countryOrgAreaName"));

				if (localCountryOrgAreaName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localCountryOrgAreaName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("countryOrgAreaName cannot be null!!");
				}
			}
			if (localCredenNumTracker) {
				elementList.add(new javax.xml.namespace.QName("", "credenNum"));

				if (localCredenNum != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCredenNum));
				} else {
					throw new org.apache.axis2.databinding.ADBException("credenNum cannot be null!!");
				}
			}
			if (localIndustryCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "industryCode"));

				if (localIndustryCode != null) {
					elementList
							.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIndustryCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("industryCode cannot be null!!");
				}
			}
			if (localIndustryNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "industryName"));

				if (localIndustryName != null) {
					elementList
							.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIndustryName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("industryName cannot be null!!");
				}
			}
			if (localLegalNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "legalName"));

				if (localLegalName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLegalName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("legalName cannot be null!!");
				}
			}
			if (localMoneyTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "moneyType"));

				if (localMoneyType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMoneyType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("moneyType cannot be null!!");
				}
			}
			if (localMoneyTypeNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "moneyTypeName"));

				if (localMoneyTypeName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localMoneyTypeName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("moneyTypeName cannot be null!!");
				}
			}
			if (localOrgAddressTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgAddress"));

				if (localOrgAddress != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgAddress));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgAddress cannot be null!!");
				}
			}
			if (localOrgCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgCode"));

				if (localOrgCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgCode cannot be null!!");
				}
			}
			if (localOrgIdTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgId"));

				if (localOrgId != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgId));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgId cannot be null!!");
				}
			}
			if (localOrgNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgName"));

				if (localOrgName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgName cannot be null!!");
				}
			}
			if (localOrgTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgType"));

				if (localOrgType != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgType));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgType cannot be null!!");
				}
			}
			if (localOrgTypeNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgTypeName"));

				if (localOrgTypeName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgTypeName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgTypeName cannot be null!!");
				}
			}
			if (localPhoneNumTracker) {
				elementList.add(new javax.xml.namespace.QName("", "phoneNum"));

				if (localPhoneNum != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPhoneNum));
				} else {
					throw new org.apache.axis2.databinding.ADBException("phoneNum cannot be null!!");
				}
			}
			if (localQuestionMarksTracker) {
				elementList.add(new javax.xml.namespace.QName("", "questionMarks"));

				if (localQuestionMarks != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(localQuestionMarks));
				} else {
					throw new org.apache.axis2.databinding.ADBException("questionMarks cannot be null!!");
				}
			}
			if (localRegisterTsTracker) {
				elementList.add(new javax.xml.namespace.QName("", "registerTs"));

				if (localRegisterTs != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRegisterTs));
				} else {
					throw new org.apache.axis2.databinding.ADBException("registerTs cannot be null!!");
				}
			}
			if (localRegisteredCapitalTracker) {
				elementList.add(new javax.xml.namespace.QName("", "registeredCapital"));

				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
						.convertToString(localRegisteredCapital));
			}
			if (localSuperOrgCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "superOrgCode"));

				if (localSuperOrgCode != null) {
					elementList
							.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSuperOrgCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("superOrgCode cannot be null!!");
				}
			}
			if (localSuperOrgNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "superOrgName"));

				if (localSuperOrgName != null) {
					elementList
							.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSuperOrgName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("superOrgName cannot be null!!");
				}
			}
			if (localWorkerNumberTracker) {
				elementList.add(new javax.xml.namespace.QName("", "workerNumber"));

				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localWorkerNumber));
			}
			if (localZipCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "zipCode"));

				if (localZipCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localZipCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("zipCode cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static OrgInfo parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				OrgInfo object = new OrgInfo();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"orgInfo".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (OrgInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "approveOrgCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setApproveOrgCode(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "approveOrgName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setApproveOrgName(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "approveTs").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object
								.setApproveTs(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "areaCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAreaCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "areaName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setAreaName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "businessScope").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBusinessScope(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "businessType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBusinessType(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "businessTypeName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBusinessTypeName(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "changeTs").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setChangeTs(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "countryOrArea").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCountryOrArea(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "countryOrgAreaName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCountryOrgAreaName(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "credenNum").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setCredenNum(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "industryCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIndustryCode(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "industryName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setIndustryName(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "legalName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setLegalName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "moneyType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMoneyType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "moneyTypeName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMoneyTypeName(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgAddress").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgAddress(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "orgId").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgType").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgTypeName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object
								.setOrgTypeName(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "phoneNum").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPhoneNum(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "questionMarks").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setQuestionMarks(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "registerTs").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRegisterTs(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToDateTime(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "registeredCapital").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRegisteredCapital(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToDouble(content));

						reader.next();

					} // End of if for expected property start element

					else {

						object.setRegisteredCapital(java.lang.Double.NaN);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "superOrgCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSuperOrgCode(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "superOrgName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSuperOrgName(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "workerNumber").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setWorkerNumber(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

						reader.next();

					} // End of if for expected property start element

					else {

						object.setWorkerNumber(java.lang.Integer.MIN_VALUE);

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "zipCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setZipCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class PostOrgInfo implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = postOrgInfo Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Arg0
		 */

		protected QueryCondition localArg0;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localArg0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return QueryCondition
		 */
		public QueryCondition getArg0() {
			return localArg0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Arg0
		 */
		public void setArg0(QueryCondition param) {

			if (param != null) {
				// update the setting tracker
				localArg0Tracker = true;
			} else {
				localArg0Tracker = false;

			}

			this.localArg0 = param;

		}

		/**
		 * field for Arg1
		 */

		protected OrgInfo localArg1;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localArg1Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return OrgInfo
		 */
		public OrgInfo getArg1() {
			return localArg1;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Arg1
		 */
		public void setArg1(OrgInfo param) {

			if (param != null) {
				// update the setting tracker
				localArg1Tracker = true;
			} else {
				localArg1Tracker = false;

			}

			this.localArg1 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					PostOrgInfo.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localArg0Tracker) {
				if (localArg0 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");
				}
				localArg0.serialize(new javax.xml.namespace.QName("", "arg0"), factory, xmlWriter);
			}
			if (localArg1Tracker) {
				if (localArg1 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg1 cannot be null!!");
				}
				localArg1.serialize(new javax.xml.namespace.QName("", "arg1"), factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArg0Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "arg0"));

				if (localArg0 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");
				}
				elementList.add(localArg0);
			}
			if (localArg1Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "arg1"));

				if (localArg1 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg1 cannot be null!!");
				}
				elementList.add(localArg1);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static PostOrgInfo parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				PostOrgInfo object = new PostOrgInfo();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"postOrgInfo".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (PostOrgInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

						object.setArg0(QueryCondition.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "arg1").equals(reader.getName())) {

						object.setArg1(OrgInfo.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class QueryCondition implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = queryCondition Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for OrgCode
		 */

		protected java.lang.String localOrgCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgCode() {
			return localOrgCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgCode
		 */
		public void setOrgCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgCodeTracker = true;
			} else {
				localOrgCodeTracker = false;

			}

			this.localOrgCode = param;

		}

		/**
		 * field for OrgName
		 */

		protected java.lang.String localOrgName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localOrgNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrgName() {
			return localOrgName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrgName
		 */
		public void setOrgName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrgNameTracker = true;
			} else {
				localOrgNameTracker = false;

			}

			this.localOrgName = param;

		}

		/**
		 * field for Password
		 */

		protected java.lang.String localPassword;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localPasswordTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPassword() {
			return localPassword;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Password
		 */
		public void setPassword(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPasswordTracker = true;
			} else {
				localPasswordTracker = false;

			}

			this.localPassword = param;

		}

		/**
		 * field for SystemCode
		 */

		protected java.lang.String localSystemCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localSystemCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSystemCode() {
			return localSystemCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SystemCode
		 */
		public void setSystemCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSystemCodeTracker = true;
			} else {
				localSystemCodeTracker = false;

			}

			this.localSystemCode = param;

		}

		/**
		 * field for UserName
		 */

		protected java.lang.String localUserName;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localUserNameTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserName() {
			return localUserName;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserName
		 */
		public void setUserName(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserNameTracker = true;
			} else {
				localUserNameTracker = false;

			}

			this.localUserName = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					QueryCondition.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localOrgCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgCode");
					}

				} else {
					xmlWriter.writeStartElement("orgCode");
				}

				if (localOrgCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrgNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "orgName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "orgName");
					}

				} else {
					xmlWriter.writeStartElement("orgName");
				}

				if (localOrgName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("orgName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localOrgName);

				}

				xmlWriter.writeEndElement();
			}
			if (localPasswordTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "password", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "password");
					}

				} else {
					xmlWriter.writeStartElement("password");
				}

				if (localPassword == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("password cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localPassword);

				}

				xmlWriter.writeEndElement();
			}
			if (localSystemCodeTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "systemCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "systemCode");
					}

				} else {
					xmlWriter.writeStartElement("systemCode");
				}

				if (localSystemCode == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("systemCode cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localSystemCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserNameTracker) {
				namespace = "";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userName", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userName");
					}

				} else {
					xmlWriter.writeStartElement("userName");
				}

				if (localUserName == null) {
					// write the nil attribute

					throw new org.apache.axis2.databinding.ADBException("userName cannot be null!!");

				} else {

					xmlWriter.writeCharacters(localUserName);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localOrgCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgCode"));

				if (localOrgCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgCode cannot be null!!");
				}
			}
			if (localOrgNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "orgName"));

				if (localOrgName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrgName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("orgName cannot be null!!");
				}
			}
			if (localPasswordTracker) {
				elementList.add(new javax.xml.namespace.QName("", "password"));

				if (localPassword != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
				} else {
					throw new org.apache.axis2.databinding.ADBException("password cannot be null!!");
				}
			}
			if (localSystemCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("", "systemCode"));

				if (localSystemCode != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSystemCode));
				} else {
					throw new org.apache.axis2.databinding.ADBException("systemCode cannot be null!!");
				}
			}
			if (localUserNameTracker) {
				elementList.add(new javax.xml.namespace.QName("", "userName"));

				if (localUserName != null) {
					elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserName));
				} else {
					throw new org.apache.axis2.databinding.ADBException("userName cannot be null!!");
				}
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static QueryCondition parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				QueryCondition object = new QueryCondition();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"queryCondition".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (QueryCondition) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "orgName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setOrgName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "password").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPassword(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "systemCode").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSystemCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("", "userName").equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setUserName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchKEY implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = searchKEY Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Arg0
		 */

		protected QueryCondition localArg0;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localArg0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return QueryCondition
		 */
		public QueryCondition getArg0() {
			return localArg0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Arg0
		 */
		public void setArg0(QueryCondition param) {

			if (param != null) {
				// update the setting tracker
				localArg0Tracker = true;
			} else {
				localArg0Tracker = false;

			}

			this.localArg0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchKEY.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localArg0Tracker) {
				if (localArg0 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");
				}
				localArg0.serialize(new javax.xml.namespace.QName("", "arg0"), factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArg0Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "arg0"));

				if (localArg0 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");
				}
				elementList.add(localArg0);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchKEY parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SearchKEY object = new SearchKEY();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"searchKEY".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SearchKEY) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

						object.setArg0(QueryCondition.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchDMInfoResponse1 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://wsserver.codeck.cncait.com/", "searchDMInfoResponse", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SearchDMInfoResponse
		 */

		protected SearchDMInfoResponse localSearchDMInfoResponse;

		/**
		 * Auto generated getter method
		 * 
		 * @return SearchDMInfoResponse
		 */
		public SearchDMInfoResponse getSearchDMInfoResponse() {
			return localSearchDMInfoResponse;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SearchDMInfoResponse
		 */
		public void setSearchDMInfoResponse(SearchDMInfoResponse param) {

			this.localSearchDMInfoResponse = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchDMInfoResponse1.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it

			if (localSearchDMInfoResponse == null) {
				throw new org.apache.axis2.databinding.ADBException("Property cannot be null!");
			}
			localSearchDMInfoResponse.serialize(MY_QNAME, factory, xmlWriter);

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it
			return localSearchDMInfoResponse.getPullParser(MY_QNAME);

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchDMInfoResponse1 parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				SearchDMInfoResponse1 object = new SearchDMInfoResponse1();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					while (!reader.isEndElement()) {
						if (reader.isStartElement()) {

							if (reader.isStartElement()
									&& new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/",
											"searchDMInfoResponse").equals(reader.getName())) {

								object.setSearchDMInfoResponse(SearchDMInfoResponse.Factory.parse(reader));

							} // End of if for expected property start element

							else {
								// A start element we are not expecting indicates an invalid parameter was passed
								throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
										+ reader.getLocalName());
							}

						} else {
							reader.next();
						}
					} // end of while loop

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class PostOrgInfoResponse3 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://wsserver.codeck.cncait.com/", "postOrgInfoResponse", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for PostOrgInfoResponse
		 */

		protected PostOrgInfoResponse localPostOrgInfoResponse;

		/**
		 * Auto generated getter method
		 * 
		 * @return PostOrgInfoResponse
		 */
		public PostOrgInfoResponse getPostOrgInfoResponse() {
			return localPostOrgInfoResponse;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PostOrgInfoResponse
		 */
		public void setPostOrgInfoResponse(PostOrgInfoResponse param) {

			this.localPostOrgInfoResponse = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					PostOrgInfoResponse3.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it

			if (localPostOrgInfoResponse == null) {
				throw new org.apache.axis2.databinding.ADBException("Property cannot be null!");
			}
			localPostOrgInfoResponse.serialize(MY_QNAME, factory, xmlWriter);

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it
			return localPostOrgInfoResponse.getPullParser(MY_QNAME);

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static PostOrgInfoResponse3 parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				PostOrgInfoResponse3 object = new PostOrgInfoResponse3();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					while (!reader.isEndElement()) {
						if (reader.isStartElement()) {

							if (reader.isStartElement()
									&& new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/",
											"postOrgInfoResponse").equals(reader.getName())) {

								object.setPostOrgInfoResponse(PostOrgInfoResponse.Factory.parse(reader));

							} // End of if for expected property start element

							else {
								// A start element we are not expecting indicates an invalid parameter was passed
								throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
										+ reader.getLocalName());
							}

						} else {
							reader.next();
						}
					} // end of while loop

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchDMInfo implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = searchDMInfo Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Arg0
		 */

		protected QueryCondition localArg0;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean localArg0Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return QueryCondition
		 */
		public QueryCondition getArg0() {
			return localArg0;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Arg0
		 */
		public void setArg0(QueryCondition param) {

			if (param != null) {
				// update the setting tracker
				localArg0Tracker = true;
			} else {
				localArg0Tracker = false;

			}

			this.localArg0 = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchDMInfo.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (localArg0Tracker) {
				if (localArg0 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");
				}
				localArg0.serialize(new javax.xml.namespace.QName("", "arg0"), factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localArg0Tracker) {
				elementList.add(new javax.xml.namespace.QName("", "arg0"));

				if (localArg0 == null) {
					throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");
				}
				elementList.add(localArg0);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchDMInfo parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SearchDMInfo object = new SearchDMInfo();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"searchDMInfo".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SearchDMInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

						object.setArg0(QueryCondition.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchDMInfo2 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://wsserver.codeck.cncait.com/", "searchDMInfo", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SearchDMInfo
		 */

		protected SearchDMInfo localSearchDMInfo;

		/**
		 * Auto generated getter method
		 * 
		 * @return SearchDMInfo
		 */
		public SearchDMInfo getSearchDMInfo() {
			return localSearchDMInfo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SearchDMInfo
		 */
		public void setSearchDMInfo(SearchDMInfo param) {

			this.localSearchDMInfo = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchDMInfo2.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it

			if (localSearchDMInfo == null) {
				throw new org.apache.axis2.databinding.ADBException("Property cannot be null!");
			}
			localSearchDMInfo.serialize(MY_QNAME, factory, xmlWriter);

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it
			return localSearchDMInfo.getPullParser(MY_QNAME);

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchDMInfo2 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SearchDMInfo2 object = new SearchDMInfo2();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					while (!reader.isEndElement()) {
						if (reader.isStartElement()) {

							if (reader.isStartElement()
									&& new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/",
											"searchDMInfo").equals(reader.getName())) {

								object.setSearchDMInfo(SearchDMInfo.Factory.parse(reader));

							} // End of if for expected property start element

							else {
								// A start element we are not expecting indicates an invalid parameter was passed
								throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
										+ reader.getLocalName());
							}

						} else {
							reader.next();
						}
					} // end of while loop

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class PostOrgInfo5 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://wsserver.codeck.cncait.com/", "postOrgInfo", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for PostOrgInfo
		 */

		protected PostOrgInfo localPostOrgInfo;

		/**
		 * Auto generated getter method
		 * 
		 * @return PostOrgInfo
		 */
		public PostOrgInfo getPostOrgInfo() {
			return localPostOrgInfo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PostOrgInfo
		 */
		public void setPostOrgInfo(PostOrgInfo param) {

			this.localPostOrgInfo = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					PostOrgInfo5.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it

			if (localPostOrgInfo == null) {
				throw new org.apache.axis2.databinding.ADBException("Property cannot be null!");
			}
			localPostOrgInfo.serialize(MY_QNAME, factory, xmlWriter);

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it
			return localPostOrgInfo.getPullParser(MY_QNAME);

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static PostOrgInfo5 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				PostOrgInfo5 object = new PostOrgInfo5();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					while (!reader.isEndElement()) {
						if (reader.isStartElement()) {

							if (reader.isStartElement()
									&& new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/",
											"postOrgInfo").equals(reader.getName())) {

								object.setPostOrgInfo(PostOrgInfo.Factory.parse(reader));

							} // End of if for expected property start element

							else {
								// A start element we are not expecting indicates an invalid parameter was passed
								throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
										+ reader.getLocalName());
							}

						} else {
							reader.next();
						}
					} // end of while loop

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ExtensionMapper {

		public static java.lang.Object getTypeObject(java.lang.String namespaceURI, java.lang.String typeName,
				javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "queryCondition".equals(typeName)) {

				return QueryCondition.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "orgInfo".equals(typeName)) {

				return OrgInfo.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "searchKEY".equals(typeName)) {

				return SearchKEY.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "postOrgInfoResponse".equals(typeName)) {

				return PostOrgInfoResponse.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "searchDMInfo".equals(typeName)) {

				return SearchDMInfo.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "searchDMInfoResponse".equals(typeName)) {

				return SearchDMInfoResponse.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "queryResult".equals(typeName)) {

				return QueryResult.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "searchKEYResponse".equals(typeName)) {

				return SearchKEYResponse.Factory.parse(reader);

			}

			if ("http://wsserver.codeck.cncait.com/".equals(namespaceURI) && "postOrgInfo".equals(typeName)) {

				return PostOrgInfo.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class SearchKEYResponse4 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://wsserver.codeck.cncait.com/", "searchKEYResponse", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SearchKEYResponse
		 */

		protected SearchKEYResponse localSearchKEYResponse;

		/**
		 * Auto generated getter method
		 * 
		 * @return SearchKEYResponse
		 */
		public SearchKEYResponse getSearchKEYResponse() {
			return localSearchKEYResponse;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SearchKEYResponse
		 */
		public void setSearchKEYResponse(SearchKEYResponse param) {

			this.localSearchKEYResponse = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchKEYResponse4.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it

			if (localSearchKEYResponse == null) {
				throw new org.apache.axis2.databinding.ADBException("Property cannot be null!");
			}
			localSearchKEYResponse.serialize(MY_QNAME, factory, xmlWriter);

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it
			return localSearchKEYResponse.getPullParser(MY_QNAME);

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchKEYResponse4 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SearchKEYResponse4 object = new SearchKEYResponse4();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					while (!reader.isEndElement()) {
						if (reader.isStartElement()) {

							if (reader.isStartElement()
									&& new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/",
											"searchKEYResponse").equals(reader.getName())) {

								object.setSearchKEYResponse(SearchKEYResponse.Factory.parse(reader));

							} // End of if for expected property start element

							else {
								// A start element we are not expecting indicates an invalid parameter was passed
								throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
										+ reader.getLocalName());
							}

						} else {
							reader.next();
						}
					} // end of while loop

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchKEY0 implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
				"http://wsserver.codeck.cncait.com/", "searchKEY", "ns1");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SearchKEY
		 */

		protected SearchKEY localSearchKEY;

		/**
		 * Auto generated getter method
		 * 
		 * @return SearchKEY
		 */
		public SearchKEY getSearchKEY() {
			return localSearchKEY;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SearchKEY
		 */
		public void setSearchKEY(SearchKEY param) {

			this.localSearchKEY = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchKEY0.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it

			if (localSearchKEY == null) {
				throw new org.apache.axis2.databinding.ADBException("Property cannot be null!");
			}
			localSearchKEY.serialize(MY_QNAME, factory, xmlWriter);

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			// We can safely assume an element has only one type associated with it
			return localSearchKEY.getPullParser(MY_QNAME);

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchKEY0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SearchKEY0 object = new SearchKEY0();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					while (!reader.isEndElement()) {
						if (reader.isStartElement()) {

							if (reader.isStartElement()
									&& new javax.xml.namespace.QName("http://wsserver.codeck.cncait.com/", "searchKEY")
											.equals(reader.getName())) {

								object.setSearchKEY(SearchKEY.Factory.parse(reader));

							} // End of if for expected property start element

							else {
								// A start element we are not expecting indicates an invalid parameter was passed
								throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
										+ reader.getLocalName());
							}

						} else {
							reader.next();
						}
					} // end of while loop

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SearchDMInfoResponse implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = searchDMInfoResponse Namespace URI =
		 * http://wsserver.codeck.cncait.com/ Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://wsserver.codeck.cncait.com/")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for _return
		 */

		protected QueryResult local_return;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set method for this attribute. It will
		 * be used to determine whether to include this field in the serialized XML
		 */
		protected boolean local_returnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return QueryResult
		 */
		public QueryResult get_return() {
			return local_return;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _return
		 */
		public void set_return(QueryResult param) {

			if (param != null) {
				// update the setting tracker
				local_returnTracker = true;
			} else {
				local_returnTracker = false;

			}

			this.local_return = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader
						.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 * 
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					SearchDMInfoResponse.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if (namespace != null) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}
			if (local_returnTracker) {
				if (local_return == null) {
					throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
				}
				local_return.serialize(new javax.xml.namespace.QName("", "return"), factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(prefix + ":"
							+ org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
						}
					} else {
						stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 * 
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (local_returnTracker) {
				elementList.add(new javax.xml.namespace.QName("", "return"));

				if (local_return == null) {
					throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
				}
				elementList.add(local_return);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an element, the current or next start
			 * element starts this object and any intervening reader events are ignorable If this object is not an
			 * element, it is a complex type and the reader is at the event just after the outer start element
			 * Postcondition: If this object is an element, the reader is positioned at its end element If this object
			 * is a complex type, the reader is positioned at the end element of its outer element
			 */
			public static SearchDMInfoResponse parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				SearchDMInfoResponse object = new SearchDMInfoResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader.getAttributeValue(
								"http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"searchDMInfoResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SearchDMInfoResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

						object.set_return(QueryResult.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException("Unexpected subelement "
								+ reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4 param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1 param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3 param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(
							com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0.MY_QNAME,
							factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(
							com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2.MY_QNAME,
							factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5 param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(
							com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5.MY_QNAME,
							factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type,
			java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {

		try {

			if (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0.class.equals(type)) {

				return com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0.Factory.parse(param
						.getXMLStreamReaderWithoutCaching());

			}

			if (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4.class.equals(type)) {

				return com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2.class.equals(type)) {

				return com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1.class
					.equals(type)) {

				return com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5.class.equals(type)) {

				return com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5.Factory.parse(param
						.getXMLStreamReaderWithoutCaching());

			}

			if (com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3.class.equals(type)) {

				return com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
