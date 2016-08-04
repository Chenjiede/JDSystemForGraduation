package com.wyu.graduate.db;

import java.sql.Connection;

import com.wyu.graduate.db.impl.SqliteConn;

public class ConnManager {

	public static ConnManager instance;
	private Connection connection;
	private SqliteConn sqliteConn;
	
	private  ConnManager() {
		sqliteConn = new SqliteConn();
		connection = sqliteConn.openConection();
	}
	
	public static ConnManager  getInstance() {
		if (instance == null) {
			instance = new ConnManager();
		}
		
		return instance;
	}
	
	public Connection getConn() {
		return connection;
	}
	
	
	public void  colse() {
		if (sqliteConn != null) {
			sqliteConn.closeConnection();
		}
	}
	
}
