package com.lw.common.orgcode.axis2;

import com.lw.common.orgcode.Arithmetic;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.QueryCondition;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfo2;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchDMInfoResponse1;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEY0;
import com.lw.common.orgcode.axis2.CodeCheckServiceServiceStub.SearchKEYResponse4;

public class CheckCode {

	/**
	 * @param args
	 * @throws Exception
	 */
	public String CheckOrgCode(String code){
		String orgName = "";
		try{
			CodeCheckServiceServiceStub service = new CodeCheckServiceServiceStub();
			QueryCondition qc = new QueryCondition();
	
			qc.setSystemCode("efpe");
			qc.setPassword(Arithmetic.getEncString("longwin", "66E78B90751C49CFA6503F66504B1797"));
			// qc.setOrgCode("609167345");
	
			SearchKEY sk = new SearchKEY();
			sk.setArg0(qc);
	
			SearchKEY0 sk0 = new SearchKEY0();
			sk0.setSearchKEY(sk);
			// 053254856
			SearchKEYResponse4 kr = service.searchKEY(sk0);
	
			String key = kr.getSearchKEYResponse().get_return();
			System.out.println(key);
	
			SearchDMInfo sd = new SearchDMInfo();
			qc.setSystemCode("efpe");
			qc.setPassword(Arithmetic.getEncString("longwin", key));
			qc.setOrgCode(code);//"609167345"
			sd.setArg0(qc);	
			SearchDMInfo2 sd2 = new SearchDMInfo2();
			sd2.setSearchDMInfo(sd);
			SearchDMInfoResponse1 sr1 = service.searchDMInfo(sd2);	
			//System.out.println(sr1.getSearchDMInfoResponse().get_return().getMessage());			
			//System.out.println(sr1.getSearchDMInfoResponse().get_return().getOrgInfos()[0].getOrgName());
			orgName = sr1.getSearchDMInfoResponse().get_return().getOrgInfos()[0].getOrgName();
			
		}catch (Exception e) {
			System.out.println("组织机构代码校验异常");
		}
		return orgName;

	}

}
