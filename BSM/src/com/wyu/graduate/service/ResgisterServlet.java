package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.log4j.Logger;

//import com.sun.java.util.jar.pack.Package.File;
import com.wyu.graduate.bean.People;
import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.Student;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.bean.Teacher;
import com.wyu.graduate.dao.PeopleManager;
import com.wyu.graduate.dao.StudentManager;
import com.wyu.graduate.dao.TeacherManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class ResgisterServlet
 */
@WebServlet("/ResgisterServlet")
public class ResgisterServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
 
	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		
				// 延时 1秒
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("register");
				
				String user = request.getParameter("user");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String type = request.getParameter("type");
				String profession = request.getParameter("profession");
				System.out.println(profession);
			
				
				/**
				 * 先到数据库查找是否存在当前的用户
				 */
				String msg = null;
				PeopleManager manager = new PeopleManager();
				People people = manager.find(People.class, user);
				
				if (people == null) { // 用户不存在
					Random random = new Random();
					int n = random.nextInt(9);
					String no = n + "";
					String imageUrl = "http://localhost:8080/BSM/resources/images/0"+ no +".jpg";
					People insert = new People(user, pwd, name, email, phone, type, imageUrl, profession);
					int ret = manager.insert(People.class, insert);
					
					System.out.println("insert "+ret);
					
					if (ret != 0) { //register ok
						msg = "注册成功";
						handleResult(Result.SUCC, msg, response);
					} else{ //register fail
						msg = "注册失败";
						handleResult(Result.FAIL, msg, response);
					}
					
					return ;
				}
				
				System.out.println("用户已经存在");
				handleResult(Result.FAIL, "用户已经存在", response);
	}

	/**
	 * 返回客户端的结果
	 * @param status
	 * @param msg
	 * @param response 
	 * @throws IOException 
	 */
	private void handleResult(int status, String msg, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Result result = new Result();
		Head head = new Head();
		head.setStatus(status);
		head.setMsg(msg);
		result.setHead(head);
		String json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
