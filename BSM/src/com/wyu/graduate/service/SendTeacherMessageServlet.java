package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.TeacherMessages;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.TeacherMessagesManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class SendTeacherMessageServlet
 */
@WebServlet("/SendTeacherMessageServlet")
public class SendTeacherMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("sendTeacherMessage");
		
		// ��ȡ����
		String user = request.getParameter("user");
		String teacherUser = request.getParameter("teacherUser");
		String name = request.getParameter("name");
		String imageUrl = request.getParameter("imageUrl");
		String topic = request.getParameter("topic");
		String profession = request.getParameter("profession");
		
		/**
		 * ȥ���ݿ����
		 */
		TeacherMessagesManager manager = new TeacherMessagesManager();
		TeacherMessages messages = manager.find(TeacherMessages.class, user);
		
		/**
		 * �ж��Ƿ��Ѿ������
		 */
		if (messages == null) { // д�����ݿ�
			TeacherMessages insert = new TeacherMessages(user, teacherUser, name, imageUrl, topic, profession);
			int ret = manager.insert(TeacherMessages.class, insert);
			if (ret != 0) return;
		}
		
		/**
		 * ��������
		 */
		Result result = new Result();
		Head head = new Head();
		head.setStatus(Result.FAIL);
		result.setHead(head);
		String json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
