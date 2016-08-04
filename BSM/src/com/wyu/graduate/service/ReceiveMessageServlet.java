package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.ChatMessage;
import com.wyu.graduate.bean.ChatPeople;
import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.Result.Head;
import com.wyu.graduate.dao.ChatMessageManager;
import com.wyu.graduate.dao.ChatPeopleManager;
import com.wyu.graduate.util.GsonUtil;

@WebServlet("/ReceiveMessageServlet")
public class ReceiveMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("receiveMessage");
		
		/**
		 * 获取参数
		 */
		String sendUser = request.getParameter("sendUser");
		String receiveUser = request.getParameter("receiveUser");
		String type = request.getParameter("type");
		
		/**
		 * 获取所有的消息人
		 */
		ChatPeopleManager peopleManager = new ChatPeopleManager();
		List<ChatPeople> chatPeoples = peopleManager.findBySelection(ChatPeople.class, "receiveUser", receiveUser);
		if (sendUser == null && type == null) {
			if (chatPeoples.size() == 0) return;
			String json = GsonUtil.Obj2Json(chatPeoples);
			
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
			
			return;
		}
		
		String json;
		// 判断要请求的类型
		if (type == null) {	// 请求具体消息内容	
			/**
			 * 查询数据库
			 */
			ChatMessageManager manager = new ChatMessageManager();
			List<ChatMessage> messageList = manager.findBySelection(ChatMessage.class, "sendUser", sendUser, "receiveUser", receiveUser);
			
			if (messageList.size() == 0) {
				System.out.println(messageList.size());
				return;
			}
			
			json = GsonUtil.Obj2Json(messageList);
			
			/**
			 * 删除数据
			 */
			for (ChatMessage message : messageList) {
				int id = message.getId();
				manager.delete(id);
			}
			
			/**
			 * 更改消息人数据
			 * 
			 */
			chatPeoples = peopleManager.findBySelection(ChatPeople.class, "sendUser", sendUser, "receiveUser", receiveUser);
			ChatPeople chatPeople = chatPeoples.get(0);
			int id = chatPeople.getId();
			chatPeople.setNum("0");
			peopleManager.update(ChatPeople.class, id, chatPeople);
			
		} else { // 请求消息的数量
			/**
			 * 查询数据库
			 */
			ChatMessageManager manager = new ChatMessageManager();
			List<ChatMessage> lists = manager.findBySelection(ChatMessage.class, "receiveUser", receiveUser);
			int num = lists.size();
			String numString = String.valueOf(num);
			Result result = new Result();
			Head head = new Head();
			head.setMsg(numString);
			result.setHead(head);
			json = GsonUtil.Obj2Json(result);
			 
		}
		
		
		/**
		 * 返回数据
		 */
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
	}

}
