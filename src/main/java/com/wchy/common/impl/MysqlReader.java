package com.wchy.common.impl;


import com.wchy.common.task.AbstractRunner;
import com.wchy.common.task.IReader;
import com.wchy.common.util.Configuration;
import com.wchy.common.util.MysqlConnectionFactory;
import com.wchy.common.util.NullObject;
import com.wchy.vo.VshopDistributionItemVo;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MysqlReader extends AbstractRunner implements IReader {

	@Override
	public void read() {
		Configuration<String, Object> context = super.getContext();
		String querySql = (String) context.get("querySql");
		Integer fetchSize = (Integer)context.get("fetch_size");
		Integer timeout = (Integer)context.get("timeout");
		
		Connection conn = MysqlConnectionFactory.getConnection();
		ResultSet resultSet = null;
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setFetchSize(fetchSize);
			stmt.setQueryTimeout(timeout);
			resultSet = stmt.executeQuery(querySql);
			resultSetToQueue(resultSet, VshopDistributionItemVo.class, super.getArrayBlockingQueue());
			super.getArrayBlockingQueue().put(NullObject.build());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(null != resultSet) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void resultSetToQueue(ResultSet rs, Class clazz,
			ArrayBlockingQueue arrayBlockingQueue) throws Exception {
		// 取得Method
		Method[] methods = clazz.getDeclaredMethods();
		// 用于获取列数、或者列类型
		ResultSetMetaData meta = rs.getMetaData();
		Object obj = null;
		Class cls = null;

		while (rs.next()) {
			// 获取formbean实例对象
			obj = clazz.newInstance(); // 用Class.forName方法实例化对象和new创建实例化对象是有很大区别的，它要求JVM首先从类加载器中查找类，然后再实例化，并且能执行类中的静态方法。而new仅仅是新建一个对象实例
			// 循环获取指定行的每一列的信息
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				// 当前列名
				String colName = meta.getColumnName(i);

				// 设置方法名
				String setMethodName = "set" + colName;

				// 遍历Method
				for (int j = 0; j < methods.length; j++) {
					if (methods[j]
							.getName()
							.replaceAll("_", "")
							.equalsIgnoreCase(setMethodName.replaceAll("_", ""))) {
						setMethodName = methods[j].getName();

						// 获取当前位置的值，返回Object类型
						Object value = rs.getObject(colName);
						if (value == null) {
							continue;
						}
						if (methods[j].getParameterTypes()[0].getName()
								.toString().equals("java.lang.Integer")) {
							try {
								value = rs.getInt(colName);
							} catch (Exception e) {
							}
						}

						if (value instanceof BigInteger) {
							value = ((BigInteger) value).longValue();
							cls = Long.class;
						} else if (value instanceof Boolean) {
							continue;
						} else {
							// cls = value.getClass();
							cls = methods[j].getParameterTypes()[0];
						}

						// 实行Set方法
						try {
							// // 利用反射获取对象
							// JavaBean内部属性和ResultSet中一致时候
							Method setMethod = obj.getClass().getMethod(
									setMethodName, cls);
							setMethod.invoke(obj, value);
						} catch (Exception e) {
							// JavaBean内部属性和ResultSet中不一致时候，使用String来输入值。
							e.printStackTrace();
						}
					}
				}
			}
			arrayBlockingQueue.put(obj);
		}
	}

}
