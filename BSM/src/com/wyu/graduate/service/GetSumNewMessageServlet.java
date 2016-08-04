package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.ChatMessage;
import com.wyu.graduate.bean.ReplyMessage;
import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.TeacherMessages;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.ChatMessageManager;
import com.wyu.graduate.dao.ReplyMessageManager;
import com.wyu.graduate.dao.TeacherMessagesManager;
import com.wyu.graduate.util.GsonUtil;

@WebServlet("/GetSumNewMessageServlet")
public class GetSumNewMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("sumMessage");
		
		// ��ȡ����
		String userString = request.getParameter("user");
		String typeString = request.getParameter("type");
		
		int sum = 0;
		// �ж��û�����
		if (typeString.equals("student")) { // ѧ��
			/**
			 * ȥ���ݿ������Ϣ
			 */
			ReplyMessageManager manager = new ReplyMessageManager();
			List<ReplyMessage> lists = manager.findBySelection(ReplyMessage.class, "user", userString);
			
			sum += lists.size();
		} else { // ��ʦ
			/**
			 * ȥ���ݿ������Ϣ
			 */
			TeacherMessagesManager manager = new TeacherMessagesManager();
			List<TeacherMessages> lists = manager.findBySelection(TeacherMessages.class, "teacherUser", userString);
			
			sum += lists.size();
		}
		
		/**
		 * ��ѯ���ݿ�
		 */
		ChatMessageManager manager = new ChatMessageManager();
		List<ChatMessage> lists = manager.findBySelection(ChatMessage.class, "receiveUser", userString);
		
		sum += lists.size();
		
		/**
		 * ��������
		 */
		String numString = String.valueOf(sum);
		Result result = new Result();
		Head head = new Head();
		head.setMsg(numString);
		result.setHead(head);
		String  json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
