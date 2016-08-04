package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.Student;
import com.wyu.graduate.dao.StudentManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class GetStudentServlet
 */
@WebServlet("/GetStudentServlet")
public class GetStudentServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("getStudent");
		
		String teacherUser = request.getParameter("user");
		
		/**
		 * ȥ���ݿ����
		 */
		StudentManager manager = new StudentManager();
		List<Student> students = manager.findBySelection(Student.class,"teacherUser" ,teacherUser);
		
		// �ж��Ƿ��������
		if (students.isEmpty()) { // ���ݲ�����
					
			return ;
		}
				
		/**
		 *  ��������
		 */
		String json = GsonUtil.Obj2Json(students);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
