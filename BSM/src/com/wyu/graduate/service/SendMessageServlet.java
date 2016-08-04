package com.wyu.graduate.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.ChatMessage;
import com.wyu.graduate.bean.ChatPeople;
import com.wyu.graduate.bean.People;
import com.wyu.graduate.dao.ChatMessageManager;
import com.wyu.graduate.dao.ChatPeopleManager;
import com.wyu.graduate.dao.PeopleManager;

@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("sendMessage");
		
		/**
		 * 获取参数
		 */
		String sendUser = request.getParameter("sendUser");
		String receiveUser = request.getParameter("receiveUser");
		String imageUrl = request.getParameter("imageUrl");
		String time = request.getParameter("time");
		String userType = request.getParameter("userType");
		String text = request.getParameter("text");
		String name = request.getParameter("name");
		
		System.out.println("send = " + sendUser + ", receive = " + receiveUser + ", text = " + text + ", name = " + name);
		
		/**
		 * 存储到数据库
		 */
		ChatMessageManager manager = new ChatMessageManager();
		ChatMessage message = new ChatMessage(sendUser, receiveUser, imageUrl, time, text, userType);
		int ret = manager.insert(ChatMessage.class, message);
		System.out.println(ret);
		
		/**
		 * 存储聊天人信息
		 */
		ChatPeopleManager peopleManager = new ChatPeopleManager();
		 List<ChatPeople> lists = peopleManager.findBySelection(ChatPeople.class, "sendUser", sendUser, "receiveUser", receiveUser);
		 ChatPeople chatPeople;
		
		if (lists.size() == 0) {
			chatPeople = new ChatPeople(sendUser, receiveUser, imageUrl, name, time, text, "1");
			peopleManager.insert(ChatPeople.class, chatPeople);
		} else {
			chatPeople = lists.get(0);
			chatPeople.setTime(time);
			chatPeople.setText(text);
//			chatPeople.setImageUrl(imageUrl);
//			chatPeople.setName(name);
			String numString = chatPeople.getNum();
			int num = Integer.valueOf(numString) + 1;
			int id = chatPeople.getId();
			chatPeople.setNum(String.valueOf(num));
			
			peopleManager.update(ChatPeople.class, id, chatPeople);
		}
		
		lists = peopleManager.findBySelection(ChatPeople.class, "sendUser", receiveUser, "receiveUser", sendUser);
		if (lists.size() == 0) {
			PeopleManager manager2 = new PeopleManager();
			People people = manager2.find(People.class, receiveUser);
			
			chatPeople = new ChatPeople(receiveUser, sendUser, people.getImageUrl(), people.getName(), time, text, "0");
			peopleManager.insert(ChatPeople.class, chatPeople);
		} else {
			 chatPeople = lists.get(0);
			 chatPeople.setTime(time);
			 chatPeople.setText(text);
			 int id = chatPeople.getId();
			 
			 peopleManager.update(ChatPeople.class, id, chatPeople);
		}
		
	}

}
