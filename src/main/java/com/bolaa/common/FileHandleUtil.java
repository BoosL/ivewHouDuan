package com.bolaa.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class FileHandleUtil {
	/**
	 * 添加视频
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String uploadVideo(@RequestParam MultipartFile file, HttpServletRequest request, String luodiyeid)
			throws Exception {
		// 图片、视频、文档的路径
		String dir = "/luo_" + luodiyeid + "/video/";
		// 文件保存路径
		String resUrl = "";
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 文件扩展名
		// String extName = fileName.substring(fileName.lastIndexOf("."));
		System.out.println("file.getOriginalFilename()" + file.getOriginalFilename());
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				String fileUrl = request.getSession().getServletContext().getRealPath("luodiye") + dir + fileName;
				resUrl = "luodiye/" + dir + fileName;
				System.out.println("filePath-->" + resUrl);
				File saveFile = new File(fileUrl);
				if (!saveFile.getParentFile().exists()) {
					saveFile.getParentFile().mkdirs();
				}
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
				out.write(file.getBytes());
				out.flush();
				out.close();
				// 转存文件
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resUrl;
	}

	/**
	 * 上传图片和视频
	 * @param files
	 * @param request
	 * @param luodiyeid
	 * @return
	 * @throws Exception
	 */
	public static String upload(@RequestParam MultipartFile[] files, HttpServletRequest request, String itemid)
			throws Exception {
		String[] retStrUrl = new String[files.length];
		// 图片、视频、文档的路径
		String dir = null;
		if (StringUtils.hasText(itemid)) {
			dir = "/res/item_" + itemid + "/file/";
		} else {
			dir = "/temp" + "/file/";
		}
		// 文件保存路径
		String retUrl = "";
		if (files.length == 0) {
			return "";
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].getSize() != 0) {
				MultipartFile file = files[i];
				// 获取文件名
				// String fileName = file.getOriginalFilename();
				String fileName = file.getOriginalFilename();
				// 文件扩展名
				String extName = fileName.substring(fileName.lastIndexOf("."));
				String fileNewName = RandomUtil.generateString(16) + extName;
				System.out.println("file.getOriginalFilename()" + file.getOriginalFilename());
				System.out.println(request.getSession().getServletContext().getRealPath("luodiye"));
				// 判断文件是否为空
				if (!file.isEmpty()) {
					try {
						String path = request.getSession().getServletContext().getRealPath("");
						String webAppStr = (new File(path)).getParent();
						String CDNname = PropertyUtil.getProperty("CDNName");
						String fileUrl = webAppStr + "/" + CDNname + "/luodiye" + dir + fileNewName; // 将资源保存到CDN上面
						System.out.println(fileUrl);
						retUrl = "/luodiye" + dir + fileNewName;
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

	/**
	 * 项目封面图
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String ProjectPic(@RequestParam MultipartFile file, HttpServletRequest request)
			throws Exception {
		// 获取文件名
		String retUrl = "";
		String fileName = file.getOriginalFilename();
		// 文件扩展名
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String fileNewName = RandomUtil.generateString(16) + extName;
		System.out.println("file.getOriginalFilename()" + file.getOriginalFilename());
		System.out.println(request.getSession().getServletContext().getRealPath(""));
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				String path = request.getSession().getServletContext().getRealPath("/upload");
				String fileUrl =path + fileNewName; // 将资源保存到upload文件下
				retUrl = "/upload" + fileNewName;
				File saveFile = new File(fileUrl);
				if (!saveFile.getParentFile().exists()) {
					saveFile.getParentFile().mkdirs();
				}
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
				out.write(file.getBytes());
				out.flush();
				out.close();
				// 转存文件
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retUrl;
	}

	public static String testUrl(HttpServletRequest request, String luodiyeid) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("");
		System.out.println(path);
		String webAppStr = (new File(path)).getParent();
		System.out.println(webAppStr);
		// String pname = PropertyUtil.getProperty("projectName");
		// String cname = PropertyUtil.getProperty("CDNName");
		// String pname = webAppStr + "/Poster/luodiye/luo_"+luodiyeid;
		// String cname = webAppStr + "/TestWeb/luodiye/luo_"+luodiyeid;
		// CloneFile.copyDir("D:/a", "E:/b");
		// CopyFile.fileMove("D:/a", "E:/b");
		// StaticOperate.convert2Html("http://localhost:8080/Poster/",
		// webAppStr+"/TestWeb","index.html");
		return "123";
	}

	public static void main(String[] args) {
		String s = "kahsdf/temp/ajdshf/slkf.jpg,kahsasxdf/temp/ajdacshf/zslkf.mp4";
		String string = s.replaceAll("temp", "luo_1");
		System.out.println(string);
	}
}
