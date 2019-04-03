package com.lw.common.orgcode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for postOrgInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="postOrgInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://wsserver.codeck.cncait.com/}queryCondition" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://wsserver.codeck.cncait.com/}orgInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postOrgInfo", propOrder = { "arg0", "arg1" })
public class PostOrgInfo {

	protected QueryCondition arg0;
	protected OrgInfo arg1;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link QueryCondition }
	 * 
	 */
	public QueryCondition getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link QueryCondition }
	 * 
	 */
	public void setArg0(QueryCondition value) {
		this.arg0 = value;
	}

	/**
	 * Gets the value of the arg1 property.
	 * 
	 * @return possible object is {@link OrgInfo }
	 * 
	 */
	public OrgInfo getArg1() {
		return arg1;
	}

	/**
	 * Sets the value of the arg1 property.
	 * 
	 * @param value
	 *            allowed object is {@link OrgInfo }
	 * 
	 */
	public void setArg1(OrgInfo value) {
		this.arg1 = value;
	}

}
