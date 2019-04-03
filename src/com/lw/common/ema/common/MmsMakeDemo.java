package com.lw.common.ema.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.chinatricom.mmsfactory.Frame;
import com.chinatricom.mmsfactory.MmsFactory;

public class MmsMakeDemo {
	private static final Logger log = Logger.getLogger(MmsMakeDemo.class);

	/**
	 * 彩信制作demo
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		makeMMS();
	}

	/**
	 * 制作彩信demo
	 */

	public static void makeMMS() {
		String foldPath = "D:\\mmstest\\";
		// smil 文件名
		String smil = UUID.randomUUID() + ".smil";
		// 素材存放路径，比如本例 中，D:\mmstest\该目录下已经有 a.jpg,a.txt, b.jpg,b.txt,几个文件
		// 需要把 a.jpg,a.txt 生成第一帧；b.jpg,b.txt 生成第二帧
		// 制作完成后,在该目录下 会新生成一个对应的smil文件。
		// [ smil文件, a.jpg,a.txt,b.jpg,b.txt]这几个文件共同组成一个彩信产品

		List<Frame> frameList = new ArrayList<Frame>();
		// 第一帧
		Frame frame1 = new Frame();
		// 本帧图片(路径：D:\mmstest\a.jpg)
		frame1.setMImage("a.jpg");
		// 本帧文本(路径：D:\mmstest\a.txt)
		frame1.setMTxt("a.txt");
		// 本帧播放时间
		frame1.setTime(5000);
		// 本帧文本位置（1 文本位于图片下方,0 文本位于图片上方）
		frame1.setTxtPosition(1);
		frameList.add(frame1);
		// 第二帧
		Frame frame2 = new Frame();
		frame2.setMImage("b.jpg");
		frame2.setMTxt("b.txt");
		frame2.setTime(500);
		frame2.setTxtPosition(0);
		frameList.add(frame2);
		// 生成产品文件（smil文件）
		MmsFactory mms = new MmsFactory();
		mms.setFrameList(frameList);
		mms.setSmilOutPutPath(foldPath + smil);
		boolean resflg = mms.generateSmil();
		if (resflg)
			log.info("彩信制作成功，smil 路径：" + foldPath + smil);

	}
}
