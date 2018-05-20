package com.zs.CRUD_3.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static String getMD5(String str) throws NoSuchAlgorithmException {
		MessageDigest md = null;
		
		md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		
		return new BigInteger(1,md.digest()).toString(16);
		
	}
}
