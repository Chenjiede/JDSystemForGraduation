package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.ReplyMessage;
import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.ReplyMessageManager;
import com.wyu.graduate.util.GsonUtil;

@WebServlet("/GetStudentMessageServlet")
public class GetStudentMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("getStudentMessage");
		
		String userString = request.getParameter("user");
		String type = request.getParameter("type");
		/**
		 * ȥ���ݿ������Ϣ
		 */
		ReplyMessageManager manager = new ReplyMessageManager();
		List<ReplyMessage> lists = manager.findBySelection(ReplyMessage.class, "user", userString);
	
		/**
		 * ��������
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
			 
			 /**
			  * ɾ����Ϣ
			  */
			 for (ReplyMessage message : lists) {
				 int id = message.getId();
				 manager.delete(id);
			 }
		}
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
