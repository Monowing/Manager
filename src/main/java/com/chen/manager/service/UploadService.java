package com.chen.manager.service;

import org.springframework.web.multipart.MultipartFile;

import com.chen.manager.viewmodel.CommonResult;

/**
 * Service层——文件上传
 * 
 * created at 2019-12-12
 * 
 * @author MonoWing
 *
 */
public interface UploadService {

	/**
	 * 文件上传
	 * 
	 * @param ftype
	 *            文件类型
	 * @param file
	 *            文件
	 * @return
	 */
	CommonResult uploadLocal(String ftype, MultipartFile file);

}
