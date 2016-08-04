package com.wyu.graduate.db.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import com.wyu.graduate.db.ConnManager;
import com.wyu.graduate.db.SqliteUtil;
import com.wyu.graduate.db.anonation.Table;
import com.wyu.graduate.db.def.DAO;

@SuppressWarnings("unchecked")
public abstract class DaoSupport<T> implements DAO<T> {

	private Connection conn;

	public DaoSupport() {
		try {
			conn = ConnManager.getInstance().getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final String getTableName() {
		String tableName = "";
		try {
			T t = getInstance();

			if (t == null) {
				throw new RuntimeException("实现类必须实现指定泛型类型");
			}

			Class<?> clazz = t.getClass();
			if (clazz.isAnnotationPresent(Table.class)) {
				tableName = clazz.getAnnotation(Table.class).tableName();
			} else {

				tableName = clazz.getSimpleName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tableName.toLowerCase();
	}

	protected String getTag() {
		return getClass().getSimpleName();
	}

	@Override
	public int insert(Class<T> clazz, T t) {

		return SqliteUtil.insert(conn, getTableName(), t, clazz);
	}

	@Override
	public int delete(Serializable id) {
		// return db.delete(getTableName(), "_id=?", new
		// String[]{id.toString()});

		boolean result = SqliteUtil.delete(conn, getTableName(), id);
		
		return result? 1: 0;
	}

	@Override
	public boolean update(Class<T> clazz, int id, T t) {

		return SqliteUtil.update(conn, getTableName(), clazz, id, t);
	}

	@Override
	public T find(Class<T> clazz, int id) {
		return SqliteUtil.find(conn, getTableName(), clazz, id);
	}

	@Override
	public T find(Class<T> clazz, String user) {
		List<T> list = SqliteUtil.find(conn, getTableName(), clazz, "user",
				user);
		if (list.size() == 0)
			return null;

		return list.get(0);
	}

	@Override
	public List<T> findBySelection(Class<T> clazz, String selection, String value) {
		
		return SqliteUtil.find(conn, getTableName(), clazz, selection, value);
	}
	
	@Override
	public List<T> findBySelection(Class<T> clazz, String selection1, String value1, String selection2, String value2) {
		
		return SqliteUtil.find(conn, getTableName(), clazz, selection1, value1, selection2, value2);
	}

	@Override
	public List<T> findAll(Class<T> clazz, int startPos, int num) {
		return (List<T>) SqliteUtil.findAll(conn, getTableName(), clazz,
				startPos, num);
	}

	@Override
	public long count() {
		return SqliteUtil.count(conn, getTableName());
	}

	/**
	 * 利用反射去到运行时候的类型获取到参数化的类型泛型
	 * 
	 * @return 实体
	 */
	private T getInstance() {
		Type type = getClass().getGenericSuperclass();
		// type =
		// com.app.shui.datailnote.dao.DaoSupport<com.app.shui.datailnote.bean.Detail>
		// System.out.println("type = " + type);
		if (type instanceof ParameterizedType) {// 参数化的类型
			// System.out.println("type1 = " + ((ParameterizedType)
			// type).getRawType());
			// System.out.println("type2 = " + ((ParameterizedType)
			// type).getOwnerType());
			Type[] actualTypeArguments = ((ParameterizedType) type)
					.getActualTypeArguments();
			// for (Type type3 : actualTypeArguments) {
			// System.out.println("type3 = " + type3);
			// }
			try {

				// why ? actualTypeArguments[0].getClass().newInstance(); 不行
				Class<T> clazz = (Class<T>) actualTypeArguments[0];
				return clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		return null;
	}
}
