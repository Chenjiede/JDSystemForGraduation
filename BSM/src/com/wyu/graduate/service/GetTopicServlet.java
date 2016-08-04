package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.Topic;
import com.wyu.graduate.dao.TopicManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class GetTopicServlet
 */
@WebServlet("/GetTopicServlet")
public class GetTopicServlet extends BaseServlet {

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 延时
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		/**
		 * 去数据库查找
		 */
		String teacherUser = request.getParameter("teacherUser");
//		System.out.println(teacherUser);
		TopicManager manager = new TopicManager();
		List<Topic> topics = manager.findBySelection(Topic.class, "teacherUser", teacherUser);

		// 判断是否存在数据
		if (topics.isEmpty()) { // 数据不存在
//			System.out.println("null");
			return ;
		}
		
		/**
		 *  返回数据
		 */
		String json = GsonUtil.Obj2Json(topics);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();

	}

}
