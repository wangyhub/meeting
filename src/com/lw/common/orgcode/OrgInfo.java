package com.lw.common.orgcode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for orgInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="orgInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="approveOrgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="approveOrgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="approveTs" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changeTs" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="countryOrArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryOrgAreaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="credenNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="industryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="industryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moneyTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="questionMarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registerTs" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="registeredCapital" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="superOrgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="superOrgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workerNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orgInfo", propOrder = { "approveOrgCode", "approveOrgName",
		"approveTs", "areaCode", "areaName", "businessScope", "businessType",
		"businessTypeName", "changeTs", "countryOrArea", "countryOrgAreaName",
		"credenNum", "industryCode", "industryName", "legalName", "moneyType",
		"moneyTypeName", "orgAddress", "orgCode", "orgId", "orgName",
		"orgType", "orgTypeName", "phoneNum", "questionMarks", "registerTs",
		"registeredCapital", "superOrgCode", "superOrgName", "workerNumber",
		"zipCode" })
public class OrgInfo {

	protected String approveOrgCode;
	protected String approveOrgName;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar approveTs;
	protected String areaCode;
	protected String areaName;
	protected String businessScope;
	protected String businessType;
	protected String businessTypeName;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar changeTs;
	protected String countryOrArea;
	protected String countryOrgAreaName;
	protected String credenNum;
	protected String industryCode;
	protected String industryName;
	protected String legalName;
	protected String moneyType;
	protected String moneyTypeName;
	protected String orgAddress;
	protected String orgCode;
	protected String orgId;
	protected String orgName;
	protected String orgType;
	protected String orgTypeName;
	protected String phoneNum;
	protected String questionMarks;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar registerTs;
	protected Double registeredCapital;
	protected String superOrgCode;
	protected String superOrgName;
	protected Integer workerNumber;
	protected String zipCode;

	/**
	 * Gets the value of the approveOrgCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApproveOrgCode() {
		return approveOrgCode;
	}

	/**
	 * Sets the value of the approveOrgCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApproveOrgCode(String value) {
		this.approveOrgCode = value;
	}

	/**
	 * Gets the value of the approveOrgName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getApproveOrgName() {
		return approveOrgName;
	}

	/**
	 * Sets the value of the approveOrgName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setApproveOrgName(String value) {
		this.approveOrgName = value;
	}

	/**
	 * Gets the value of the approveTs property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getApproveTs() {
		return approveTs;
	}

	/**
	 * Sets the value of the approveTs property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setApproveTs(XMLGregorianCalendar value) {
		this.approveTs = value;
	}

	/**
	 * Gets the value of the areaCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Sets the value of the areaCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAreaCode(String value) {
		this.areaCode = value;
	}

	/**
	 * Gets the value of the areaName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets the value of the areaName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAreaName(String value) {
		this.areaName = value;
	}

	/**
	 * Gets the value of the businessScope property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBusinessScope() {
		return businessScope;
	}

	/**
	 * Sets the value of the businessScope property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBusinessScope(String value) {
		this.businessScope = value;
	}

	/**
	 * Gets the value of the businessType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * Sets the value of the businessType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBusinessType(String value) {
		this.businessType = value;
	}

	/**
	 * Gets the value of the businessTypeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBusinessTypeName() {
		return businessTypeName;
	}

	/**
	 * Sets the value of the businessTypeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBusinessTypeName(String value) {
		this.businessTypeName = value;
	}

	/**
	 * Gets the value of the changeTs property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getChangeTs() {
		return changeTs;
	}

	/**
	 * Sets the value of the changeTs property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setChangeTs(XMLGregorianCalendar value) {
		this.changeTs = value;
	}

	/**
	 * Gets the value of the countryOrArea property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryOrArea() {
		return countryOrArea;
	}

	/**
	 * Sets the value of the countryOrArea property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountryOrArea(String value) {
		this.countryOrArea = value;
	}

	/**
	 * Gets the value of the countryOrgAreaName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryOrgAreaName() {
		return countryOrgAreaName;
	}

	/**
	 * Sets the value of the countryOrgAreaName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountryOrgAreaName(String value) {
		this.countryOrgAreaName = value;
	}

	/**
	 * Gets the value of the credenNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCredenNum() {
		return credenNum;
	}

	/**
	 * Sets the value of the credenNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCredenNum(String value) {
		this.credenNum = value;
	}

	/**
	 * Gets the value of the industryCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIndustryCode() {
		return industryCode;
	}

	/**
	 * Sets the value of the industryCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIndustryCode(String value) {
		this.industryCode = value;
	}

	/**
	 * Gets the value of the industryName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIndustryName() {
		return industryName;
	}

	/**
	 * Sets the value of the industryName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIndustryName(String value) {
		this.industryName = value;
	}

	/**
	 * Gets the value of the legalName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLegalName() {
		return legalName;
	}

	/**
	 * Sets the value of the legalName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLegalName(String value) {
		this.legalName = value;
	}

	/**
	 * Gets the value of the moneyType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMoneyType() {
		return moneyType;
	}

	/**
	 * Sets the value of the moneyType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMoneyType(String value) {
		this.moneyType = value;
	}

	/**
	 * Gets the value of the moneyTypeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMoneyTypeName() {
		return moneyTypeName;
	}

	/**
	 * Sets the value of the moneyTypeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMoneyTypeName(String value) {
		this.moneyTypeName = value;
	}

	/**
	 * Gets the value of the orgAddress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgAddress() {
		return orgAddress;
	}

	/**
	 * Sets the value of the orgAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgAddress(String value) {
		this.orgAddress = value;
	}

	/**
	 * Gets the value of the orgCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * Sets the value of the orgCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgCode(String value) {
		this.orgCode = value;
	}

	/**
	 * Gets the value of the orgId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * Sets the value of the orgId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgId(String value) {
		this.orgId = value;
	}

	/**
	 * Gets the value of the orgName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * Sets the value of the orgName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgName(String value) {
		this.orgName = value;
	}

	/**
	 * Gets the value of the orgType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * Sets the value of the orgType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgType(String value) {
		this.orgType = value;
	}

	/**
	 * Gets the value of the orgTypeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrgTypeName() {
		return orgTypeName;
	}

	/**
	 * Sets the value of the orgTypeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrgTypeName(String value) {
		this.orgTypeName = value;
	}

	/**
	 * Gets the value of the phoneNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * Sets the value of the phoneNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneNum(String value) {
		this.phoneNum = value;
	}

	/**
	 * Gets the value of the questionMarks property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQuestionMarks() {
		return questionMarks;
	}

	/**
	 * Sets the value of the questionMarks property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQuestionMarks(String value) {
		this.questionMarks = value;
	}

	/**
	 * Gets the value of the registerTs property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getRegisterTs() {
		return registerTs;
	}

	/**
	 * Sets the value of the registerTs property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setRegisterTs(XMLGregorianCalendar value) {
		this.registerTs = value;
	}

	/**
	 * Gets the value of the registeredCapital property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getRegisteredCapital() {
		return registeredCapital;
	}

	/**
	 * Sets the value of the registeredCapital property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setRegisteredCapital(Double value) {
		this.registeredCapital = value;
	}

	/**
	 * Gets the value of the superOrgCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSuperOrgCode() {
		return superOrgCode;
	}

	/**
	 * Sets the value of the superOrgCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSuperOrgCode(String value) {
		this.superOrgCode = value;
	}

	/**
	 * Gets the value of the superOrgName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSuperOrgName() {
		return superOrgName;
	}

	/**
	 * Sets the value of the superOrgName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSuperOrgName(String value) {
		this.superOrgName = value;
	}

	/**
	 * Gets the value of the workerNumber property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getWorkerNumber() {
		return workerNumber;
	}

	/**
	 * Sets the value of the workerNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setWorkerNumber(Integer value) {
		this.workerNumber = value;
	}

	/**
	 * Gets the value of the zipCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the value of the zipCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setZipCode(String value) {
		this.zipCode = value;
	}

}
