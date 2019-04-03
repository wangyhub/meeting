package com.lw.common.orgcode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for queryCondition complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="queryCondition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryCondition", propOrder = { "orgCode", "orgName",
		"password", "systemCode", "userName" })
public class QueryCondition {

	protected String orgCode;
	protected String orgName;
	protected String password;
	protected String systemCode;
	protected String userName;

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
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * Gets the value of the systemCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * Sets the value of the systemCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSystemCode(String value) {
		this.systemCode = value;
	}

	/**
	 * Gets the value of the userName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the value of the userName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserName(String value) {
		this.userName = value;
	}

}
