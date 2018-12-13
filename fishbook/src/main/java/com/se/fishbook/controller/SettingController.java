//////////////////////////////////////////////////////////////
// SettingController.java  response to web requests         //
// ver 1.0                                                  //
//                                                          //
//////////////////////////////////////////////////////////////
/*
 * This package provides one Java class SettingController
 * which is responsible for the front end request from the
 * setting page.
 *
 * */
package com.se.fishbook.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/*setting usr info*/
@Controller
@RequestMapping(value = "setting")
public class SettingController {

    private String uploadPath = "E:\\JavaWeb\\SpringBoot\\";

    @RequestMapping("/")
	public String home(){
    	return "home";
	}

	/*@RequestMapping(value="upload",method = RequestMethod.POST)
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
	*/
    @RequestMapping(value="upload",method = RequestMethod.POST)
	public String upload(MultipartFile fileimg, HttpServletRequest request)
			throws Exception{
		System.out.println("upload=====");
		//String path="";
		if(!fileimg.isEmpty()){
			// 上传的文件路径  建在WebRoot目录下--fileupload
			//path = session.getServletContext().getRealPath("/fileupload/");
			//System.out.println(path);
			// 上传文件名
			String filename = fileimg.getOriginalFilename();

			// 做一个判断 图片扩展名   substring(int index) 返回一个以index为索引作为起点的含头不含尾的后面的字符串
			String types = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();

			// 如果有需求是要修改上传的图片的名字为用户id开头的
			//String newfilename = account+filename.substring(filename.lastIndexOf(".")); // 取得的是 .jpg

			File filepath = new File(uploadPath,filename);
			// 判断路径是否存在，不存在就创建一个
			if(!filepath.getParentFile().exists()){
				filepath.getParentFile().mkdirs();
			}
			fileimg.transferTo(new File(uploadPath + File.separator + filename)); // 会上传到服务器中的路径
		}

		return "account/profile";
	}

}
