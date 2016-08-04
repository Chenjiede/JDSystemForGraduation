package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.TopicNumber;
import com.wyu.graduate.dao.TopicNumberManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class GetStudentServlet
 */
@WebServlet("/GetTeacherServlet")
public class GetTeacherServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("getTeacher");
		
		/**
		 * 查找数据
		 */
		TopicNumberManager manager = new TopicNumberManager();
		int num = (int)manager.count();
		List<TopicNumber> lists = manager.findAll(TopicNumber.class, 0, num);
		
		/**
		 * 返回数据
		 */
		String json = GsonUtil.Obj2Json(lists);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
