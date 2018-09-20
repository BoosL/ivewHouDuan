package com.bolaa.common;

import java.io.File;

public class CopyFile {
	
	public static void fileMove(String from, String to) throws Exception {
		try {
			File dir = new File(from);
			File[] files = dir.listFiles();
			if (files == null)
				return;
			File moveDir = new File(to);
			if (!moveDir.exists()) {
				moveDir.mkdirs();
			}
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					fileMove(files[i].getPath(), to + "//" + files[i].getName());
					files[i].delete();
				}
				File moveFile = new File(moveDir.getPath() + "//" + files[i].getName());
				if (moveFile.exists()) {
					moveFile.delete();
				}
				files[i].renameTo(moveFile);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
