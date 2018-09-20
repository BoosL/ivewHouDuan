package com.bolaa.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bolaa.common.CloneFile;
import com.bolaa.common.FileHandleUtil;
import com.bolaa.common.PropertyUtil;
import com.bolaa.common.StaticOperate;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	/**
	 * 添加视频
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	@ResponseBody
	public String uploadVideo(@RequestParam MultipartFile[] files, HttpServletRequest request, String luodiyeid) throws Exception {
		String upload = FileHandleUtil.upload(files, request, luodiyeid);
		return upload;
	}
	
	@RequestMapping(value="/testPath",method={RequestMethod.POST})
	@ResponseBody
	public String testPath(HttpServletRequest request) {
		System.out.println(request.getServletPath());
		System.out.println(request.getSession().getServletContext().getRealPath(""));
		return "123";
	}
	
	@RequestMapping(value = "/img", method = { RequestMethod.POST })
	@ResponseBody
	public String uploadImg(@RequestParam MultipartFile[] files, HttpServletRequest request, String luodiyeid) throws Exception {
		String[] retStrUrl = new String[files.length];
		// 图片、视频、文档的路径
		String dir = "/luo_"+luodiyeid+"/img/";
		// 文件保存路径
		String retUrl = "";
		for (int i = 0; i < files.length; i++) {
			if(files[i].getSize() != 0){
				MultipartFile file = files[i];
				// 获取文件名
				String fileName = file.getOriginalFilename();
				// 文件扩展名
				// String extName = fileName.substring(fileName.lastIndexOf("."));
				System.out.println("file.getOriginalFilename()" + file.getOriginalFilename());
				System.out.println(request.getSession().getServletContext().getRealPath("luodiye"));
				// 判断文件是否为空
				if (!file.isEmpty()) {
					try {
						String fileUrl = request.getSession().getServletContext().getRealPath("luodiye") + dir + fileName;
						retUrl = "luodiye"  + dir + fileName;
						System.out.println("resUrl-->" + retUrl);
						File saveFile = new File(fileUrl);
						if (!saveFile.getParentFile().exists()) {
							saveFile.getParentFile().mkdirs();
						}
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
						out.write(file.getBytes());
						out.flush();
						out.close();
						// 转存文件
						retStrUrl[i] = retUrl;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return String.join(",", retStrUrl);
	}
	
	
	
	@RequestMapping(value = "/testUrl", method = { RequestMethod.POST ,RequestMethod.GET})
	@ResponseBody
	public String testUrl(HttpServletRequest request) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("");
		System.out.println(path);
		String webAppStr = (new File(path)).getParent();
		System.out.println(webAppStr);
//		String pname = PropertyUtil.getProperty("projectName");
//		String cname = PropertyUtil.getProperty("CDNName");
		String pname =  webAppStr + "/Poster/luodiye/luo_"+1;
		String cname =  webAppStr + "/TestWeb/luodiye/luo_"+1;
		CloneFile.copyDir("D:/a", "E:/b");
		//CopyFile.fileMove("D:/a", "E:/b");
		//StaticOperate.convert2Html("http://ligu.e2cysd.com/index.html#/home", webAppStr+"/TestWeb","index.html");
		return "123";
	}
	
	@RequestMapping(value = "/testUrlHtml", method = { RequestMethod.POST ,RequestMethod.GET})
	@ResponseBody
	public boolean testUrlHtml(HttpServletRequest request) throws IOException{
		String path = request.getSession().getServletContext().getRealPath("");
		String webAppStr = (new File(path)).getParent();
		String property = PropertyUtil.getProperty("CDNName");
		String dir = webAppStr+"/"+property+"/luodiye/code/";
		StaticOperate.convert2Html("http://localhost:8080/Poster/adclass/testHtml", dir,"index.html");
		return true;
	}
	public static void main(String[] args) {
		try {
			StaticOperate.convert2Html("http://ligu.e2cysd.com/index.html#/home", "D:/a","phone.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
