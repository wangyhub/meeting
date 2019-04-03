package com.lw.common.ema.jwsserver.sms.contacts;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lw.common.ema.common.MD5;

/**
 * ������������ͨƽ̨(6.0)ͨѶ¼����ӿڿͻ���
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-9-4 ����09:51:42
 */
public class Test {

	private static String url = "http://127.0.0.1:8080/ctc-emassh60/webService/contactsOper?wsdl";

	// account:���û���,
	// password:�����롱,
	private static String account = "8523";

	private static String password = MD5.MD5Encode("123.com");

	public static void main(String[] args) {

		// addGroup();
		 //modifyGroup();
		// deleteGroup();

		 addPerson();
		// modifyPerson();
		// deletePerson();
		// loadPerson();
		//deletePersonByGroupId();

	}

	/**
	 * �����������½�����
	 * 
	 * @author: 8521
	 * @date: 2012-10-9 ����05:00:39
	 */
	public static void addGroup() {
		System.out
				.println("------------------------��ʼ�½�����------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\"" + account + "\",password:\""
					+ password + "\",names:['','zhang','�˴�']}";
			String res = ioperator.addGroup(message);
			try {
				JSONObject jsonObject = JSONObject.fromObject(res);
				// JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("�½����鷵��:" + jsonObject);
			} catch (Exception e) {
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("�½����鷵��:" + jsonArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------�����½�����------------------------");

	}

	/**
	 * �����������޸ķ���
	 * 
	 * @author: 8521
	 * @date: 2012-10-9 ����05:00:47
	 */
	public static void modifyGroup() {
		System.out
				.println("------------------------��ʼ�޸ķ���------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			String gid = "6";
			String newGroupName = "�·���";
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\""
					+ account
					+ "\",password:\""
					+ password
					+ "\",groups:[{gid:'1',newname:'li'},{gid:'10',newname:'li3'},{gid:'11',newname:'li2'}]}";
			String res = ioperator.modifyGroup(message);
			try {
				// JSONObject jsonObject = JSONObject.fromObject(res);
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("�޸ķ��鷵��:" + jsonArray);
			} catch (Exception e) {
				JSONObject jsonObject = JSONObject.fromObject(res);
				System.out.println("�޸ķ��鷵��:" + jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------�����޸ķ���------------------------");

	}

	/**
	 * ����������ɾ�����
	 * 
	 * @author: 8521
	 * @date: 2012-10-9 ����05:00:54
	 */
	public static void deleteGroup() {
		System.out
				.println("------------------------��ʼɾ�����------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			String gid = "6";
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\"" + account + "\",password:\""
					+ password + "\",gids:['12','1','2','3']}";
			String res = ioperator.deleteGroup(message);
			try {
				// JSONObject jsonObject = JSONObject.fromObject(res);
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("ɾ����鷵��:" + jsonArray);
			} catch (Exception e) {
				JSONObject jsonObject = JSONObject.fromObject(res);
				System.out.println("ɾ����鷵��:" + jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------����ɾ�����------------------------");
	}

	/**
	 * �����������½���ϵ��
	 * 
	 * @author: 8521
	 * @date: 2012-10-9 ����05:00:39
	 */
	public static void addPerson() {
		System.out
				.println("------------------------��ʼ�½���ϵ��------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			// account:���û���,
			// password:�����롱,
			// gid:��Ҫ����ķ���id��,
			// pname:����ϵ������,
			// mobiles:���ֻ���롱,
			// email:�������ַ��
			String mobiles = "15000792782";
			String email = "8521@dahantc.com";
			String pName = "�Ժ�002";
			String gid = "4";
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\""
					+ account
					+ "\",password:\""
					+ password
					+ "\",persons:[{gid:'10',pname:'zhans',email:'lis@dahan.com',mobiles:'13701825491'},{gid:'5',pname:'zhans1',email:'lis1@dahan.com',mobiles:'13701825492'},{gid:'6',pname:'zhans2',email:'lis2@dahan.com',mobiles:'13701825493'} ]}";
			String res = ioperator.addPerson(message);
			try {
				// JSONObject jsonObject = JSONObject.fromObject(res);
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("�½���ϵ�˷���:" + jsonArray);
			} catch (Exception e) {
				JSONObject jsonObject = JSONObject.fromObject(res);
				System.out.println("�½���ϵ�˷���:" + jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------�����½���ϵ��------------------------");

	}

	/**
	 * �����������޸���ϵ��
	 * 
	 * @author: 8521
	 * @date: 2012-10-9 ����07:09:04
	 */
	public static void modifyPerson() {
		System.out
				.println("------------------------��ʼ�޸���ϵ��------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			// account:���û���,
			// password:�����롱,
			// pid:��ԭ��ϵ��id��,
			// gid:��Ҫ���¼���ķ���id��,
			// pname:����ϵ������,
			// mobiles:���ֻ���롱,
			// email:�������ַ��
			String mobiles = "15000792785";
			String email = "8521@dahantc.com";
			String pName = "�Ժ�003";
			String gid = "4";
			String pid = "6";
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\""
					+ account
					+ "\",password:\""
					+ password
					+ "\",persons:[{gid:'10',pid:'6',pname:'li',mobiles:'1370182549',email:'li@dahan.com'},{gid:'2',pid:'7',pname:'li1',mobiles:'13701825492',email:'li1@dahan.com'},{gid:'13',pid:'8',pname:'li5',mobiles:'13701825495',email:'li5@dahan.com'}]}";
			String res = ioperator.modifyPerson(message);
			try {
				// JSONObject jsonObject = JSONObject.fromObject(res);
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("�޸���ϵ�˷���:" + jsonArray);
			} catch (Exception e) {
				JSONObject jsonObject = JSONObject.fromObject(res);
				System.out.println("�޸���ϵ�˷���:" + jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------�����޸���ϵ��------------------------");

	}

	/**
	 * ����������ɾ����ϵ��
	 * 
	 * @author: 8521
	 * @date: 2012-10-10 ����09:18:35
	 */
	public static void deletePerson() {
		System.out
				.println("------------------------��ʼɾ����ϵ��------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			// account:���û���,
			// password:�����롱,
			// pid:����ϵ��id��,
			String pid = "81";
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\"" + account + "\",password:\""
					+ password + "\",pids:['1','2','3','4']}";
			String res = ioperator.deletePerson(message);
			try {
				// JSONObject jsonObject = JSONObject.fromObject(res);
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("ɾ����ϵ�˷���:" + jsonArray);
			} catch (Exception e) {
				JSONObject jsonObject = JSONObject.fromObject(res);
				System.out.println("ɾ����ϵ�˷���:" + jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------����ɾ����ϵ��------------------------");

	}

	public static void loadPerson() {
		System.out
				.println("------------------------��ʼ��ȡ��ϵ����Ϣ------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		try {
			// account:���û���,
			// password:�����롱,
			// pid:����ϵ��id��,
			String pid = "10";
			String gid = "14";
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));
			String message = "{account:\"" + account + "\",password:\""
					+ password + "\",gid:\"" + gid + "\",pid:\"" + pid + "\"}";
			String res = ioperator.loadPerson(message);
			try {
				JSONObject jsonObject = JSONObject.fromObject(res);
				// JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("��ȡ��ϵ����Ϣ����:" + jsonObject);
			} catch (Exception e) {
				JSONArray jsonArray = JSONArray.fromObject(res);
				System.out.println("��ȡ��ϵ����Ϣ����:" + jsonArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("------------------------�����ȡ��ϵ����Ϣ------------------------");

	}

	private static void deletePersonByGroupId() {
		System.out
				.println("------------------------��ʼɾ�������ϵ��------------------------");
		ContactsService4JSONImplServiceLocator locator = new ContactsService4JSONImplServiceLocator();
		String message = "{account:\"" + account + "\",password:\"" + password
				+ "\",gids:[{gid:'',pids:['1','2']},{gid:'11',pids:[]},{gid:'10',pids:['5','6']},{gid:'10',pids:['7','8']}]}";
		String res = "";
		try {
			IContactsService4JSON ioperator = locator
					.getContactsService4JSONImplPort(new URL(url));

			res = ioperator.deletePersonByGroupId(message);
			JSONObject jsonObject = JSONObject.fromObject(res);
			System.out.println("��ȡ��ϵ����Ϣ����:" + jsonObject);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (ServiceException e) {

			e.printStackTrace();
		} catch (RemoteException e) {

			e.printStackTrace();
		} catch (Exception e) {
			JSONArray jsonArray = JSONArray.fromObject(res);
			System.out.println("��ȡ��ϵ����Ϣ����:" + jsonArray);
		}
	}
}