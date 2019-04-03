package com.lw.common.ema.ea.sms.socket.message;


import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;

import com.chinatricom.common.XmlPackParser;
import com.chinatricom.message.ctcserverea.CTCEaLoginResp;
import com.chinatricom.slidewindow.IRespPackage;
import com.chinatricom.slidewindow.ISMSHead;
import com.lw.common.ema.ea.sms.socket.EaCtcppChannel;

/**
 * ��������CTCPP������Ӧ��ṹ
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 ����04:45:50
 */
public class EaLoginResp implements IRespPackage {

    public CTCEaLoginResp mStub = new CTCEaLoginResp();

    private EaCtcppChannel channel;

    public EaLoginResp() {

    }

    public EaLoginResp(EaCtcppChannel channel) {
        this.channel = channel;
    }

    public byte[] getBytes() {
        return mStub.pack().getBytes();
    }

    public boolean parsePackage(byte[] bodyPack) {
        boolean res = true;
        if (bodyPack != null) {
            res = mStub.unPack(new String(bodyPack));
            if (StringUtils.isNotBlank(mStub.getLoginMessageRes()) && null != this.channel) {
                XmlPackParser _parser = new XmlPackParser(mStub.getLoginMessageRes());
                String keyStr = _parser.decode("key");
                try {
                    /** ����xml */
                    BASE64Decoder decoder = new BASE64Decoder();
                    this.channel.setKey(decoder.decodeBuffer(keyStr));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public void setHead(ISMSHead head) {
        EaHead _head = (EaHead) head;
        mStub.setHead(_head.mHead);
    }

    public int getResult() {
        int res = -1;
        if (StringUtils.isNotBlank(mStub.getLoginMessageRes())) {
            XmlPackParser _parser = new XmlPackParser(mStub.getLoginMessageRes());
            String _content = _parser.decode("stat");
            System.out.println("��¼���أ�" + _content);
            if ("r:000".equals(_content)) {
                res = 0;
            } else {
                res = -1;
            }
        }
        return res;
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub
        return null;
    }

}
