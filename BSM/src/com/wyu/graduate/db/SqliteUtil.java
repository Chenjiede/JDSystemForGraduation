package com.wyu.graduate.db;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wyu.graduate.db.anonation.FieldType;
import com.wyu.graduate.util.StringUtil;


public class SqliteUtil {

	/**
	 * 根据参数创建表
	 * 
	 * @param conn
	 * @param table
	 * @param clazz
	 * @throws Exception
	 */
	public static <T> void createTable(Connection conn, String table, Class<T> clazz)  {

		try {
//			Field[] fields = clazz.getDeclaredFields();
//
//			List<String> entry = new ArrayList<>();
//			try {
//				for (Field field : fields) {
//					field.setAccessible(true);
//					String key = field.getName();
//
//					entry.add(key);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		
		Field[] fields = getFieldByClass(clazz);
		

			Statement statement = conn.createStatement();

			String sql = "create TABLE IF NOT EXISTS " + table;
			StringBuilder sb = new StringBuilder(sql);
			sb.append(" (");

			for (Field	field :fields) {
				
				String key =  field.getName();
				
				StringBuilder fieldType = new StringBuilder();
				
				if (field.isAnnotationPresent(FieldType.class)) {
					
					FieldType fType = field.getAnnotation(FieldType.class);
					if (fType.unique()) {
						System.out.println("the field "+key + " is unique ");
						fieldType.append(" UNIQUE ");
					}
					
					if (fType.notNUll()) {
						System.out.println("the field "+key + " is notnull ");
						fieldType.append(" NOT NULL ");
					}
					
					
					if (!StringUtil.isEmpty(fType.ColumnName())) {
						 key = fType.ColumnName();
					}
					
					
					
				};

				if (key.equalsIgnoreCase("id")) {
					sb.append(key + " INTEGER PRIMARY KEY   AUTOINCREMENT NOT NULL , ");
				}
				else {
					sb.append(key + " text ");
					sb.append(fieldType);
					sb.append(" ,");
				}
			}
			
			
			
			

			sb.deleteCharAt(sb.length() - 1);

			sb.append(" );");

			System.out.println(sb.toString());
			
			statement.executeUpdate(sb.toString());
			statement.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 往数据库里写入一个实体
	 * 
	 * @param conn
	 * @param table
	 * @param bean
	 * @param clazz
	 * @throws Exception
	 */
	public static <T> int insert(Connection conn, String table, T bean, Class<T> clazz) {
		int ret = 0;
		try {
			Statement statement = conn.createStatement();
			// insert into person values(1, 'leo')
			String sql = "insert into " + table;
			StringBuilder sb = new StringBuilder(sql);

			Field[] fields = clazz.getDeclaredFields();

			StringBuilder column = new StringBuilder("(");
			StringBuilder values = new StringBuilder("(");

			for (Field field : fields) {
				field.setAccessible(true);
				String key = field.getName();
				Object value = field.get(bean);
				
				if (key.equalsIgnoreCase("id")) {
					continue;
				}

				column.append(key);
				column.append(",");

				values.append("'" + value + "'");
				values.append(",");

			}

			column.deleteCharAt(column.length() - 1);
			values.deleteCharAt(values.length() - 1);

			column.append(")");
			values.append(")");

			sb.append(column);
			sb.append("VALUES ");
			sb.append(values);

			System.out.println(sb.toString());

			ret = statement.executeUpdate(sb.toString());

			statement.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * delete 一个实体
	 * 
	 * @param conn
	 *            数据库连接
	 * @param table
	 *            表名
	 * @param id
	 *            实体id
	 * @return true 表示成功 false 表示失败
	 */
	public static boolean delete(Connection conn, String table, Serializable id) {
		int ret = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = String.format("DELETE FROM %s WHERE id = %s;", table, id);
			ret = statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret != 0;
	}

	/**
	 * 
	 * @param conn
	 * @param table
	 * @param clazz
	 * @param id
	 * @return
	 */
	public static <T> T find(Connection conn, String table, Class<T> clazz, int id) {
//		int ret = 0;
//		try {
//			Statement statement = conn.createStatement();
//			String sql = String.format("SELECT * FROM %s WHERE id = %s;", table, id);
//			ResultSet set = statement.executeQuery(sql);
//			T t = clazz.newInstance();
//
//			while (set.next()) {
//
//				Field[] fields = getFieldByClass(clazz);
//
//				for (Field field : fields) {
//					field.setAccessible(true);
//					String name = field.getName();
//
//					if (name.equalsIgnoreCase("id")) {
//						int _id = set.getInt(name);
//						field.set(t, _id);
//					} else {
//						String value = set.getString(name);
//						field.set(t, value);
//					}
//				}
//			}
//
//			statement.close();
//
//			return t;
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
		List<T> list = find(conn, table, clazz, "id", id+"");
		if(list.size() == 0)
			return null;
		
		return list.get(0);
	}
	
	
	/**
	 * 根据两个选择条件来筛选数据
	 * @param conn
	 * @param table
	 * @param clazz
	 * @param id
	 * @return
	 */
	public static <T> List<T>  find(Connection conn, String table, Class<T> clazz,String selectOne, String selectArgOne, String selectTwo, String selectArgTwo) {
		try {
			Statement statement = conn.createStatement();
			String sql = String.format("SELECT * FROM %s WHERE %s = %s AND %s = %s;", table, selectOne, "'"+selectArgOne+"'", selectTwo, "'"+selectArgTwo+"'");
//			System.out.println(sql);
			ResultSet set = statement.executeQuery(sql);
			
			List<T> list = new ArrayList<>();

			while (set.next()) {
				T t = clazz.newInstance();
				Field[] fields = getFieldByClass(clazz);

				for (Field field : fields) {
					field.setAccessible(true);
					String name = field.getName();

					if (name.equalsIgnoreCase("id")) {
						int _id = set.getInt(name);
						field.set(t, _id);
					} else {
						String value = set.getString(name);
						field.set(t, value);
					}
				}
				list.add(t);
			}

			statement.close();

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	
	/**
	 * 根据一个选择条件来筛选数据
	 * @param conn
	 * @param table
	 * @param clazz
	 * @param id
	 * @return
	 */
	public static <T> List<T>  find(Connection conn, String table, Class<T> clazz,String select, String selectArg) {
		try {
			Statement statement = conn.createStatement();
			String sql = String.format("SELECT * FROM %s WHERE %s = %s;", table,select, "'"+selectArg+"'");
			ResultSet set = statement.executeQuery(sql);
			
			List<T> list = new ArrayList<>();

			while (set.next()) {
				T t = clazz.newInstance();
				Field[] fields = getFieldByClass(clazz);

				for (Field field : fields) {
					field.setAccessible(true);
					String name = field.getName();

					if (name.equalsIgnoreCase("id")) {
						int _id = set.getInt(name);
						field.set(t, _id);
					} else {
						String value = set.getString(name);
						field.set(t, value);
					}
				}
				list.add(t);
			}

			statement.close();

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	
	

	public static <T> List<T> findAll(Connection conn, String table, Class<T> clazz, int startPos, int num) {
		// select * from users order by id limit 10 offset
		// 0;//offset代表从第几条记录 之后 开始查询，limit代表查询多少条结果

		int ret = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = String.format("SELECT * FROM %s order by id limit %s offset %s;", table, num, startPos);
			ResultSet set = statement.executeQuery(sql);

			List<T> list = new ArrayList<>();

			while (set.next()) {
				T t = clazz.newInstance();
				Field[] fields = getFieldByClass(clazz);

				for (Field field : fields) {
					field.setAccessible(true);
					String name = field.getName();

					if (name.equalsIgnoreCase("id")) {
						int _id = set.getInt(name);
						field.set(t, _id);
					} else {
						String value = set.getString(name);
						field.set(t, value);
					}
				}

				list.add(t);

			}

			statement.close();

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static long count(Connection conn, String table) {
		long ret = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = String.format("select count(*) from %s;", table);
			ResultSet set = statement.executeQuery(sql);

			if (set.next()) {

				ret = set.getLong(1);

			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public static <T> boolean update(Connection conn, String table, Class<T> clazz, int id, T bean) {
		/**
		 * UPDATE table_name SET column1 = value1, column2 = value2...., columnN
		 * = valueN WHERE [condition];
		 */

		int ret = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = String.format("update %s  set ", table);
			StringBuilder sb = new StringBuilder(sql);

			Field[] fields = getFieldByClass(clazz);

			for (Field field : fields) {
				field.setAccessible(true);
				String name = field.getName();

				if (name.equalsIgnoreCase("id")) {
					continue;
				}

				Object value = field.get(bean);

				sb.append(name + " =  '" + value + "'");
				sb.append(" ,");

			}

			sb.deleteCharAt(sb.length() - 1);
			sb.append(" where id = " + id);

			System.out.println(sb.toString());

			ret = statement.executeUpdate(sb.toString());
			System.out.println("update bean " + ret);

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return ret != 0;
	}

	public static Field[] getFieldByClass(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				String key = field.getName();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fields;

	}

}
