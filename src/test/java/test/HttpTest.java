package test;

import java.util.HashMap;
import java.util.Map;

import com.bolaa.common.HttpClientUtil;

public class HttpTest {
	public static void main(String[] args) {
		String url = "http://localhost:81/user/getInfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", "test");
		String string = HttpClientUtil.doPost(url, map);
		System.out.println(string);
	}
}
