package com.wyu.graduate.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javafx.scene.chart.PieChart.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wyu.graduate.bean.Topic;
import com.wyu.graduate.dao.TopicManager;

/**
 * Servlet implementation class SubmitTopicServlet
 */
@WebServlet("/SubmitTopicServlet")
public class SubmitTopicServlet extends BaseServlet {

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ��ʱ
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * ȥ���ݿ��������
		 */
		// ��ȡ����
		String user = request.getParameter("user");
		String describe = request.getParameter("describe");
		String difficult = request.getParameter("difficult");
		String title = request.getParameter("title");
		String teacherUser = request.getParameter("teacherUser");
//		Data topics = 
//		Gson gson = new Gson();
		
		TopicManager manager = new TopicManager();
		Topic topic = manager.find(Topic.class, user);
		
		// �ж��Ƿ��������
		if (topic == null) { // ���ݿⲻ���ڣ�д�����ݿ�
			Topic insertTopic = new Topic(user, describe, difficult, title, teacherUser) ;
			int ret = manager.insert(Topic.class, insertTopic);
			System.out.println(ret);
		} else { // �����Ѵ��ڣ���������
			Topic updatetTopic = new Topic(user, describe, difficult, title, teacherUser);
			int id = topic.getId();
			boolean ret = manager.update(Topic.class, id, updatetTopic);
			System.out.println(ret);
		}
	}

}
