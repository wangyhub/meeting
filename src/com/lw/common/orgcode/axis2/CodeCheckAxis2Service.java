package com.lw.common.orgcode.axis2;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.lw.common.orgcode.Arithmetic;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.OrgInfo;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfo5;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.PostOrgInfoResponse3;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.QueryCondition;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.QueryResult;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4;

/**
 * axis2查找组织机构
 * @author liuz
 *
 */
public class CodeCheckAxis2Service {
	private static CodeCheckAxis2Service instance = new CodeCheckAxis2Service();

	private static CodeCheckServiceServiceStub service = null;

	private CodeCheckAxis2Service() {
	}

	public static CodeCheckAxis2Service getInstance() throws AxisFault {
		if (service == null) {
			service = new CodeCheckServiceServiceStub();
		}
		return instance;
	}

	/**
	 * 通过组机构代码查找信息
	 * @param orgCode
	 * @return
	 * @throws RemoteException
	 */
	public QueryResult getOrgInfoByCode(String orgCode) throws RemoteException {
		String key = getKey();

		QueryCondition qc = new QueryCondition();

		SearchDMInfo sd = new SearchDMInfo();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", key));
		qc.setOrgCode(orgCode);
		sd.setArg0(qc);

		SearchDMInfo2 sd2 = new SearchDMInfo2();
		sd2.setSearchDMInfo(sd);
		SearchDMInfoResponse1 sr1 = service.searchDMInfo(sd2);

		return sr1.getSearchDMInfoResponse().get_return();
	}

	/**
	 * 通过组机构名称查找信息
	 * @param orgName
	 * @return
	 * @throws RemoteException
	 */
	public QueryResult getOrgInfoByName(String orgName) throws RemoteException {
		String key = getKey();

		QueryCondition qc = new QueryCondition();

		SearchDMInfo sd = new SearchDMInfo();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", key));
		qc.setOrgName(orgName);
		sd.setArg0(qc);

		SearchDMInfo2 sd2 = new SearchDMInfo2();
		sd2.setSearchDMInfo(sd);
		SearchDMInfoResponse1 sr1 = service.searchDMInfo(sd2);

		return sr1.getSearchDMInfoResponse().get_return();
	}

	/**
	 * 推送组织机构信息
	 * @param orgInfo
	 * @return
	 * @throws RemoteException
	 */
	public String postOrgInfo(OrgInfo orgInfo) throws RemoteException {
		String key = getKey();

		QueryCondition qc = new QueryCondition();

		PostOrgInfo po = new PostOrgInfo();
		po.setArg1(orgInfo);

		PostOrgInfo5 po5 = new PostOrgInfo5();
		po5.setPostOrgInfo(po);

		PostOrgInfoResponse3 pr3 = service.postOrgInfo(po5);

		return pr3.getPostOrgInfoResponse().get_return();
	}

	/**
	 * 获取动态密钥
	 * @return
	 * @throws RemoteException
	 */
	public String getKey() throws RemoteException {
		QueryCondition qc = new QueryCondition();

		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", "66E78B90751C49CFA6503F66504B1797"));

		SearchKEY sk = new SearchKEY();
		sk.setArg0(qc);

		SearchKEY0 sk0 = new SearchKEY0();
		sk0.setSearchKEY(sk);
		// 053254856
		SearchKEYResponse4 kr = service.searchKEY(sk0);

		String key = kr.getSearchKEYResponse().get_return();

		return key;
	}
}
