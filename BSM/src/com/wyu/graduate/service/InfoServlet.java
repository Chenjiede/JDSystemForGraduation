package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class InfoServlet
 * @author chen
 *
 */
@WebServlet("/InfoServlet")
public class InfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * «Î«Û¥¶¿Ì
		 */
		System.out.println("info");
		
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		String imageUrl = request.getParameter("imageUrl");
		String profession = request.getParameter("profession");
		
		PeopleManager manager = new PeopleManager();
		People people = manager.find(People.class, user);
		int id = people.getId();
		
		People update = new People(user, pwd, name, email, phone, type, imageUrl, profession);
		manager.update(People.class, id, update);
		
	}
	
}
