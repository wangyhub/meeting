package com.lw.common.ema.jwsserver.smsinter;

import java.net.URL;

import com.lw.common.ema.common.MD5;

/**
 * ������������ͨ6.0Ӧ�ýӿڶ��ſͻ���ʾ��
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-9-4 ����09:51:07
 */
public class TestInterf {
	public static void main(String[] args) {
		SmsOperatorImpInterServiceLocator locator = new SmsOperatorImpInterServiceLocator();
		try {
			ISmsOperatorInter ioperator = locator
					.getSmsOperatorImpInterPort(new URL(
							"http://192.168.0.52:8080/ema/webService/smsOperInter?wsdl"));
			System.out
					.println("------------------------�������ж��ſ�ʼ------------------------");
			// ���Ͷ���
			MtMessage msg = new MtMessage();
			msg.setContent("Ӧ�ýӿ�����99���Ӻ���123456����֯������8500");
			msg.setSubCode("");
			// ��ɼ��������ֻ����
			int phoneLen = 1000;
			java.lang.String[] phoneNumber = new java.lang.String[1];
			// for (int i = 10000; i < phoneNumber.length +10000; i++) {
			// phoneNumber[i - 10000] = "150007" + i;
			// }
			// 18918700333
			phoneNumber[0] = "18611907868";
			msg.setPhoneNumber(phoneNumber);
			MtMessageRes mtMessageRes = ioperator.sendSms("test001",
					MD5.MD5Encode("123.com"), "8500", "01", msg);
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
					.println("------------------------�������ж��Ž���------------------------");
			System.out.println("\n\n");
			System.out
					.println("------------------------��ȡ״̬���濪ʼ------------------------");
			// ��ȡ״̬����
			ReportMessageRes reportMessageRes = ioperator.getReport("test001",
					MD5.MD5Encode("123.com"));
			if ("r:000".equals(reportMessageRes.getSubStat())) {
				System.out
						.println("����ɹ���" + reportMessageRes.getSubStat()
								+ ";����: " + reportMessageRes.getSubStatDes());
				if (reportMessageRes.getResDetail() != null
						&& reportMessageRes.getResDetail().length > 0) {
					for (ReportMessageResDetail resdeail : reportMessageRes
							.getResDetail()) {
						System.out.println("�����ֻ��:" + resdeail.getPhoneNumber()
								+ ";��ˮ��:" + resdeail.getSmsId()
								+ ";����״̬:" + resdeail.getStat() + "; ״̬����:"
								+ resdeail.getStatDes());
					}
				} else {
					System.out.println("��ʱû��״̬������Ϣ��");
				}
			} else {
				System.out.println("����ʧ��,�����룺" + reportMessageRes.getSubStat()
						+ ";��������: "
						+ reportMessageRes.getSubStatDes());
			}
			System.out
					.println("------------------------��ȡ״̬�������------------------------");
			System.out.println("\n\n");
			// ��ȡ�ʻ����ж���
			System.out
					.println("------------------------��ȡ���ж��ſ�ʼ------------------------");
			MoMessageRes moMessageRes = ioperator.getSms("test001",
					MD5.MD5Encode("123.com"));
			if ("r:000".equals(moMessageRes.getRevStat())) {
				System.out.println("����ɹ���" + moMessageRes.getRevStat()
						+ ";����: " + moMessageRes.getRevStatDes());
				if (moMessageRes.getResDetail() != null
						&& moMessageRes.getResDetail().length > 0) {
					for (MoMessageResDetail detail : moMessageRes
							.getResDetail()) {
						System.out.println("�����ֻ��:" + detail.getPhoneNumber()
								+ ";��������:" + detail.getContent()
								+ ";��չ��:" + detail.getSubCode() + ";����ʱ��:"
								+ detail.getRevTime());

					}
				} else {
					System.out.println("��ʱû�����ж��š�");
				}
			} else {
				System.out.println("����ʧ��,�����룺" + moMessageRes.getRevStat()
						+ ";��������: " + moMessageRes.getRevStatDes());
			}
			System.out
					.println("------------------------��ȡ���ж��Ž���------------------------");
			System.out.println("\n\n");
			// ��ȡ�ʻ����
			System.out
					.println("------------------------��ȡ�ʻ���ʼ------------------------");

			BalanceRes balanceRes = ioperator.getBalance("rtx00",
					MD5.MD5Encode("123.com"), "8500");
			if ("r:000".equals(balanceRes.getRevStat())) {
				System.out.println("����ɹ���" + balanceRes.getRevStat()
						+ ";�ʻ������Ϣ: " + balanceRes.getRevStatDes());
			} else {
				System.out.println("����ʧ��,�����룺" + balanceRes.getRevStat()
						+ ";��������: " + balanceRes.getRevStatDes());
			}
			System.out
					.println("------------------------��ȡ�ʻ�������------------------------");
			System.out.println("\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}