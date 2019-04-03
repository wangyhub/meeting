package com.lw.common.ema.jwsserver.mms;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Calendar;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import com.lw.common.ema.common.MD5;

/**
 * ����ͨ�·�����websevice�ӿ�
 * 
 * @author 8521
 * @version 1.0
 */
public class MmsTest {
    private static final Logger log = Logger.getLogger(MmsTest.class);

    public static void main(String[] args) {
        String url = "http://localhost:8080/ctc-emassh60/webService/mmsOper?wsdl";
        String mmsZipFilePath = "D:\\mms2.zip";
        String account = "8001";
        String password = MD5.MD5Encode("123.com");
        String subject = "�Ժ����0200";
        // ���ַ�ʽ�ϴ�����
        // 2c92826b3a8673d1013a86741d310014
        // 2c92826b3a8673d1013a867453cf0015
        String mmsproductID = "";
        MmsFileGroup[] _mmsFileGroupArray = getUploadMmsInfo();
        mmsproductID = upMms(url, subject, _mmsFileGroupArray, account, password);
        System.out.println(mmsproductID);
//      mmsproductID = upMmsZip(url, subject, mmsZipFilePath, account, password);
        String sendRes = sendMms(url, account, password, "13701825491", "2c92826b3adfca36013adffdb15d002d", "333");
        
        getMmsReport(url, account, password);

    }

    /**
     * �ϴ����Ų�Ʒ(zip��ʽ)
     * 
     * @param url
     *            url
     * 
     * @param mmsZipFilePath
     *            �����ļ�·��(zip�ļ�)
     * 
     * @param account
     *            �˻�
     * @param password
     *            ����
     * @param subject
     *            ���ű���
     */
    public static String upMmsZip(String url, String subject, String mmsZipFilePath, String account, String password) {
        MmsOperatorImpServiceLocator locator = new MmsOperatorImpServiceLocator();
        String res = "";
        try {
            IMmsOperator ioperator = locator.getMmsOperatorImpPort(new URL(url));
            javax.xml.rpc.holders.StringHolder mmsproductID = new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder errMsg = new javax.xml.rpc.holders.StringHolder();
            FileInputStream fis = new FileInputStream(new File(mmsZipFilePath));
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();
            BASE64Encoder baseEncoder = new BASE64Encoder();
            String zipFileData = baseEncoder.encodeBuffer(b);
            ioperator.upMmsZipfile(account, password, subject, zipFileData, mmsproductID, errMsg);
            if (errMsg != null && errMsg.value != null && errMsg.value.length() > 0)
                log.info("\n\n upMmsZipfile Res Error:" + errMsg.value);
            else if (mmsproductID != null) {
                res = mmsproductID.value;
                log.info("\n\n upMmsZipfile Res:" + mmsproductID.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * �ϴ����Ų�Ʒ
     * 
     * @param url
     *            url
     * 
     * @param _mmsFileGroupArray
     *            �������
     * 
     * @param account
     *            �˻�
     * @param password
     *            ����
     * @param subject
     *            ���ű���
     */
    public static String upMms(String url, String subject, MmsFileGroup[] _mmsFileGroupArray, String account,
            String password) {
        MmsOperatorImpServiceLocator locator = new MmsOperatorImpServiceLocator();
        String res = "";
        try {
            IMmsOperator ioperator = locator.getMmsOperatorImpPort(new URL(url));
            javax.xml.rpc.holders.StringHolder mmsproductID = new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder errMsg = new javax.xml.rpc.holders.StringHolder();
            ioperator.upMms(account, password, subject, _mmsFileGroupArray, mmsproductID, errMsg);
            if (errMsg != null && errMsg.value != null && errMsg.value.length() > 0)
                log.info("\n\n upMms Res Error:" + errMsg.value);
            else if (mmsproductID != null) {
                res = mmsproductID.value;
                log.info("\n\n upMms Res:" + mmsproductID.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }

    /**
     * ���Ͳ���
     * 
     * @param url
     *            url
     * @param account
     *            �˻�
     * @param password
     *            ����
     * @param phone
     *            �·�����
     * @param productId
     *            ��Ʒid(���� http/UploadMms �ӿڷ��ص�id)
     */
    public static String sendMms(String url, String account, String password, String phone, String productId,String mmsType) {

        MmsOperatorImpServiceLocator locator = new MmsOperatorImpServiceLocator();
        String res = "";
        try {
            IMmsOperator ioperator = locator.getMmsOperatorImpPort(new URL(url));
            javax.xml.rpc.holders.StringHolder resMsg = new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder errMsg = new javax.xml.rpc.holders.StringHolder();
            ioperator.sendMms(account, password, productId, mmsType, Calendar.getInstance(), phone, resMsg, errMsg);
            if (errMsg != null && errMsg.value != null && errMsg.value.length() > 0)
                log.info("\n\n sendMms Res Error:" + errMsg.value);
            else if (resMsg != null) {
                res = resMsg.value;
                log.info("\n\n sendMms Res:" + resMsg.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    
    }
    
    public static String getMmsReport(String url, String account, String password) {

        MmsOperatorImpServiceLocator locator = new MmsOperatorImpServiceLocator();
        String res = "";
        try {
            IMmsOperator ioperator = locator.getMmsOperatorImpPort(new URL(url));
            javax.xml.rpc.holders.StringHolder resMsg = new javax.xml.rpc.holders.StringHolder();
            javax.xml.rpc.holders.StringHolder errMsg = new javax.xml.rpc.holders.StringHolder();
            ioperator.getMmsReport(account, password,resMsg, errMsg);
            if (errMsg != null && errMsg.value != null && errMsg.value.length() > 0)
                log.info("\n\n getMmsReport Res Error:" + errMsg.value);
            else if (resMsg != null) {
                res = resMsg.value;
                log.info("\n\n getMmsReport Res:" + resMsg.value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    
    }

    /**
     * ��ȡ�ϴ����
     */
    private static MmsFileGroup[] getUploadMmsInfo() {
        MmsFileGroup[] mmsFileGroup = null;
        try {
            mmsFileGroup = new MmsFileGroup[12];
            FileInputStream fis = new FileInputStream(new File("c:\\logo.jpg"));
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();
            BASE64Encoder baseEncoder = new BASE64Encoder();
            String img = baseEncoder.encodeBuffer(b);
            // ��һ֡�ı�
            mmsFileGroup[0] = new MmsFileGroup();
            mmsFileGroup[0].setText_FileName("1.txt");
            mmsFileGroup[0].setText_FileData(baseEncoder.encodeBuffer("�𾴵�zhaohong,���ѳɹ����?���Ϊ��A0001,����ʱ�䣺20120101�ĳ˻�����,��ѡ�����λ��Ϊ��E15".getBytes()));
            mmsFileGroup[0].setImage_FileName("a.jpg");
            mmsFileGroup[0].setImage_FileData(img);
            mmsFileGroup[0].setTxtPosition(2);
            mmsFileGroup[0].setPlayTime(500);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return mmsFileGroup;
    }
}
