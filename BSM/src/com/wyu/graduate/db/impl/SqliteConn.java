package com.wyu.graduate.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wyu.graduate.db.def.IConnection;

/**
 * sqlite ���ݿ�����ʵ����
 * @author Administrator
 *
 */
public class SqliteConn implements IConnection {

	private Connection conn;

	@Override
	public Connection openConection() {
		try {
			Class.forName("org.sqlite.JDBC");
//			conn = DriverManager.getConnection("jdbc:sqlite:D:\\developer\\workspace\\BSM\\bsm.sqlite");
//			conn = DriverManager.getConnection("jdbc:sqlite:/home/chen/workspace/java/BSM/bsm.sqlite");
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/chen/workspace/java/BSM/bsm.sqlite");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	

	@Override
	public void closeConnection() {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

//	public static void main(String[] args) {
//		SqliteConn conn = new SqliteConn();
//		try {
//			conn.openConection();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
