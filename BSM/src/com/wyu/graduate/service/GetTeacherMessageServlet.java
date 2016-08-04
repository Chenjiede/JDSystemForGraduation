package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.TeacherMessages;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.TeacherMessagesManager;
import com.wyu.graduate.util.GsonUtil;

@WebServlet("/GetTeacherMessageServlet")
public class GetTeacherMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("getTeacherMessage");
		
		String teacherUser = request.getParameter("user");
		String type = request.getParameter("type");
		/**
		 * 去数据库查找信息
		 */
		TeacherMessagesManager manager = new TeacherMessagesManager();
		List<TeacherMessages> lists = manager.findBySelection(TeacherMessages.class, "teacherUser", teacherUser);
			
		/**
		 * 返回数据
		 */
		String json;
		if (type.equals("num")) { // num
			int num = lists.size();
			String numString = String.valueOf(num);
			Result result = new Result();
			Head head = new Head();
			head.setMsg(numString);
			result.setHead(head);
			json = GsonUtil.Obj2Json(result);
		} else {
			 json = GsonUtil.Obj2Json(lists);
			 
		}
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
