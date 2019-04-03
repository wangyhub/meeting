package com.lw.common.ema.sso.nbtravel;

import java.net.URL;

import com.lw.common.ema.common.MD5;

/**
 * ������������ͨ6.0�������ξ��û�ͬ���ͻ���
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-9-4 ����09:51:42
 */
public class Test {
    public static void main(String[] args) {
        try {
            CreateUserImpServiceLocator locator = new CreateUserImpServiceLocator();
            ICreateUser icreateUser = locator.getCreateUserImpPort(new URL(
                    "http://180.168.192.126:53080/ctc-emassh60/webService/nbtravelCreateUser?wsdl"));
            String pwd = MD5.MD5Encode("20121105");
            System.out.println(icreateUser.buildUser(pwd, "zh9","", "13585858586"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}