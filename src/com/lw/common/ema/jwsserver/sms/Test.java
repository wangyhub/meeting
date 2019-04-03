package com.lw.common.ema.jwsserver.sms;

import java.net.URL;

import com.lw.common.ema.common.MD5;

/**
 * ������������ͨ6.0�û��ӿڶ��ſͻ���
 * @version: 1.0
 * @author: 8521
 * @date: 2012-9-4 ����09:51:42 
 */
public class Test {
	public static void main(String[] args) {
		SmsOperatorImpServiceLocator locator = new SmsOperatorImpServiceLocator();
		try {
			ISmsOperator ioperator = locator
					.getSmsOperatorImpPort(new URL(
							//"sms.cnca:8080/ema/webService/smsOper?wsdl"));  
		"http://192.168.5.34:8080/ema/webService/smsOper?wsdl"));  
							//Web Service�����ַ�����÷�������DNS������Ϊ192.168.2.2�������轫sms.cnca�滻Ϊ192.168.5.34
			System.out
					.println("------------------------�������ж���------------------------");
			// ���Ͷ���
			MtMessage msg = new MtMessage();
			msg.setContent("邮件发送成功,带有字号码"); //���Ͷ�������
			//msg.setSubCode("145");   //�Ӻ��룺0-3λ
			String[] phoneNumber = new String[1];
			phoneNumber[0] = "18661204635";
			msg.setPhoneNumber(phoneNumber);
			MtMessageRes mtMessageRes = ioperator.sendSms("cefpr", MD5
					.MD5Encode("3tyn(iz8"), "01", msg);  //����Web Service�û������룬01Ϊҵ�����ͺ���
			if ("r:000".equals(mtMessageRes.getSubStat())) {
				System.out.println("����ɹ���" + mtMessageRes.getSubStat()
						+ ";����: " + mtMessageRes.getSubStatDes());
				System.out.println("�����ύ������ˮ��:" + mtMessageRes.getSmsId());
				for (MtMessageResDetail detail : mtMessageRes.getResDetail()) {
					System.out.println("�����ֻ��:" + detail.getPhoneNumber()
							+ ";�ύ״̬:" + detail.getStat() + ";״̬����:"
							+ detail.getStatDes());

				}
			} else {
				System.out.println("����ʧ��,�����룺" + mtMessageRes.getSubStat()
						+ ";��������: " + mtMessageRes.getSubStatDes());
			}

			System.out
					.println("------------------------��ȡ״̬����------------------------");
			// ��ȡ״̬����
			ReportMessageRes reportMessageRes = ioperator.getReport("test001", MD5
					.MD5Encode("123456.com"));
			if ("r:000".equals(reportMessageRes.getSubStat())) {
				System.out.println("����ɹ���" + reportMessageRes.getSubStat()
						+ ";����: " + reportMessageRes.getSubStatDes());
				if (reportMessageRes.getResDetail() != null
						&& reportMessageRes.getResDetail().length > 0) {
					for (ReportMessageResDetail resdeail : reportMessageRes
							.getResDetail()) {
						System.out.println("�����ֻ��:" + resdeail.getPhoneNumber()
								+ ";��ˮ��:" + resdeail.getSmsId() + ";����״̬:"
								+ resdeail.getStat() + "; ״̬����:"
								+ resdeail.getStatDes());
					}
				} else {
					System.out.println("��ʱû��״̬������Ϣ��");
				}
			} else {
				System.out.println("����ʧ��,�����룺" + reportMessageRes.getSubStat()
						+ ";��������: " + reportMessageRes.getSubStatDes());
			}
			// ��ȡ�ʻ����ж���
			System.out
					.println("------------------------��ȡ���ж���------------------------");
			MoMessageRes moMessageRes = ioperator.getSms("test001", MD5
					.MD5Encode("123456.com"));
			if ("r:000".equals(moMessageRes.getRevStat())) {
				System.out.println("����ɹ���" + moMessageRes.getRevStat()
						+ ";����: " + moMessageRes.getRevStatDes());
				if (moMessageRes.getResDetail() != null
						&& moMessageRes.getResDetail().length > 0) {
					for (MoMessageResDetail detail : moMessageRes
							.getResDetail()) {
						System.out.println("�����ֻ��:" + detail.getPhoneNumber()
								+ ";��������:" + detail.getContent() + ";��չ��:"
								+ detail.getSubCode() + ";����ʱ��:"
								+ detail.getRevTime());

					}
				} else {
					System.out.println("��ʱû�����ж��š�");
				}
			} else {
				System.out.println("����ʧ��,�����룺" + moMessageRes.getRevStat()
						+ ";��������: " + moMessageRes.getRevStatDes());
			}

			// ��ȡ�ʻ����
			System.out
					.println("------------------------��ȡ�ʻ����------------------------");

			BalanceRes balanceRes = ioperator.getBalance("test001", MD5
					.MD5Encode("123456.com"));
			if ("r:000".equals(balanceRes.getRevStat())) {
				System.out.println("����ɹ���" + balanceRes.getRevStat()
						+ ";�ʻ������Ϣ: " + balanceRes.getRevStatDes());
			} else {
				System.out.println("����ʧ��,�����룺" + balanceRes.getRevStat()
						+ ";��������: " + balanceRes.getRevStatDes());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}