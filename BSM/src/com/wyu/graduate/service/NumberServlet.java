package com.wyu.graduate.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyu.graduate.bean.Result;
import com.wyu.graduate.bean.TopicNumber;
import com.wyu.graduate.dao.TopicNumberManager;
import com.wyu.graduate.util.GsonUtil;

/**
 * Servlet implementation class NumberServlet
 */
@WebServlet("/NumberServlet")
public class NumberServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 延时 1秒
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String user = request.getParameter("user");
		
		/**
		 *  去数据库查找
		 */
		TopicNumberManager manager = new TopicNumberManager();
		TopicNumber topicNum = manager.find(TopicNumber.class, user);

		// 判断
		if (topicNum == null) {
			
			return ;
		}
		
		/**
		 *  返回数据
		 */
		Result result = new Result();
		result.setBody(topicNum);
		String json = GsonUtil.Obj2Json(result);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}

}
