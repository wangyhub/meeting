/**
 * Copyright &copy; 2012-2020 <a href="https://www.cait.com">CAIT</a> All rights reserved.
 */
package com.lw.modules.meeting.service.file;

import com.google.common.collect.Lists;
import com.lw.common.config.Global;
import com.lw.common.persistence.Page;
import com.lw.common.service.CrudService;
import com.lw.common.utils.FileDownUtil;
import com.lw.common.utils.IdGen;
import com.lw.common.utils.StringUtils;
import com.lw.modules.meeting.dao.file.TbAnnexFileDao;
import com.lw.modules.meeting.entity.file.TbAnnexFile;
import com.lw.modules.meeting.entity.model.FileContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 会议资料Service
 * @author meijx
 * @version 2019-03-16
 */
@Service
@Transactional(readOnly = true)
public class TbAnnexFileService extends CrudService<TbAnnexFileDao, TbAnnexFile> {

    @Autowired
    private TbAnnexFileDao tbAnnexFileDao;

	public TbAnnexFile get(String id) {
		return super.get(id);
	}

    /**
     * 附件获取
     * @param modulecode 模块编号
     * @param moduleid 模块id
     * @return
     */
	public List<TbAnnexFile> findList(String modulecode,String moduleid) {
        if (StringUtils.isEmpty(modulecode) || StringUtils.isEmpty(moduleid)) {
            return Lists.newArrayList();
        }
        TbAnnexFile tbAnnexFile = new TbAnnexFile();
        tbAnnexFile.setModulecode(modulecode);
        tbAnnexFile.setModuleid(moduleid);
        return super.findList(tbAnnexFile);
	}
	
	public Page<TbAnnexFile> findPage(Page<TbAnnexFile> page, TbAnnexFile tbAnnexFile) {
		return super.findPage(page, tbAnnexFile);
	}

    /**
     * 保存附件
     * @param file 附件
     * @param moduleid 模块数据id
     * @param modulecode 模块编码
     * @param moduleName 模块名称
     */
    public boolean save(MultipartFile file, String moduleid, String modulecode, String moduleName) {
        if (file == null || StringUtils.isEmpty(moduleid) || StringUtils.isEmpty(modulecode)) {
            return false;
        }
        //存储附件
        //上传文件路径
        String path = Global.getConfig("annexFileBasePath");//
        //上传文件名
        String filename = file.getOriginalFilename();
        File filepath = new File(path,filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文件当中
        String prefix = "";
        String uuidFileName = IdGen.uuid();
        if (filename.lastIndexOf(".") != -1) {
            prefix=filename.substring(filename.lastIndexOf(".")+1);
            uuidFileName = uuidFileName + filename.substring(filename.lastIndexOf("."));
        }
        try {
            file.transferTo(new File(path + File.separator + uuidFileName));
        } catch (Exception e) {
            logger.info("上传文件错误，错误信息"+e.getMessage());
            return false;
        }
        //存储附件数据
        TbAnnexFile annexFile = new TbAnnexFile();
        annexFile.setId(IdGen.uuid());
        annexFile.setModulecode(modulecode);
        if (StringUtils.isNotEmpty(moduleName)) {
            annexFile.setModulename(moduleName);
        }
        annexFile.setModuleid(moduleid);
        annexFile.setFilename(filename);
        annexFile.setFiletype(prefix);
        //获取后缀
        annexFile.setPath(path + File.separator + uuidFileName);
        annexFile.preInsert();
        dao.insert(annexFile);
        return true;
    }
	
	@Transactional(readOnly = false)
	public void delete(TbAnnexFile tbAnnexFile) {
        // 删除文件
        File file = new File(tbAnnexFile.getPath());
        if (file.delete()) {
            // 删除附件数据
            super.delete(tbAnnexFile);
        }
	}

    public void deleteById(String annexId) {
        TbAnnexFile tbAnnexFile = dao.get(annexId);
        if (tbAnnexFile != null) {
            // 删除文件
            File file = new File(tbAnnexFile.getPath());
            if (file.delete()) {
                // 删除附件数据
                super.delete(tbAnnexFile);
            }
        }
    }

    public List<FileContent> findAAA(String meetId) {

        List<FileContent> TbAnnexFileList = tbAnnexFileDao.findAAA(meetId);
        System.out.println("----------------------------------------"+TbAnnexFileList.size());
        System.out.println("----------------------------------------"+TbAnnexFileList);
        return TbAnnexFileList;

    }

    public List<TbAnnexFile> dowmLoad(String meetId) {

        List<TbAnnexFile>  tbAnnexFile= tbAnnexFileDao.findById(meetId);

        return  tbAnnexFile;

    }

    public void appDownFile(String id, HttpServletRequest request, HttpServletResponse response) {
        TbAnnexFile tbAnnexFile = this.get(id);
        //String path = "D:/upload"+tbAnnexFile.getPath();
        //String path = "D://upload//meeting//upload//b610e2c9424d4ebf9eb3e0754f19525a.docx";
        String path = tbAnnexFile.getPath();
        try {
            File file = new File(path);
            if(file.exists()){
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Content-Disposition", "attachment;filename=\""+ FileDownUtil.encodeFilename(request,tbAnnexFile.getFilename())+"\"");
                response.addHeader("Content-Length", "" + file.length());
                ServletOutputStream out = response.getOutputStream();
                InputStream inStream=new FileInputStream(file);
                byte[] b = new byte[1024];
                int len;
                while((len=inStream.read(b)) >0)
                    out.write(b,0,len);
                out.flush();
                out.close();
                inStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}