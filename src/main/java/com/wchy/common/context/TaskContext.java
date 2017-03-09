package com.wchy.common.context;


import com.wchy.common.task.IReader;
import com.wchy.common.task.IWriter;

import java.util.HashMap;
import java.util.Map;

public class TaskContext {
	private static Map<String, String> readerMap = new HashMap<String, String>();
	static {
		readerMap.put("mysql", "com.wchy.common.impl.MysqlReader");
	}
	
	private static Map<String, String> writerMap = new HashMap<String, String>();
	static{
		writerMap.put("mysql", "com.wchy.common.impl.MysqlWriter");
	}
	
	public static IReader getReader(String name) {
		String reader = readerMap.get(name);
		try {
			return (IReader) Class.forName(reader).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static IWriter getWriter(String name) {
		String writer = writerMap.get(name);
		
		try {
			return (IWriter) Class.forName(writer).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
