package com.wyu.graduate.db.def;

import java.sql.Connection;

public interface IConnection {

	
	/**
	 * open db 
	 * @return a connection
 	 */
	Connection openConection() throws Exception;
	
	/**
	 * close db
	 */
	void closeConnection();
	
}
