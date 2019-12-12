package com.chen.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chen.manager.service.UploadService;
import com.chen.manager.viewmodel.CommonResult;

/**
 * 系统——文件上传
 * 
 * created at 2019-12-12
 * 
 * @author MonoWing
 *
 */
@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	
	@RequestMapping(value = "/uploadFile/{ftype}", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
	public CommonResult upLoadFile(MultipartFile file,@PathVariable("ftype") String ftype,HttpServletRequest req,HttpServletResponse resp) throws IOException {

		if (file == null) {
			return new CommonResult().error("请选择要上传文件!");
		}
		return uploadService.uploadLocal(ftype, file);
	}

	
}
