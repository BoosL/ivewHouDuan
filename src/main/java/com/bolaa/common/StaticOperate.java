package com.bolaa.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class StaticOperate {
	public static void convert2Html(String sSourceUrl, String sDestDir, String sHtmlFile) throws IOException {
		int HttpResult;
		URL url = new URL(sSourceUrl);
		URLConnection urlconn = url.openConnection();
		urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		HttpResult = httpconn.getResponseCode();
		if (HttpResult != HttpURLConnection.HTTP_OK) {
		} else {
			InputStreamReader isr = new InputStreamReader(httpconn.getInputStream(), "utf-8");
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			if (!sDestDir.endsWith("/")){
				sDestDir += "/";
			}	
			File saveFile = new File(sDestDir + sHtmlFile);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileOutputStream fout = new FileOutputStream(saveFile);
			while ((inputLine = in.readLine()) != null) {
				fout.write(inputLine.getBytes());
			}
			in.close();
			fout.close();
		}
	}

	public static void main(String[] args) {
		try {
			//http://localhost:8080/Poster/
			//http://slide.news.sina.com.cn/slide_1_2841_315031.html#p=1
			convert2Html("http://localhost:8080/Poster/adclass/testHtml", "D:/","test.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
