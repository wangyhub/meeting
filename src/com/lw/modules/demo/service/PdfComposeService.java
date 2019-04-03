/**
 * Copyright &copy; 2012-2020 <a href="https://www.longwi.com">LongWin</a> All rights reserved.
 */
package com.lw.modules.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lw.common.config.Global;
import com.lw.common.utils.FTPUtil;
import com.lw.common.utils.StringUtils;
import com.lw.modules.base.dao.TBaFileDao;
import com.lw.modules.base.entity.TBaFile;
import com.lw.modules.sys.utils.UserUtils;

/**
 * 功能：PDF合成Service
 * 说明：具体的合成代码，均在本类中。
 * 作者： handf
 * 创建时间： 2015-08-24
 */
@Service
@Transactional(readOnly = true)
public class PdfComposeService{

	@Autowired
	private TBaFileDao tBaFileDao;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 功能：文件合成总方法
	 * 步骤： 1、获取要合成的文件集合
	 *      2、创建合成的文件信息
	 *      3、开始文件合成
	 *         3.1、连接服务器的准备工作
	 *         3.2、创建合成的文件类
	 *         3.3、文件合并处理
	 *         3.4、关闭相关流
	 *      4、保存合成完后的文件信息
	 * @param tBaFileList 需要合成的文件集合
	 * @return
	 */
    public TBaFile createPublictyPdf(List<TBaFile> tBaFileList){
    	//1、获取要合成的文件集合
        List<TBaFile> tBaFiles = tBaFileList;
        
        //2、创建合成的文件信息
        TBaFile tBaFile = new TBaFile();
        tBaFile.preInsert();
        tBaFile.setIsMarge("01");//是否合成成功
        tBaFile.setFileType(".pdf");//因为是PDF合成，所以合成的文件也是PDF格式
        tBaFile.setFileName("数据库存储的文件名");//数据库存储的文件名称（下载时起到展示作用）
        
        //3、开始文件合成
        String path = Global.getConfig("FTP_PATH");// 文件夹根路径
        FTPClient ftp = new FTPClient();  
        try {
        	//3.1、连接FTP服务器
        	FTPUtil ftpUtil = new FTPUtil();
        	ftp = ftpUtil.getFtpConnect(); // 获得FTP服务器的连接
        	ftp.changeWorkingDirectory(path + "/" + UserUtils.getUser().getId());//跳转到FTP相应的路径
            OutputStream out = ftp.appendFileStream(tBaFile.getId() + tBaFile.getFileType()); // FTP存储的文件名称   
            
            //3.2、创建合成的文件类
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, out); 
            document.open();
            
            //3.3、文件合并处理
            tBaFile = MergePDF(tBaFile, tBaFiles, document, ftp, pdfWriter);
            if(tBaFile != null && !"01".equals(tBaFile.getIsMarge())){
            	tBaFile.setIsMarge("您上传的附件“"+ tBaFile.getFileName() + "”存在问题，导致合成失败！");
            	return tBaFile;
            }
            
            //3.4、关闭相关流
            closeStream(document, ftp, out);
            
        }catch (Exception e) {
        	logger.error("合成失败" , e); // 将异常保存到日志里面
		} 
        //4、保存file 
        if(tBaFile != null && !"01".equals(tBaFile.getIsMarge())){
        	 tBaFileDao.insert(tBaFile);
        }
        return tBaFile;
    }
    
    //--------------------------------------------------------FTP相关工具使用START
    /**
	 * 功能：连接FTP服务器
	 * @return 打开到根目录的FTP对象
	 */
	public FTPClient getFtpConnect(){
        String url = Global.getConfig("FTP_URL"); // 配置的url路径
        int port = Integer.parseInt(Global.getConfig("FTP_PORT")); // 配置的端口号
        String username = Global.getConfig("FTP_USERNAME"); // FTP连接的帐号
        String password = Global.getConfig("FTP_PASSWORD"); // FTP连接的密码
        FTPClient ftp = new FTPClient();  
        try {
			ftp.connect(url, port);//连接FTP服务器  
            int reply;  
            ftp.login(username, password);//登录  
            ftp.enterLocalPassiveMode();//设置PassiveMode传输  
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);//设置以二进制流的方式传输    
            ftp.setFileType(FTP.BINARY_FILE_TYPE);  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
            	ftp.disconnect();  // 服务器连接失败，断开服务连接
            }
        } catch (Exception e) {
			e.printStackTrace();
		}
        return ftp;
	}
	
	/**
	 * 功能：登出FTP服务器，并断开相关连接
	 * @param ftp 需要断开的FTP客户机
	 */
	public void logoutFtp(FTPClient ftp){
		try {
			ftp.logout();
			if(ftp.isConnected()){
	        	ftp.disconnect();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：打开文件夹路径，如果没有该路径，进行创建
	 * @param folderPath 文件夹名称（以“/”隔开）
	 * @param ftp 连接的FTP客户机
	 * @return FTP客户端
	 */
	public FTPClient openFolder(String folderPath, FTPClient ftp){
		String[] folderPaths = folderPath.split("/");
		boolean openFlag = true;
		if(folderPaths != null && folderPaths.length > 0){
			for (String TempFolderPath : folderPaths) {
				try{
					openFlag = ftp.changeWorkingDirectory(TempFolderPath);
			        if(openFlag == false && TempFolderPath != null && StringUtils.isNotBlank(TempFolderPath)){
			        	ftp.makeDirectory(TempFolderPath);
			        }
		        }catch(Exception e){
		        	//lo.error("目录打开失败！");
		        	e.printStackTrace();
				}
			}
		}
		return ftp;
	}
	//--------------------------------------------------------FTP相关工具使用END
	
	//--------------------------------------------------------PDF相关工具使用START
	/**
	 * 功能：具体的PDF合成代码
	 * @param tBaFile 生成的文件
	 * @param tBaFiles 需要合成的文件信息集合
	 * @return 合成的文件信息
	 * @throws Exception 
	 */
	public TBaFile MergePDF(TBaFile tBaFile, List<TBaFile> tBaFiles, Document document, FTPClient ftp,PdfWriter pdfWriter ) throws Exception{
		TBaFile tBaFileFail = new TBaFile(); // 记录失败的附件
		int mergeFlag = -1; // 记录失败的附件的坐标
		String path = Global.getConfig("FTP_PATH"); // FTP的根路径
		String url = Global.getConfig("FTP_URL"); // 配置的url路径
        int port = Integer.parseInt(Global.getConfig("FTP_PORT")); // 配置的端口号
        String username = Global.getConfig("FTP_USERNAME"); // FTP连接的帐号
        String password = Global.getConfig("FTP_PASSWORD"); // FTP连接的密码
        
		tBaFile.setFolderPath("/" + UserUtils.getUser().getId());// 记录合并完成文件的存储文件夹
		
        PdfContentByte cb = pdfWriter.getDirectContent();
        //document.setPageSize(PageSize.A4);// 设置页面大小
        //设置页面大小，就是对pdf的方向控制，会导致PDF页面的比例失调
        //注：此处需要的文件合成，需要展示源文件，所以放弃此处的方向控制，直接通过旋转进行控制还原源文件方向。
        InputStream inputStream = null;
        //文件依次合成
        try{
        	for (int i = 0; i < tBaFiles.size(); i++){
        		mergeFlag = i;
        		if(".pdf".equals(tBaFiles.get(i).getFileType())){//合并PDF
                	//注：Java FTP 读多个文本文件输入流，只能读第一个文本，读第二个时输入流为null。
                    //当前解决办法通过URL获得输入流，进行操作
                	inputStream = new URL("ftp://" + username + ":" + password + "@" + url + ":" + port + "/" + path + tBaFiles.get(i).getFolderPath() + "/"+ tBaFiles.get(i).getId() + tBaFiles.get(i).getFileType()).openStream();
                    PdfReader readerTemp =  new PdfReader(inputStream);            
                    int nTemp = readerTemp.getNumberOfPages();            
                    for (int j = 1; j <= nTemp; j++){   
                        PdfImportedPage page = pdfWriter.getImportedPage(readerTemp, j);                      
                        if(readerTemp.getPageRotation(j) == 0){//pdf没有自动旋转
                            document.setPageSize(new Rectangle(readerTemp.getPageSizeWithRotation(j).getWidth(), readerTemp.getPageSizeWithRotation(j).getHeight()));
                            //必须在规定的document尺寸大小确定后，才可以进行插入新页面。否则第一个页面方向默认竖向，并且在横竖交界处，将会导致转换错误
                            document.newPage();
                            cb.addTemplate(page,0,0);
                        }
                        if(readerTemp.getPageRotation(j) == 90){//pdf旋转了90度
                            document.setPageSize(new Rectangle(readerTemp.getPageSizeWithRotation(j).getWidth(), readerTemp.getPageSizeWithRotation(j).getHeight()));
                            document.newPage(); 
                            cb.addTemplate(page,0,-1, 1,0,0, readerTemp.getPageSizeWithRotation(j).getHeight());
                        } 
                        if(readerTemp.getPageRotation(j) == 180){//pdf旋转了180度
                            document.setPageSize(new Rectangle(readerTemp.getPageSizeWithRotation(j).getWidth(), readerTemp.getPageSizeWithRotation(j).getHeight()));
                            document.newPage(); 
                            cb.addTemplate(page,-1,0, 0,-1,readerTemp.getPageSizeWithRotation(j).getWidth(), readerTemp.getPageSizeWithRotation(j).getHeight());
                        }
                        if(readerTemp.getPageRotation(j) == 270){//pdf旋转了270度
                            document.setPageSize(new Rectangle(readerTemp.getPageSizeWithRotation(j).getWidth(), readerTemp.getPageSizeWithRotation(j).getHeight()));
                            document.newPage();
                            cb.addTemplate(page,0,1, -1,0,readerTemp.getPageSizeWithRotation(j).getWidth(), 0);
                        }
                    }
                    //合成一个文件后，关闭该文件流，减少资源浪费
                    if(inputStream != null){
                    	inputStream.close();
                    }
                }else{//合并图片 注：图片的大小可以根据需求，自行调节
                    document.setPageSize(PageSize.A4);
                    document.newPage(); 
                    URL urlImage = new URL("ftp://" + username + ":" + password + "@" + url + ":" + port + "/" + path + tBaFiles.get(i).getFolderPath() + "/"+ tBaFiles.get(i).getId() + tBaFiles.get(i).getFileType());
                    Image img = Image.getInstance(urlImage);
                    img.setAlignment(Image.ALIGN_CENTER);//设置居中
                    float imageHeight = img.getHeight();
                    float imageWidth = img.getWidth();
                    float widthChange = img.getWidth() / 555;
                    if(widthChange >= 1){
                        imageWidth = 555;
                        imageHeight = imageHeight * 555 / img.getWidth();
                    }
                    double heightChange = imageHeight / 802;
                    if( heightChange >= 1){
                        float imageHeightTemp = imageHeight;
                        imageHeight = 802;
                        imageWidth = imageWidth * 802 / imageHeightTemp;
                    }
                    img.scaleToFit(imageWidth, imageHeight);//设置图片大小
                    document.add(img);
                }
            }
        //注：方法虽然用到了throws Exception了，但是这里存在Error。导致Error的原因，合成的文件中，存在加密文件。	
        }catch (Error e) {
		    // 具体的Error异常是NoClassDefFoundError
        	logger.error("合成失败" , e); // 将异常保存到日志里面
        	tBaFileFail = tBaFileDao.get(tBaFiles.get(mergeFlag));
        }
        if(tBaFileFail != null && StringUtils.isNotBlank(tBaFileFail.getId())){
        	tBaFile = tBaFileFail;
        	tBaFile.setIsMarge("02"); // 合成失败
        }else {
        	tBaFile.setIsMarge("01"); // 合成成功
        }
		return tBaFile;
	}
	
	/**
	 * 关闭合成所需要的管道
	 * @param document
	 * @param ftp
	 * @param out
	 */
	public void closeStream(Document document, FTPClient ftp, OutputStream out){
		if(document != null){
			document.close();
		}
		if(ftp != null){
			FTPUtil ftpUtil = new FTPUtil();
			ftpUtil.logoutFtp(ftp);
		}
		try {
			if(out != null){
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------PDF相关工具使用END
}