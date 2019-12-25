package com.chen.manager.serviceimpl;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.chen.manager.service.UploadService;
import com.chen.manager.viewmodel.CommonResult;

/**
 * ServiceImpl层——文件上传
 * 
 * created at 2019-12-12
 * 
 * @author MonoWing
 *
 */
@Service
@Transactional
public class UploadServiceImpl implements UploadService, ServletContextAware {

	/**
	 * 文件上传路径
	 */
	@Value("${file.upload.path}")
	private String fileUploadPath;
	
	/**
	 * 文件上传绝对路径
	 */
	@Value("${file.upload.static.access.path}")
	private String fileStaticAccessPath;

	

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public CommonResult uploadLocal(String type, MultipartFile file) {

		if (file == null) {
			return new CommonResult().error("请选择要上传文件！");
		}

		try {
			String filename = UUID.randomUUID().toString() + "."
					+ FilenameUtils.getExtension(file.getOriginalFilename());
			String destPath = fileStaticAccessPath + filename;

			File destFile = new File(fileUploadPath + destPath);

			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			file.transferTo(destFile);

			String contextPath = servletContext.getContextPath() + destPath;

			/*
			 * // 如果是附件，需要入库 if ("attachment".equals(type)) { Enclosure t = new
			 * Enclosure(); t.setFileName(filename);
			 * t.setOldName(multipartFile.getOriginalFilename());
			 * t.setContentType(multipartFile.getContentType());
			 * t.setFileUrl(destPath); t.setUploadTime(new Date()); t =
			 * enclosureService.save(t); if (t.getId() > 0) { Map<String,
			 * String> map = new HashMap<String, String>(); map.put("fid",
			 * t.getId().toString()); map.put("fname", t.getOldName()); return
			 * new CommonResult(Type.OK, servletContext.getContextPath() +
			 * destPath, map); }
			 * 
			 * }
			 */

			/*if ("excelCalculate".equals(type)) {
				return excelRowService.calculateExcel(
						servletContext.getContextPath() + destPath, filename);
			}*/

			return new CommonResult().success(contextPath);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonResult().error("上传发生错误！");
		}

	}
}
