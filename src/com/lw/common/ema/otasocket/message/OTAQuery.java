package com.lw.common.ema.otasocket.message;


import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISMSPackage;

public class OTAQuery implements ISMSPackage {
	
	public com.chinatricom.message.otaserver.OTAQuery mStub = new com.chinatricom.message.otaserver.OTAQuery();

	public byte[] getBytes() {
		return mStub.pack();
	}

	public boolean parsePackage(byte[] bodyPack) {
		if(bodyPack!=null)
			return  mStub.unPack(bodyPack) == 0;
		
		return true;
	}

	public void setHead(ISMSHead head) {
	    OTAHead _head = (OTAHead) head;
		mStub.setHead(_head.mHead);

	}

}
