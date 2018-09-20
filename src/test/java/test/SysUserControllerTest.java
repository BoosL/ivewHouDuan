package test;

import java.util.UUID;

import com.bolaa.common.MD5;

public class SysUserControllerTest {

    public static void main(String[] args) {
	// 生成后台管理员密码
	// 账号
	String userName = "admin";
	//  明文密码(数据库不存放此密码)
	String password = "admin";
	// 增加密码复杂度的盐
	String salt = UUID.randomUUID().toString();
	// 数据库存放的密码
	String md5 = MD5.md5(userName + password + salt);
	System.out.println(salt);
	System.out.println(md5);
    }
    
}
