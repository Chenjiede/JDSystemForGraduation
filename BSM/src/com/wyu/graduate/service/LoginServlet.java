package com.wyu.graduate.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.People;
import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.Student;
import com.wyu.graduate.bean.Teacher;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.PeopleManager;
import com.wyu.graduate.dao.StudentManager;
import com.wyu.graduate.dao.TeacherManager;
import com.wyu.graduate.db.SqliteUtil;
import com.wyu.graduate.db.impl.SqliteConn;
import com.wyu.graduate.util.GsonUtil;


/**
 * get http://localhost:8080/BSM/LoginServlet?xxx=xxx
 */


/**
 * post
 * http://localhost:8080/BSM/LoginServlet
 * 
 * body
 */

//
//String xxx = request.getParameter("xxx");	
//String clazz = request.getParameter("clazz");
//
//String name = request.getParameter("name");
//
//InputStream in = request.getInputStream();
//
//BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
//String line = bReader.readLine();
//
//

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 延时 1秒
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("login");
		// 获取用户信息
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		/**
		 * 判断用户是否存在
		 */
		PeopleManager manager = new PeopleManager();
		
		People people = manager.find(People.class, user);
		
		System.out.println("check database has this user  "+ people);
		
		if (people == null) { // 用户不存在
			
			loginResult("用户不存在", response);
			return ;
		}
		
		/**
		 * 判断密码是否正确
		 */
		String realPwd = people.getPwd();
		if (!realPwd.equals(pwd)) { // 密码不正确
			
			loginResult("密码不正确", response);
			return ;
		}
		
		/**
		 * 返回用户信息
		 */ 
		Result result = new Result();
		Head head = new Head();
		head.setStatus(Result.SUCC);
		head.setMsg("登陆成功");
		result.setHead(head);
		result.setBody(people);
		String json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();	
		
	}

	/**
	 * 登录结果返回给客户端
	 * @param msg
	 * @param response
	 * @throws IOException
	 */
	private void loginResult(String msg, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Result result = new Result();
		Head head = new Head();
		head.setStatus(Result.FAIL);
		head.setMsg(msg);
		result.setHead(head);
		String json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}




//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		
//		System.out.println("service init");
//		
//		try {
//			Connection connection= new SqliteConn().openConection();
//			SqliteUtil.createTable(connection, "student", Student.class);
//			SqliteUtil.createTable(connection, "teather", Teacher.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	

}
