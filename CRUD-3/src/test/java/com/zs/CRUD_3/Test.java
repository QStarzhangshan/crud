package com.zs.CRUD_3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		Date now  = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String client = dateFormat.format(new Date()).replaceAll("-", "");
		
		System.out.println(client);
		
		StringBuffer sb = new StringBuffer(client);
		sb.insert(4, "-").insert(7, "-");
		client = sb.toString();
		System.out.println(client);
		
		
		
		System.out.println(now);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(now));
	}
}
