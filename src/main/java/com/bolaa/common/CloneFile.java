package com.bolaa.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CloneFile {

	public static void copyDir(String oldPath, String newPath) throws IOException {
		File file = new File(oldPath);
		// 文件名称列表
		String[] filePath = file.list();

		if (!(new File(newPath)).exists()) {
			(new File(newPath)).mkdir();
		}

		for (int i = 0; i < filePath.length; i++) {
			if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
				copyDir(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}

			if (new File(oldPath + file.separator + filePath[i]).isFile()) {
				copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}

		}
	}

	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;

		byte[] buffer = new byte[2097152];

		while ((in.read(buffer)) != -1) {
			out.write(buffer);
		}
	}
}
