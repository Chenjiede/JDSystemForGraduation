package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.Teacher;
import com.wyu.graduate.dao.TeacherManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class GetSelectedTeacherServlet
 */
@WebServlet("/GetSelectedTeacherServlet")
public class GetSelectedTeacherServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GetSelectedTeacherServlet");
		
		/**
		 * 获取参数
		 */
		String userString = request.getParameter("user");
		
		/**
		 * 去数据库查找
		 */
		TeacherManager teacherManager = new TeacherManager();
		Teacher teacher = teacherManager.find(Teacher.class, userString);
		
		if (teacher == null) return;
		
		/**
		 * 返回数据
		 */
		String json = GsonUtil.Obj2Json(teacher);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
