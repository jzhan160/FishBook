package com.se.fishbook.controller;


import com.se.fishbook.util.Constants;
import com.se.fishbook.util.FileUtil;
import com.se.fishbook.util.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/*setting usr info*/
@Controller
@RequestMapping(value = "setting")
public class SettingController {

    private String uploadPath = "E:\\JavaWeb\\SpringBoot\\";

    @RequestMapping("/")
	public String home(){
    	return "home";
	}

	@RequestMapping(value="upload")
    public @ResponseBody
	Result upload(@RequestParam("file")MultipartFile[] files, HttpServletRequest request) {
		Result result = new Result();
		try {
	        //多文件上传
	        if(files!=null && files.length>=1) {
	            BufferedOutputStream bw = null;
	            try {
	                String fileName = files[0].getOriginalFilename();
	                //判断是否有文件(实际生产中要判断是否是音频文件)
	                if(StringUtils.isNoneBlank(fileName)) {
	                	String name = FileUtil.getFileType(fileName);
	                    //创建输出文件对象
	                    File outFile = new File(uploadPath + name);
	                    //拷贝文件到输出文件对象
	                    FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);

					}
	                result.setCode(Constants.SUCCESS);
	            } catch (Exception e) {
	                e.printStackTrace();
	                result.setCode(Constants.ERROR);
	            } finally {
	                try {
	                    if(bw!=null) {bw.close();}
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Constants.ERROR);
		}
		return result;
    }
}
