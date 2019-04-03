package com.lw.common.orgcode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.cncait.client package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _SearchKEY_QNAME = new QName(
			"http://wsserver.codeck.cncait.com/", "searchKEY");
	private final static QName _PostOrgInfoResponse_QNAME = new QName(
			"http://wsserver.codeck.cncait.com/", "postOrgInfoResponse");
	private final static QName _SearchDMInfo_QNAME = new QName(
			"http://wsserver.codeck.cncait.com/", "searchDMInfo");
	private final static QName _SearchDMInfoResponse_QNAME = new QName(
			"http://wsserver.codeck.cncait.com/", "searchDMInfoResponse");
	private final static QName _SearchKEYResponse_QNAME = new QName(
			"http://wsserver.codeck.cncait.com/", "searchKEYResponse");
	private final static QName _PostOrgInfo_QNAME = new QName(
			"http://wsserver.codeck.cncait.com/", "postOrgInfo");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.cncait.client
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PostOrgInfoResponse }
	 * 
	 */
	public PostOrgInfoResponse createPostOrgInfoResponse() {
		return new PostOrgInfoResponse();
	}

	/**
	 * Create an instance of {@link QueryResult }
	 * 
	 */
	public QueryResult createQueryResult() {
		return new QueryResult();
	}

	/**
	 * Create an instance of {@link QueryCondition }
	 * 
	 */
	public QueryCondition createQueryCondition() {
		return new QueryCondition();
	}

	/**
	 * Create an instance of {@link SearchDMInfoResponse }
	 * 
	 */
	public SearchDMInfoResponse createSearchDMInfoResponse() {
		return new SearchDMInfoResponse();
	}

	/**
	 * Create an instance of {@link SearchKEYResponse }
	 * 
	 */
	public SearchKEYResponse createSearchKEYResponse() {
		return new SearchKEYResponse();
	}

	/**
	 * Create an instance of {@link OrgInfo }
	 * 
	 */
	public OrgInfo createOrgInfo() {
		return new OrgInfo();
	}

	/**
	 * Create an instance of {@link SearchKEY }
	 * 
	 */
	public SearchKEY createSearchKEY() {
		return new SearchKEY();
	}

	/**
	 * Create an instance of {@link SearchDMInfo }
	 * 
	 */
	public SearchDMInfo createSearchDMInfo() {
		return new SearchDMInfo();
	}

	/**
	 * Create an instance of {@link PostOrgInfo }
	 * 
	 */
	public PostOrgInfo createPostOrgInfo() {
		return new PostOrgInfo();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SearchKEY }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wsserver.codeck.cncait.com/", name = "searchKEY")
	public JAXBElement<SearchKEY> createSearchKEY(SearchKEY value) {
		return new JAXBElement<SearchKEY>(_SearchKEY_QNAME, SearchKEY.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link PostOrgInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wsserver.codeck.cncait.com/", name = "postOrgInfoResponse")
	public JAXBElement<PostOrgInfoResponse> createPostOrgInfoResponse(
			PostOrgInfoResponse value) {
		return new JAXBElement<PostOrgInfoResponse>(_PostOrgInfoResponse_QNAME,
				PostOrgInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SearchDMInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wsserver.codeck.cncait.com/", name = "searchDMInfo")
	public JAXBElement<SearchDMInfo> createSearchDMInfo(SearchDMInfo value) {
		return new JAXBElement<SearchDMInfo>(_SearchDMInfo_QNAME,
				SearchDMInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SearchDMInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wsserver.codeck.cncait.com/", name = "searchDMInfoResponse")
	public JAXBElement<SearchDMInfoResponse> createSearchDMInfoResponse(
			SearchDMInfoResponse value) {
		return new JAXBElement<SearchDMInfoResponse>(
				_SearchDMInfoResponse_QNAME, SearchDMInfoResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SearchKEYResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wsserver.codeck.cncait.com/", name = "searchKEYResponse")
	public JAXBElement<SearchKEYResponse> createSearchKEYResponse(
			SearchKEYResponse value) {
		return new JAXBElement<SearchKEYResponse>(_SearchKEYResponse_QNAME,
				SearchKEYResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link PostOrgInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://wsserver.codeck.cncait.com/", name = "postOrgInfo")
	public JAXBElement<PostOrgInfo> createPostOrgInfo(PostOrgInfo value) {
		return new JAXBElement<PostOrgInfo>(_PostOrgInfo_QNAME,
				PostOrgInfo.class, null, value);
	}

}
