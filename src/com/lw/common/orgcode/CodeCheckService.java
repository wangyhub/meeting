package com.lw.common.orgcode;

public class CodeCheckService {

	private static CodeCheckService instance = new CodeCheckService();

	private static CodeCheckServiceService service = new CodeCheckServiceService();

	private CodeCheckService() {
	}

	public static CodeCheckService getInstance() {
		return instance;
	}

	public QueryResult getOrgInfoByCode(String orgCode) {
		CodeCheckServiceDelegate csd = service.getCodeCheckServicePort();

		String key = getKey(csd);

		QueryCondition qc = new QueryCondition();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", key));
		qc.setOrgCode(orgCode);
		QueryResult qr = csd.searchDMInfo(qc);

		return qr;
	}

	public QueryResult getOrgInfoByName(String orgName) {
		CodeCheckServiceDelegate csd = service.getCodeCheckServicePort();

		String key = getKey(csd);

		QueryCondition qc = new QueryCondition();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", key));
		qc.setOrgName(orgName);
		QueryResult qr = csd.searchDMInfo(qc);

		return qr;
	}

	public String postOrgInfo(OrgInfo orgInfo) {
		CodeCheckServiceDelegate csd = service.getCodeCheckServicePort();

		String key = getKey(csd);

		QueryCondition qc = new QueryCondition();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", key));

		if (orgInfo.getOrgCode() != null) {
			qc.setOrgCode(orgInfo.getOrgCode());
		}

		if (orgInfo.getOrgName() != null) {
			qc.setOrgName(orgInfo.getOrgName());
		}

		return csd.postOrgInfo(qc, orgInfo);

	}

	public String geKey() {
		CodeCheckServiceDelegate csd = service.getCodeCheckServicePort();
		QueryCondition qc = new QueryCondition();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", "66E78B90751C49CFA6503F66504B1797"));
		// qc.setOrgCode("609167345");
		String key = csd.searchKEY(qc);

		return key;
	}

	private String getKey(CodeCheckServiceDelegate csd) {
		QueryCondition qc = new QueryCondition();
		qc.setSystemCode("efpe");
		qc.setPassword(Arithmetic.getEncString("longwin", "66E78B90751C49CFA6503F66504B1797"));
		String key = csd.searchKEY(qc);

		return key;
	}

}
