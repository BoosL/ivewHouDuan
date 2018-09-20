package test;


import com.bolaa.common.BeanUtil;
import com.bolaa.manage.entity.Ldyitems;

public class BeanUtilTest {
	public static void main(String[] args) {
		Ldyitems items = new Ldyitems();
		items.setLdyitemid(1);
		items.setLtitle("wahh");
		items.setItemclass("tupian");
		items.setIsmust("shi");
		
		Ldyitems item = new Ldyitems();
		item.setLdyitemid(1);
		item.setPicurl("./asd/asd/sdf.jpg");
		
		BeanUtil.copyNotNullProperties(item, items);
		System.out.println(item);
		System.out.println(items);
	}
}
