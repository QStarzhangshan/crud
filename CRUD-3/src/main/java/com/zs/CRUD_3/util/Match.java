package com.zs.CRUD_3.util;

import java.util.HashMap;
import java.util.Map;

public class Match {
	
	public static Map<Integer,String> addNameById(Integer id){
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		if(id==1) {
			map.put(id, "admin");
		}else if(id==2) {
			map.put(id, "1区管理员");
		}else if(id==3) {
			map.put(id, "2区管理员");
		}else if(id==4){
			map.put(id, "群众");
		}
		return map;
	}
}
